package dstudio.com.healthway.ui.dashboard.fragment.timeinoutfragment.presenter;

import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.view.MonthlyFragmentView;
import dstudio.com.healthway.ui.dashboard.fragment.timeinoutfragment.view.TimeInOutFragmentView;

/**
 * Created by janwelcris on 10/23/2017.
 */

public interface TimeInOutFragmentPresenter {

    void setView(TimeInOutFragmentView view);

    void checkDevice();

    void getCurrentDate();

    void onUserClickTimeIn();
}
