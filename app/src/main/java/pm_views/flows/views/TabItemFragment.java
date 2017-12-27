package pm_views.flows.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import MVC.GlobalControllerFactory;
import pm_views.flows.life_cycler_owners.TabItemLifecycleOwner;
import pm_views.PMFragment;
import pm_views.R;
import pm_views.flows.controllers.TabItemController;

/**
 * Created by shrikanth on 11/6/17.
 */

public class TabItemFragment extends PMFragment<TabItemController> implements TabItemLifecycleOwner {



    TabItemController controller;
    ListView myListview;
    ArrayAdapter<String> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = GlobalControllerFactory.getInstance().createControllerForLifecycleOwner(this, TabItemController.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_item, container, false);
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
        adapter.addAll(controller.getData().getStrings());
        myListview.setAdapter(adapter);
    }

    @Override
    public void setupViews() {
        super.setupViews();
        View v = getView();
        myListview = (ListView)v.findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1);
    }

    @Override
    public Class getViewInterface() {
        return TabItemLifecycleOwner.class;
    }
}
