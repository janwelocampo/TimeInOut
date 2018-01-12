package dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dstudio.com.healthway.R;
import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.model.DailyAttendance;



public class MonthlyFragmentAdapter extends ArrayAdapter<DailyAttendance> {

    private List<DailyAttendance> dailyAttendanceList;

    public MonthlyFragmentAdapter(Context context, int resource, List<DailyAttendance> dailyAttendanceList) {
        super(context, resource, dailyAttendanceList);
        this.dailyAttendanceList = dailyAttendanceList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder;

        if (v != null) {
            holder = (ViewHolder) v.getTag();
        } else {
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_monthly, parent, false);
            holder = new ViewHolder(v);
            v.setTag(holder);
        }

        holder.textViewDate.setText(dailyAttendanceList.get(position).getDate());
        holder.textViewLeave.setText(String.valueOf(dailyAttendanceList.get(position).isLeave()));
        holder.textViewIn.setText(dailyAttendanceList.get(position).getTimeIn());
        holder.textViewOut.setText(dailyAttendanceList.get(position).getTimeOut());
        holder.textViewActualHours.setText(dailyAttendanceList.get(position).getActualHours());
        holder.textViewExpectedHours.setText(dailyAttendanceList.get(position).getExpectedHours());

        return v;
    }
    static class ViewHolder {
        @BindView(R.id.txt_date)
        TextView textViewDate;

        @BindView(R.id.txt_leave)
        TextView textViewLeave;

        @BindView(R.id.txt_in)
        TextView textViewIn;

        @BindView(R.id.txt_out)
        TextView textViewOut;

        @BindView(R.id.txt_actual_hours)
        TextView textViewActualHours;

        @BindView(R.id.txt_expected_hours)
        TextView textViewExpectedHours;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}