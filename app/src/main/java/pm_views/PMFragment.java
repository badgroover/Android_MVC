package pm_views;

import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

import MVC.PMLifecycleOwner;

/**
 * Created by nsohoni on 16/10/17.
 */

public abstract class PMFragment extends Fragment implements PMLifecycleOwner {

    UUID                fragmentId;
    LifecycleRegistry   mLifecycleRegistry = new LifecycleRegistry(this);
    UUID                targetLifecycleOwner;
    int                 requestCode = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            fragmentId = UUID.fromString(savedInstanceState.getString("FRAGMENT_ID"));
            String targetId = savedInstanceState.getString("TARGET_FRAGMENT_ID");
            if(targetId != null) {
                targetLifecycleOwner = UUID.fromString(targetId);
            }
            requestCode = savedInstanceState.getInt("REQUEST_CODE", -1);
        } else {
            fragmentId = UUID.randomUUID();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("FRAGMENT_ID", fragmentId.toString());
        if(targetLifecycleOwner != null) {
            outState.putString("TARGET_FRAGMENT_ID", targetLifecycleOwner.toString());
        }
        outState.putInt("REQUEST_CODE", requestCode);
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

    public void setTargetController(UUID targetControllerId, int requestCode) {
        this.targetLifecycleOwner = targetControllerId;
        this.requestCode = requestCode;
    }

    public UUID getTargetLifecycleOwner() {
        return targetLifecycleOwner;
    }

    public int getRequestCode() {
        return requestCode;
    }

    @Override
    public void kill() {
        PMActivity parent = getOwnerActivity();
        if(parent != null) {
            parent.onBackPressed();
        }
    }

    @Override
    public Class getViewInterface() {
        return PMLifecycleOwner.class;
    }

    protected abstract boolean isMarkedForDeath();

    protected abstract void markForDeath();
}
