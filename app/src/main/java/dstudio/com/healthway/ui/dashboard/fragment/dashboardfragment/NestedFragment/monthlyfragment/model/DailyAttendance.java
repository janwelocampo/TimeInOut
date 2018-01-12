package dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.NestedFragment.monthlyfragment.model;

/**
 * Created by janwelcris on 11/25/2017.
 */

public class DailyAttendance {

    private String date;
    private boolean leave;
    private String timeIn;
    private String timeOut;
    private String actualHours;
    private String expectedHours;

    public DailyAttendance(String date, boolean leave, String timeIn, String timeOut, String actualHours, String expectedHours){
        this.date = date;
        this.leave = leave;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.actualHours = actualHours;
        this.expectedHours = expectedHours;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isLeave() {
        return leave;
    }

    public void setLeave(boolean leave) {
        this.leave = leave;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getActualHours() {
        return actualHours;
    }

    public void setActualHours(String actualHours) {
        this.actualHours = actualHours;
    }

    public String getExpectedHours() {
        return expectedHours;
    }

    public void setExpectedHours(String expectedHours) {
        this.expectedHours = expectedHours;
    }
}
