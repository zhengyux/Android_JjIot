package com.iot.zyx.android_jjiot.device_managementactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.BaseRespone;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.add_deviceactivity.AddDeviceActivity;
import com.iot.zyx.android_jjiot.add_zigbeeactivity.AddZigBeeAPIBean;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class DeviceManagementActivity extends BaseActivity {

    @BindView(R.id.device_management_back_img)
    RelativeLayout deviceManagementBackImg;
    @BindView(R.id.device_management_recycler)
    RecyclerView deviceManagementRecycler;
    DeviceManagementAadapter deviceManagementAadapter;
    AddZigBeeAPIBean addZigBeeAPIBean;
    @BindView(R.id.device_management_left_spn)
    Spinner deviceManagementLeftSpn;
    @BindView(R.id.device_management_right_spn)
    Spinner deviceManagementRightSpn;

    @Override
    protected int setLayout() {
        return R.layout.activity_device_management;
    }

    @Override
    protected void initView() {
        deviceManagementRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void initData() {
        AreaGet();
        DeviceGet();
    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.device_management_back_img)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AreaGet();
        DeviceGet();
    }

    public void DeleteDevice(String str) {
        OkhttpUtil.okHttpPostJson(API.IP+API.DELETE_DEVICE, str, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败！");
            }

            @Override
            public void onResponse(String response) {
                try {
                    BaseRespone baseRespone = GsonUtil.GsonToBean(response, BaseRespone.class);
                    if (baseRespone.getResult().equals("00")) {
                        toastShort("删除成功");
                    } else {
                        toastShort(baseRespone.getMessage());
                    }
                    DeviceGet();
                }catch (Exception e){
                    toastShort("删除数据错误");
                }

            }
        });
    }

    public void AreaGet(){

        OkhttpUtil.okHttpGet(API.IP+API.GET_AREA, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败！");
            }

            @Override
            public void onResponse(String response) {

                try{

                    AreaGetBean areaGetBean = GsonUtil.GsonToBean(response,AreaGetBean.class);
                    deviceManagementLeftSpn.setAdapter(new AreaSpnAdapter(DeviceManagementActivity.this,areaGetBean));
                    deviceManagementLeftSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }catch (Exception e){
                    toastShort(e.getMessage());
                }

            }
        });

    }

    public void DeviceGet() {
        DeviceGetParameter deviceGetParameter = new DeviceGetParameter();
        deviceGetParameter.setAreaId(null);
        String jsonStr = GsonUtil.GsonString(deviceGetParameter);

        OkhttpUtil.okHttpPostJson(API.IP+API.DEVICE_GET+"?differe=false", jsonStr, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败！");
            }

            @Override
            public void onResponse(String response) {
                try {
                    addZigBeeAPIBean = GsonUtil.GsonToBean(response, AddZigBeeAPIBean.class);
                    if("00".equals(addZigBeeAPIBean.getResult())){
                        deviceManagementAadapter = new DeviceManagementAadapter(R.layout.device_management_recyler_item, addZigBeeAPIBean.getData().getList());
                        deviceManagementRecycler.setAdapter(deviceManagementAadapter);

                        deviceManagementAadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Bundle bundle = new Bundle();
                                bundle.putString("Device",GsonUtil.GsonString(addZigBeeAPIBean.getData().getList().get(position)));
                                Intent intent = new Intent(DeviceManagementActivity.this,AddDeviceActivity.class);
                                intent.putExtras(bundle);
                                intent.putExtra("from",1);
                                startActivity(intent);
                            }
                        });

                        deviceManagementAadapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {

                                Snackbar.make(view, "确认删除" + addZigBeeAPIBean.getData().getList().get(position).getName() + "?", Snackbar.LENGTH_SHORT)
                                        .setAction("确定", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                DeleteDevice(GsonUtil.GsonString(addZigBeeAPIBean.getData().getList().get(position)));
                                            }
                                        }).show();

                                return true;
                            }
                        });
                    }else {
                        toastShort(addZigBeeAPIBean.getMessage());
                    }

                }catch (Exception e){
                    toastShort(e.getMessage());
                }

            }
        });
    }

}
