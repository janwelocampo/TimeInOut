package dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.presenter;

import android.content.Context;

import java.util.ArrayList;

import javax.inject.Inject;

import dstudio.com.healthway.dagger.AndroidMVPDaggerApplication;
import dstudio.com.healthway.retrofit.APIInterface;
import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.model.DailyAttendance;
import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.view.MonthlyFragmentView;

/**
 * Created by janwelcris on 10/23/2017.
 */

public class MonthlyFragmentPresenterImp implements MonthlyFragmentPresenter {
    @Inject
    APIInterface apiInterface;

    private MonthlyFragmentView view;

    private String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December" };

    public MonthlyFragmentPresenterImp(Context context) {
        ((AndroidMVPDaggerApplication) context)
                .getAppComponent(context)
                .inject(this);
    }

    @Override
    public void setView(MonthlyFragmentView view) {
        this.view = view;
    }

    @Override
    public void getAttendance() {
        ArrayList<DailyAttendance> list = new ArrayList<>();
        list.add(new DailyAttendance("Nov 13 20117 - Monday", false, "10:00 AM", "08:00 PM", "10", "9"));
        list.add(new DailyAttendance("Nov 14 20117 - Tuesday", false, "10:30 AM", "08:00 PM", "9.3", "9"));
        list.add(new DailyAttendance("Nov 15 20117 - Wednesday", false, "10:11 AM", "08:00 PM", "9.11", "9"));
        list.add(new DailyAttendance("Nov 16 20117 - Thursday", false, "10:00 AM", "08:00 PM", "10", "9"));
        list.add(new DailyAttendance("Nov 17 20117 - Friday", false, "10:00 AM", "08:00 PM", "10", "9"));

        view.showListAttendance(list);
    }

    @Override
    public void getMonths() {
        view.showListMonth(monthName);
    }


}
