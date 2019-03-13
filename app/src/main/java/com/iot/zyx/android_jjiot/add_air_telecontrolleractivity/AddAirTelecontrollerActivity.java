package com.iot.zyx.android_jjiot.add_air_telecontrolleractivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.BaseParameter;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.add_deviceactivity.AddDeviceActivity;
import com.iot.zyx.android_jjiot.controlactivity.ControlActivity;
import com.iot.zyx.android_jjiot.controlactivity.ControlApiBean;
import com.iot.zyx.android_jjiot.controlactivity.ControlLampEListViewAdapter;
import com.iot.zyx.android_jjiot.device_managementactivity.AreaGetBean;
import com.iot.zyx.android_jjiot.device_managementactivity.AreaSpnAdapter;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AddAirTelecontrollerActivity extends BaseActivity {


    @BindView(R.id.add_air_back_img)
    RelativeLayout addAirBackImg;
    @BindView(R.id.add_air_ok_txt)
    TextView addAirOkTxt;
    @BindView(R.id.add_air_devicename_edt)
    EditText addAirDevicenameEdt;
    @BindView(R.id.add_air_area_txt)
    TextView addAirAreaTxt;
    @BindView(R.id.add_air_area_spn)
    Spinner addAirAreaSpn;
    @BindView(R.id.add_air_remotecontrol_txt)
    TextView addAirRemotecontrolTxt;
    @BindView(R.id.add_air_remotecontrol_spn)
    Spinner addAirRemotecontrolSpn;
    @BindView(R.id.add_air_txt)
    TextView addAirTxt;
    @BindView(R.id.add_air_ln)
    LinearLayout addAirLn;
    AirBean airBean;
    private List<AirBean.DataBean.ListBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    @Override
    protected int setLayout() {
        return R.layout.activity_add_air_telecontroller;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        AreaGet();
        getDevice();
        getAir();
    }

    @Override
    protected void initListener() {

    }



    @OnClick({R.id.add_air_back_img, R.id.add_air_ok_txt, R.id.add_air_ln})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_air_back_img:
                finish();
                break;
            case R.id.add_air_ok_txt:
                break;
            case R.id.add_air_ln:
                showOptionsPickerView();
                break;
        }
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
                            addAirAreaSpn.setAdapter(new AreaSpnAdapter(AddAirTelecontrollerActivity.this, areaGetBean));
                            addAirAreaSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    addAirAreaTxt.setText(String.valueOf(areaGetBean.getData().getList().get(position).getId()));
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


    public void getDevice() {

        BaseParameter baseParameter = new BaseParameter();
        baseParameter.setType(API.Device.RemoteControlDevice);

        OkhttpUtil.okHttpPostJson(API.IP + API.DEVICE_GET, GsonUtil.GsonString(baseParameter), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {

//                try {
//                    controlApiBean = GsonUtil.GsonToBean(response, ControlApiBean.class);
//
//                    if (controlApiBean.getResult().equals("00")) {
//                        if (null != controlApiBean.getData().getLight()) {
//                            if (!controlApiBean.getData().getLight().isEmpty()) {
//                                controlLampEListViewAdapter = new ControlLampEListViewAdapter(ControlActivity.this, controlApiBean);
//                                controlElist.setAdapter(controlLampEListViewAdapter);
//                                controlElist.setVisibility(View.VISIBLE);
//                            }
//                        } else {
//                            toastShort("暂无设备");
//                            controlElist.setVisibility(View.INVISIBLE);
//                        }
//                    } else {
//                        toastShort(controlApiBean.getMessage());
//                    }
//
//
//                } catch (Exception e) {
//                    toastShort(e.getMessage());
//                }
//
            }
        });
    }

    public void getAir(){

        OkhttpUtil.okHttpPost(API.IP + API.AIR_GET, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {

                airBean = GsonUtil.GsonToBean(response,AirBean.class);
                if("00".equals(airBean.getResult())){

                    setPickerData(airBean);


                }else {
                    toastShort(airBean.getMessage());
                }

            }
        });

    }

    public void setPickerData(AirBean airBean){

        options1Items = airBean.getData().getList();
        for (int i = 0; i < airBean.getData().getList().size(); i++) {
            ArrayList<String> cityList = new ArrayList<>();

            for (int c = 0; c < airBean.getData().getList().get(i).getList().size(); c++) {
                String cityName = airBean.getData().getList().get(i).getList().get(c);
                cityList.add(cityName);

            }

            options2Items.add(cityList);

        }


    }

    public void showOptionsPickerView(){


        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(AddAirTelecontrollerActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText()
//                        + options2Items.get(options1).get(option2)
//                        + options3Items.get(options1).get(option2).get(options3).getPickerViewText();
//                tvOptions.setText(tx);
            }
        }).build();
        pvOptions.setPicker(options1Items,options2Items);
        pvOptions.show();

    }





}
