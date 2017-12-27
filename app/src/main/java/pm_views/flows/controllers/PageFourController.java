package pm_views.flows.controllers;

import java.util.HashMap;

import MVC.BaseController;
import MVC.PMLifecycleOwner;

/**
 * Created by nsohoni on 20/11/17.
 */

public class PageFourController extends BaseController<PMLifecycleOwner> {

    public PageFourController() {
    }

    public PageFourController(PMLifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void onResult(int requestCode, int resultOk, HashMap<String, Object> results) {

    }

    @Override
    public void returnResults(HashMap<String, Object> hashMap, int returnCode) {

    }
}
