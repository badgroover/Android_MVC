package pm_views.flows;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import MVC.GlobalControllerFactory;
import MVC.PMExtendedLifecycleOwner;
import pm_views.PMFragment;
import pm_views.R;
import pm_views.flows.flow_controllers.PageOneController;

/**
 * Created by shrikanth on 11/6/17.
 */

public class PageOneFragment extends PMFragment implements PMExtendedLifecycleOwner {


    Button click, back_from_controller_test;
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
        back_from_controller_test = (Button)view.findViewById(R.id.back_from_controller_test);
        back_from_controller_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.justTellMeSomething();
            }
        });
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
    public Class getViewInterface() {
        return PMExtendedLifecycleOwner.class;
    }



    @Override
    public void launchNextFragment() {
        Toast.makeText(getContext(), "Wow I Got something " + controller.getCount(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void killFragment() {

    }
}
