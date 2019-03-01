package com.iot.zyx.android_jjiot.homeactivity;

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
 * 创建时间：2018/10/31 11:19
 * 修改人：xuan
 * 修改时间：2018/10/31 11:19
 * 修改备注：
 */
public class HomeContentAdapter extends BaseQuickAdapter<HomeContentBean.EmployeesBean,BaseViewHolder>{


    public HomeContentAdapter(int layoutResId, @Nullable List<HomeContentBean.EmployeesBean> data) {
        super(layoutResId, data);
        ScreenAdapterTools.getInstance().loadView(mLayoutInflater.from(BaseApplication.getContext()).inflate(layoutResId,null));
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeContentBean.EmployeesBean item) {

        switch (helper.getLayoutPosition()){
            case 0:
                helper.setText(R.id.home_content_recycler_item_txt,"灯光");
                helper.setImageResource(R.id.home_content_recycler_item_img,R.mipmap.icon_index_dg);
                break;
            case 1:
                helper.setText(R.id.home_content_recycler_item_txt,"开关");
                helper.setImageResource(R.id.home_content_recycler_item_img,R.mipmap.icon_znkg);
                break;
            case 2:
                helper.setText(R.id.home_content_recycler_item_txt,"TV");
                helper.setImageResource(R.id.home_content_recycler_item_img,R.mipmap.icon_index_tv);
                break;
            case 3:
                helper.setText(R.id.home_content_recycler_item_txt,"空调");
                helper.setImageResource(R.id.home_content_recycler_item_img,R.mipmap.icon_index_kt);
                break;
            case 4:
                helper.setText(R.id.home_content_recycler_item_txt,"窗帘");
                helper.setImageResource(R.id.home_content_recycler_item_img,R.mipmap.icon_index_cl);
                break;
            case 5:
                helper.setText(R.id.home_content_recycler_item_txt,"室内环境");
                helper.setImageResource(R.id.home_content_recycler_item_img,R.mipmap.icon_index_snhj);
                break;
        }

    }
}
