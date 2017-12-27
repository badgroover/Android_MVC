package MVC;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shrikanth on 12/27/17.
 */

public interface ResultsListener {
    void onResult(int requestCode, int resultOk, HashMap<String, Object> results);
}
