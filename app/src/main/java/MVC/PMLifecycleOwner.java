package MVC;

import android.arch.lifecycle.LifecycleRegistryOwner;

import java.util.UUID;

import pm_views.PMActivity;

/**
 * Created by nsohoni on 01/11/17.
 */

public interface PMLifecycleOwner extends LifecycleRegistryOwner{
    void        updateViews();
    void        setupViews();
    PMActivity  getOwnerActivity();
    UUID        getIdentifier();
    void        kill();
    Class getViewInterface();
}
