package com.iot.zyx.android_jjiot.add_zigbeeactivity;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iot.zyx.android_jjiot.BaseApplication;
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
public class AddZigBeeWSAdapter extends BaseQuickAdapter<AddZigBeeWSBean.MsgBean,BaseViewHolder>{
    public AddZigBeeWSAdapter(int layoutResId, @Nullable List<AddZigBeeWSBean.MsgBean> data) {
        super(layoutResId, data);
        ScreenAdapterTools.getInstance().loadView(LayoutInflater.from(BaseApplication.getContext()).inflate(layoutResId,null));
    }

    @Override
    protected void convert(BaseViewHolder helper, AddZigBeeWSBean.MsgBean item) {

    }
}
