package pm_views.flows.flow_controllers;

import java.util.HashMap;

import MVC.BaseController;
import MVC.PMLifecycleRegistryOwner;

/**
 * Created by nsohoni on 20/11/17.
 */

public class PageFourController extends BaseController {

    public static final int REQUEST_CODE_A = 1;

    public PageFourController(PMLifecycleRegistryOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void setResultData(int requestCode, int resultOk, HashMap<String, Object> results) {

    }
}
