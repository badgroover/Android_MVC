package MVC;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

import pm_views.PMActivity;
import pm_views.PMFragment;
import pm_views.R;

/**
 * Created by nsohoni on 14/10/17.
 */

public abstract class BaseController<L extends PMLifecycleOwner> implements LifecycleObserver {

    protected PM_Model                              model;
    private WeakReference<L>                        lifecycleRegistryOwner;
    private Object                                  mutex = new Object();
    private boolean                                 isControllerAlive = false;
    HashMap<String, Object>                         returnData;
    LinkedBlockingQueue<MESSAGE_TYPE>               deferredCommand = new LinkedBlockingQueue<>(1);
    HashMap<String, Object>                         arguments;
    int returnCode;

    protected enum MESSAGE_TYPE {EXIT, RETURN_DATA_AND_EXIT};

    Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            MESSAGE_TYPE type = MESSAGE_TYPE.values()[msg.what];
            switch(type) {
                case EXIT:
                    exit();
                case RETURN_DATA_AND_EXIT:
                    returnResults(returnData, returnCode);
            }
            return false;
        } });


    public BaseController() {
        //create the associated view(fragment)
    }

    public void init(PMActivity pmActivity, Class fragmentClass) {
        //launch the fragment associated with this controller
        //pmActivity.launchFragment(fragmentClass);

        PMFragment fragment;
        try {
            fragment = (PMFragment) fragmentClass.newInstance();
            GlobalControllerFactory.getInstance().addController(fragment.getIdentifier(), this);
            FragmentManager fm = pmActivity.getSupportFragmentManager();
            int count = fm.getBackStackEntryCount();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer, fragment, Integer.toString(count + 1));
            ft.addToBackStack(Integer.toString(count + 1));
            ft.commit();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    public void setArguments(HashMap<String, Object> args) {
        arguments = args;
    }

    public BaseController(L lifecycleOwner) {
        isControllerAlive = true;
        this.lifecycleRegistryOwner = new WeakReference(lifecycleOwner);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
        // disconnect if connected
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        L owner = getLifecycleOwner();
        if(owner != null) {
            if(deferredCommand.size() == 1) {
                try {
                    MESSAGE_TYPE message_type = deferredCommand.take();
                    handler.sendEmptyMessage(message_type.ordinal());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                owner.setupViews();
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        //lets check if the fragment is going away due to kill switch...
        ///if not (which means that user is backing out of fragment)
        //we remove ourselves from the GlobalController map
        L owner = getLifecycleOwner();
        if(owner != null) {
            FragmentActivity activity = owner.getOwnerActivity();
            if(activity != null && (activity.isFinishing() || activity.isDestroyed())) {
                if(!((PMActivity)activity).isStateSaved()) {
                    isControllerAlive = false;
                    GlobalControllerFactory.getInstance().remove(owner.getIdentifier());
                }
            } else {
                isControllerAlive = false;
                GlobalControllerFactory.getInstance().remove(owner.getIdentifier());
            }
        }
    }

    public void detachController() {
        L owner = getLifecycleOwner();
        if(owner != null) {
            isControllerAlive = false;
            GlobalControllerFactory.getInstance().remove(owner.getIdentifier());
            owner.getLifecycle().removeObserver(this);
        }
    }

    public L getLifecycleOwner() {
        return lifecycleRegistryOwner.get();
    }

    public boolean isControllerAlive() {
        return isControllerAlive;
    }

    public void setControllerState(boolean isAlive) {
        synchronized (mutex) {
            isControllerAlive = isAlive;
        }
    }

    public <T> T getModel(Class<T> modelType) {
        if(model != null && modelType.isInstance(model)) {
            return modelType.cast(model);
        } else {
            return null;
        }
    }

    public void setLifecycleRegistryOwner(L owner) {
        lifecycleRegistryOwner = new WeakReference(owner);
    }

    public void queueExit() {
        deferredCommand.clear();
        deferredCommand.add(MESSAGE_TYPE.EXIT);
    }

    public void queueReturnResultsAndExit(HashMap<String, Object> hashMap, int returnCode) {
        returnData = hashMap;
        this.returnCode = returnCode;
        deferredCommand.clear();
        deferredCommand.add(MESSAGE_TYPE.RETURN_DATA_AND_EXIT);
    }

    public void exit() {
        //Exiting a controller is done by exiting/removing the attached LifeCycleOwner.
        //This in turn will remove the controller in the correct order
        L owner = getLifecycleOwner();
        if(owner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            owner.kill();
        }
    }


    public abstract void onResult(int requestCode, int resultOk, HashMap<String, Object> results);

    public void returnResults(HashMap<String, Object> hashMap, int returnCode) {
        PMLifecycleOwner owner = getLifecycleOwner();
        if(isControllerAlive()) {
            UUID targetId = owner.getTargetLifecycleOwner();
            int requestCode = owner.getRequestCode();
            BaseController controller = GlobalControllerFactory.getInstance().getControllerForLifecycleOwner(targetId);

            if (owner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                controller.onResult(requestCode, returnCode, hashMap);
                exit();
            } else {
                queueReturnResultsAndExit(hashMap, returnCode);
            }
        }
    }
}
