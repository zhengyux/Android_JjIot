package com.iot.zyx.android_jjiot.loginactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.homeactivity.HomeActivity;
import com.iot.zyx.android_jjiot.switchover_hostactivity.SwitchoverHostActivity;
import com.iot.zyx.android_jjiot.util.AppUtil.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_Switchover_host_txt)
    TextView loginSwitchoverHostTxt;
    @BindView(R.id.login_user_name_edt)
    EditText loginUserNameEdt;
    @BindView(R.id.login_password_edt)
    EditText loginPasswordEdt;
    @BindView(R.id.login_remember_password_chk)
    CheckBox loginRememberPasswordChk;
    @BindView(R.id.login_sign_in_img)
    ImageView loginSignInImg;

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
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

    @OnClick({R.id.login_Switchover_host_txt, R.id.login_sign_in_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_Switchover_host_txt:
                openActivity(SwitchoverHostActivity.class);
                break;
            case R.id.login_sign_in_img:
                if("null".equals(SharedPreferencesUtils.getParam(this,"devicename","null"))){
                    toastShort("请先选择主机");
                }else {
                    openActivityAndCloseThis(HomeActivity.class);
                }

                break;
        }
    }
}

