package views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import MVC.AddressController;
import MVC.GlobalControllerFactory;
import lifecycle.MyLifecycleObserver;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends PMFragment {

    private MyLifecycleObserver observer;
    private AddressController controller;

    EditText    name;
    EditText    address1;
    EditText    address2;
    EditText    city;

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

    @Override
    public void setupViews(){
        //called by controller at the appropriate time
        View v = getView();
        name = (EditText) v.findViewById(R.id.name);
        address1 = (EditText) v.findViewById(R.id.add1);
        address2 = (EditText) v.findViewById(R.id.add2);
        city = (EditText) v.findViewById(R.id.city);
    }

    @Override
    public void updateViews() {
        //called by the controller when the model has been updated.

    }


}
