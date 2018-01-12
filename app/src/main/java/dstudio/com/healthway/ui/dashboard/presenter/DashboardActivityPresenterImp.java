package dstudio.com.healthway.ui.dashboard.presenter;

import android.content.Context;

import dstudio.com.healthway.dagger.AndroidMVPDaggerApplication;
import dstudio.com.healthway.ui.dashboard.view.DashboardActivityView;

/**
 * Created by janwelcris on 10/23/2017.
 */

public class DashboardActivityPresenterImp implements DashboardActivityPresenter{

    private DashboardActivityView view;

    public DashboardActivityPresenterImp(Context context) {
        ((AndroidMVPDaggerApplication) context).getAppComponent(context).inject(this);
    }

    @Override
    public void setView(DashboardActivityView view) {
        this.view = view;
    }

    @Override
    public void onUserClickTimeInOut() {
        view.redirectTimeInOut();
    }

    @Override
    public void onUserClickDashboard() {
        view.redirectDashboard();
    }

    @Override
    public void onUserClickRequest() {
        view.redirectRequest();
    }


    @Override
    public void onUserClickLogout() {
        view.redirectLogout();
    }
}
