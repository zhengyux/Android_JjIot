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

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.BaseParameter;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.add_zigbeeactivity.AddZigBeeActivity;
import com.iot.zyx.android_jjiot.air_conditioningactivity.AirConditioningActivity;
import com.iot.zyx.android_jjiot.controlactivity.ControlActivity;
import com.iot.zyx.android_jjiot.device_managementactivity.DeviceManagementActivity;
import com.iot.zyx.android_jjiot.televisionactivity.TelevisionActivity;
import com.iot.zyx.android_jjiot.BaseRespone;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.home_toolbar)
    Toolbar homeToolbar;
    @BindView(R.id.home_content_recycler)
    RecyclerView homeContentRecycler;
    @BindView(R.id.home_nav_view)
    NavigationView homeNavView;
    @BindView(R.id.home_drawer_layout)
    DrawerLayout homeDrawerLayout;
    HomeContentAdapter homeContentAdapter;
    //退出时的时间
    private long mExitTime;

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
        homeContentRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        HomeContentBean homeContentBean = new HomeContentBean();
        List<HomeContentBean.EmployeesBean> ls = new ArrayList<>();
        ls.add(new HomeContentBean.EmployeesBean());
        ls.add(new HomeContentBean.EmployeesBean());
        ls.add(new HomeContentBean.EmployeesBean());
        ls.add(new HomeContentBean.EmployeesBean());
        ls.add(new HomeContentBean.EmployeesBean());
        homeContentBean.setEmployees(ls);

        homeContentAdapter = new HomeContentAdapter(R.layout.home_content_recycler_item, homeContentBean.getEmployees());
        homeContentRecycler.setAdapter(homeContentAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

        homeContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position==0){
                    Bundle bundle = new Bundle();
                    bundle.putString("activity","lamp");
                    openActivity(ControlActivity.class,bundle);
                } else if (position == 2) {
                    openActivity(TelevisionActivity.class);
                } else if (position == 3) {
                    openActivity(AirConditioningActivity.class);
                } else if(position==4){
                    Bundle bundle = new Bundle();
                    bundle.putString("activity","curtanin");
                    openActivity(ControlActivity.class,bundle);
                }else {
                    openActivity(ControlActivity.class);
                }

            }
        });

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

        if (id == R.id.home_drawer_add_zigbee) {
            openActivity(AddZigBeeActivity.class);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.home_drawer_device_management) {
            openActivity(DeviceManagementActivity.class);
        } else if (id == R.id.home_drawer_open_network) {
            openNetwork();
        }
        homeDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            toastShort("再次返回退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    public void openNetwork() {
        BaseParameter baseParameter = new BaseParameter();
        String Josnstr = GsonUtil.GsonString(baseParameter);
        OkhttpUtil.okHttpPostJson(API.OPEN_NETWORK, Josnstr, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {
                try{
                    BaseRespone baseRespone = GsonUtil.GsonToBean(response, BaseRespone.class);
                    if(baseRespone.getResult().equals("00")){
                        toastShort("打开网络成功");
                    }else {
                        toastShort(baseRespone.getMessage());
                    }
                }catch (Exception e){

                }



            }
        });
    }


}
