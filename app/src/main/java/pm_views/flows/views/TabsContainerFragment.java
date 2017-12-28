package pm_views.flows.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import MVC.GlobalControllerFactory;
import pm_views.flows.life_cycler_owners.TabsContainerLifecycleOwner;
import pm_views.PMFragment;
import pm_views.R;
import pm_views.flows.controllers.TabItemController;
import pm_views.flows.controllers.TabsContainerController;
import pm_views.flows.adapters.TabsAdapter;

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
        try {
            TabItemController tabItemController = TabItemController.class.newInstance();
            PMFragment fragment = new TabItemFragment();
            fragment.setController(tabItemController);
            tabItemController.setArguments(map);
            GlobalControllerFactory.getInstance().addController(fragment.getIdentifier(), tabItemController);
            tabItemController.setResultsListener(this.controller, 5);
            return fragment;
        } catch (java.lang.InstantiationException e) {
           throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCount() {
        return controller.getData().getTabsCount();
    }

    public CharSequence getTitle(int position) {
        return controller.getData().getTabData(position).getTitle();
    }
}
