package views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import MVC.AddressController;
import MVC.DataFetchListener;
import MVC.GlobalControllerFactory;
import MVC.UserModel;
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
    Button      testAsyncDataFetchButton;
    Button      launchFragmentBtn;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = GlobalControllerFactory.getInstance().getControllerForLifecycleOwner(this, AddressController.class);
        getLifecycle().addObserver(controller);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setupViews(){
        //called by controller at the appropriate time
        View v = getView();
        name = (EditText) v.findViewById(R.id.name);
        address1 = (EditText) v.findViewById(R.id.add1);
        address2 = (EditText) v.findViewById(R.id.add2);
        city = (EditText) v.findViewById(R.id.city);
        testAsyncDataFetchButton = (Button) v.findViewById(R.id.fetchDataButton);

        testAsyncDataFetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.getSecondaryAddress(new DataFetchListener<UserModel>() {
                    @Override
                    public void success(UserModel data) {
                        updateViews();
                    }

                    @Override
                    public void error() {

                    }
                });
            }
        });

        launchFragmentBtn = (Button) v.findViewById(R.id.launchFragmentBtn);

        launchFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    @Override
    public void updateViews() {
        //called by the controller when the model has been updated.
        UserModel model = controller.getModel(UserModel.class);
        if(model != null) {
            name.setText(model.name);
            address1.setText(model.address1);
            address2.setText(model.address2);
            city.setText(model.city);
        }

    }


}
