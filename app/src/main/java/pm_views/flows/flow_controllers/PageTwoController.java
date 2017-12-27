package pm_views.flows.flow_controllers;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;

import java.util.HashMap;
import java.util.UUID;

import MVC.BaseController;
import MVC.GlobalControllerFactory;
import MVC.PMExtendedLifecycleOwner;
import MVC.PMLifecycleOwner;

/**
 * Created by nsohoni on 08/11/17.
 */

public class PageTwoController extends BaseController<PMExtendedLifecycleOwner> {

    public static final int REQUEST_CODE_B = 1;

    public PageTwoController() {
    }

    public PageTwoController(PMExtendedLifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void onResult(int requestCode, int resultOk, HashMap<String, Object> results) {
        switch (requestCode) {
            case REQUEST_CODE_B:
                PMExtendedLifecycleOwner owner = getLifecycleOwner();
                returnResults(results, resultOk);
                break;

        }
    }

}

