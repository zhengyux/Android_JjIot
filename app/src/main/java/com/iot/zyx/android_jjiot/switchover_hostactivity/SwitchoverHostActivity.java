package com.iot.zyx.android_jjiot.switchover_hostactivity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseActivity;
import com.iot.zyx.android_jjiot.BaseApplication;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.util.AppUtil.SharedPreferencesUtils;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;
import com.iot.zyx.android_jjiot.util.network.UDPClient;
import com.iot.zyx.android_jjiot.util.widget.RxDialogEditSureCancel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class SwitchoverHostActivity extends BaseActivity {


    @BindView(R.id.switchover_host_back_img)
    RelativeLayout switchoverHostBackImg;
    @BindView(R.id.switchover_host_recycler)
    RecyclerView switchoverHostRecycler;
    @BindView(R.id.add_host_img)
    ImageView addHostImg;
    SwitchoverHostBean switchoverHostBean;
    SwitchoverHostContentAdapter switchoverHostContentAdapter;
    @BindView(R.id.switchHost_tab)
    TabLayout switchHostTab;
    String lanIp;

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

        new Thread(new Runnable() {
            @Override
            public void run() {
                UDPClient udpClient = new UDPClient();

            }
        }).start();

        getHost();

    }

    @Override
    protected void initListener() {
        switchHostTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getText().toString()){
                    case "外网主机":
                        getHost();
                        break;
                    case "内网主机":

                        if(null!=API.Lanip){
                            getLanHost();
                        }else {
                            toastShort("没有内网主机");
                        }

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    void getLanHost() {

  //      try {
            switchoverHostBean = new SwitchoverHostBean();
            SwitchoverHostBean.DataBean dataBean = new SwitchoverHostBean.DataBean();
            List<SwitchoverHostBean.DataBean.ListBean> listBeans = new ArrayList<>();
            SwitchoverHostBean.DataBean.ListBean listBean = new SwitchoverHostBean.DataBean.ListBean();
            listBean.setName(API.Lanip);
            listBeans.add(listBean);
            dataBean.setList(listBeans);
            switchoverHostBean.setData(dataBean);

            switchoverHostContentAdapter = new SwitchoverHostContentAdapter(R.layout.switchover_host_recycler_item, switchoverHostBean.getData().getList());
            switchoverHostRecycler.setAdapter(switchoverHostContentAdapter);
            switchoverHostContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            API.IP = API.Lanip;
                            SharedPreferencesUtils.setParam(BaseApplication.getContext(),"lan",API.Lanip);
                            toastShort("切换成功");
                            finish();
                        }
                    });


  //      } catch (Exception e) {
  //          Log.e(TAG, "getLanHost: "+e.getMessage() );
  //      }
    }

    void getHost() {

        API.IP=API.setip2();

        OkhttpUtil.okHttpGet(API.IP+API.GET_GATEWAY, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                toastShort("服务器连接失败");
            }

            @Override
            public void onResponse(String response) {

                try {
                    switchoverHostBean = GsonUtil.GsonToBean(response, SwitchoverHostBean.class);
                    if("00".equals(switchoverHostBean.getResult())){
                        if (!switchoverHostBean.getData().getList().isEmpty()) {
                            switchoverHostContentAdapter = new SwitchoverHostContentAdapter(R.layout.switchover_host_recycler_item, switchoverHostBean.getData().getList());
                            switchoverHostRecycler.setAdapter(switchoverHostContentAdapter);
                            switchoverHostContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    SharedPreferencesUtils.setParam(SwitchoverHostActivity.this, "productkey", switchoverHostBean.getData().getList().get(position).getProductkey());
                                    SharedPreferencesUtils.setParam(SwitchoverHostActivity.this, "devicename", switchoverHostBean.getData().getList().get(position).getDevicename());
                                    API.Device.productkey = switchoverHostBean.getData().getList().get(position).getProductkey();
                                    API.Device.devicename = switchoverHostBean.getData().getList().get(position).getDevicename();
                                    SharedPreferencesUtils.setParam(BaseApplication.getContext(),"lan","null");
                                    API.IP=API.setip();
                                    toastShort("切换成功");
                                    finish();
                                }
                            });
                        }
                    }else {
                        toastShort(switchoverHostBean.getMessage());
                    }

                } catch (Exception e) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
