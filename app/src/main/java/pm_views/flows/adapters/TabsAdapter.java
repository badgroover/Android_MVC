package pm_views.flows.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import pm_views.flows.views.TabsContainerFragment;

/**
 * Created by shrikanth on 12/21/17.
 */

public class TabsAdapter extends FragmentStatePagerAdapter {

    TabsContainerFragment containerFragment;
    public TabsAdapter(FragmentManager fm, TabsContainerFragment containerFragment) {
        super(fm);
        this.containerFragment = containerFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return containerFragment.getTabFragment(position);
    }

    @Override
    public int getCount() {
        return containerFragment.getCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return containerFragment.getTitle(position);
    }
}
