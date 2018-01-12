package dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.dailyfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.intrusoft.scatter.ChartData;
import com.intrusoft.scatter.PieChart;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dstudio.com.healthway.R;

/**
 * Created by janwelcris on 11/25/2017.
 */

public class DailyFragment extends Fragment {

    @BindView(R.id.daily_chart)
    PieChart pieChart;

    private List<ChartData> data;

    public static DailyFragment newInstance() {
        DailyFragment fragment = new DailyFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        data = new ArrayList<>();
//        data.add(new ChartData("35%", 35));
//        data.add(new ChartData("25%", 25));
//        data.add(new ChartData("30%", 30));
//        data.add(new ChartData("10%", 10));

        data = new ArrayList<>();
        data.add(new ChartData("CONSUMED TIME", 55, Color.WHITE, Color.parseColor("#0090A8")));
        data.add(new ChartData("BREAK", 15, Color.WHITE, Color.parseColor("#757575")));
        data.add(new ChartData("REMAINING TIME", 30, Color.WHITE, Color.parseColor("#F57F17")));

        pieChart.setChartData(data);
    }



}
