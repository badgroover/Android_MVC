package pm_views.flows.controllers;

import java.util.HashMap;

import MVC.BaseController;
import utils.MockApi;
import pm_views.flows.life_cycler_owners.TabsContainerLifecycleOwner;
import pm_views.flows.helpers.RandomHelper;
import pm_views.flows.models.TabContainerData;

/**
 * Created by nsohoni on 20/11/17.
 */

public class TabsContainerController extends BaseController<TabsContainerLifecycleOwner> {

    TabContainerData data = new TabContainerData();


    public TabsContainerController() {
    }

    public TabsContainerController(TabsContainerLifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void create() {
        super.create();
        fetchData();
    }

    @Override
    public void onResult(int requestCode, int resultOk, HashMap<String, Object> results) {

    }

    public void fetchData(){
        MockApi.getData(new MockApi.ResponseHandler() {
            @Override
            public void onSuccess() {
                TabContainerData.TabData tabData = new TabContainerData.TabData();
                tabData.setStrings(RandomHelper.getRandomStringsList());
                tabData.setTitle("One");
                data.addTabData(tabData);

                tabData = new TabContainerData.TabData();
                tabData.setStrings(RandomHelper.getRandomStringsList());
                tabData.setTitle("Two");
                data.addTabData(tabData);

                tabData = new TabContainerData.TabData();
                tabData.setTitle("Three");
                data.addTabData(tabData);

                getLifecycleOwner().updateViews();
            }

            @Override
            public void onError() {

            }
        });
    }


    @Override
    public void returnResults(HashMap<String, Object> hashMap, int returnCode) {

    }

    public TabContainerData getData(){
        return data;
    }

}
