package dstudio.com.healthway.ui.dashboard.fragment.timeinoutfragment.view;

import java.util.List;


/**
 * Created by janwelcris on 10/19/2017.
 */

public interface TimeInOutFragmentView {

    void showLoading();

    void hideLoading();

    void showCurrentDate(String date);

    void showPageStatus (String status);

    void showErrorMessage(String errorMessage);

    void redirectTimeIn();
}
