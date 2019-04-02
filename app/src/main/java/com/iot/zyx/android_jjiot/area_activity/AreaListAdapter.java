package com.iot.zyx.android_jjiot.area_activity;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iot.zyx.android_jjiot.BaseApplication;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.air_conditioningactivity.RemoteListBean;
import com.iot.zyx.android_jjiot.device_managementactivity.AreaGetBean;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.List;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2019/4/2 15:06
 * 修改人：xuan
 * 修改时间：2019/4/2 15:06
 * 修改备注：
 */
public class AreaListAdapter extends BaseQuickAdapter<AreaGetBean.DataBean.ListBean,BaseViewHolder> {
    public AreaListAdapter(int layoutResId, @Nullable List<AreaGetBean.DataBean.ListBean> data) {
        super(layoutResId, data);
        ScreenAdapterTools.getInstance().loadView(LayoutInflater.from(BaseApplication.getContext()).inflate(layoutResId, null));
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaGetBean.DataBean.ListBean item) {
        helper.setText(R.id.switchover_host_recycler_item_txt,item.getName());
    }
}
