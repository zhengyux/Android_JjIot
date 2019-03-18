package com.iot.zyx.android_jjiot.add_air_telecontrolleractivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.controlactivity.ControlApiBean;
import com.iot.zyx.android_jjiot.device_managementactivity.AreaGetBean;
import com.iot.zyx.android_jjiot.device_managementactivity.AreaSpnAdapter;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2019/3/14 11:03
 * 修改人：xuan
 * 修改时间：2019/3/14 11:03
 * 修改备注：
 */
public class RemoteAdapter extends BaseAdapter {
    private Context mcontext;
    private ControlApiBean controlApiBean;


    public RemoteAdapter(Context mcontext, ControlApiBean controlApiBean) {
        this.mcontext = mcontext;
        this.controlApiBean = controlApiBean;
    }

    @Override
    public int getCount() {
        return controlApiBean.getData().getRemoteControlDevice().size();
    }

    @Override
    public Object getItem(int position) {
        return controlApiBean.getData().getRemoteControlDevice().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RemoteAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.device_management_spn_item, null);
            ScreenAdapterTools.getInstance().loadView(convertView);
            viewHolder = new RemoteAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (RemoteAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.deviceManagementSpnText.setText(controlApiBean.getData().getRemoteControlDevice().get(position).getName());

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
