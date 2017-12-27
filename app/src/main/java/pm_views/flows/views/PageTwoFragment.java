package pm_views.flows.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import MVC.GlobalControllerFactory;
import pm_views.flows.life_cycler_owners.PMExtendedLifecycleOwner;
import pm_views.PMActivity;
import pm_views.PMFragment;
import pm_views.R;
import pm_views.flows.controllers.PageThreeController;
import pm_views.flows.controllers.PageTwoController;
import pm_views.flows.models.RequestCode;

/**
 * Created by shrikanth on 11/6/17.
 */

public class PageTwoFragment extends PMFragment<PageTwoController> implements PMExtendedLifecycleOwner {


    Button click;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = GlobalControllerFactory.getInstance().createControllerForLifecycleOwner(this, PageTwoController.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page_two, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        click = (Button)view.findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getOwnerActivity().launchControllerForResult(PageThreeController.class, PageThreeFragment.class, null, controller, RequestCode.ONE);
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

    public void foo() {

    }

    public void doo() {

    }

    @Override
    public void launchNextFragment() {
    }

    @Override
    public void killFragment() {
//        try {
//            Field f = ((Fragment)this).getClass().getDeclaredField("mTarget"); //NoSuchFieldException
//            f.setAccessible(true);
//            //f.set(this, 1);
//        } //catch (IllegalAccessException e) {
//            //e.printStackTrace();
//        //}
//        catch (NoSuchFieldException e) {
//
//        }
        PMActivity activity = getOwnerActivity();
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(this);
        ft.commitAllowingStateLoss();

    }
}
