package dstudio.com.healthway.ui.dashboard.fragment.profilefragment.presenter;

import dstudio.com.healthway.ui.dashboard.fragment.profilefragment.view.ProfileFragmentView;

/**
 * Created by janwelcris on 10/23/2017.
 */

public interface ProfileFragmentPresenter {

    void setView(ProfileFragmentView view);

    void getProfileData();
}
