package pm_views.flows.controllers;

import java.util.HashMap;

import MVC.BaseController;
import utils.ApiStateManager;
import utils.MockApi;
import MVC.PMLifecycleOwner;
import utils.state_machine.Actions;
import utils.state_machine.StateObserver;

/**
 * Created by shrikanth on 11/6/17.
 */

public class AutoCloseFragmentController extends BaseController<PMLifecycleOwner> {


    ApiStateManager apiStateManager = new ApiStateManager();
    public AutoCloseFragmentController() {
    }

    public AutoCloseFragmentController(PMLifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchAndFinish();
    }

    @Override
    public void onResult(int requestCode, int resultOk, HashMap<String, Object> results) {

    }

    @Override
    public void returnResults(HashMap<String, Object> hashMap, int returnCode) {

    }


    public void fetchAndFinish(){
        apiStateManager.setController(this);
        apiStateManager.setObserver(new StateObserver() {
            @Override
            public void onAction(int action) {
                switch (action){
                    case Actions.SUCCESS:
                        exit();
                        break;
                }
            }
        });
        apiStateManager.run();
    }
}
