package pm_views.flows.flow_controllers;

import android.app.Activity;

import java.util.HashMap;
import java.util.UUID;

import MVC.BaseController;
import MVC.GlobalControllerFactory;
import MVC.PMExtendedLifecycleOwner;

/**
 * Created by nsohoni on 08/11/17.
 */

public class PageTwoController extends BaseController<PMExtendedLifecycleOwner> {

    public static final int REQUEST_CODE_B = 1;

    public PageTwoController(PMExtendedLifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void onResult(int requestCode, int resultOk, HashMap<String, Object> results) {
        switch (requestCode) {
            case REQUEST_CODE_B:
                PMExtendedLifecycleOwner owner = getLifecycleOwner();

                if(isControllerAlive() && owner != null) {
                    UUID targetId = owner.getTargetLifecycleOwner();
                    int  targetRequestCode = owner.getRequestCode();
                    //find the controller
                    BaseController controller = GlobalControllerFactory.getInstance().getControllerForLifecycleOwner(targetId);
                    controller.onResult(targetRequestCode, Activity.RESULT_OK, null);
                    owner.killFragment();
                }
                break;

        }
    }

    @Override
    public void returnResults(HashMap<String, Object> hashMap, int returnCode) {

    }
}

