package pm_views;

import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import java.util.UUID;

import MVC.BaseController;
import MVC.GlobalControllerFactory;
import MVC.PMLifecycleOwner;
import MVC.ResultsListener;

/**
 * Created by nsohoni on 16/10/17.
 */

public abstract class PMFragment<T extends BaseController> extends DialogFragment implements PMLifecycleOwner {

    UUID                fragmentId;
    LifecycleRegistry   mLifecycleRegistry = new LifecycleRegistry(this);
    public T controller;

    public PMFragment() {
        fragmentId = UUID.randomUUID();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindController(savedInstanceState);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("FRAGMENT_ID", fragmentId.toString());
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycleRegistry;
    }

    @Override
    public void updateViews() {

    }

    @Override
    public void setupViews() {

    }

    @Override
    public UUID getIdentifier() {
        return fragmentId;
    }

    @Override
    public PMActivity getOwnerActivity() {
        return (PMActivity) getActivity();
    }


    @Override
    public void kill() {
        PMActivity parent = getOwnerActivity();
        if(parent != null) {
            parent.onBackPressed();
        }
    }


    public void setController(T controller){
        this.controller = controller;
    }
    private void bindController(Bundle savedInstanceState){
        if(controller == null){
            if(savedInstanceState != null) {
                fragmentId = UUID.fromString(savedInstanceState.getString("FRAGMENT_ID"));
            }
            controller = (T)GlobalControllerFactory.getInstance().getControllerForLifecycleOwner(fragmentId);
        }
        if(controller != null) {
            getLifecycle().addObserver(controller);
            controller.setLifecycleRegistryOwner(this);
        }else{
            throw new RuntimeException("Controller for " + this.getClass().getCanonicalName() + " not found");
        }
    }
    @Override
    public abstract Class getViewInterface();
}
