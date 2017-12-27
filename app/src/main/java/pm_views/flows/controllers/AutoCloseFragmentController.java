package pm_views.flows.controllers;

import android.arch.lifecycle.Lifecycle;

import java.util.HashMap;

import MVC.BaseController;
import utils.MockApi;
import MVC.PMLifecycleOwner;
import pm_views.PMActivity;

/**
 * Created by shrikanth on 11/6/17.
 */

public class AutoCloseFragmentController extends BaseController<PMLifecycleOwner> {


    public AutoCloseFragmentController() {
    }

    public AutoCloseFragmentController(PMLifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void onResult(int requestCode, int resultOk, HashMap<String, Object> results) {

    }

    @Override
    public void returnResults(HashMap<String, Object> hashMap, int returnCode) {

    }


    public void fetchAndFinish(){
        MockApi.getData(new MockApi.ResponseHandler() {
            @Override
            public void onSuccess() {
                if(isControllerAlive() && getLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED){
                    PMActivity activity = getLifecycleOwner().getOwnerActivity();
                    if(activity != null){
                        activity.onBackPressed();
                        detachController();
                    }
                }else{
                    /*
                     * We need a mechanism here to mark it and finish when it comes alive
                     */
                }
            }

            @Override
            public void onError() {

            }
        });
    }
}
