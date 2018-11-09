package com.iot.zyx.android_jjiot.switchover_hostactivity;

import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.util.widget.RxDialogEditSureCancel;

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
                showAddHostdialog();
                break;
        }
    }

    public void showAddHostdialog() {
        final RxDialogEditSureCancel rxDialogEditSureCancel = new RxDialogEditSureCancel(SwitchoverHostActivity.this);//提示弹窗
        rxDialogEditSureCancel.setTitle("请输入主机id");
        rxDialogEditSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogEditSureCancel.cancel();
            }
        });
        rxDialogEditSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogEditSureCancel.cancel();
            }
        });
        rxDialogEditSureCancel.show();
    }

}
