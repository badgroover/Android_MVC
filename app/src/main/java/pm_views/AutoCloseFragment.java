package pm_views;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import MVC.PMLifecycleOwner;
import lifecycle.MyAsyncTaskLoader;
import pm_views.flows.controllers.AutoCloseFragmentController;

/**
 * Created by shrikanth on 11/6/17.
 */

public class AutoCloseFragment extends PMFragment<AutoCloseFragmentController> {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(/*id*/1000, /*Bundle*/null, new LoaderManager.LoaderCallbacks<String>() {
            @Override
            public Loader<String> onCreateLoader(int id, Bundle args) {
                if(id == 1000) {
                    return new MyAsyncTaskLoader(getActivity());
                }
                return null;
            }

            @Override
            public void onLoadFinished(Loader<String> loader, String data) {
                if(data != null){

                }
            }

            @Override
            public void onLoaderReset(Loader<String> loader) {
                if(loader != null){

                }
            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auto_close, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        //controller.fetchAndFinish();
    }
    @Override
    public Class getViewInterface() {
        return PMLifecycleOwner.class;
    }
}
