package com.iot.zyx.android_jjiot.add_zigbeeactivity;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iot.zyx.android_jjiot.BaseApplication;
import com.iot.zyx.android_jjiot.R;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.List;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/11/29 13:55
 * 修改人：xuan
 * 修改时间：2018/11/29 13:55
 * 修改备注：
 */
public class AddZigBeeAPIAdapter extends BaseQuickAdapter<AddZigBeeAPIBean.DataBean.ListBean,BaseViewHolder>{


    public AddZigBeeAPIAdapter(int layoutResId, @Nullable List<AddZigBeeAPIBean.DataBean.ListBean> data) {
        super(layoutResId, data);
        ScreenAdapterTools.getInstance().loadView(LayoutInflater.from(BaseApplication.getContext()).inflate(layoutResId,null));
    }

    @Override
    protected void convert(BaseViewHolder helper, AddZigBeeAPIBean.DataBean.ListBean item) {
        helper.setText(R.id.dialog_addzigbee_recycler_txt,item.getName());
    }
}
