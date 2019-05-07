package com.iot.zyx.android_jjiot.device_managementactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iot.zyx.android_jjiot.R;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/12/3 15:23
 * 修改人：xuan
 * 修改时间：2018/12/3 15:23
 * 修改备注：
 */
public class AreaSpnAdapter extends BaseAdapter {
    private Context mcontext;
    private AreaGetBean areaGetBean;


    public AreaSpnAdapter(Context mcontext, AreaGetBean areaGetBean) {
        this.mcontext = mcontext;
        this.areaGetBean = areaGetBean;
    }

    @Override
    public int getCount() {
        return areaGetBean.getData().getList().size();
    }

    @Override
    public Object getItem(int position) {
        return areaGetBean.getData().getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            convertView = LayoutInflater.from(mcontext).inflate(R.layout.device_management_spn_item, null);
            ScreenAdapterTools.getInstance().loadView(convertView);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.deviceManagementSpnText.setText(areaGetBean.getData().getList().get(position).getName());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.device_management_spn_text)
        TextView deviceManagementSpnText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
