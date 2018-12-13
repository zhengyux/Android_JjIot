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
import com.iot.zyx.android_jjiot.device_managementactivity.AreaGetBean;
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
    ControlCurtainEListViewAdapter controlCurtainEListViewAdapter;
    ControlApiBean controlApiBean;

    @Override
    protected int setLayout() {
        return R.layout.activity_control;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String activity = getIntent().getExtras().getString("activity");
        switch (activity){
            case "lamp":
                getLampDevice();
                break;

            case "curtanin":

                getcurtainDevice();

                break;
        }
        AreaGet();

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
        baseParameter.setType("1");

        OkhttpUtil.okHttpPostJson(API.DEVICE_GET, GsonUtil.GsonString(baseParameter), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                    toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {

                try {

                    controlApiBean = GsonUtil.GsonToBean(response,ControlApiBean.class);
                    controlLampEListViewAdapter = new ControlLampEListViewAdapter(ControlActivity.this,controlApiBean);
                    controlElist.setAdapter(controlLampEListViewAdapter);


                }catch (Exception e){

                }


            }
        });
    }

    public void getcurtainDevice() {
        BaseParameter baseParameter = new BaseParameter();
        baseParameter.setType("8");

        OkhttpUtil.okHttpPostJson(API.DEVICE_GET, GsonUtil.GsonString(baseParameter), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {

                try {
                    controlApiBean = GsonUtil.GsonToBean(response,ControlApiBean.class);
                    controlCurtainEListViewAdapter = new ControlCurtainEListViewAdapter(ControlActivity.this,controlApiBean);
                    controlElist.setAdapter(controlCurtainEListViewAdapter);


                }catch (Exception e){

                }


            }
        });
    }


}
