package dstudio.com.healthway.ui.dashboard.fragment.profilefragment.view;

import dstudio.com.healthway.ui.dashboard.fragment.profilefragment.model.Profile;

/**
 * Created by janwelcris on 10/19/2017.
 */

public interface ProfileFragmentView {

    void showLoading();

    void hideLoading();

    void showProfile(Profile profile);

    void showErrorMessage(String errorMessage);
}
