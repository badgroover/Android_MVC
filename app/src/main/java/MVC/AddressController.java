package MVC;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import java.lang.ref.WeakReference;

import MVC.BaseController;
import MVC.UserModel;
import views.PMFragment;

import static java.lang.Thread.sleep;

/**
 * Created by nsohoni on 14/10/17.
 */

public class AddressController extends BaseController {

    public AddressController(PMFragment fragment) {
        super(fragment);
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
        PMFragment fragment = ownerFragment.get();
        if(fragment != null && fragment.isFragmentVisible()) {
            fragment.updateViews();
        }
    }

    private void fetchData() {
        //Start a thread to get data
        PMFragment frag = getOwnerFragment();
        if(frag != null) {
            //frag.showProgressDialogWithMessage("Loading");
            TestThread thread = new TestThread();
            new Thread(thread).start();

        }

    }

    class TestThread implements Runnable {
        @Override
        public void run() {
            try {
                sleep(7000);
                PMFragment frag = getOwnerFragment();
                if(isControllerAlive() && frag != null) {
                    frag.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            UserModel m = new UserModel();
                            m.name = new String("Nikhil Sohoni");
                            m.address1 = new String("591 W. Remington Dr");
                            m.address2 = new String("APt 211");
                            m.city = new String("Sunnyvale");
                            model = m;
                            updateViews();
                        }
                    });
                }

                } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
