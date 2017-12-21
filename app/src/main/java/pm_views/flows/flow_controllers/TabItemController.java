package pm_views.flows.flow_controllers;

import java.util.HashMap;

import MVC.BaseController;
import MVC.PMLifecycleOwner;
import MVC.TabItemLifecycleOwner;

/**
 * Created by nsohoni on 20/11/17.
 */

public class TabItemController extends BaseController<TabItemLifecycleOwner> {

    public static final int REQUEST_CODE_A = 1;

    public TabItemController(TabItemLifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void onResult(int requestCode, int resultOk, HashMap<String, Object> results) {

    }

    @Override
    public void returnResults(HashMap<String, Object> hashMap, int returnCode) {

    }
}
