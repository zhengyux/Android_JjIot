package com.iot.zyx.android_jjiot.add_telecontrolleractivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.add_air_telecontrolleractivity.AddAirTelecontrollerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTelecontrollerActivity extends BaseActivity {


    @BindView(R.id.telecontroller_back_img)
    RelativeLayout telecontrollerBackImg;
    @BindView(R.id.add_telecontroller_air_rl)
    RelativeLayout addTelecontrollerAirRl;
    @BindView(R.id.add_telecontroller_pc_rl)
    RelativeLayout addTelecontrollerPcRl;

    @Override
    protected int setLayout() {
        return R.layout.activity_add_telecontroller;
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



    @OnClick({R.id.telecontroller_back_img, R.id.add_telecontroller_air_rl, R.id.add_telecontroller_pc_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.telecontroller_back_img:
                finish();
                break;
            case R.id.add_telecontroller_air_rl:
                openActivity(AddAirTelecontrollerActivity.class);
                break;
            case R.id.add_telecontroller_pc_rl:
                break;
        }
    }
}
