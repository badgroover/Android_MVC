package pm_views;

import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;
import java.util.UUID;

import MVC.BaseController;
import MVC.GlobalControllerFactory;
import MVC.PMLifecycleOwner;
import MVC.ResultsListener;

/**
 * Created by nsohoni on 16/10/17.
 */

public class PMActivity extends FragmentActivity implements PMLifecycleOwner {

    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);
    boolean isStateSaved = false;
    UUID    activityId;
    int     count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            activityId = UUID.fromString(savedInstanceState.getString("ACTIVITY_ID"));
        } else {
            activityId = UUID.randomUUID();
        }

    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        if(count == 0) {
            finish();
        } else {
            fm.popBackStackImmediate();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        isStateSaved = true;
        outState.putString("ACTIVITY_ID", activityId.toString());
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }

    @Override
    public void updateViews() {

    }

    @Override
    public void setupViews() {

    }

    @Override
    public PMActivity getOwnerActivity() {
        return this;
    }

    @Override
    public UUID getIdentifier() {
        return null;
    }

    @Override
    public void kill() {
        finish();
    }

    @Override
    public Class getViewInterface() {
        return PMLifecycleOwner.class;
    }


    public boolean isStateSaved() {
        return isStateSaved;
    }

    /*public void launchFragment(Class fragmentClass) {
        PMFragment fragment;
        try {
            fragment = (PMFragment) fragmentClass.newInstance();
            FragmentManager fm = getSupportFragmentManager();
            int count = fm.getBackStackEntryCount();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer, fragment, Integer.toString(count + 1));
            ft.addToBackStack(Integer.toString(count + 1));
            ft.commit();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public void launchFragmentForResult(Class fragmentClass, UUID targetControllerID, int requestCode) {
        PMFragment fragment;
        try {
            fragment = (PMFragment) fragmentClass.newInstance();
            //fragment.setResultsListener(targetControllerID, requestCode);
            FragmentManager fm = getSupportFragmentManager();
            int count = fm.getBackStackEntryCount();
            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
            ft.replace(R.id.fragmentContainer, fragment, Integer.toString(count + 1));
            ft.addToBackStack(Integer.toString(count + 1));
            ft.commit();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }*/

    public void launchController(Class controllerClass, Class fragmentClass, HashMap<String, Object> map) {
        launchControllerForResult(controllerClass, fragmentClass, map, null, -1);
    }

    /**
     * 1. Create controller
     * 2. Set results listener if there is one
     * 3. Create the fragment
     * 4. Add controller to fragment
     * 5. Add fragment as the life cycle owner
     * 6. Add controller to global space
     * 7. Launch the fragment
     * @param controllerClass
     * @param fragmentClass
     * @param map
     * @param resultsListener
     * @param returnCode
     */
    public void launchControllerForResult(Class controllerClass, Class fragmentClass, HashMap<String, Object> map, ResultsListener resultsListener, int returnCode) {
        BaseController controller;
        try {
            controller = (BaseController) controllerClass.newInstance();
            controller.setArguments(map);
            if(resultsListener != null){
                controller.setResultsListener(resultsListener, returnCode);
            }
            PMFragment fragment = (PMFragment) fragmentClass.newInstance();
            fragment.setController(controller);
            controller.setLifecycleRegistryOwner(fragment);
            GlobalControllerFactory.getInstance().addController(fragment.getIdentifier(), controller);

            FragmentManager fm = getSupportFragmentManager();
            int count = fm.getBackStackEntryCount();
            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
            ft.replace(R.id.fragmentContainer, fragment, Integer.toString(count + 1));
            ft.addToBackStack(Integer.toString(count + 1));
            ft.commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
