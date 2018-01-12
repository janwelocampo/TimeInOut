package dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.presenter;

import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.view.MonthlyFragmentView;

/**
 * Created by janwelcris on 10/23/2017.
 */

public interface MonthlyFragmentPresenter {

    void setView(MonthlyFragmentView view);

    void getAttendance();

    void getMonths();
}
