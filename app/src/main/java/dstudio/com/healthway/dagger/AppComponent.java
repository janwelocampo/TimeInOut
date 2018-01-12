package dstudio.com.healthway.dagger;

import javax.inject.Singleton;

import dagger.Component;

import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.presenter.MonthlyFragmentPresenterImp;
import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.view.MonthlyFragment;
import dstudio.com.healthway.ui.dashboard.fragment.profilefragment.presenter.ProfileFragmentPresenterImp;
import dstudio.com.healthway.ui.dashboard.fragment.timeinoutfragment.presenter.TimeInOutFragmentPresenterImp;
import dstudio.com.healthway.ui.dashboard.fragment.timeinoutfragment.view.TimeInOutFragment;
import dstudio.com.healthway.ui.dashboard.presenter.DashboardActivityPresenterImp;
import dstudio.com.healthway.ui.dashboard.view.DashboardActivity;
import dstudio.com.healthway.ui.dashboard.fragment.profilefragment.view.ProfileFragment;

/**
 * Created by janwelcris on 9/15/2017.
 */

    @Singleton
    @Component(modules = {AppModule.class,PresenterModule.class, ObjectManagerModule.class, APIModule.class})
    public interface AppComponent {

        //Dashboard Activity
        void inject(DashboardActivity target);
        void inject(DashboardActivityPresenterImp target);
            //Profile Fragment
            void inject(ProfileFragment target);
            void inject(ProfileFragmentPresenterImp target);
            //TimeInOut Fragment
            void inject(TimeInOutFragment target);
            void inject(TimeInOutFragmentPresenterImp target);
            //Dashboard Fragment
                void inject(MonthlyFragment target);
                void inject(MonthlyFragmentPresenterImp target);


    }

