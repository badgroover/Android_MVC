package pm_views;

import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

import MVC.BaseController;
import MVC.PMLifecycleRegistryOwner;
import MVC.ViewInterface;

/**
 * Created by nsohoni on 16/10/17.
 */

public class PMFragment extends Fragment implements PMLifecycleRegistryOwner, ViewInterface {

    UUID                fragmentId;
    LifecycleRegistry   mLifecycleRegistry = new LifecycleRegistry(this);
    BaseController      targetController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            fragmentId = UUID.fromString(savedInstanceState.getString("FRAGMENT_ID"));
        } else {
            fragmentId = UUID.randomUUID();
        }
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
    public boolean isFragmentVisible() {
        if(isVisible() && isAdded()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PMActivity getOwnerActivity() {
        return (PMActivity) getActivity();
    }

    public void setTargetController(BaseController targetController) {
        this.targetController = targetController;
    }

    public BaseController getTargetController() {
        return targetController;
    }
}
