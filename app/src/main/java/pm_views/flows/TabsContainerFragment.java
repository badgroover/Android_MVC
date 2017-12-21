package pm_views.flows;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import MVC.GlobalControllerFactory;
import MVC.PMExtendedLifecycleOwner;
import MVC.TabsContainerLifecycleOwner;
import pm_views.PMActivity;
import pm_views.PMFragment;
import pm_views.R;
import pm_views.flows.flow_controllers.PageTwoController;
import pm_views.flows.flow_controllers.TabsContainerController;
import pm_views.flows.models.TabContainerData;
import pm_views.flows.pager_adapter.TabsAdapter;

/**
 * Created by shrikanth on 12/21/17.
 */

public class TabsContainerFragment extends PMFragment implements TabsContainerLifecycleOwner{
    Button click;
    TabsContainerController controller;
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
        Bundle b = new Bundle();
        b.putString("title", position+"");
        b.putInt("index", position);
        PMFragment fragment = new TabItemFragment();
        fragment.setArguments(b);
        fragment.setTargetController(getIdentifier(), 5);
        return fragment;
    }

    public int getCount() {
        return controller.getData().getTabsCount();
    }

    public CharSequence getTitle(int position) {
        return controller.getData().getTabData(position).getTitle();
    }
}
