package com.iot.zyx.android_jjiot.add_zigbeeactivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.add_deviceactivity.AddDeviceActivity;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.BaseParameter;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;
import com.iot.zyx.android_jjiot.util.widget.RxListDialog;
import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class AddZigBeeActivity extends BaseActivity {


    @BindView(R.id.add_zigbee_back_img)
    RelativeLayout addZigbeeBackImg;
    @BindView(R.id.add_zigbee_time_txt)
    TextView addZigbeeTimeTxt;
    @BindView(R.id.add_zigbee_begin_img)
    ImageView addZigbeeBeginImg;
    private AddZigBeeWSBean addZigBeeWSBean;
    private AddZigBeeAPIBean addZigBeeAPIBean;
    private AddZigBeeAPIAdapter addZigBeeAPIAdapter;
    mWebSocketListener mWebSocketListener;
    WebSocket webSocket;

    @Override
    protected int setLayout() {
        return R.layout.activity_add_zig_bee;
    }

    @Override
    protected void initView() {
        mWebSocketListener = new mWebSocketListener();
    }

    @Override
    protected void initData() {

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

    @OnClick({R.id.add_zigbee_back_img, R.id.add_zigbee_begin_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_zigbee_back_img:
                finish();
                break;
            case R.id.add_zigbee_begin_img:
                countDownTimer.start();
                addZigBee();
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

    public void addZigBee(){
        BaseParameter baseParameter = new BaseParameter();

        OkhttpUtil.okHttpPostJson(API.IP+API.DEVICE_ACCESS, GsonUtil.GsonString(baseParameter), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    addZigBeeAPIBean = GsonUtil.GsonToBean(response,AddZigBeeAPIBean.class);
                    if(!addZigBeeAPIBean.getData().getList().isEmpty()){
                        final RxListDialog rxListDialog = new RxListDialog(AddZigBeeActivity.this);
                        rxListDialog.getRecyclerView().setLayoutManager(new LinearLayoutManager(AddZigBeeActivity.this));
                        addZigBeeAPIAdapter = new AddZigBeeAPIAdapter(R.layout.dialog_addzigbee_recycler_item,addZigBeeAPIBean.getData().getList());
                        rxListDialog.getRecyclerView().setAdapter(addZigBeeAPIAdapter);
                        rxListDialog.show();
                        addZigBeeAPIAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Bundle bundle = new Bundle();
                                bundle.putString("Device",GsonUtil.GsonString(addZigBeeAPIBean.getData().getList().get(position)));
                                openActivityAndCloseThis(AddDeviceActivity.class,bundle);
                            }
                        });
                    }else {
                        webSocket = OkhttpUtil.okHttpWebSocket(API.WSIP,mWebSocketListener);
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

                if(!addZigBeeWSBean.getMsg().isEmpty()){

                    toastShort("发现新设备！");
                    addZigBee();
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

            if(text.contains("1010")){
                addZigBeeWSBean = GsonUtil.GsonToBean(text,AddZigBeeWSBean.class);
                mHandler.post(runnable);
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
