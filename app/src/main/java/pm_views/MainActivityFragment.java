package pm_views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pm_views.flows.controllers.AutoCloseFragmentController;
import pm_views.flows.life_cycler_owners.MainActivityFragmentLifeCycleOwner;
import pm_views.flows.views.PageOneFragment;
import pm_views.flows.views.TabsContainerFragment;
import pm_views.flows.controllers.MainActivityFragmentController;
import pm_views.flows.controllers.PageOneController;
import pm_views.flows.controllers.TabsContainerController;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends PMFragment<MainActivityFragmentController> implements MainActivityFragmentLifeCycleOwner {



    View    threeFlows, autoClose, tabsContainer;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setupViews(){
        //called by controller at the appropriate time
        View v = getView();
        threeFlows = v.findViewById(R.id.three_fragment_flow);
        autoClose = v.findViewById(R.id.auto_close);
        tabsContainer = v.findViewById(R.id.tabs_container);

    }


    @Override
    public void updateViews() {
        threeFlows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOwnerActivity().launchController(PageOneController.class, PageOneFragment.class, null);
            }
        });
        autoClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOwnerActivity().launchController(AutoCloseFragmentController.class, AutoCloseFragment.class, null);
            }
        });

        tabsContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOwnerActivity().launchControllerForResult(TabsContainerController.class, TabsContainerFragment.class, null, controller, 10);
            }
        });

    }

    @Override
    public Class getViewInterface() {
        return MainActivityFragmentLifeCycleOwner.class;
    }
}
