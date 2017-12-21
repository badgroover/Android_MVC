package pm_views.flows.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by shrikanth on 12/21/17.
 */

public class RandomHelper {

    public static List<String> getRandomStringsList(){
        List<String> strings = new ArrayList<>();
        for(int i=0;i<70;i++){
            strings.add(UUID.randomUUID().toString());
        }
        return strings;
    }

}
