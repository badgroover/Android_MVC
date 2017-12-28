package pm_views.flows.controllers;

import java.util.HashMap;

import MVC.BaseController;
import utils.MockApi;
import MVC.PMLifecycleOwner;

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
                exit();
                detachController();
            }

            @Override
            public void onError() {

            }
        });
    }
}
