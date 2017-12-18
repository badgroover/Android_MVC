package pm_views.flows.flow_controllers;

import java.util.HashMap;

import MVC.BaseController;
import MVC.PMExtendedLifecycleOwner;

/**
 * Created by nsohoni on 08/11/17.
 */

public class PageOneController extends BaseController<PMExtendedLifecycleOwner>   {

    public static final int REQUEST_CODE_A = 1;
    int count = 0;
    public PageOneController(PMExtendedLifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void onResult(int requestCode, int resultOk, HashMap<String, Object> results) {
        switch(requestCode) {
            case REQUEST_CODE_A:
                PMExtendedLifecycleOwner owner = getLifecycleOwner();
                owner.launchNextFragment();
                break;
        }
    }

    @Override
    public void returnResults(HashMap<String, Object> hashMap, int returnCode) {

    }

    public void justTellMeSomething() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
