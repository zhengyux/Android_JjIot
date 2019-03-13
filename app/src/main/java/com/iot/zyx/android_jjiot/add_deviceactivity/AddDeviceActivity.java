package com.iot.zyx.android_jjiot.add_deviceactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.BaseRespone;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.add_zigbeeactivity.AddZigBeeAPIBean;
import com.iot.zyx.android_jjiot.add_zigbeeactivity.AddZigBeeActivity;
import com.iot.zyx.android_jjiot.device_managementactivity.AreaGetBean;
import com.iot.zyx.android_jjiot.device_managementactivity.AreaSpnAdapter;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AddDeviceActivity extends BaseActivity {

    @BindView(R.id.add_device_hold_txt)
    TextView addDeviceHoldTxt;
    @BindView(R.id.add_device_devicename_edt)
    EditText addDeviceDevicenameEdt;
    AddZigBeeAPIBean.DataBean.ListBean bean;
    @BindView(R.id.add_device_circuit_recycler)
    RecyclerView addDeviceCircuitRecycler;
    AddDeviceAdapter addDeviceAdapter;
    @BindView(R.id.add_device_area_spn)
    Spinner addDeviceAreaSpn;
    @BindView(R.id.add_device_area_txt)
    TextView addDeviceAreaTxt;
    int from;

    @Override
    protected int setLayout() {
        return R.layout.activity_add_device;
    }

    @Override
    protected void initView() {

        addDeviceCircuitRecycler.setLayoutManager(new LinearLayoutManager(AddDeviceActivity.this));


    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        from = intent.getIntExtra("from", 0);
        bean = GsonUtil.GsonToBean(intent.getExtras().getString("Device"), AddZigBeeAPIBean.DataBean.ListBean.class);
        addDeviceDevicenameEdt.setText(bean.getName());
        addDeviceAreaTxt.setText(bean.getAreaId());
        if (null != bean.getNode()) {
            if (!bean.getNode().isEmpty()) {
                addDeviceAdapter = new AddDeviceAdapter(R.layout.add_device_circuit_recycler_item, bean.getNode());
                addDeviceCircuitRecycler.setAdapter(addDeviceAdapter);
            }
        }

        AreaGet();

    }

    @Override
    protected void initListener() {

    }


    @OnClick(R.id.add_device_hold_txt)
    public void onViewClicked() {
        showLoading();
        bean.setName(addDeviceDevicenameEdt.getText().toString());
        bean.setAreaId(addDeviceAreaTxt.getText().toString());
        if (from == 1) {
            update();
        } else {
            hold();
        }

    }


    public void update() {
        OkhttpUtil.okHttpPostJson(API.IP + API.DEVICE_UPDATE, GsonUtil.GsonString(bean), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                closeLoading();
                toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {
                closeLoading();
                try {
                    BaseRespone baseRespone = GsonUtil.GsonToBean(response, BaseRespone.class);
                    if (baseRespone.getResult().equals("00")) {
                        toastShort("修改成功");
                        finish();
                    } else {
                        toastShort(baseRespone.getMessage());
                        finish();
                    }
                } catch (Exception e) {

                }

            }
        });
    }

    public void hold() {
        OkhttpUtil.okHttpPostJson(API.IP + API.DEVICE_HOLD, GsonUtil.GsonString(bean), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                closeLoading();
                toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {
                closeLoading();
                try {
                    BaseRespone baseRespone = GsonUtil.GsonToBean(response, BaseRespone.class);
                    if (baseRespone.getResult().equals("00")) {
                        toastShort("保存成功");
                        openActivityAndCloseThis(AddZigBeeActivity.class);
                    } else {
                        toastShort(baseRespone.getMessage());
                        finish();
                    }
                } catch (Exception e) {

                }

            }
        });
    }

    public void AreaGet() {

        OkhttpUtil.okHttpGet(API.IP + API.GET_AREA, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败！");
            }

            @Override
            public void onResponse(String response) {

                try {
                    final AreaGetBean areaGetBean = GsonUtil.GsonToBean(response, AreaGetBean.class);
                    if ("00".equals(areaGetBean.getResult())) {
                        if (null != areaGetBean.getData().getList()) {
                            addDeviceAreaSpn.setAdapter(new AreaSpnAdapter(AddDeviceActivity.this, areaGetBean));
                            addDeviceAreaSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    addDeviceAreaTxt.setText(String.valueOf(areaGetBean.getData().getList().get(position).getId()));
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        } else {
                            toastLong("暂无区域请添加区域");
                        }

                    } else {
                        toastShort(areaGetBean.getMessage());
                    }

                } catch (Exception e) {
                    toastShort(e.getMessage());
                }

            }
        });

    }

}
