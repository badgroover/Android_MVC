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
        PMLifecycleRegistryOwner owner = getLifecycleOwner();
        if(owner != null) {
            //frag.showProgressDialogWithMessage("Loading");
            TestThread thread = new TestThread();
            new Thread(thread).start();

        }

    }

    public void getSecondaryAddress(DataFetchListener<UserModel> listener) {
        final WeakReference<DataFetchListener> weakListener = new WeakReference<DataFetchListener>(listener);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(7000);
                    PMLifecycleRegistryOwner owner = getLifecycleOwner();
                    if(isControllerAlive() && owner != null) {
                        Activity activity = owner.getOwnerActivity();
                        if(activity != null && !activity.isFinishing()) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    UserModel m = new UserModel();
                                    m.name = new String("Sapna Sohoni");
                                    m.address1 = new String("591 W. Remington Dr");
                                    m.address2 = new String("APt 211");
                                    m.city = new String("Fremont");
                                    model = m;
                                    DataFetchListener<UserModel> l = weakListener.get();
                                    if(l != null) {
                                        l.success((UserModel)model);
                                    }
                                }
                            });
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        new Thread(r).start();
    }

    class TestThread implements Runnable {
        @Override
        public void run() {
            try {
                sleep(7000);
                PMLifecycleRegistryOwner owner = getLifecycleOwner();
                if(isControllerAlive() && owner != null) {
                    Activity activity = owner.getOwnerActivity();
                    if(activity != null && !activity.isFinishing()) {
                        activity.runOnUiThread(new Runnable() {
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
                }

                } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
