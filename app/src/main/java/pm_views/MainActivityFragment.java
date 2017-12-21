package pm_views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import MVC.AddressController;
import MVC.DataFetchListener;
import MVC.GlobalControllerFactory;
import MVC.UserModel;
import lifecycle.MyLifecycleObserver;
import pm_views.flows.PageOneFragment;
import pm_views.flows.TabsContainerFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends PMFragment {

    private MyLifecycleObserver observer;
    private AddressController controller;

    View    threeFlows, autoClose, tabsContainer;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = GlobalControllerFactory.getInstance().createControllerForLifecycleOwner(this, AddressController.class);
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
                getOwnerActivity().launchFragment(PageOneFragment.class);
            }
        });
        autoClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOwnerActivity().launchFragment(AutoCloseFragment.class);
            }
        });

        tabsContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOwnerActivity().launchFragment(TabsContainerFragment.class);
            }
        });

    }


}
