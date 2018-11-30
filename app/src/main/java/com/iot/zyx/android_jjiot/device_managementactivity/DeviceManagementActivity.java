package com.iot.zyx.android_jjiot.device_managementactivity;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.BaseRespone;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class DeviceManagementActivity extends BaseActivity {

    @BindView(R.id.device_management_back_img)
    ImageView deviceManagementBackImg;
    @BindView(R.id.device_management_recycler)
    RecyclerView deviceManagementRecycler;
    DeviceManagementAadapter deviceManagementAadapter;
    DeviceGetBean deviceGetBean;

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
        DeviceGet();
    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.device_management_back_img)
    public void onViewClicked() {
        finish();
    }

    public void DeleteDevice(String str){
        OkhttpUtil.okHttpPostJson(API.DELETE_DEVICE, str, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败！");
            }

            @Override
            public void onResponse(String response) {
                BaseRespone baseRespone = GsonUtil.GsonToBean(response,BaseRespone.class);
                if(baseRespone.getResult().equals("00")){
                    toastShort("删除成功");
                    //DeviceGet();
                }else {
                    toastShort("删除失败");
                }
            }
        });
    }

    public void DeviceGet() {
        DeviceGetParameter deviceGetParameter = new DeviceGetParameter();
        deviceGetParameter.setAreaId(null);
        String jsonStr = GsonUtil.GsonString(deviceGetParameter);

        OkhttpUtil.okHttpPostJson(API.DEVICE_GET, jsonStr, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败！");
            }

            @Override
            public void onResponse(String response) {
                deviceGetBean = GsonUtil.GsonToBean(response,DeviceGetBean.class);
                deviceManagementAadapter = new DeviceManagementAadapter(R.layout.device_management_recyler_item,deviceGetBean.getData().getList());
                deviceManagementRecycler.setAdapter(deviceManagementAadapter);
                deviceManagementAadapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {

                        Snackbar.make(view,"确认删除"+deviceGetBean.getData().getList().get(position).getName()+"?",Snackbar.LENGTH_SHORT)
                                .setAction("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        DeleteDevice(GsonUtil.GsonString(deviceGetBean.getData().getList().get(position)));
                                    }
                                }).show();

                        return true;
                    }
                });
            }
        });
    }
}
