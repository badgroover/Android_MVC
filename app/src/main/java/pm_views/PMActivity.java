package pm_views;

import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.UUID;

import MVC.PMLifecycleRegistryOwner;

/**
 * Created by nsohoni on 16/10/17.
 */

public class PMActivity extends FragmentActivity implements PMLifecycleRegistryOwner {

    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);
    boolean isStateSaved = false;
    UUID    activityId;

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


    public boolean isStateSaved() {
        return isStateSaved;
    }
}
