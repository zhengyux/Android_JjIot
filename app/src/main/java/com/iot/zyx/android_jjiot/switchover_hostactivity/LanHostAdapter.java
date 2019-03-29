package com.iot.zyx.android_jjiot.switchover_hostactivity;

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
 * 创建时间：2018/11/5 15:54
 * 修改人：xuan
 * 修改时间：2018/11/5 15:54
 * 修改备注：
 */
public class LanHostAdapter extends BaseQuickAdapter<SwitchoverHostBean,BaseViewHolder> {


    public LanHostAdapter(int layoutResId, @Nullable List<SwitchoverHostBean> data) {
        super(layoutResId, data);
        ScreenAdapterTools.getInstance().loadView(LayoutInflater.from(BaseApplication.getContext()).inflate(layoutResId, null));
    }

    @Override
    protected void convert(BaseViewHolder helper, SwitchoverHostBean item) {

        helper.setText(R.id.switchover_host_recycler_item_txt,item.getIp());

    }
}