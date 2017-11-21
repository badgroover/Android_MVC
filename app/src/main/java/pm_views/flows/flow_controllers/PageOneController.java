package pm_views.flows.flow_controllers;

import java.util.HashMap;

import MVC.BaseController;
import MVC.PMExtendedLifecycleRegistryOwner;
import MVC.PMLifecycleRegistryOwner;

/**
 * Created by nsohoni on 08/11/17.
 */

public class PageOneController extends BaseController<PMExtendedLifecycleRegistryOwner>   {

    public static final int REQUEST_CODE_A = 1;
    int count = 0;
    public PageOneController(PMExtendedLifecycleRegistryOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void setResultData(int requestCode, int resultOk, HashMap<String, Object> results) {
        switch(requestCode) {
            case REQUEST_CODE_A:
                //PMExtendedLifecycleRegistryOwner owner = getLifecycleOwner();
                break;
        }
    }

    public void justTellMeSomething() {
        count++;
        getLifecycleOwner().foo();
    }

    public int getCount() {
        return count;
    }
}
