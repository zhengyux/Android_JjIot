package com.iot.zyx.android_jjiot.homeactivity;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/10/26 16:53
 * 修改人：xuan
 * 修改时间：2018/10/26 16:53
 * 修改备注：
 */
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.R;

import butterknife.BindView;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.home_toolbar)
    Toolbar homeToolbar;
    @BindView(R.id.content_home_recycler)
    RecyclerView contentHomeRecycler;
    @BindView(R.id.home_nav_view)
    NavigationView homeNavView;
    @BindView(R.id.home_drawer_layout)
    DrawerLayout homeDrawerLayout;

    @Override
    protected int setLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        setSupportActionBar(homeToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, homeDrawerLayout, homeToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        homeDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        homeNavView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onBackPressed() {

        if (homeDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            homeDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        homeDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
