package com.iot.zyx.android_jjiot.controlactivity;

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
import com.iot.zyx.android_jjiot.R;
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
    ControlApiBean controlApiBean;
    String activity;
    mWebSocketListener mWebSocketListener;
    WebSocket webSocket;
    ControlWSBean controlWSBean;
    @BindView(R.id.control_signal_txt)
    TextView controlSignalTxt;

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
            activity = getIntent().getExtras().getString("activity");
            if (!activity.isEmpty()) {
                switch (activity) {
                    case "lamp":

                        getLampDevice();
                        controlTitleTxt.setText("灯控");

                        break;

                    case "curtanin":
                        getcurtainDevice();
                        controlTitleTxt.setText("窗帘");

                        break;

                    case "switch":
                        getSwitchDevice();
                        controlTitleTxt.setText("开关");
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

        OkhttpUtil.okHttpGet(API.GET_AREA, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败！");
            }

            @Override
            public void onResponse(String response) {

                try {
                    AreaGetBean areaGetBean = GsonUtil.GsonToBean(response, AreaGetBean.class);
                    for (int i = 0; i < areaGetBean.getData().getList().size(); i++) {
                        controlTab.addTab(controlTab.newTab().setText(areaGetBean.getData().getList().get(i).getName()));
                    }

                } catch (Exception e) {
                    toastShort(e.getMessage());
                }

            }
        });

    }

    public void getLampDevice() {
        BaseParameter baseParameter = new BaseParameter();
        baseParameter.setType(API.Device.Lamp);

        OkhttpUtil.okHttpPostJson(API.DEVICE_GET, GsonUtil.GsonString(baseParameter), new CallBackUtil.CallBackString() {
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
                            }
                        } else {
                            toastShort("暂无设备");
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
        BaseParameter baseParameter = new BaseParameter();
        baseParameter.setType(API.Device.Curtain);

        OkhttpUtil.okHttpPostJson(API.DEVICE_GET, GsonUtil.GsonString(baseParameter), new CallBackUtil.CallBackString() {
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
                            }
                        } else {
                            toastShort("暂无设备");
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
        BaseParameter baseParameter = new BaseParameter();
        baseParameter.setType(API.Device.Switch);

        OkhttpUtil.okHttpPostJson(API.DEVICE_GET, GsonUtil.GsonString(baseParameter), new CallBackUtil.CallBackString() {
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
                                for (int i = 0; i <count ; i++) {
                                    controlElist.expandGroup(i);
                                }
                            }
                        } else {
                            toastShort("暂无设备");
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

                    if(null !=controlWSBean.getMsg().get(0).getRSSI()){
                        controlSignalTxt.setText("RSSI："+controlWSBean.getMsg().get(0).getRSSI().intValue()+" dBm");
                    }

                    switch (activity) {

                        case "curtanin":

                            for (int i = 0; i < controlApiBean.getData().getCurtain().size(); i++) {
                                if (controlWSBean.getMsg().get(0).getUuid().equals(controlApiBean.getData().getCurtain().get(i).getUuid())) {
                                    controlApiBean.getData().getCurtain().get(i).setMotorPosi(controlWSBean.getMsg().get(0).getMotorPosi());
                                    controlCurtainEListViewAdapter.update(controlApiBean);
                                }
                            }

                            break;

                        case "lamp":

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

                        case "switch":

                            for (int i = 0; i < controlApiBean.getData().getOnoffSwitch().size(); i++) {
                                for (int j = 0; j <controlApiBean.getData().getOnoffSwitch().get(i).getNode().size() ; j++) {
                                    if (controlWSBean.getMsg().get(0).getUuid().equals(controlApiBean.getData().getOnoffSwitch().get(i).getNode().get(j).getUuid())) {

                                        if (null != controlWSBean.getMsg().get(0).getOnoff()) {
                                            controlApiBean.getData().getOnoffSwitch().get(i).getNode().get(j).setOnoff(controlWSBean.getMsg().get(0).getOnoff().intValue());
                                        }
                                        controlSwitchEListViewAdapter.update(controlApiBean);
                                    }
                                }

                            }

                            break;
                    }
                }

            } catch (Exception e) {
                toastShort(e.getMessage());
                Log.e(TAG, "run: "+e.getMessage() );
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

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
                    case "curtanin":

                        if (text.contains("1030")) {
                            controlWSBean = GsonUtil.GsonToBean(text, ControlWSBean.class);
                            mHandler.post(runnable);
                        }

                        break;

                    case "lamp":

                        if (text.contains("设备灯控制")) {
                            controlWSBean = GsonUtil.GsonToBean(text, ControlWSBean.class);
                            mHandler.post(runnable);
                        }

                        break;
                    case "switch":

                        if (text.contains("智能开关")) {
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
