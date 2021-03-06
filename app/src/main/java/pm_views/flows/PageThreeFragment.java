package pm_views.flows;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.HashMap;

import MVC.BaseController;
import MVC.GlobalControllerFactory;
import pm_views.PMFragment;
import pm_views.R;
import pm_views.flows.flow_controllers.PageThreeController;

/**
 * Created by shrikanth on 11/6/17.
 */

public class PageThreeFragment extends PMFragment{


    Button click;
    PageThreeController controller;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = GlobalControllerFactory.getInstance().createControllerForLifecycleOwner(this, PageThreeController.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page_three, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        click = (Button)view.findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passBack();
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

    private void passBack(){
        controller.passBackResults();
    }
}
