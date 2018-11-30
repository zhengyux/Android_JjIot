package com.iot.zyx.android_jjiot.controlactivity;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.BaseParameter;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class ControlActivity extends BaseActivity {

    @BindView(R.id.control_title_txt)
    TextView controlTitleTxt;
    @BindView(R.id.control_back_img)
    ImageView controlBackImg;
    @BindView(R.id.control_tab)
    TabLayout controlTab;
    @BindView(R.id.control_elist)
    ExpandableListView controlElist;
    ControlLampEListViewAdapter controlLampEListViewAdapter;
    ControlLampApiBean controlLampApiBean;

    @Override
    protected int setLayout() {
        return R.layout.activity_control;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getDevice();
    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.control_back_img)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.control_back_img:
                finish();
                break;
        }
    }

    public void getDevice() {
        OkhttpUtil.okHttpPostJson(API.DEVICE_GET, GsonUtil.GsonString(new BaseParameter()), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                    toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {

                try {

                    controlLampApiBean = GsonUtil.GsonToBean(response,ControlLampApiBean.class);
                    controlLampEListViewAdapter = new ControlLampEListViewAdapter(ControlActivity.this,controlLampApiBean);
                    controlElist.setAdapter(controlLampEListViewAdapter);


                }catch (Exception e){

                }


            }
        });
    }


}
