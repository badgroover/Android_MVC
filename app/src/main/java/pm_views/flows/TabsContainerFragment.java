package pm_views.flows;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import MVC.GlobalControllerFactory;
import MVC.TabsContainerLifecycleOwner;
import pm_views.PMFragment;
import pm_views.R;
import pm_views.flows.flow_controllers.TabItemController;
import pm_views.flows.flow_controllers.TabsContainerController;
import pm_views.flows.pager_adapter.TabsAdapter;

/**
 * Created by shrikanth on 12/21/17.
 */

public class TabsContainerFragment extends PMFragment<TabsContainerController> implements TabsContainerLifecycleOwner{
    Button click;

    TabsAdapter adapter;
    ViewPager vpPager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = GlobalControllerFactory.getInstance().createControllerForLifecycleOwner(this, TabsContainerController.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tabs_container, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void updateViews() {
        super.updateViews();
        vpPager.setAdapter(adapter);
        //vpPager.setCurrentItem(1);
    }

    @Override
    public void setupViews() {
        super.setupViews();
        vpPager = (ViewPager)getView().findViewById(R.id.view_pager);
        adapter = new TabsAdapter(getChildFragmentManager(), this);
    }

    @Override
    public Class getViewInterface() {
        return TabsContainerLifecycleOwner.class;
    }

    public PMFragment getTabFragment(int position){
        Map<String, Object> map = new HashMap<>();
        map.put("title", controller.getData().getTabData(position).getTitle());
        map.put("index", position);
        map.put("tabData", controller.getData().tabData.get(position));
        PMFragment fragment = new TabItemFragment();
        try {
            TabItemController tabItemController = TabItemController.class.newInstance();
            tabItemController.setArguments(map);
            GlobalControllerFactory.getInstance().addController(fragment.getIdentifier(), tabItemController);
            tabItemController.setResultsListener(this.controller, 5);
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    public int getCount() {
        return controller.getData().getTabsCount();
    }

    public CharSequence getTitle(int position) {
        return controller.getData().getTabData(position).getTitle();
    }
}
