package dstudio.com.healthway.ui.dashboard.fragment.timeinoutfragment.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dstudio.com.healthway.R;
import dstudio.com.healthway.dagger.AndroidMVPDaggerApplication;
import dstudio.com.healthway.ui.camera.CameraActivity;
import dstudio.com.healthway.ui.dashboard.fragment.timeinoutfragment.presenter.TimeInOutFragmentPresenter;


/**
 * Created by janwelcris on 11/25/2017.
 */

public class TimeInOutFragment extends Fragment implements TimeInOutFragmentView, SwipeRefreshLayout.OnRefreshListener , View.OnClickListener{

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.text_time)
    TextClock textViewTime;

    @BindView(R.id.text_date)
    TextView textViewDate;

    @BindView(R.id.text_error)
    TextView textViewError;

    @BindView(R.id.button_time_in)
    Button buttonTimeIn;

    @BindView(R.id.button_time_out)
    Button buttonTimeOut;

    @Inject
    TimeInOutFragmentPresenter timeInOutFragmentPresenter;

    public static TimeInOutFragment newInstance() {
        TimeInOutFragment fragment = new TimeInOutFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_in_out, container, false);
        ((AndroidMVPDaggerApplication) getActivity().getApplication())
                .getAppComponent(getActivity())
                .inject(this);
        timeInOutFragmentPresenter.setView(this);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(this);
        buttonTimeIn.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        timeInOutFragmentPresenter.checkDevice();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_time_in:
                timeInOutFragmentPresenter.onUserClickTimeIn();
                break;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showCurrentDate(String date) {

    }

    @Override
    public void showPageStatus(String error) {
        if (error.toString().equals("")){
            textViewError.setText("");
            buttonTimeIn.setEnabled(true);
            buttonTimeOut.setEnabled(true);
        }
        else {
            textViewError.setText(error);
            buttonTimeIn.setEnabled(false);
            buttonTimeOut.setEnabled(false);
        }


    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }

    @Override
    public void redirectTimeIn() {
        Intent intent = new Intent(getActivity(), CameraActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        timeInOutFragmentPresenter.checkDevice();
    }


}
