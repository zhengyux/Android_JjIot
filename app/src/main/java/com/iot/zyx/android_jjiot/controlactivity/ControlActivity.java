package com.iot.zyx.android_jjiot.controlactivity;

import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.BaseParameter;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.add_zigbeeactivity.AddZigBeeWSBean;
import com.iot.zyx.android_jjiot.device_managementactivity.AreaGetBean;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.WebSocket;

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
    ControlCurtainEListViewAdapter controlCurtainEListViewAdapter;
    ControlApiBean controlApiBean;
    String activity;
    mWebSocketListener mWebSocketListener;
    WebSocket webSocket;
    ControlWSBean controlWSBean;

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
        activity = getIntent().getExtras().getString("activity");
        switch (activity){
            case "lamp":

                getLampDevice();

                break;

            case "curtanin":

                getcurtainDevice();

                break;
        }
        AreaGet();
        webSocket = OkhttpUtil.okHttpWebSocket(API.WSIP,mWebSocketListener);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(webSocket!=null){
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

    public void AreaGet(){

        OkhttpUtil.okHttpGet(API.GET_AREA, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败！");
            }

            @Override
            public void onResponse(String response) {

                try{
                    AreaGetBean areaGetBean = GsonUtil.GsonToBean(response,AreaGetBean.class);
                    for (int i = 0; i < areaGetBean.getData().getList().size(); i++) {
                        controlTab.addTab(controlTab.newTab().setText(areaGetBean.getData().getList().get(i).getName()));
                    }

                }catch (Exception e){
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


                    controlApiBean = GsonUtil.GsonToBean(response,ControlApiBean.class);
                    if(!controlApiBean.getData().getList().isEmpty()){
                        controlLampEListViewAdapter = new ControlLampEListViewAdapter(ControlActivity.this,controlApiBean);
                        controlElist.setAdapter(controlLampEListViewAdapter);
                    }



                }catch (Exception e){

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
                    controlApiBean = GsonUtil.GsonToBean(response,ControlApiBean.class);
                    if(!controlApiBean.getData().getList().isEmpty()){
                        controlCurtainEListViewAdapter = new ControlCurtainEListViewAdapter(ControlActivity.this,controlApiBean);
                        controlElist.setAdapter(controlCurtainEListViewAdapter);
                    }

                }catch (Exception e){

                }


            }
        });
    }


    Handler mHandler = new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            try {

                switch (activity){

                    case "curtanin":

                        for (int i = 0; i < controlApiBean.getData().getList().size(); i++) {
                            if(controlWSBean.getMsg().get(0).getUuid().equals(controlApiBean.getData().getList().get(i).getUuid())){
                                controlApiBean.getData().getList().get(i).setMotorPosi(controlWSBean.getMsg().get(0).getMotorPosi());
                                controlCurtainEListViewAdapter.update(controlApiBean);
                            }
                        }

                        break;


                }


            }catch (Exception e){

            }

        }
    };

    class mWebSocketListener extends okhttp3.WebSocketListener{

        //建立连接
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            Log.e("WebSocket", "onOpen: " );
            super.onOpen(webSocket, response);

        }

        //收到消息回调
        @Override
        public void onMessage(WebSocket webSocket, String text) {
            Log.e("WebSocket", "onMessage: "+text );

            switch (activity){
                case "curtanin":

                    if(text.contains("1030")){
                        controlWSBean = GsonUtil.GsonToBean(text,ControlWSBean.class);
                        mHandler.post(runnable);
                    }

                    break;
            }

        }

        //准备关闭
        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            Log.e("WebSocket", "onClosing: " );
            super.onClosing(webSocket, code, reason);
        }

        //已经关闭
        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            Log.e("WebSocket", "onClosed: " );
            super.onClosed(webSocket, code, reason);

        }

        //连接失败发送失败
        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            Log.e("WebSocket", "onFailure: " +t.getMessage());
            super.onFailure(webSocket, t, response);
        }
    }
}
