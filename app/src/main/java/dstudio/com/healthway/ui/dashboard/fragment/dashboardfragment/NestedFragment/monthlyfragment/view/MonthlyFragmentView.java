package dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.view;

import java.util.List;

import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.model.DailyAttendance;

/**
 * Created by janwelcris on 10/19/2017.
 */

public interface MonthlyFragmentView {

    void showLoading();

    void hideLoading();

    void showListAttendance(List<DailyAttendance> dailyAttendanceList);

    void showListMonth(String[] months);

    void showErrorMessage(String errorMessage);
}
