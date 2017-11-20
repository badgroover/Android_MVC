package MVC;

import android.arch.lifecycle.LifecycleRegistryOwner;

import java.util.UUID;

import pm_views.PMActivity;

/**
 * Created by nsohoni on 01/11/17.
 */

public interface PMLifecycleRegistryOwner extends LifecycleRegistryOwner{
    void        updateViews();
    void        setupViews();
    PMActivity  getOwnerActivity();
    UUID        getIdentifier();
    UUID        getTargetLifecycleOwner();
    int         getRequestCode();

    void        kill();
}
