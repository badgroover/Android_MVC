package MVC;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


import java.lang.ref.WeakReference;

import pm_views.PMActivity;

/**
 * Created by nsohoni on 14/10/17.
 */

public abstract class BaseController implements LifecycleObserver {

    PM_Model                                model;
    WeakReference<PMLifecycleRegistryOwner> lifecycleRegistryOwner;
    Object                                  mutex = new Object();
    boolean                                 isControllerAlive = false;
    Bundle                                  resultData;


    public BaseController(PMLifecycleRegistryOwner lifecycleOwner) {
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
        PMLifecycleRegistryOwner owner = getLifecycleOwner();
        if(owner != null) {
            owner.setupViews();
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
        PMLifecycleRegistryOwner owner = getLifecycleOwner();
        if(owner != null) {
            FragmentActivity activity = owner.getOwnerActivity();
            if(activity != null) {
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
        PMLifecycleRegistryOwner owner = getLifecycleOwner();
        if(owner != null) {
            isControllerAlive = false;
            GlobalControllerFactory.getInstance().remove(owner.getIdentifier());
            owner.getLifecycle().removeObserver(this);
        }
    }

    public PMLifecycleRegistryOwner getLifecycleOwner() {
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

    public void setLifecycleRegistryOwner(PMLifecycleRegistryOwner owner) {
        lifecycleRegistryOwner = new WeakReference(owner);
    }

    public void setResultData(Bundle b) {
        resultData = b;

    }
}
