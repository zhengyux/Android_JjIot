package com.iot.zyx.android_jjiot.area_activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.BaseRespone;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.device_managementactivity.AreaGetBean;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AreaActivity extends BaseActivity {


    @BindView(R.id.area_back_img)
    RelativeLayout areaBackImg;
    @BindView(R.id.area_ok_txt)
    TextView areaOkTxt;
    @BindView(R.id.area_area_edt)
    EditText areaAreaEdt;
    @BindView(R.id.area_num_edt)
    EditText areaNumEdt;
    AddAreaBean addAreaBean;

    @Override
    protected int setLayout() {
        return R.layout.activity_area;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        addAreaBean=new AddAreaBean();
    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.area_back_img, R.id.area_ok_txt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.area_back_img:
                finish();
                break;
            case R.id.area_ok_txt:

                addAreaBean.setName(areaAreaEdt.getText().toString());
                addAreaBean.setEncode(areaNumEdt.getText().toString());
                showLoading();
                addArea(addAreaBean);


                break;
        }
    }

    public void addArea(AddAreaBean addAreaBean) {

        OkhttpUtil.okHttpPostJson(API.IP + API.ADD_AREA, GsonUtil.GsonString(addAreaBean), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                closeLoading();
                toastShort("网络异常");
            }

            @Override
            public void onResponse(String response) {
                closeLoading();
                try {
                    BaseRespone baseRespone = GsonUtil.GsonToBean(response, BaseRespone.class);
                    if (baseRespone.getResult().equals("00")) {
                        toastShort("保存成功");
                        finish();
                    } else {
                        toastShort(baseRespone.getMessage());
                    }
                } catch (Exception e) {
                    toastShort(e.getMessage());
                }

            }
        });

    }


}
