package com.iot.zyx.android_jjiot.televisionactivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.BaseParameter;
import com.iot.zyx.android_jjiot.BaseRespone;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.add_air_telecontrolleractivity.AirControlBean;
import com.iot.zyx.android_jjiot.air_conditioningactivity.AirConditioningActivity;
import com.iot.zyx.android_jjiot.air_conditioningactivity.RemoteListBean;
import com.iot.zyx.android_jjiot.air_conditioningactivity.RemoteListtAdapter;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;
import com.iot.zyx.android_jjiot.util.widget.RxListDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class TelevisionActivity extends BaseActivity {

    @BindView(R.id.television_back_img)
    RelativeLayout televisionBackImg;
    @BindView(R.id.television_open_img)
    ImageView televisionOpenImg;
    @BindView(R.id.television_close_img)
    ImageView televisionCloseImg;
    RemoteListBean remoteListBean;
    RemoteListtAdapter remoteListtAdapter;
    AirControlBean airControlBean;

    @Override
    protected int setLayout() {
        return R.layout.activity_television;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        airControlBean = new AirControlBean();
        getRemoteList();
    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.television_back_img, R.id.television_open_img, R.id.television_close_img})
    public void onViewClicked(View view) {
        showLoading();
        switch (view.getId()) {
            case R.id.television_back_img:
                finish();
                break;
            case R.id.television_open_img:

                airControlBean.setValue(0);
                controlAllIn(airControlBean);

                break;
            case R.id.television_close_img:

                airControlBean.setValue(0);
                controlAllIn(airControlBean);

                break;
        }
    }

    public void getRemoteList(){
        BaseParameter baseParameter = new BaseParameter();
        baseParameter.setType("0501");
        OkhttpUtil.okHttpPostJson(API.IP + API.REMOTE_GET, GsonUtil.GsonString(baseParameter), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

                toastShort("网络异常");

            }

            @Override
            public void onResponse(String response) {

                try {

                    remoteListBean = GsonUtil.GsonToBean(response,RemoteListBean.class);
                    if("00".equals(remoteListBean.getResult())){
                        if(!remoteListBean.getData().getList().isEmpty()){
                            final RxListDialog rxListDialog = new RxListDialog(TelevisionActivity.this);
                            rxListDialog.getRecyclerView().setLayoutManager(new LinearLayoutManager(TelevisionActivity.this));
                            remoteListtAdapter = new RemoteListtAdapter(R.layout.switchover_host_recycler_item, remoteListBean.getData().getList());
                            rxListDialog.getRecyclerView().setAdapter(remoteListtAdapter);
                            rxListDialog.show();
                            remoteListtAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                                    airControlBean.setUuid(remoteListBean.getData().getList().get(position).getDeviceUuid());
                                    toastShort("选择遥控成功");
                                    rxListDialog.cancel();
                                }
                            });

                        }else {
                            toastShort("请添加遥控器");
                        }


                    }else {
                        toastShort(remoteListBean.getMessage());
                    }


                }catch (Exception e){
                    toastShort(e.getMessage());
                }


            }
        });
    }

    public void controlAllIn(AirControlBean airControlBean){

        OkhttpUtil.okHttpPostJson(API.IP + API.CONTROL_ALLIN, GsonUtil.GsonString(airControlBean), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                closeLoading();
                toastShort("网络连接异常");
            }

            @Override
            public void onResponse(String response) {
                closeLoading();
                try {
                    BaseRespone baseRespone = GsonUtil.GsonToBean(response, BaseRespone.class);
                    if (baseRespone.getResult().equals("00")) {
                        toastShort("操作成功");
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
