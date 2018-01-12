package dstudio.com.healthway.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.presenter.MonthlyFragmentPresenter;
import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.presenter.MonthlyFragmentPresenterImp;
import dstudio.com.healthway.ui.dashboard.fragment.profilefragment.presenter.ProfileFragmentPresenter;
import dstudio.com.healthway.ui.dashboard.fragment.profilefragment.presenter.ProfileFragmentPresenterImp;
import dstudio.com.healthway.ui.dashboard.fragment.timeinoutfragment.presenter.TimeInOutFragmentPresenter;
import dstudio.com.healthway.ui.dashboard.fragment.timeinoutfragment.presenter.TimeInOutFragmentPresenterImp;
import dstudio.com.healthway.ui.dashboard.presenter.DashboardActivityPresenter;
import dstudio.com.healthway.ui.dashboard.presenter.DashboardActivityPresenterImp;

/**
 * Created by janwelcris on 10/19/2017.
 */
@Module
public class PresenterModule {

    @Provides
    @Singleton
    DashboardActivityPresenter provideDashboardPresenter(Context context) {
        return new DashboardActivityPresenterImp(context);
    }

    @Provides
    @Singleton
    ProfileFragmentPresenter provideProfileFragmentPresenter(Context context) {
        return new ProfileFragmentPresenterImp(context);
    }
    @Provides
    @Singleton
    TimeInOutFragmentPresenter provideTimeInOutFragmentPresenter(Context context) {
        return new TimeInOutFragmentPresenterImp(context);
    }


    @Provides
    @Singleton
    MonthlyFragmentPresenter provideMonthlyFragmentPresenter(Context context) {
        return new MonthlyFragmentPresenterImp(context);
    }


}
