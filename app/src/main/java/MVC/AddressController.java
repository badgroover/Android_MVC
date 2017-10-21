package MVC;

import java.lang.ref.WeakReference;

import MVC.BaseController;
import MVC.UserModel;
import views.PMFragment;

import static java.lang.Thread.sleep;

/**
 * Created by nsohoni on 14/10/17.
 */

public class AddressController extends BaseController {
    UserModel model;

    public AddressController(PMFragment fragment) {
        //add fragment to the weak reference;
        ownerFragment = new WeakReference(fragment);
    }

    public void populateModel(boolean refresh) {
        if (refresh) {
            //re-fetch data from source (db/cloud/file)
        } else if (model == null){
            //create a model
            model = new UserModel();
            fetchData();
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
                sleep(15000);
                PMFragment frag = getOwnerFragment();
                if(frag != null) {
                    boolean b = frag.isFragmentVisible();

                    //frag.hideProgressDialog();;
                }

                } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
