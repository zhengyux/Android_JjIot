package com.iot.zyx.android_jjiot.air_conditioningactivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.BaseRespone;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.add_air_telecontrolleractivity.AirControlBean;
import com.iot.zyx.android_jjiot.homeactivity.HomeActivity;
import com.iot.zyx.android_jjiot.switchover_hostactivity.SwitchoverHostContentAdapter;
import com.iot.zyx.android_jjiot.util.AppUtil.SharedPreferencesUtils;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;
import com.iot.zyx.android_jjiot.util.widget.RxListDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AirConditioningActivity extends BaseActivity {

    @BindView(R.id.air_conditioning_back_img)
    RelativeLayout airConditioningBackImg;
    @BindView(R.id.air_conditioning_shut_down_txt)
    TextView airConditioningShutDownTxt;
    @BindView(R.id.air_wd_txt)
    TextView airWdTxt;
    @BindView(R.id.air_fl_txt)
    TextView airFlTxt;
    @BindView(R.id.air_zl_img)
    ImageView airZlImg;
    @BindView(R.id.air_fr_img)
    ImageView airFrImg;
    @BindView(R.id.air_cs_img)
    ImageView airCsImg;
    @BindView(R.id.air_fx_img)
    ImageView airFxImg;
    @BindView(R.id.air_zd_img)
    ImageView airZdImg;
    @BindView(R.id.air_ds_img)
    ImageView airDsImg;
    @BindView(R.id.air_wdjia_img)
    ImageView airWdjiaImg;
    @BindView(R.id.air_wdjian_img)
    ImageView airWdjianImg;
    @BindView(R.id.air_fljia_img)
    ImageView airFljiaImg;
    @BindView(R.id.air_fljian_img)
    ImageView airFljianImg;
    RemoteListBean remoteListBean;
    RemoteListtAdapter remoteListtAdapter;
    AirControlBean airControlBean;
    boolean isShutDown = true;
    boolean isFx = true;
    int wd = 26;
    int fl = 2;

    @Override
    protected int setLayout() {
        return R.layout.activity_air_conditioning;
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

    @OnClick({R.id.air_conditioning_back_img, R.id.air_conditioning_shut_down_txt, R.id.air_zl_img, R.id.air_fr_img, R.id.air_cs_img, R.id.air_fx_img, R.id.air_zd_img, R.id.air_ds_img, R.id.air_wdjia_img, R.id.air_wdjian_img, R.id.air_fljia_img, R.id.air_fljian_img})
    public void onViewClicked(View view) {
        showLoading();
        switch (view.getId()) {
            case R.id.air_conditioning_back_img:
                finish();
                break;
            case R.id.air_conditioning_shut_down_txt:

                airControlBean.setControl("050002");
                if(isShutDown){
                    airControlBean.setValue(1);
                    isShutDown = false;
                    airConditioningShutDownTxt.setText("关机");
                }else {
                    airControlBean.setValue(0);
                    isShutDown = true;
                    airConditioningShutDownTxt.setText("开机");
                }
                controlAir(airControlBean);

                break;
            case R.id.air_zl_img:

                airControlBean.setControl("050003");
                airControlBean.setValue(1);
                Glide.with(this).load(R.mipmap.icon_zl_down).into(airZlImg);
                Glide.with(this).load(R.mipmap.icon_fr_default).into(airFrImg);
                Glide.with(this).load(R.mipmap.icon_cs_default).into(airCsImg);
                Glide.with(this).load(R.mipmap.icon_zd_default).into(airZdImg);
                controlAir(airControlBean);

                break;
            case R.id.air_fr_img:

                airControlBean.setControl("050003");
                airControlBean.setValue(4);
                Glide.with(this).load(R.mipmap.icon_fr_down).into(airFrImg);
                Glide.with(this).load(R.mipmap.icon_zl_default).into(airZlImg);
                Glide.with(this).load(R.mipmap.icon_cs_default).into(airCsImg);
                Glide.with(this).load(R.mipmap.icon_zd_default).into(airZdImg);
                controlAir(airControlBean);

                break;
            case R.id.air_cs_img:

                airControlBean.setControl("050003");
                airControlBean.setValue(2);
                Glide.with(this).load(R.mipmap.icon_cs_down).into(airCsImg);
                Glide.with(this).load(R.mipmap.icon_fr_default).into(airFrImg);
                Glide.with(this).load(R.mipmap.icon_zl_default).into(airZlImg);
                Glide.with(this).load(R.mipmap.icon_zd_default).into(airZdImg);
                controlAir(airControlBean);

                break;
            case R.id.air_fx_img:

                airControlBean.setControl("050006");
                if(isFx){
                    airControlBean.setValue(0);
                    isFx = false;
                    Glide.with(this).load(R.mipmap.icon_fx_down).into(airFxImg);
                }else {
                    airControlBean.setValue(1);
                    isFx = true;
                    Glide.with(this).load(R.mipmap.icon_fx_default).into(airFxImg);
                }
                controlAir(airControlBean);

                break;
            case R.id.air_zd_img:

                airControlBean.setControl("050003");
                airControlBean.setValue(0);
                Glide.with(this).load(R.mipmap.icon_zd_down).into(airZdImg);
                Glide.with(this).load(R.mipmap.icon_cs_default).into(airCsImg);
                Glide.with(this).load(R.mipmap.icon_fr_default).into(airFrImg);
                Glide.with(this).load(R.mipmap.icon_zl_default).into(airZlImg);
                controlAir(airControlBean);

                break;
            case R.id.air_ds_img:
                closeLoading();
                break;
            case R.id.air_wdjia_img:


                if(wd<31){
                    if(wd==31){

                    }else {
                        wd++;
                    }
                    airControlBean.setControl("050004");
                    airControlBean.setValue(wd);
                    airWdTxt.setText(String.valueOf(wd));

                    controlAir(airControlBean);
                }

                break;
            case R.id.air_wdjian_img:

                if(wd>16){
                    if(wd==16){

                    }else {
                        wd--;
                    }
                    airControlBean.setControl("050004");
                    airControlBean.setValue(wd);
                    airWdTxt.setText(String.valueOf(wd));

                    controlAir(airControlBean);
                }

                break;
            case R.id.air_fljia_img:

                if(fl<3){
                    if(fl==3){

                    }else {
                        fl++;
                    }
                    airControlBean.setControl("050005");
                    airControlBean.setValue(fl);
                    airFlTxt.setText("风力："+fl+"级");

                    controlAir(airControlBean);
                }

                break;
            case R.id.air_fljian_img:

                if(fl>1){
                    if(fl==1){

                    }else {
                        fl--;
                    }
                    airControlBean.setControl("050005");
                    airControlBean.setValue(fl);
                    airFlTxt.setText("风力："+fl+"级");

                    controlAir(airControlBean);
                }
                break;
        }
    }

    public void controlAir(AirControlBean airControlBean){
        OkhttpUtil.okHttpPostJson(API.IP + API.CONTROL_AIR, GsonUtil.GsonString(airControlBean), new CallBackUtil.CallBackString() {
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

    public void getRemoteList(){
        OkhttpUtil.okHttpPostJson(API.IP + API.REMOTE_GET,GsonUtil.GsonString(null), new CallBackUtil.CallBackString() {
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
                            final RxListDialog rxListDialog = new RxListDialog(AirConditioningActivity.this);
                            rxListDialog.getRecyclerView().setLayoutManager(new LinearLayoutManager(AirConditioningActivity.this));
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

}
