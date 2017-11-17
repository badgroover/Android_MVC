package pm_views.flows.flow_controllers;

import android.app.Activity;
import android.os.Bundle;

import java.util.HashMap;
import java.util.UUID;

import MVC.BaseController;
import MVC.GlobalControllerFactory;
import MVC.MockApi;
import MVC.PMLifecycleRegistryOwner;

/**
 * Created by nsohoni on 08/11/17.
 */

public class PageThreeController extends BaseController {

    public PageThreeController(PMLifecycleRegistryOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void setResultData(int requestCode, int resultOk, HashMap<String, Object> results) {

    }

    public void passBackResults() {
        MockApi.getData(new MockApi.ResponseHandler() {
            @Override
            public void onSuccess() {
                PMLifecycleRegistryOwner owner = getLifecycleOwner();
                if(isControllerAlive() && owner != null) {
                    UUID targetId = owner.getTargetControllerId();
                    int  requestCode = owner.getRequestCode();
                    //find the controller
                    BaseController controller = GlobalControllerFactory.getInstance().getControllerForLifecycleOwner(targetId);
                    HashMap<String, Object> results = new HashMap<>();
                    results.put("Result_1", "Result Data 1");
                    results.put("Result_2", "Result Data 1");
                    Bundle b = new Bundle();
                    b.putString("Result", "Result Data");
                    controller.setResultData(requestCode, Activity.RESULT_OK, results);
                    postToUIQueue(new UIQueueCallback() {
                        @Override
                        public void run() {
                            PMLifecycleRegistryOwner owner = getLifecycleOwner();
                            owner.getOwnerActivity().onBackPressed();
                        }
                    });
                }
            }

            @Override
            public void onError() {

            }
        });

    }
}

