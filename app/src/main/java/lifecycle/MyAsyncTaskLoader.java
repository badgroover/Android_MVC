package lifecycle;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by shrikanth on 1/17/18.
 */

public class MyAsyncTaskLoader extends AsyncTaskLoader<String> {


    private boolean resultsHaveCome = false;
    public MyAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if(!resultsHaveCome) {
            forceLoad();
        }else {
            deliverResult("Return Data");
        }

    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
    }

    @Override
    protected boolean onCancelLoad() {
        return super.onCancelLoad();
    }

    @Override
    public void onCanceled(String data) {
        super.onCanceled(data);
    }

    @Override
    protected String onLoadInBackground() {
        return super.onLoadInBackground();
    }

    @Override
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
    }

    @Override
    public boolean isLoadInBackgroundCanceled() {
        return super.isLoadInBackgroundCanceled();
    }

    @Override
    public String loadInBackground() {
        try {
            Thread.sleep(5000);
            resultsHaveCome = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Return Data";
    }
}
