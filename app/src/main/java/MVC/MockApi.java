package MVC;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;


import static java.lang.Thread.sleep;

/**
 * Created by shrikanth on 11/6/17.
 */

public class MockApi {

    public interface ResponseHandler{
        void onSuccess();
        void onError();
    }
    public static void getData(final ResponseHandler listener){
        final Handler h = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                listener.onSuccess();
                return false;
            }
        });
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                    h.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        new Thread(r).start();
    }
}
