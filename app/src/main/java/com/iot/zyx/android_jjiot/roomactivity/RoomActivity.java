package com.iot.zyx.android_jjiot.roomactivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.BaseParameter;
import com.iot.zyx.android_jjiot.BaseRespone;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.controlactivity.ControlApiBean;
import com.iot.zyx.android_jjiot.controlactivity.ControlWSBean;
import com.iot.zyx.android_jjiot.device_managementactivity.AreaGetBean;
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

public class RoomActivity extends BaseActivity {


    @BindView(R.id.room_back_img)
    RelativeLayout roomBackImg;
    @BindView(R.id.room_tab)
    TabLayout roomTab;
    @BindView(R.id.room_celsius_txt)
    TextView roomCelsiusTxt;
    @BindView(R.id.room_humidity_txt)
    TextView roomHumidityTxt;
    @BindView(R.id.room_light_txt)
    TextView roomLightTxt;
    @BindView(R.id.room_lamp_elist)
    ExpandableListView roomLampElist;
    @BindView(R.id.room_curtain_elist)
    ExpandableListView roomCurtainElist;
    @BindView(R.id.room_switch_elist)
    ExpandableListView roomSwitchElist;
    @BindView(R.id.room_sk_txt)
    TextView roomSkTxt;
    @BindView(R.id.room_xk_txt)
    TextView roomXkTxt;

    mWebSocketListener mWebSocketListener;
    WebSocket webSocket;
    BaseParameter baseParameter;
    ControlLampEListViewAdapter controlLampEListViewAdapter;
    ControlCurtainEListViewAdapter controlCurtainEListViewAdapter;
    ControlSwitchEListViewAdapter controlSwitchEListViewAdapter;
    ControlApiBean controlLampApiBean;
    ControlApiBean controlCurtainApiBean;
    ControlApiBean controlSwitchApiBean;
    ControlWSBean controlLampWSBean;
    ControlWSBean controlCurtainWSBean;
    ControlWSBean controlSwitchWSBean;
    ControlWSBean controlEnvironmentWSBean;
    String type;


    @Override
    protected int setLayout() {
        return R.layout.activity_room;
    }

    @Override
    protected void initView() {
        mWebSocketListener = new mWebSocketListener();
    }

    @Override
    protected void initData() {
        baseParameter = new BaseParameter();
        webSocket = OkhttpUtil.okHttpWebSocket(API.WSIP, mWebSocketListener);
        AreaGet();
        getLampDevice();
        getcurtainDevice();
        getSwitchDevice();
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

    @OnClick({R.id.room_back_img,R.id.room_sk_txt, R.id.room_xk_txt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.room_back_img:
                finish();
                break;
            case R.id.room_sk_txt:
                setSk();
                break;
            case R.id.room_xk_txt:
                setXk();
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
                        roomTab.addTab(roomTab.newTab().setText(areaGetBean.getData().getList().get(i).getName()));
                    }

                    roomTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {

                            baseParameter.setAreaId(String.valueOf(areaGetBean.getData().getList().get(tab.getPosition()).getId()));

                            getLampDevice();

                            getcurtainDevice();

                            getSwitchDevice();


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
                    controlLampApiBean = GsonUtil.GsonToBean(response, ControlApiBean.class);


                    if (controlLampApiBean.getResult().equals("00")) {
                        if (null != controlLampApiBean.getData().getLight()) {
                            if (!controlLampApiBean.getData().getLight().isEmpty()) {
                                controlLampEListViewAdapter = new ControlLampEListViewAdapter(RoomActivity.this, controlLampApiBean);
                                roomLampElist.setAdapter(controlLampEListViewAdapter);
                            }
                            roomLampElist.setVisibility(View.VISIBLE);
                        } else {
                            roomLampElist.setVisibility(View.GONE);
                        }
                    } else {
                        toastShort(controlLampApiBean.getMessage());
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
                    controlCurtainApiBean = GsonUtil.GsonToBean(response, ControlApiBean.class);
                    if (controlCurtainApiBean.getResult().equals("00")) {
                        if (null != controlCurtainApiBean.getData().getCurtain()) {
                            if (!controlCurtainApiBean.getData().getCurtain().isEmpty()) {
                                controlCurtainEListViewAdapter = new ControlCurtainEListViewAdapter(RoomActivity.this, controlCurtainApiBean);
                                roomCurtainElist.setAdapter(controlCurtainEListViewAdapter);
                                roomCurtainElist.setVisibility(View.VISIBLE);
                            }
                        } else {
                            roomCurtainElist.setVisibility(View.GONE);
                        }
                    } else {
                        toastShort(controlCurtainApiBean.getMessage());
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
                    controlSwitchApiBean = GsonUtil.GsonToBean(response, ControlApiBean.class);

                    if (controlSwitchApiBean.getResult().equals("00")) {
                        if (null != controlSwitchApiBean.getData().getOnoffSwitch()) {
                            if (!controlSwitchApiBean.getData().getOnoffSwitch().isEmpty()) {
                                controlSwitchEListViewAdapter = new ControlSwitchEListViewAdapter(RoomActivity.this, controlSwitchApiBean);
                                roomSwitchElist.setAdapter(controlSwitchEListViewAdapter);
                                int count = roomSwitchElist.getCount();
                                for (int i = 0; i < count; i++) {
                                    roomSwitchElist.expandGroup(i);
                                }
                            }
                            roomSwitchElist.setVisibility(View.VISIBLE);
                        } else {
                            roomSwitchElist.setVisibility(View.GONE);
                        }
                    } else {
                        toastShort(controlSwitchApiBean.getMessage());
                    }
                } catch (Exception e) {
                    toastShort(e.getMessage());
                }


            }
        });
    }

    public void setSk(){
        OkhttpUtil.okHttpPost(API.IP + API.SET_SK, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("网络连接失败");
            }

            @Override
            public void onResponse(String response) {
                    try {

                    }catch (Exception e){

                    }
            }
        });
    }

    public void setXk(){
        OkhttpUtil.okHttpPost(API.IP + API.SET_XK, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("网络连接失败");
            }

            @Override
            public void onResponse(String response) {

            }
        });
    }


    Handler mHandler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {

                if (type != null) {

                    switch (type) {

                        case "设备窗帘控制":

                            for (int i = 0; i < controlCurtainApiBean.getData().getCurtain().size(); i++) {
                                if (controlCurtainWSBean.getMsg().get(0).getUuid().equals(controlCurtainApiBean.getData().getCurtain().get(i).getUuid())) {
                                    controlCurtainApiBean.getData().getCurtain().get(i).setMotorPosi(controlCurtainWSBean.getMsg().get(0).getMotorPosi());
                                    controlCurtainEListViewAdapter.update(controlCurtainApiBean);
                                }
                            }

                            break;

                        case "设备灯控制":

                            for (int i = 0; i < controlLampApiBean.getData().getLight().size(); i++) {
                                if (controlLampWSBean.getMsg().get(0).getUuid().equals(controlLampApiBean.getData().getLight().get(i).getUuid())) {
                                    if (null != controlLampWSBean.getMsg().get(0).getValue()) {
                                        controlLampApiBean.getData().getLight().get(i).setValue(controlLampWSBean.getMsg().get(0).getValue().intValue());
                                    }
                                    if (null != controlLampWSBean.getMsg().get(0).getOnoff()) {
                                        controlLampApiBean.getData().getLight().get(i).setOnoff(controlLampWSBean.getMsg().get(0).getOnoff().intValue());
                                    }
                                    controlLampEListViewAdapter.update(controlLampApiBean);
                                }
                            }

                            break;

                        case "智能开关":

                            for (int i = 0; i < controlSwitchApiBean.getData().getOnoffSwitch().size(); i++) {
                                for (int j = 0; j < controlSwitchApiBean.getData().getOnoffSwitch().get(i).getNode().size(); j++) {
                                    if (controlSwitchWSBean.getMsg().get(0).getUuid().equals(controlSwitchApiBean.getData().getOnoffSwitch().get(i).getNode().get(j).getUuid())) {

                                        if (null != controlSwitchWSBean.getMsg().get(0).getOnoff()) {
                                            controlSwitchApiBean.getData().getOnoffSwitch().get(i).getNode().get(j).setOnoff(controlSwitchWSBean.getMsg().get(0).getOnoff().intValue());
                                        }
                                        controlSwitchEListViewAdapter.update(controlSwitchApiBean);
                                    }
                                }

                            }

                            break;

                        case "多功能传感器":


                            if (null != controlEnvironmentWSBean.getMsg().get(0).getCelsius()) {
                                roomCelsiusTxt.setText(controlEnvironmentWSBean.getMsg().get(0).getCelsius().toString() + "°C");
                            }
                            if (null != controlEnvironmentWSBean.getMsg().get(0).getHumidity()) {
                                roomHumidityTxt.setText(controlEnvironmentWSBean.getMsg().get(0).getHumidity().toString() + "%");
                            }
                            if (null != controlEnvironmentWSBean.getMsg().get(0).getLight()) {
                                roomLightTxt.setText(controlEnvironmentWSBean.getMsg().get(0).getLight().toString() + "Lux");
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

            BaseRespone baseRespone = GsonUtil.GsonToBean(text, BaseRespone.class);
            if ("设备窗帘控制".equals(baseRespone.getDesignation())) {
                controlCurtainWSBean = GsonUtil.GsonToBean(text, ControlWSBean.class);
                type = "设备窗帘控制";
                mHandler.post(runnable);
            }

            if ("设备灯控制".equals(baseRespone.getDesignation())) {
                controlLampWSBean = GsonUtil.GsonToBean(text, ControlWSBean.class);
                type = "设备灯控制";
                mHandler.post(runnable);
            }


            if ("智能开关".equals(baseRespone.getDesignation())) {
                controlSwitchWSBean = GsonUtil.GsonToBean(text, ControlWSBean.class);
                type = "智能开关";
                mHandler.post(runnable);
            }

            if ("多功能传感器".equals(baseRespone.getDesignation())) {
                controlEnvironmentWSBean = GsonUtil.GsonToBean(text, ControlWSBean.class);
                type = "多功能传感器";
                mHandler.post(runnable);
            }
            if ("光照传感器".equals(baseRespone.getDesignation())) {
                controlEnvironmentWSBean = GsonUtil.GsonToBean(text, ControlWSBean.class);
                type = "多功能传感器";
                mHandler.post(runnable);
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
