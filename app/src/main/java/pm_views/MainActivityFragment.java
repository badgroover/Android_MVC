package pm_views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import MVC.AddressController;
import MVC.AutoCloseFragmentController;
import MVC.DataFetchListener;
import MVC.GlobalControllerFactory;
import MVC.MainActivityFragmentLifeCycleOwner;
import MVC.UserModel;
import lifecycle.MyLifecycleObserver;
import pm_views.flows.PageOneFragment;
import pm_views.flows.TabsContainerFragment;
import pm_views.flows.flow_controllers.MainActivityFragmentController;
import pm_views.flows.flow_controllers.PageOneController;
import pm_views.flows.flow_controllers.TabsContainerController;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends PMFragment<MainActivityFragmentController> implements MainActivityFragmentLifeCycleOwner {

    private MyLifecycleObserver observer;

    View    threeFlows, autoClose, tabsContainer;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = GlobalControllerFactory.getInstance().createControllerForLifecycleOwner(this, MainActivityFragmentController.class);
        getLifecycle().addObserver(controller);
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
