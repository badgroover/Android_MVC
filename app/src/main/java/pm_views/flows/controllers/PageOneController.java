package pm_views.flows.controllers;

import java.util.HashMap;

import MVC.BaseController;
import pm_views.flows.life_cycler_owners.PMExtendedLifecycleOwner;
import pm_views.flows.models.RequestCode;

/**
 * Created by nsohoni on 08/11/17.
 */

public class PageOneController extends BaseController<PMExtendedLifecycleOwner>   {


    int count = 0;

    public PageOneController() {
    }

    public PageOneController(PMExtendedLifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void onResult(int requestCode, int resultOk, HashMap<String, Object> results) {
        switch(requestCode) {
            case RequestCode.ONE:
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
