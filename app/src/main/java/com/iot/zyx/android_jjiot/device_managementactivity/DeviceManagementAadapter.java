package com.iot.zyx.android_jjiot.device_managementactivity;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iot.zyx.android_jjiot.BaseApplication;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.add_zigbeeactivity.AddZigBeeAPIBean;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.List;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/11/29 9:56
 * 修改人：xuan
 * 修改时间：2018/11/29 9:56
 * 修改备注：
 */
public class DeviceManagementAadapter extends BaseQuickAdapter<AddZigBeeAPIBean.DataBean.ListBean,BaseViewHolder>{
    public DeviceManagementAadapter(int layoutResId, @Nullable List<AddZigBeeAPIBean.DataBean.ListBean> data) {
        super(layoutResId, data);
        ScreenAdapterTools.getInstance().loadView(LayoutInflater.from(BaseApplication.getContext()).inflate(layoutResId,null));
    }

    @Override
    protected void convert(BaseViewHolder helper, AddZigBeeAPIBean.DataBean.ListBean item) {

        helper.setText(R.id.device_management_recycler_item_name_txt,item.getName());

    }
}
