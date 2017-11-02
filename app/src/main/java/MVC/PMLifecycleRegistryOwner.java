package MVC;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.support.annotation.NonNull;

import java.util.UUID;

import views.PMActivity;

/**
 * Created by nsohoni on 01/11/17.
 */

public interface PMLifecycleRegistryOwner extends LifecycleRegistryOwner{
    void        updateViews();
    void        setupViews();
    PMActivity  getOwnerActivity();
    UUID        getIdentifier();
}
