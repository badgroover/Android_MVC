package pm_views.flows.flow_controllers;

import android.app.Activity;

import java.util.HashMap;
import java.util.UUID;

import MVC.BaseController;
import MVC.GlobalControllerFactory;
import MVC.PMLifecycleRegistryOwner;

/**
 * Created by nsohoni on 08/11/17.
 */

public class PageTwoController extends BaseController {

    public static final int REQUEST_CODE_B = 1;

    public PageTwoController(PMLifecycleRegistryOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void setResultData(int requestCode, int resultOk, HashMap<String, Object> results) {
        switch (requestCode) {
            case REQUEST_CODE_B:
                PMLifecycleRegistryOwner owner = getLifecycleOwner();
                if(isControllerAlive() && owner != null) {
                    UUID targetId = owner.getTargetControllerId();
                    int  targetRequestCode = owner.getRequestCode();
                    //find the controller
                    BaseController controller = GlobalControllerFactory.getInstance().getControllerForLifecycleOwner(targetId);
                    controller.setResultData(targetRequestCode, Activity.RESULT_OK, null);
                    owner.markForDeath();
                }
                break;

        }
    }
}

