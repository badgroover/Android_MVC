package pm_views.flows.flow_controllers;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;

import java.util.HashMap;
import java.util.UUID;

import MVC.BaseController;
import MVC.GlobalControllerFactory;
import MVC.MockApi;
import MVC.PMLifecycleOwner;

/**
 * Created by nsohoni on 08/11/17.
 */

public class PageThreeController extends BaseController<PMLifecycleOwner> {

    public PageThreeController() {
    }

    public PageThreeController(PMLifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void onResult(int requestCode, int resultOk, HashMap<String, Object> results) {
    }

    public void passBackResults() {
        MockApi.getData(new MockApi.ResponseHandler() {
            @Override
            public void onSuccess() {
                HashMap<String, Object> results = new HashMap<>();
                results.put("Result_1", "Result Data 1");
                results.put("Result_2", "Result Data 1");
                returnResults(results, Activity.RESULT_OK);
            }

            @Override
            public void onError() {

            }
        });

    }


}

