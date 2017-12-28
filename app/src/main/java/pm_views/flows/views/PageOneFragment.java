package pm_views.flows.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import pm_views.flows.life_cycler_owners.PMExtendedLifecycleOwner;
import pm_views.PMFragment;
import pm_views.R;
import pm_views.flows.controllers.PageOneController;
import pm_views.flows.controllers.PageTwoController;
import pm_views.flows.models.RequestCode;

/**
 * Created by shrikanth on 11/6/17.
 */

public class PageOneFragment extends PMFragment<PageOneController> implements PMExtendedLifecycleOwner {


    Button click, back_from_controller_test;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                getOwnerActivity().launchControllerForResult(PageTwoController.class, PageTwoFragment.class, null, controller, RequestCode.ONE);
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
