package dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.dailyfragment.DailyFragment;
import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.view.MonthlyFragment;
import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.shiftschedule.ShiftScheduleFragment;

/**
 * Created by janwelcris on 11/24/2017.
 */

public class DashboardPagerAdapter extends FragmentPagerAdapter {

     private Context context=null;
    private int mNumOfTabs;

    public DashboardPagerAdapter(Context context, FragmentManager mgr, int mNumOfTabs) {
        super(mgr);
        this.context = context;
        this.mNumOfTabs = mNumOfTabs;
    }



    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return DailyFragment.newInstance();
            case 1:
                return ShiftScheduleFragment.newInstance();
            case 2:
                return MonthlyFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}