package dstudio.com.healthway.ui.dashboard.fragment.profilefragment.presenter;

import android.content.Context;

import javax.inject.Inject;

import dstudio.com.healthway.dagger.AndroidMVPDaggerApplication;
import dstudio.com.healthway.retrofit.APIInterface;
import dstudio.com.healthway.ui.dashboard.fragment.profilefragment.model.Profile;
import dstudio.com.healthway.ui.dashboard.fragment.profilefragment.view.ProfileFragmentView;
import dstudio.com.healthway.util.network.NoNetworkException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by janwelcris on 10/23/2017.
 */

public class ProfileFragmentPresenterImp implements ProfileFragmentPresenter {
    @Inject
    APIInterface apiInterface;

    private ProfileFragmentView view;

    public ProfileFragmentPresenterImp(Context context) {
        ((AndroidMVPDaggerApplication) context)
                .getAppComponent(context)
                .inject(this);
    }

    @Override
    public void setView(ProfileFragmentView view) {
        this.view = view;
    }

    @Override
    public void getProfileData() {
        view.showLoading();
        apiInterface.doGetProfileData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Profile>() {
                    @Override
                    public void onCompleted() {
                        view.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideLoading();
                        if (e instanceof NoNetworkException) {
                            // handle 'no network'
                            view.showErrorMessage("No internet connection");
                        }
                        else
                        {
                            view.showErrorMessage(e.getLocalizedMessage());
                        }

                    }

                    @Override
                    public void onNext(Profile profile) {
                        view.showProfile(profile);
                    }
                });
    }
}
