package MVC;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v4.app.FragmentActivity;


import java.lang.ref.WeakReference;

import views.PMActivity;
import views.PMFragment;

import static android.arch.lifecycle.Lifecycle.State.STARTED;

/**
 * Created by nsohoni on 14/10/17.
 */

public abstract class BaseController implements LifecycleObserver {

    WeakReference<PMFragment> ownerFragment;



    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
        // disconnect if connected
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        PMFragment fragment = getOwnerFragment();
        if(fragment != null) {
            fragment.setupViews();
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
        PMFragment fragment = getOwnerFragment();
        if(fragment != null) {
            FragmentActivity activity = fragment.getActivity();
            if(activity != null) {
                if(!((PMActivity)activity).isStateSaved()) {
                    GlobalControllerFactory.getInstance().remove(fragment.getFragmentId());
                }
            } else {
                GlobalControllerFactory.getInstance().remove(fragment.getFragmentId());
            }
        }
    }

    public PMFragment getOwnerFragment() {
        return ownerFragment.get();
    }
}
