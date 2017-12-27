package pm_views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pm_views.flows.controllers.AutoCloseFragmentController;
import MVC.GlobalControllerFactory;

/**
 * Created by shrikanth on 11/6/17.
 */

public class AutoCloseFragment extends PMFragment {

    AutoCloseFragmentController controller;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = GlobalControllerFactory.getInstance().createControllerForLifecycleOwner(this, AutoCloseFragmentController.class);
        getLifecycle().addObserver(controller);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auto_close, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        controller.fetchAndFinish();
    }
}
