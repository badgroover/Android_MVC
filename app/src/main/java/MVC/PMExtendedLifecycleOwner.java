package MVC;

/**
 * Created by nsohoni on 20/11/17.
 */

public interface PMExtendedLifecycleOwner extends PMLifecycleOwner {
    void launchNextFragment();
    void killFragment();
}
