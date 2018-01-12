package dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dstudio.com.healthway.R;
import dstudio.com.healthway.dagger.AndroidMVPDaggerApplication;
import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.model.DailyAttendance;
import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.presenter.MonthlyFragmentPresenter;

/**
 * Created by janwelcris on 11/25/2017.
 */

public class MonthlyFragment extends Fragment  implements MonthlyFragmentView {

    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.list_month)
    ListView listView;

    @Inject
    MonthlyFragmentPresenter monthlyFragmentPresenter;

    private MonthlyFragmentAdapter monthlyFragmentAdapter;


    public static MonthlyFragment newInstance() {
        MonthlyFragment fragment = new MonthlyFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthly, container, false);

        ((AndroidMVPDaggerApplication) getParentFragment().getActivity().getApplication())
                .getAppComponent(getParentFragment().getActivity())
                .inject(this);
        monthlyFragmentPresenter.setView(this);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        monthlyFragmentPresenter.getMonths();
        monthlyFragmentPresenter.getAttendance();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showListAttendance(List<DailyAttendance> dailyAttendanceList) {
        monthlyFragmentAdapter = new MonthlyFragmentAdapter(getParentFragment().getActivity(),R.layout.list_monthly, dailyAttendanceList);
        listView.setAdapter(monthlyFragmentAdapter);
    }

    @Override
    public void showListMonth(String[] months) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getParentFragment().getActivity(),
                android.R.layout.simple_spinner_item, months);
        spinner.setAdapter(adapter);
    }


    @Override
    public void showErrorMessage(String errorMessage) {

    }
}
