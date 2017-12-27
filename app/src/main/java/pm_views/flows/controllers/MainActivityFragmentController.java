package pm_views.flows.controllers;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import java.util.HashMap;

import MVC.BaseController;
import pm_views.flows.life_cycler_owners.MainActivityFragmentLifeCycleOwner;

import static java.lang.Thread.sleep;

/**
 * Created by nsohoni on 14/10/17.
 */

public class MainActivityFragmentController extends BaseController<MainActivityFragmentLifeCycleOwner> {

    public MainActivityFragmentController() {
    }

    public MainActivityFragmentController(MainActivityFragmentLifeCycleOwner owner) {
        super(owner);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        super.onResume();
        getLifecycleOwner().updateViews();

    }

    @Override
    public void onResult(int requestCode, int resultCode, HashMap<String, Object> results) {
        //handle the results here
    }

    @Override
    public void returnResults(HashMap<String, Object> hashMap, int returnCode) {
        if(returnCode == 10){

        }
    }

}
