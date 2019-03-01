package com.iot.zyx.android_jjiot.homeactivity;

import android.support.annotation.Nullable;

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
 * 创建时间：2018/10/31 11:19
 * 修改人：xuan
 * 修改时间：2018/10/31 11:19
 * 修改备注：
 */
public class HomeContentCzAdapter extends BaseQuickAdapter<HomeContentBean.EmployeesBean,BaseViewHolder>{


    public HomeContentCzAdapter(int layoutResId, @Nullable List<HomeContentBean.EmployeesBean> data) {
        super(layoutResId, data);
        ScreenAdapterTools.getInstance().loadView(mLayoutInflater.from(BaseApplication.getContext()).inflate(layoutResId,null));
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeContentBean.EmployeesBean item) {



    }
}
