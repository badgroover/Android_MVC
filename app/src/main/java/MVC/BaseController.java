package MVC;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;


import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import pm_views.PMActivity;
import pm_views.flows.controllers.PageOneController;
import pm_views.flows.views.PageOneFragment;

/**
 * Created by nsohoni on 14/10/17.
 */

public abstract class BaseController<L extends PMLifecycleOwner> implements LifecycleObserver, ResultsListener {

    private WeakReference<L>                        lifecycleRegistryOwner;
    private final Object                            mutex = new Object();
    private boolean                                 isControllerAlive = false;
    private HashMap<String, Object>                 returnData;
    private LinkedBlockingQueue<MESSAGE_TYPE>       deferredCommand = new LinkedBlockingQueue<>(1);
    protected Map<String, Object>                   arguments;
    private int                                     requestCode;
    private int                                     returnCode;
    private ResultsListener                         resultsListener;

    protected enum MESSAGE_TYPE {EXIT, RETURN_DATA_AND_EXIT}

    private Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            MESSAGE_TYPE type = MESSAGE_TYPE.values()[msg.what];
            switch(type) {
                case EXIT:
                    exit();
                    break;
                case RETURN_DATA_AND_EXIT:
                    returnResults(returnData, returnCode);
                    break;
            }
            return false;
        } });



    public BaseController() {
        //create the associated view(fragment)
        isControllerAlive = true;
    }

    public void setArguments(Map<String, Object> args) {
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
            PMActivity activity = owner.getOwnerActivity();
            if(activity != null && (activity.isFinishing() || activity.isDestroyed())) {
                if(!activity.isStateSaved()) {
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


    public void setLifecycleRegistryOwner(L owner) {
        lifecycleRegistryOwner = new WeakReference<>(owner);
    }

    public void queueExit() {
        deferredCommand.clear();
        deferredCommand.add(MESSAGE_TYPE.EXIT);
    }

    protected void queueReturnResultsAndExit(HashMap<String, Object> hashMap, int returnCode) {
        returnData = hashMap;
        this.returnCode = returnCode;
        deferredCommand.clear();
        deferredCommand.add(MESSAGE_TYPE.RETURN_DATA_AND_EXIT);
    }

    public void exit() {
        //Exiting a controller is done by exiting/removing the attached LifeCycleOwner.
        //This in turn will remove the controller in the correct order
        if(isResumed()) {
            L owner = getLifecycleOwner();
            owner.kill();
        }else {
            queueExit();
        }
    }

    public void setResultsListener(ResultsListener resultsListener, int requestCode) {
        this.resultsListener = resultsListener;
        this.requestCode = requestCode;
    }

    @Override
    public abstract void onResult(int requestCode, int resultOk, HashMap<String, Object> results);

    public void returnResults(HashMap<String, Object> hashMap, int returnCode) {
        if(isControllerAlive() && resultsListener != null) {
            if (isResumed()) {
                resultsListener.onResult(requestCode, returnCode, hashMap);
                exit();
            } else {
                queueReturnResultsAndExit(hashMap, returnCode);
            }
        }
    }


    //abstraction helpers
    protected void launchController(Class controllerClass, Class fragmentClass, Map data){
        launchControllerForResult(controllerClass, fragmentClass , data, null, -1);
    }

    protected void launchControllerForResult(Class controllerClass, Class fragmentClass, Map data, ResultsListener resultsListener, int requestCode){
        if(isResumed()){
            getLifecycleOwner().getOwnerActivity().launchControllerForResult(controllerClass, fragmentClass, data, resultsListener, requestCode);
        }else{

        }
    }
    //helpers

    private boolean isResumed(){
        PMLifecycleOwner owner = getLifecycleOwner();
        return owner != null && owner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED);
    }
}
