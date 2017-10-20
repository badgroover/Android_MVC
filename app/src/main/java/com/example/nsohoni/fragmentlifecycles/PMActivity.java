package com.example.nsohoni.fragmentlifecycles;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;

/**
 * Created by nsohoni on 16/10/17.
 */

public class PMActivity extends LifecycleActivity {
    boolean isStateSaved = false;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        isStateSaved = true;
    }

    public boolean isStateSaved() {
        return isStateSaved;
    }
}
