package MVC;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;


import java.lang.ref.WeakReference;
import java.util.HashMap;

import pm_views.PMActivity;

/**
 * Created by nsohoni on 14/10/17.
 */

public abstract class BaseController<L extends PMLifecycleRegistryOwner> implements LifecycleObserver {

    protected PM_Model                              model;
    private WeakReference<L>                        lifecycleRegistryOwner;
    private Object                                  mutex = new Object();
    private boolean                                 isControllerAlive = false;
    boolean                                         bIsMarkedForDeath = false;
    protected enum MESSAGE_TYPE {EXIT};

    Handler h = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch(msg.what) {
                case 1:
                    exit();
            }
            return false;
        } });


    public BaseController(L lifecycleOwner) {
        isControllerAlive = true;
        this.lifecycleRegistryOwner = new WeakReference(lifecycleOwner);
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
            if(isMarkedForDeath()) {
                queueExit();
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
        h.sendEmptyMessage(1);
    }

    public void exit() {
        //Exiting a controller is done by exiting/removing the attached LifeCycleOwner.
        //This in turn will remove the controller in the correct order
        L owner = getLifecycleOwner();
        if(owner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            owner.kill();
        }
    }

    public void markForDeath() {
        bIsMarkedForDeath = true;
    }

    public boolean isMarkedForDeath() {
        return bIsMarkedForDeath;
    }


    public abstract void setResultData(int requestCode, int resultOk, HashMap<String, Object> results);
}
