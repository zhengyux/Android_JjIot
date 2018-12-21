package com.iot.zyx.android_jjiot.switchover_hostactivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.util.AppUtil.SharedPreferencesUtils;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;
import com.iot.zyx.android_jjiot.util.widget.RxDialogEditSureCancel;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class SwitchoverHostActivity extends BaseActivity {


    @BindView(R.id.switchover_host_back_img)
    ImageView switchoverHostBackImg;
    @BindView(R.id.switchover_host_recycler)
    RecyclerView switchoverHostRecycler;
    @BindView(R.id.add_host_img)
    ImageView addHostImg;
    SwitchoverHostBean switchoverHostBean;
    SwitchoverHostContentAdapter switchoverHostContentAdapter;



    @Override
    protected int setLayout() {
        return R.layout.activity_switchover_host;
    }

    @Override
    protected void initView() {
        switchoverHostRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void initData() {

        getHost();

    }

    @Override
    protected void initListener() {



    }

    void getHost(){
        OkhttpUtil.okHttpGet(API.GET_GATEWAY, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {

                try {
                    switchoverHostBean = GsonUtil.GsonToBean(response,SwitchoverHostBean.class);
                    if(!switchoverHostBean.getData().getList().isEmpty()){
                        switchoverHostContentAdapter = new SwitchoverHostContentAdapter(R.layout.switchover_host_recycler_item,switchoverHostBean.getData().getList());
                        switchoverHostRecycler.setAdapter(switchoverHostContentAdapter);
                        switchoverHostContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                SharedPreferencesUtils.setParam(SwitchoverHostActivity.this,"productkey",switchoverHostBean.getData().getList().get(position).getProductkey());
                                SharedPreferencesUtils.setParam(SwitchoverHostActivity.this,"devicename",switchoverHostBean.getData().getList().get(position).getDevicename());
                                API.Device.productkey=switchoverHostBean.getData().getList().get(position).getProductkey();
                                API.Device.devicename=switchoverHostBean.getData().getList().get(position).getDevicename();
                                toastShort("切换成功");
                                finish();
                            }
                        });

                    }

                }catch (Exception e){

                }

            }
        });
    }

    @OnClick({R.id.switchover_host_back_img, R.id.add_host_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.switchover_host_back_img:
                finish();
                break;
            case R.id.add_host_img:
                showAddHostdialog();
                break;
        }
    }

    public void showAddHostdialog() {
        final RxDialogEditSureCancel rxDialogEditSureCancel = new RxDialogEditSureCancel(SwitchoverHostActivity.this);//提示弹窗
        rxDialogEditSureCancel.setTitle("请输入主机id");
        rxDialogEditSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogEditSureCancel.cancel();
            }
        });
        rxDialogEditSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogEditSureCancel.cancel();
            }
        });
        rxDialogEditSureCancel.show();
    }

}
