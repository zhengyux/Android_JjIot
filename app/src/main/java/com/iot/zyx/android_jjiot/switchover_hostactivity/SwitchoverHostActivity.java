package com.iot.zyx.android_jjiot.switchover_hostactivity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.R;

import butterknife.BindView;
import butterknife.OnClick;

public class SwitchoverHostActivity extends BaseActivity {


    @BindView(R.id.switchover_host_back_img)
    ImageView switchoverHostBackImg;
    @BindView(R.id.switchover_host_recycler)
    RecyclerView switchoverHostRecycler;
    @BindView(R.id.add_host_img)
    ImageView addHostImg;

    @Override
    protected int setLayout() {
        return R.layout.activity_switchover_host;
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


    @OnClick({R.id.switchover_host_back_img, R.id.add_host_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.switchover_host_back_img:
                finish();
                break;
            case R.id.add_host_img:
                break;
        }
    }
}
