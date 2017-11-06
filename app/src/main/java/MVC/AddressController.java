package MVC;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import java.lang.ref.WeakReference;

import static java.lang.Thread.sleep;

/**
 * Created by nsohoni on 14/10/17.
 */

public class AddressController extends BaseController {

    public AddressController(PMLifecycleRegistryOwner owner) {
        super(owner);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        super.onResume();
        populateModel(false);
    }




    public void populateModel(boolean refresh) {
        if (refresh) {
            //re-fetch data from source (db/cloud/file)
        } else if (model == null){
            fetchData();
        } else {
            //we have data...
            updateViews();
        }
    }

    public void updateViews() {
        //call an interface method implemented in fragment and pass in the model
        PMLifecycleRegistryOwner owner = getLifecycleOwner();
        if(owner != null && owner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            owner.updateViews();
        }
    }

    private void fetchData() {
        //Start a thread to get data
        final PMLifecycleRegistryOwner owner = getLifecycleOwner();
        if(owner != null) {
            //frag.showProgressDialogWithMessage("Loading");
            MockApi.getData(new MockApi.ResponseHandler() {
                @Override
                public void onSuccess() {
                    if(isControllerAlive() && owner != null) {
                        UserModel m = new UserModel();
                        m.name = "Nikhil Sohoni";
                        m.address1 = "591 W. Remington Dr";
                        m.address2 = "APt 211";
                        m.city = "Sunnyvale";
                        model = m;
                        updateViews();
                    }
                }
                @Override
                public void onError() {

                }
            });
        }

    }

    public void getSecondaryAddress(DataFetchListener<UserModel> listener) {
        final WeakReference<DataFetchListener> weakListener = new WeakReference<DataFetchListener>(listener);
        MockApi.getData(new MockApi.ResponseHandler() {
            @Override
            public void onSuccess() {
                PMLifecycleRegistryOwner owner = getLifecycleOwner();
                if(isControllerAlive() && owner != null) {
                    UserModel m = new UserModel();
                    m.name = "Sapna Sohoni";
                    m.address1 = "591 W. Remington Dr";
                    m.address2 = "APt 211";
                    m.city = "Fremont";
                    model = m;
                    DataFetchListener<UserModel> l = weakListener.get();
                    if(l != null) {
                        l.success((UserModel)model);
                    }
                }
            }
            @Override
            public void onError() {

            }
        });
    }

    class TestThread implements Runnable {
        @Override
        public void run() {
            try {
                sleep(7000);



                } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
