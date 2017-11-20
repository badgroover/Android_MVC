package pm_views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nsohoni on 30/10/17.
 */

public class SecondFragment extends PMFragment {

    public SecondFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //controller = GlobalControllerFactory.getInstance().createControllerForLifecycleOwner(this, AddressController.class);
        //getLifecycle().addObserver(controller);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setupViews(){
    }

    @Override
    protected boolean isMarkedForDeath() {
        return false;
    }

    @Override
    protected void markForDeath() {

    }

    @Override
    public void updateViews() {
    }


}
