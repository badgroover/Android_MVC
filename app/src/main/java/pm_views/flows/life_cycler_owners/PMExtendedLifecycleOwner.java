package pm_views.flows.life_cycler_owners;

import MVC.PMLifecycleOwner;

/**
 * Created by nsohoni on 20/11/17.
 */

public interface PMExtendedLifecycleOwner extends PMLifecycleOwner {
    void launchNextFragment();
    void killFragment();
}