package pm_views.flows;

import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import MVC.GlobalControllerFactory;
import MVC.PMExtendedLifecycleRegistryOwner;
import pm_views.AutoCloseFragment;
import pm_views.PMFragment;
import pm_views.R;
import pm_views.flows.flow_controllers.PageOneController;

/**
 * Created by shrikanth on 11/6/17.
 */

public class PageOneFragment extends PMFragment implements PMExtendedLifecycleRegistryOwner{


    Button click;
    PageOneController controller;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = GlobalControllerFactory.getInstance().createControllerForLifecycleOwner(this, PageOneController.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        click = (Button)view.findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOwnerActivity().launchFragmentForResult(PageTwoFragment.class, getIdentifier(), PageOneController.REQUEST_CODE_A);
            }
        });
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
    protected boolean isMarkedForDeath() {
        if(controller != null && controller.isControllerAlive()) {
            return controller.isMarkedForDeath();
        } else {
            return false;
        }
    }

    @Override
    protected void markForDeath() {
        if(controller != null && controller.isControllerAlive()) {
            controller.markForDeath();
        }
    }

    @Override
    public void foo() {

    }

    @Override
    public void doo() {

    }
}
