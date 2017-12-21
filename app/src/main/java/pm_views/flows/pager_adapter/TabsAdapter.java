package pm_views.flows.pager_adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import pm_views.flows.TabsContainerFragment;

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
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position+"";
    }
}
