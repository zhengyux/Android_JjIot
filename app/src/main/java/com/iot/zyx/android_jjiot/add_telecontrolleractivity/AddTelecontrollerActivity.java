package com.iot.zyx.android_jjiot.add_telecontrolleractivity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.BaseRespone;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.add_air_telecontrolleractivity.AddAirTelecontrollerActivity;
import com.iot.zyx.android_jjiot.air_conditioningactivity.RemoteListBean;
import com.iot.zyx.android_jjiot.air_conditioningactivity.RemoteListtAdapter;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AddTelecontrollerActivity extends BaseActivity {

    @BindView(R.id.add_telecontroller_rec)
    RecyclerView recyclerView;
    @BindView(R.id.telecontroller_back_img)
    RelativeLayout telecontrollerBackImg;
    @BindView(R.id.add_telecontroller_air_rl)
    RelativeLayout addTelecontrollerAirRl;
    @BindView(R.id.add_telecontroller_pc_rl)
    RelativeLayout addTelecontrollerPcRl;
    RemoteListBean remoteListBean;
    RemoteListtAdapter remoteListtAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_add_telecontroller;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        getRemoteList();
    }

    @Override
    protected void initListener() {

    }



    @OnClick({R.id.telecontroller_back_img, R.id.add_telecontroller_air_rl, R.id.add_telecontroller_pc_rl})
    public void onViewClicked(View view) {
                 Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.telecontroller_back_img:
                finish();
                break;
            case R.id.add_telecontroller_air_rl:

                bundle.putString("activity", "Air");
                openActivity(AddAirTelecontrollerActivity.class,bundle);
                break;
            case R.id.add_telecontroller_pc_rl:
                bundle.putString("activity", "AllIn");
                openActivity(AddAirTelecontrollerActivity.class,bundle);
                break;
        }
    }


    public void getRemoteList(){
        OkhttpUtil.okHttpPostJson(API.IP + API.REMOTE_GET, GsonUtil.GsonString(null), new CallBackUtil.CallBackString() {
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
                            remoteListtAdapter = new RemoteListtAdapter(R.layout.switchover_host_recycler_item, remoteListBean.getData().getList());
                            recyclerView.setAdapter(remoteListtAdapter);
                            remoteListtAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                                @Override
                                public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {

                                    Snackbar.make(view, "确认删除" + remoteListBean.getData().getList().get(position).getName() + "?", Snackbar.LENGTH_SHORT)
                                            .setAction("确定", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    DeleteRemote(GsonUtil.GsonString(remoteListBean.getData().getList().get(position)));
                                                }
                                            }).show();

                                    return true;
                                }
                            });
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

    public void DeleteRemote(String str) {
        OkhttpUtil.okHttpPostJson(API.IP+API.DELETE_REMOTE, str, new CallBackUtil.CallBackString() {
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
                    } else {
                        toastShort(baseRespone.getMessage());
                    }
                    getRemoteList();
                }catch (Exception e){
                    toastShort("删除数据错误");
                }

            }
        });
    }


}
