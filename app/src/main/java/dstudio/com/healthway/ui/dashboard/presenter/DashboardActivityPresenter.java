package dstudio.com.healthway.ui.dashboard.presenter;

import dstudio.com.healthway.ui.dashboard.view.DashboardActivityView;

/**
 * Created by janwelcris on 10/23/2017.
 */

public interface DashboardActivityPresenter {

    void setView(DashboardActivityView view);

    void onUserClickTimeInOut();

    void onUserClickDashboard();

    void onUserClickRequest();


    void onUserClickLogout();
}
