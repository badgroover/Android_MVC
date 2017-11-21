package pm_views.flows.flow_controllers;

import java.util.HashMap;

import MVC.BaseController;
import MVC.PMExtendedLifecycleRegistryOwner;
import MVC.PMLifecycleRegistryOwner;

/**
 * Created by nsohoni on 08/11/17.
 */

public class PageOneController<L extends PMLifecycleRegistryOwner> extends BaseController<L>   {

    public static final int REQUEST_CODE_A = 1;

    public PageOneController(L lifecycleOwner) {
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
}
