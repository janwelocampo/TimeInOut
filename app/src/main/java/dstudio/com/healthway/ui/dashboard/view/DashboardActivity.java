package dstudio.com.healthway.ui.dashboard.view;


import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

import javax.inject.Inject;

import dstudio.com.healthway.R;
import dstudio.com.healthway.dagger.AndroidMVPDaggerApplication;
import dstudio.com.healthway.ui.dashboard.fragment.dashboardfragment.DashboardFragment;

import dstudio.com.healthway.ui.dashboard.fragment.requestfragment.RequestFragment;
import dstudio.com.healthway.ui.dashboard.fragment.timeinoutfragment.view.TimeInOutFragment;
import dstudio.com.healthway.ui.dashboard.menu.DrawerAdapter;
import dstudio.com.healthway.ui.dashboard.menu.DrawerItem;
import dstudio.com.healthway.ui.dashboard.menu.SimpleItem;
import dstudio.com.healthway.ui.dashboard.menu.SpaceItem;
import dstudio.com.healthway.ui.dashboard.presenter.DashboardActivityPresenter;

public class DashboardActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener, DashboardActivityView{

    @Inject
    DashboardActivityPresenter dashboardActivityPresenter;
    private static final int MENU_TIME_INOUT = 0;
    private static final int MENU_DASHBOARD = 1;
    private static final int MENU_REQUEST = 2;
    private static final int MENU_SUPPORT = 3;
    private static final int MENU_LOGOUT = 5;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ((AndroidMVPDaggerApplication) getApplication()).getAppComponent(this).inject(this);
        dashboardActivityPresenter.setView(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withGravity(SlideGravity.LEFT)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(MENU_TIME_INOUT).setChecked(true),
                createItemFor(MENU_DASHBOARD),
                createItemFor(MENU_REQUEST),
                createItemFor(MENU_SUPPORT),
                new SpaceItem(48),
                createItemFor(MENU_LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        adapter.setSelected(MENU_TIME_INOUT);

    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.textColorSecondary))
                .withTextTint(color(R.color.textColorPrimary))
                .withSelectedIconTint(color(R.color.colorAccent))
                .withSelectedTextTint(color(R.color.colorAccent));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }

    @Override
    public void onItemSelected(int position) {
        switch (position){
            case MENU_TIME_INOUT:
                dashboardActivityPresenter.onUserClickTimeInOut();
                break;
            case MENU_DASHBOARD:
                dashboardActivityPresenter.onUserClickDashboard();
                break;

            case MENU_REQUEST:
                dashboardActivityPresenter.onUserClickRequest();
                break;

            case MENU_LOGOUT:
                dashboardActivityPresenter.onUserClickLogout();
                break;
        }

        slidingRootNav.closeMenu();

    }

    @Override
    public void redirectTimeInOut() {
        showFragment(TimeInOutFragment.newInstance());
    }

    @Override
    public void redirectDashboard() {
        showFragment(DashboardFragment.newInstance());
    }

    @Override
    public void redirectRequest() {
        showFragment(RequestFragment.newInstance());
    }


    @Override
    public void redirectLogout() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Log out")
                .setMessage("Are you sure you want to Log - out?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
