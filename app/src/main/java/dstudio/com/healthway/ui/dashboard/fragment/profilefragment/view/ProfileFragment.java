package dstudio.com.healthway.ui.dashboard.fragment.profilefragment.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dstudio.com.healthway.R;
import dstudio.com.healthway.dagger.AndroidMVPDaggerApplication;
import dstudio.com.healthway.ui.dashboard.fragment.profilefragment.model.Profile;
import dstudio.com.healthway.ui.dashboard.fragment.profilefragment.presenter.ProfileFragmentPresenter;

/**
 * Created by janwelcris on 10/19/2017.
 */

public class ProfileFragment extends Fragment implements ProfileFragmentView {

    @Inject
    ProfileFragmentPresenter profileFragmentPresenter;

    @BindView(R.id.image_profile)
    ImageView imageViewProfile;

    @BindView(R.id.text_username)
    TextView textViewUserName;

    @BindView(R.id.text_fName)
    TextView textViewFName;

    @BindView(R.id.text_lName)
    TextView textViewLName;

    @BindView(R.id.text_email)
    TextView textViewEmail;

    private ProgressDialog progressDialog;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ((AndroidMVPDaggerApplication) getActivity().getApplication())
                .getAppComponent(getActivity())
                .inject(this);
        profileFragmentPresenter.setView(this);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getActivity());

        profileFragmentPresenter.getProfileData();
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void showProfile(Profile profile) {
        textViewUserName.setText(profile.getUsername());
        textViewFName.setText(profile.getFname());
        textViewLName.setText(profile.getLname());
        textViewEmail.setText(profile.getEmail());

        Glide.with(getActivity()).load(profile.getAvatar())
                .into(imageViewProfile);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(getActivity(), ""+errorMessage, Toast.LENGTH_SHORT).show();
    }
}
