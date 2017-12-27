package pm_views.flows.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import MVC.GlobalControllerFactory;
import pm_views.PMFragment;
import pm_views.R;
import pm_views.flows.controllers.PageFourController;
import pm_views.flows.controllers.PageOneController;
import pm_views.flows.controllers.PageTwoController;
import pm_views.flows.models.RequestCode;

/**
 * Created by nsohoni on 20/11/17.
 */

public class PageFourFragment extends PMFragment {


    Button click;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = GlobalControllerFactory.getInstance().createControllerForLifecycleOwner(this, PageFourController.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page_four, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        click = (Button)view.findViewById(R.id.click);
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


}

