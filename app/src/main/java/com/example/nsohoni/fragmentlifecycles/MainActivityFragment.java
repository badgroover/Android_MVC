package com.example.nsohoni.fragmentlifecycles;

import android.arch.lifecycle.LifecycleFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import MVC.AddressController;
import MVC.GlobalControllerFactory;
import lifecycle.MyLifecycleObserver;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends PMFragment {

    private MyLifecycleObserver observer;
    private AddressController controller;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = GlobalControllerFactory.getInstance().getControllerForFragment(this, AddressController.class);
        getLifecycle().addObserver(controller);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
