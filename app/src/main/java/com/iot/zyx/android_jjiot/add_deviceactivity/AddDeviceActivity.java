package com.iot.zyx.android_jjiot.add_deviceactivity;

import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.add_zigbeeactivity.AddZigBeeAPIBean;
import com.iot.zyx.android_jjiot.BaseRespone;
import com.iot.zyx.android_jjiot.add_zigbeeactivity.AddZigBeeActivity;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;
import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class AddDeviceActivity extends BaseActivity {

    @BindView(R.id.add_device_hold_txt)
    TextView addDeviceHoldTxt;
    @BindView(R.id.add_device_devicename_edt)
    EditText addDeviceDevicenameEdt;
    AddZigBeeAPIBean.DataBean.ListBean bean;
    AddDeviceParameter addDeviceParameter;

    @Override
    protected int setLayout() {
        return R.layout.activity_add_device;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        Intent intent = getIntent();

        bean =GsonUtil.GsonToBean(intent.getExtras().getString("Device"), AddZigBeeAPIBean.DataBean.ListBean.class);
        addDeviceDevicenameEdt.setText(bean.getName());
        addDeviceParameter = new AddDeviceParameter();
        addDeviceParameter.setAreaId("10002");
        addDeviceParameter.setDeviceName(bean.getDevicename());
        addDeviceParameter.setProductKey(bean.getProductkey());
        addDeviceParameter.setUuid(bean.getUuid());


    }

    @Override
    protected void initListener() {

    }



    @OnClick(R.id.add_device_hold_txt)
    public void onViewClicked() {
        showLoading();
        addDeviceParameter.setName(addDeviceDevicenameEdt.getText().toString());
        hold();
    }
    public void hold(){
        OkhttpUtil.okHttpPostJson(API.DEVICE_HOLD, GsonUtil.GsonString(addDeviceParameter), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                closeLoading();
                toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {
                closeLoading();
                try{
                    BaseRespone baseRespone = GsonUtil.GsonToBean(response, BaseRespone.class);
                    if(baseRespone.getResult().equals("00")){
                        toastShort("保存成功");
                        openActivityAndCloseThis(AddZigBeeActivity.class);
                    }else {
                        toastShort(baseRespone.getMessage());
                        finish();
                    }
                }catch (Exception e){

                }

            }
        });
    }
}
