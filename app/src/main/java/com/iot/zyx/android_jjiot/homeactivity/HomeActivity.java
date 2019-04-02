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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.BaseParameter;
import com.iot.zyx.android_jjiot.BaseRespone;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.add_telecontrolleractivity.AddTelecontrollerActivity;
import com.iot.zyx.android_jjiot.add_zigbeeactivity.AddZigBeeActivity;
import com.iot.zyx.android_jjiot.air_conditioningactivity.AirConditioningActivity;
import com.iot.zyx.android_jjiot.area_activity.AddAreaBean;
import com.iot.zyx.android_jjiot.area_activity.AreaActivity;
import com.iot.zyx.android_jjiot.controlactivity.ControlActivity;
import com.iot.zyx.android_jjiot.device_managementactivity.DeviceManagementActivity;
import com.iot.zyx.android_jjiot.loginactivity.LoginActivity;
import com.iot.zyx.android_jjiot.roomactivity.RoomActivity;
import com.iot.zyx.android_jjiot.switchover_hostactivity.SwitchoverHostBean;
import com.iot.zyx.android_jjiot.switchover_hostactivity.SwitchoverHostContentAdapter;
import com.iot.zyx.android_jjiot.televisionactivity.TelevisionActivity;
import com.iot.zyx.android_jjiot.util.AppUtil.SharedPreferencesUtils;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;
import com.iot.zyx.android_jjiot.util.widget.RxListDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.home_content_cz_recycler)
    RecyclerView homeContentCzRecycler;
    HomeContentCzAdapter homeContentCzAdapter;
    //退出时的时间
    private long mExitTime;
    SwitchoverHostBean switchoverHostBean;
    SwitchoverHostContentAdapter switchoverHostContentAdapter;

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
        ls.add(new HomeContentBean.EmployeesBean());
        ls.add(new HomeContentBean.EmployeesBean());
        homeContentBean.setEmployees(ls);
        homeContentAdapter = new HomeContentAdapter(R.layout.home_content_recycler_item, homeContentBean.getEmployees());
        homeContentRecycler.setAdapter(homeContentAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homeContentCzRecycler.setLayoutManager(linearLayoutManager);
        homeContentCzAdapter = new HomeContentCzAdapter(R.layout.home_content_cz_recycler_item,homeContentBean.getEmployees());
        homeContentCzRecycler.setAdapter(homeContentCzAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

        homeContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("activity", API.Device.Lamp);
                    openActivity(ControlActivity.class, bundle);
                } else if (position == 1) {
                    Bundle bundle = new Bundle();
                    bundle.putString("activity", API.Device.Switch);
                    openActivity(ControlActivity.class, bundle);
                } else if (position == 2) {
                    openActivity(TelevisionActivity.class);
                } else if (position == 3) {
                    openActivity(AirConditioningActivity.class);
                } else if (position == 4) {
                    Bundle bundle = new Bundle();
                    bundle.putString("activity", API.Device.Curtain);
                    openActivity(ControlActivity.class, bundle);
                } else if (position == 5) {
                    Bundle bundle = new Bundle();
                    bundle.putString("activity", API.Device.MultSensor);
                    openActivity(ControlActivity.class, bundle);
                } else if (position == 6) {
                    openActivity(RoomActivity.class);
                } else {
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

            SwitchoverHost();

            return true;
        }
        if(id == R.id.action_login_out){

            finish();
            openActivity(LoginActivity.class);

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
        } else if (id == R.id.home_drawer_add_telecontroller) {
            openActivity(AddTelecontrollerActivity.class);
        } else if (id == R.id.home_drawer_add_area) {
            openActivity(AreaActivity.class);
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

    public void SwitchoverHost() {
        OkhttpUtil.okHttpGet(API.IP + API.GET_GATEWAY, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {

                try {
                    switchoverHostBean = GsonUtil.GsonToBean(response, SwitchoverHostBean.class);
                    if ("00".equals(switchoverHostBean.getResult())) {

                        if (!switchoverHostBean.getData().getList().isEmpty()) {
                            final RxListDialog rxListDialog = new RxListDialog(HomeActivity.this);
                            rxListDialog.getRecyclerView().setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                            switchoverHostContentAdapter = new SwitchoverHostContentAdapter(R.layout.switchover_host_recycler_item, switchoverHostBean.getData().getList());
                            rxListDialog.getRecyclerView().setAdapter(switchoverHostContentAdapter);
                            rxListDialog.show();
                            switchoverHostContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    SharedPreferencesUtils.setParam(HomeActivity.this, "productkey", switchoverHostBean.getData().getList().get(position).getProductkey());
                                    SharedPreferencesUtils.setParam(HomeActivity.this, "devicename", switchoverHostBean.getData().getList().get(position).getDevicename());
                                    API.Device.productkey = switchoverHostBean.getData().getList().get(position).getProductkey();
                                    API.Device.devicename = switchoverHostBean.getData().getList().get(position).getDevicename();
                                    toastShort("切换成功");
                                    rxListDialog.cancel();
                                }
                            });

                        }

                    } else {
                        toastShort(switchoverHostBean.getMessage());
                    }


                } catch (Exception e) {

                }

            }
        });
    }


    public void openNetwork() {
        BaseParameter baseParameter = new BaseParameter();
        String Josnstr = GsonUtil.GsonString(baseParameter);
        OkhttpUtil.okHttpPostJson(API.IP + API.OPEN_NETWORK, Josnstr, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {
                try {
                    BaseRespone baseRespone = GsonUtil.GsonToBean(response, BaseRespone.class);
                    if (baseRespone.getResult().equals("00")) {
                        toastShort("打开网络成功");
                    } else {
                        toastShort(baseRespone.getMessage());
                    }
                } catch (Exception e) {

                }


            }
        });
    }

}
