package pm_views.flows.controllers;

import java.util.HashMap;

import MVC.BaseController;
import pm_views.flows.life_cycler_owners.PMExtendedLifecycleOwner;
import pm_views.flows.models.RequestCode;
import pm_views.flows.views.PageThreeFragment;

/**
 * Created by nsohoni on 08/11/17.
 */

public class PageTwoController extends BaseController<PMExtendedLifecycleOwner> {


    public PageTwoController() {
    }

    public PageTwoController(PMExtendedLifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void onResult(int requestCode, int resultOk, HashMap<String, Object> results) {
        switch (requestCode) {
            case RequestCode.ONE:
                returnResults(results, resultOk);
                break;

        }
    }

    public void launchPageThree() {
        launchControllerForResult(PageThreeController.class, PageThreeFragment.class, null, this, RequestCode.ONE);
    }
}

