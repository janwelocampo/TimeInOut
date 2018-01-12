package dstudio.com.healthway.ui.dashboard.fragment.timeinoutfragment.presenter;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import java.util.ArrayList;

import javax.inject.Inject;

import dstudio.com.healthway.dagger.AndroidMVPDaggerApplication;
import dstudio.com.healthway.retrofit.APIInterface;
import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.model.DailyAttendance;
import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.presenter.MonthlyFragmentPresenter;
import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.view.MonthlyFragmentView;
import dstudio.com.healthway.ui.dashboard.fragment.timeinoutfragment.view.TimeInOutFragmentView;
import dstudio.com.healthway.util.network.NetworkMonitor;

/**
 * Created by janwelcris on 10/23/2017.
 */

public class TimeInOutFragmentPresenterImp implements TimeInOutFragmentPresenter {
    @Inject
    APIInterface apiInterface;

    private TimeInOutFragmentView view;

    private Context context;



    public TimeInOutFragmentPresenterImp(Context context) {
        ((AndroidMVPDaggerApplication) context)
                .getAppComponent(context)
                .inject(this);
        this.context = context;
    }


    @Override
    public void setView(TimeInOutFragmentView view) {
        this.view = view;
    }

    @Override
    public void checkDevice() {
       if (!NetworkMonitor.isNetworkAvailable(context)) {
           view.showPageStatus("Internet connection is required");
           return;
       }

       if (!isTimeAutomatic(context)){
           view.showPageStatus("Automatic date & time on settings needs to be enabled");
           return;
       }

        if (!isTimeZoneAutomatic(context)){
            view.showPageStatus("Automatic time zone on settings needs to be enabled");
            return;
        }

        view.showPageStatus("");
    }

    @Override
    public void getCurrentDate() {

    }

    @Override
    public void onUserClickTimeIn() {
        view.redirectTimeIn();
    }

    private boolean isTimeAutomatic(Context c) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.Global.getInt(c.getContentResolver(), Settings.Global.AUTO_TIME, 0) == 1;
        } else {
            return android.provider.Settings.System.getInt(c.getContentResolver(), android.provider.Settings.System.AUTO_TIME, 0) == 1;
        }
    }

    private boolean isTimeZoneAutomatic(Context c) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.Global.getInt(c.getContentResolver(), Settings.Global.AUTO_TIME_ZONE, 0) == 1;
        } else {
            return android.provider.Settings.System.getInt(c.getContentResolver(), Settings.System.AUTO_TIME_ZONE, 0) == 1;
        }
    }
}
