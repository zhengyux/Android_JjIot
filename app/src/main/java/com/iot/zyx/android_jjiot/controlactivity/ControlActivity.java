package com.iot.zyx.android_jjiot.controlactivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.BaseParameter;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.device_managementactivity.AreaGetBean;
import com.iot.zyx.android_jjiot.util.AppUtil.PackageUtil;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class ControlActivity extends BaseActivity {

    @BindView(R.id.control_title_txt)
    TextView controlTitleTxt;
    @BindView(R.id.control_back_img)
    RelativeLayout controlBackImg;
    @BindView(R.id.control_tab)
    TabLayout controlTab;
    @BindView(R.id.control_elist)
    ExpandableListView controlElist;
    ControlLampEListViewAdapter controlLampEListViewAdapter;
    ControlCurtainEListViewAdapter controlCurtainEListViewAdapter;
    ControlSwitchEListViewAdapter controlSwitchEListViewAdapter;
    ControlMultSensorEListViewAdapter controlMultSensorEListViewAdapter;
    ControlApiBean controlApiBean;
    ControlWSBean controlWSBean;
    String activity;
    mWebSocketListener mWebSocketListener;
    WebSocket webSocket;
    @BindView(R.id.control_signal_txt)
    TextView controlSignalTxt;
    @BindView(R.id.control_celsius_txt)
    TextView controlCelsiusTxt;
    @BindView(R.id.control_humidity_txt)
    TextView controlHumidityTxt;
    @BindView(R.id.control_light_txt)
    TextView controlLightTxt;
    @BindView(R.id.control_pm_txt)
    TextView controlPmTxt;
    @BindView(R.id.container_environment)
    LinearLayout containerEnvironment;
    BaseParameter baseParameter ;

    @Override
    protected int setLayout() {
        return R.layout.activity_control;
    }

    @Override
    protected void initView() {
        mWebSocketListener = new mWebSocketListener();
    }

    @Override
    protected void initData() {
        try {
            baseParameter = new BaseParameter();
            activity = getIntent().getExtras().getString("activity");
            if (!activity.isEmpty()) {
                switch (activity) {
                    case "1":

                        getLampDevice();
                        controlTitleTxt.setText("灯控");

                        break;

                    case "2":
                        getcurtainDevice();
                        controlTitleTxt.setText("窗帘");

                        break;

                    case "3":
                        getSwitchDevice();
                        controlTitleTxt.setText("开关");
                        break;

                    case "4":
                        getMultNodeSensorDevice();
                        containerEnvironment.setVisibility(View.GONE);
                        controlTitleTxt.setText("室内环境");
                        break;
                }
            }
            AreaGet();
            webSocket = OkhttpUtil.okHttpWebSocket(API.WSIP, mWebSocketListener);
        } catch (Exception e) {
            toastShort("数据异常");
        }


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webSocket != null) {
            webSocket.cancel();
        }
    }

    @OnClick(R.id.control_back_img)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.control_back_img:
                finish();
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
                    for (int i = 0; i < areaGetBean.getData().getList().size(); i++) {
                        controlTab.addTab(controlTab.newTab().setText(areaGetBean.getData().getList().get(i).getName()));
                    }

                    controlTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {

                            baseParameter.setAreaId(String.valueOf(areaGetBean.getData().getList().get(tab.getPosition()).getId()));

                            switch (activity) {
                                case "1":


                                    getLampDevice();


                                    break;

                                case "2":

                                    getcurtainDevice();


                                    break;

                                case "3":
                                    getSwitchDevice();

                                    break;

                                case "4":

                                    getMultNodeSensorDevice();

                                    break;
                            }
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });

                } catch (Exception e) {
                    toastShort(e.getMessage());
                }

            }
        });

    }

    public void getLampDevice() {

        baseParameter.setType(API.Device.Lamp);

        OkhttpUtil.okHttpPostJson(API.IP + API.DEVICE_GET, GsonUtil.GsonString(baseParameter), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {

                try {
                    controlApiBean = GsonUtil.GsonToBean(response, ControlApiBean.class);


                    if (controlApiBean.getResult().equals("00")) {
                        if (null != controlApiBean.getData().getLight()) {
                            if (!controlApiBean.getData().getLight().isEmpty()) {
                                controlLampEListViewAdapter = new ControlLampEListViewAdapter(ControlActivity.this, controlApiBean);
                                controlElist.setAdapter(controlLampEListViewAdapter);
                                controlElist.setVisibility(View.VISIBLE);
                            }
                        } else {
                            toastShort("暂无设备");
                            controlElist.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        toastShort(controlApiBean.getMessage());
                    }


                } catch (Exception e) {
                    toastShort(e.getMessage());
                }


            }
        });
    }

    public void getcurtainDevice() {
        baseParameter.setType(API.Device.Curtain);

        OkhttpUtil.okHttpPostJson(API.IP + API.DEVICE_GET, GsonUtil.GsonString(baseParameter), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {

                try {
                    controlApiBean = GsonUtil.GsonToBean(response, ControlApiBean.class);
                    if (controlApiBean.getResult().equals("00")) {
                        if (null != controlApiBean.getData().getCurtain()) {
                            if (!controlApiBean.getData().getCurtain().isEmpty()) {
                                controlCurtainEListViewAdapter = new ControlCurtainEListViewAdapter(ControlActivity.this, controlApiBean);
                                controlElist.setAdapter(controlCurtainEListViewAdapter);
                                controlElist.setVisibility(View.VISIBLE);
                            }
                        } else {
                            toastShort("暂无设备");
                            controlElist.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        toastShort(controlApiBean.getMessage());
                    }


                } catch (Exception e) {
                    toastShort(e.getMessage());
                }

            }
        });
    }

    public void getSwitchDevice() {

        baseParameter.setType(API.Device.Switch);

        OkhttpUtil.okHttpPostJson(API.IP + API.DEVICE_GET, GsonUtil.GsonString(baseParameter), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {

                try {
                    controlApiBean = GsonUtil.GsonToBean(response, ControlApiBean.class);

                    if (controlApiBean.getResult().equals("00")) {
                        if (null != controlApiBean.getData().getOnoffSwitch()) {
                            if (!controlApiBean.getData().getOnoffSwitch().isEmpty()) {
                                controlSwitchEListViewAdapter = new ControlSwitchEListViewAdapter(ControlActivity.this, controlApiBean);
                                controlElist.setAdapter(controlSwitchEListViewAdapter);
                                int count = controlElist.getCount();
                                for (int i = 0; i < count; i++) {
                                    controlElist.expandGroup(i);
                                }
                                controlElist.setVisibility(View.VISIBLE);
                            }
                        } else {
                            toastShort("暂无设备");
                            controlElist.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        toastShort(controlApiBean.getMessage());
                    }
                } catch (Exception e) {
                    toastShort(e.getMessage());
                }


            }
        });
    }

    public void getMultNodeSensorDevice() {

        baseParameter.setType(API.Device.MultSensor);

        OkhttpUtil.okHttpPostJson(API.IP + API.DEVICE_GET, GsonUtil.GsonString(baseParameter), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {

                try {
                    controlApiBean = GsonUtil.GsonToBean(response, ControlApiBean.class);

                    if (controlApiBean.getResult().equals("00")) {
                        if (null != controlApiBean.getData().getMultNodeSensor()) {
                            if (!controlApiBean.getData().getMultNodeSensor().isEmpty()) {
                                controlMultSensorEListViewAdapter = new ControlMultSensorEListViewAdapter(ControlActivity.this, controlApiBean);
                                controlElist.setAdapter(controlMultSensorEListViewAdapter);
                                int count = controlElist.getCount();
                                for (int i = 0; i < count; i++) {
                                    controlElist.expandGroup(i);
                                }
                                controlElist.setVisibility(View.VISIBLE);
                            }
                        } else {
                            toastShort("暂无设备");
                            controlElist.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        toastShort(controlApiBean.getMessage());
                    }
                } catch (Exception e) {
                    toastShort(e.getMessage());
                }


            }
        });
    }

    Handler mHandler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {

                if (activity != null) {

                    if (null != controlWSBean.getMsg().get(0).getRSSI()) {
                        controlSignalTxt.setText("RSSI：" + controlWSBean.getMsg().get(0).getRSSI().intValue() + " dBm");
                    }

                    switch (activity) {

                        case "2":

                            for (int i = 0; i < controlApiBean.getData().getCurtain().size(); i++) {
                                if (controlWSBean.getMsg().get(0).getUuid().equals(controlApiBean.getData().getCurtain().get(i).getUuid())) {
                                    controlApiBean.getData().getCurtain().get(i).setMotorPosi(controlWSBean.getMsg().get(0).getMotorPosi());
                                    controlCurtainEListViewAdapter.update(controlApiBean);
                                }
                            }

                            break;

                        case "1":

                            for (int i = 0; i < controlApiBean.getData().getLight().size(); i++) {
                                if (controlWSBean.getMsg().get(0).getUuid().equals(controlApiBean.getData().getLight().get(i).getUuid())) {
                                    if (null != controlWSBean.getMsg().get(0).getValue()) {
                                        controlApiBean.getData().getLight().get(i).setValue(controlWSBean.getMsg().get(0).getValue().intValue());
                                    }
                                    if (null != controlWSBean.getMsg().get(0).getOnoff()) {
                                        controlApiBean.getData().getLight().get(i).setOnoff(controlWSBean.getMsg().get(0).getOnoff().intValue());
                                    }
                                    controlLampEListViewAdapter.update(controlApiBean);
                                }
                            }

                            break;

                        case "3":

                            for (int i = 0; i < controlApiBean.getData().getOnoffSwitch().size(); i++) {
                                for (int j = 0; j < controlApiBean.getData().getOnoffSwitch().get(i).getNode().size(); j++) {
                                    if (controlWSBean.getMsg().get(0).getUuid().equals(controlApiBean.getData().getOnoffSwitch().get(i).getNode().get(j).getUuid())) {

                                        if (null != controlWSBean.getMsg().get(0).getOnoff()) {
                                            controlApiBean.getData().getOnoffSwitch().get(i).getNode().get(j).setOnoff(controlWSBean.getMsg().get(0).getOnoff().intValue());
                                        }
                                        controlSwitchEListViewAdapter.update(controlApiBean);
                                    }
                                }

                            }

                            break;

                        case "4":

                            for (int i = 0; i < controlApiBean.getData().getMultNodeSensor().size(); i++) {
                                for (int j = 0; j < controlApiBean.getData().getMultNodeSensor().get(i).getNode().size(); j++) {
                                    if(controlWSBean.getMsg().get(0).getUuid().equals(controlApiBean.getData().getMultNodeSensor().get(i).getNode().get(j).getUuid())){


                                        if(null!=controlWSBean.getMsg().get(0).getCelsius()){
                                            controlApiBean.getData().getMultNodeSensor().get(i).getNode().get(j).setCelsius(controlWSBean.getMsg().get(0).getCelsius().intValue());
                                        }
                                        if(null!=controlWSBean.getMsg().get(0).getHumidity()){
                                            controlApiBean.getData().getMultNodeSensor().get(i).getNode().get(j).setHumidity(controlWSBean.getMsg().get(0).getHumidity().intValue());
                                        }
                                        if(null!=controlWSBean.getMsg().get(0).getLight()){
                                            controlApiBean.getData().getMultNodeSensor().get(i).getNode().get(j).setLight(controlWSBean.getMsg().get(0).getLight().intValue());
                                        }
                                        controlMultSensorEListViewAdapter.update(controlApiBean);

                                    }
                                }
                            }

                            break;
                    }
                }

            } catch (Exception e) {
                toastShort(e.getMessage());
                Log.e(TAG, "run: " + e.getMessage());
            }

        }
    };


    class mWebSocketListener extends WebSocketListener {

        //建立连接
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            Log.e("WebSocket", "onOpen: ");
            super.onOpen(webSocket, response);

        }

        //收到消息回调
        @Override
        public void onMessage(WebSocket webSocket, String text) {
            Log.e("WebSocket", "onMessage: " + text);

            if (activity != null) {
                switch (activity) {
                    case "2":

                        if (text.contains("1030")) {
                            controlWSBean = GsonUtil.GsonToBean(text, ControlWSBean.class);
                            mHandler.post(runnable);
                        }

                        break;

                    case "1":

                        if (text.contains("设备灯控制")) {
                            controlWSBean = GsonUtil.GsonToBean(text, ControlWSBean.class);
                            mHandler.post(runnable);
                        }

                        break;
                    case "3":

                        if (text.contains("智能开关")) {
                            controlWSBean = GsonUtil.GsonToBean(text, ControlWSBean.class);
                            mHandler.post(runnable);
                        }

                        break;

                    case "4":

                        if (text.contains("温湿度传感器")) {
                            controlWSBean = GsonUtil.GsonToBean(text, ControlWSBean.class);
                            mHandler.post(runnable);
                        }
                        if (text.contains("光照传感器")) {
                            controlWSBean = GsonUtil.GsonToBean(text, ControlWSBean.class);
                            mHandler.post(runnable);
                        }

                        break;
                }
            }

        }

        //准备关闭
        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            Log.e("WebSocket", "onClosing: ");
            super.onClosing(webSocket, code, reason);
        }

        //已经关闭
        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            Log.e("WebSocket", "onClosed: ");
            super.onClosed(webSocket, code, reason);

        }

        //连接失败发送失败
        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            Log.e("WebSocket", "onFailure: " + t.getMessage());
            super.onFailure(webSocket, t, response);
        }
    }
}
