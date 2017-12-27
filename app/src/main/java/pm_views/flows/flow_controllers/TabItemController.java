package pm_views.flows.flow_controllers;

import android.os.Bundle;

import java.util.HashMap;

import MVC.BaseController;
import MVC.GlobalControllerFactory;
import MVC.MockApi;
import MVC.PMLifecycleOwner;
import MVC.TabItemLifecycleOwner;
import pm_views.flows.helpers.RandomHelper;
import pm_views.flows.models.TabContainerData;

/**
 * Created by nsohoni on 20/11/17.
 */

public class TabItemController extends BaseController<TabItemLifecycleOwner> {
    int index;
    String title;
    TabContainerData.TabData data;

    public TabItemController() {
    }

    public TabItemController(TabItemLifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void create() {
        super.create();
        index = (int)arguments.get("index");
        title = (String)arguments.get("title");
        data = (TabContainerData.TabData)arguments.get("tabData");
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(data.getStrings() != null && data.getStrings().size() > 0){
            getLifecycleOwner().updateViews();
        }else{
            MockApi.getData(new MockApi.ResponseHandler() {
                @Override
                public void onSuccess() {
                    data = new TabContainerData.TabData();
                    data.setStrings(RandomHelper.getRandomStringsList());
                    getLifecycleOwner().updateViews();
                }

                @Override
                public void onError() {

                }
            });
        }
    }

    public TabContainerData.TabData getData(){
        return data;
    }
    @Override
    public void onResult(int requestCode, int resultOk, HashMap<String, Object> results) {

    }

    @Override
    public void returnResults(HashMap<String, Object> hashMap, int returnCode) {

    }
}
