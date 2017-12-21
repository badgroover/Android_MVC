package pm_views.flows;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import MVC.GlobalControllerFactory;
import MVC.PMExtendedLifecycleOwner;
import MVC.TabItemLifecycleOwner;
import pm_views.PMActivity;
import pm_views.PMFragment;
import pm_views.R;
import pm_views.flows.flow_controllers.PageTwoController;
import pm_views.flows.flow_controllers.TabItemController;

/**
 * Created by shrikanth on 11/6/17.
 */

public class TabItemFragment extends PMFragment implements TabItemLifecycleOwner {



    TabItemController controller;

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
    }

    @Override
    public void setupViews() {
        super.setupViews();
    }

    @Override
    public Class getViewInterface() {
        return TabItemLifecycleOwner.class;
    }
}
