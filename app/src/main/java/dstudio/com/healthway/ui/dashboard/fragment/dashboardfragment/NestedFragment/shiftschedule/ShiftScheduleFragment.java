package dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.shiftschedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import dstudio.com.healthway.R;

/**
 * Created by janwelcris on 12/7/2017.
 */

public class ShiftScheduleFragment extends Fragment {

    public static ShiftScheduleFragment newInstance() {
        ShiftScheduleFragment fragment = new ShiftScheduleFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shiftschedule, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
