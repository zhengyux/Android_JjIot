package com.iot.zyx.android_jjiot.area_activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.BaseRespone;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.add_air_telecontrolleractivity.AddAirTelecontrollerActivity;
import com.iot.zyx.android_jjiot.device_managementactivity.AreaGetBean;
import com.iot.zyx.android_jjiot.device_managementactivity.AreaSpnAdapter;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AreaActivity extends BaseActivity {


    @BindView(R.id.area_back_img)
    RelativeLayout areaBackImg;
    @BindView(R.id.area_ok_txt)
    TextView areaOkTxt;
    @BindView(R.id.area_area_edt)
    EditText areaAreaEdt;
    @BindView(R.id.area_num_edt)
    EditText areaNumEdt;
    AddAreaBean addAreaBean;
    @BindView(R.id.area_list_rec)
    RecyclerView areaListRec;
    AreaListAdapter areaListAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_area;
    }

    @Override
    protected void initView() {
        areaListRec.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        addAreaBean = new AddAreaBean();
        AreaGet();
    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.area_back_img, R.id.area_ok_txt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.area_back_img:
                finish();
                break;
            case R.id.area_ok_txt:

                addAreaBean.setName(areaAreaEdt.getText().toString());
                addAreaBean.setEncode(areaNumEdt.getText().toString());
                showLoading();
                addArea(addAreaBean);


                break;
        }
    }

    public void addArea(AddAreaBean addAreaBean) {

        OkhttpUtil.okHttpPostJson(API.IP + API.ADD_AREA, GsonUtil.GsonString(addAreaBean), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                closeLoading();
                toastShort("网络异常");
            }

            @Override
            public void onResponse(String response) {
                closeLoading();
                try {
                    BaseRespone baseRespone = GsonUtil.GsonToBean(response, BaseRespone.class);
                    if (baseRespone.getResult().equals("00")) {
                        toastShort("保存成功");
                        AreaGet();
                        areaAreaEdt.setText("");
                        areaNumEdt.setText("");
                    } else {
                        toastShort(baseRespone.getMessage());
                    }
                } catch (Exception e) {
                    toastShort(e.getMessage());
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
                            areaListAdapter = new AreaListAdapter(R.layout.switchover_host_recycler_item,areaGetBean.getData().getList());
                            areaListRec.setAdapter(areaListAdapter);
                            areaListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                                @Override
                                public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {

                                    Snackbar.make(view, "确认删除" + areaGetBean.getData().getList().get(position).getName() + "?", Snackbar.LENGTH_SHORT)
                                            .setAction("确定", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    DeleteArea(GsonUtil.GsonString(areaGetBean.getData().getList().get(position)));
                                                }
                                            }).show();

                                    return true;
                                }
                            });
                        }
                    }else {
                        toastShort(areaGetBean.getMessage());
                    }


                } catch (Exception e) {

                }
            }
        });


        }


    public void DeleteArea(String str) {
        OkhttpUtil.okHttpPostJson(API.IP+API.DELETE_AREA, str, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败！");
            }

            @Override
            public void onResponse(String response) {
                try {
                    BaseRespone baseRespone = GsonUtil.GsonToBean(response, BaseRespone.class);
                    if (baseRespone.getResult().equals("00")) {
                        toastShort("删除成功");
                        AreaGet();
                    } else {
                        toastShort(baseRespone.getMessage());
                    }
                    AreaGet();
                }catch (Exception e){
                    toastShort("删除数据错误");
                }

            }
        });
    }


}

