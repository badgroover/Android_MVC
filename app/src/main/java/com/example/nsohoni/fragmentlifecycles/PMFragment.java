package com.example.nsohoni.fragmentlifecycles;

import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;

import java.util.UUID;

import MVC.ViewInterface;

/**
 * Created by nsohoni on 16/10/17.
 */

public class PMFragment extends LifecycleFragment implements ViewInterface {

    UUID    fragmentId;

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

    public boolean isFragmentVisible() {
        if(isVisible() && isAdded()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateViews() {

    }

    public UUID getFragmentId() {
        return fragmentId;
    }
}
