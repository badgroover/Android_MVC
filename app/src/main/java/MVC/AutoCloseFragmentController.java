package MVC;

import android.arch.lifecycle.Lifecycle;

import java.util.HashMap;

import pm_views.PMActivity;

/**
 * Created by shrikanth on 11/6/17.
 */

public class AutoCloseFragmentController<L extends PMLifecycleRegistryOwner> extends BaseController<L> {

    public AutoCloseFragmentController(L lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override
    public void setResultData(int requestCode, int resultOk, HashMap<String, Object> results) {

    }


    public void fetchAndFinish(){
        MockApi.getData(new MockApi.ResponseHandler() {
            @Override
            public void onSuccess() {
                if(isControllerAlive() && getLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED){
                    PMActivity activity = getLifecycleOwner().getOwnerActivity();
                    if(activity != null){
                        activity.onBackPressed();
                        detachController();
                    }
                }else{
                    /*
                     * We need a mechanism here to mark it and finish when it comes alive
                     */
                }
            }

            @Override
            public void onError() {

            }
        });
    }
}
