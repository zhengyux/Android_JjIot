package com.iot.zyx.android_jjiot.add_zigbeeactivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddZigBeeActivity extends BaseActivity {


    @BindView(R.id.add_zigbee_back_img)
    ImageView addZigbeeBackImg;
    @BindView(R.id.add_zigbee_time_txt)
    TextView addZigbeeTimeTxt;
    @BindView(R.id.add_zigbee_begin_img)
    ImageView addZigbeeBeginImg;

    @Override
    protected int setLayout() {
        return R.layout.activity_add_zig_bee;
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


    @OnClick({R.id.add_zigbee_back_img, R.id.add_zigbee_begin_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_zigbee_back_img:
                finish();
                break;
            case R.id.add_zigbee_begin_img:
                countDownTimer.start();
                addZigbeeBeginImg.setEnabled(false);
                break;
        }
    }
    CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            String value = String.valueOf((int) (millisUntilFinished / 1000));
            addZigbeeTimeTxt.setText(value);
        }

        @Override
        public void onFinish() {
            addZigbeeTimeTxt.setText("10");
            addZigbeeBeginImg.setEnabled(true);
        }
    };
}
