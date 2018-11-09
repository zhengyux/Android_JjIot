package com.iot.zyx.android_jjiot.device_managementactivity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeviceManagementActivity extends BaseActivity {

    @BindView(R.id.device_management_back_img)
    ImageView deviceManagementBackImg;
    @BindView(R.id.device_management_recycler)
    RecyclerView deviceManagementRecycler;

    @Override
    protected int setLayout() {
        return R.layout.activity_device_management;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.device_management_back_img)
    public void onViewClicked() {
        finish();
    }
}
