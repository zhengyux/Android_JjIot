package com.iot.zyx.android_jjiot.controlactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iot.zyx.android_jjiot.R;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/11/16 14:04
 * 修改人：xuan
 * 修改时间：2018/11/16 14:04
 * 修改备注：
 */
public class ControlMultSensorEListViewAdapter extends BaseExpandableListAdapter {

    private ControlActivity mcontext;
    private ControlApiBean controlApiBean;

    public ControlMultSensorEListViewAdapter(ControlActivity mcontext, ControlApiBean controlApiBean) {
        this.mcontext = mcontext;
        this.controlApiBean = controlApiBean;
    }

    public ControlMultSensorEListViewAdapter(Context mcontext, ControlApiBean controlApiBean) {
        this.mcontext = (ControlActivity) mcontext;
        this.controlApiBean = controlApiBean;
    }

    public void update(ControlApiBean controlApiBean) {
        this.controlApiBean = controlApiBean;
        this.notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return controlApiBean.getData().getMultNodeSensor().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return controlApiBean.getData().getMultNodeSensor().get(groupPosition).getNode().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return controlApiBean.getData().getMultNodeSensor().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return controlApiBean.getData().getMultNodeSensor().get(groupPosition).getNode().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.control_elist_socket_item_group, null);
            ScreenAdapterTools.getInstance().loadView(convertView);
        }
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.controlElistItemGroupTxt.setText(controlApiBean.getData().getMultNodeSensor().get(groupPosition).getName());


        return convertView;
    }


    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.control_elist_multsensor_item_child, null);
            ScreenAdapterTools.getInstance().loadView(convertView);
        }
        cViewHolder viewHolder = new cViewHolder(convertView);
        switch (controlApiBean.getData().getMultNodeSensor().get(groupPosition).getNode().get(childPosition).getType()){
            case 41:

                viewHolder.controlElistItemChildCelsiusRl.setVisibility(View.GONE);
                viewHolder.controlElistItemChildHumidityRl.setVisibility(View.GONE);
                viewHolder.controlElistItemChildLightRl.setVisibility(View.VISIBLE);
                viewHolder.controlElistItemChildPmRl.setVisibility(View.GONE);

                break;

            case 42:
                viewHolder.controlElistItemChildCelsiusRl.setVisibility(View.VISIBLE);
                viewHolder.controlElistItemChildHumidityRl.setVisibility(View.VISIBLE);
                viewHolder.controlElistItemChildLightRl.setVisibility(View.GONE);
                viewHolder.controlElistItemChildPmRl.setVisibility(View.GONE);



                break;
        }
        viewHolder.controlElistItemChildCelsiusTxt.setText(controlApiBean.getData().getMultNodeSensor().get(groupPosition).getNode().get(childPosition).getCelsius() + "°C");
        viewHolder.controlElistItemChildHumidityTxt.setText(controlApiBean.getData().getMultNodeSensor().get(groupPosition).getNode().get(childPosition).getHumidity() + "%");
        viewHolder.controlElistItemChildLightTxt.setText(controlApiBean.getData().getMultNodeSensor().get(groupPosition).getNode().get(childPosition).getLight() + "Lux");
        // viewHolder.controlElistItemChildPmTxt.setText(controlApiBean.getData().getMultNodeSensor().get(groupPosition).getNode().get(childPosition).getCelsius());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }



    static class ViewHolder {
        @BindView(R.id.control_elist_switch_item_group_txt)
        TextView controlElistItemGroupTxt;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class cViewHolder {
        @BindView(R.id.control_elist_item_child_celsius_txt)
        TextView controlElistItemChildCelsiusTxt;
        @BindView(R.id.control_elist_item_child_celsius_rl)
        RelativeLayout controlElistItemChildCelsiusRl;
        @BindView(R.id.control_elist_item_child_humidity_txt)
        TextView controlElistItemChildHumidityTxt;
        @BindView(R.id.control_elist_item_child_humidity_rl)
        RelativeLayout controlElistItemChildHumidityRl;
        @BindView(R.id.control_elist_item_child_light_txt)
        TextView controlElistItemChildLightTxt;
        @BindView(R.id.control_elist_item_child_light_rl)
        RelativeLayout controlElistItemChildLightRl;
        @BindView(R.id.control_elist_item_child_pm_txt)
        TextView controlElistItemChildPmTxt;
        @BindView(R.id.control_elist_item_child_pm_rl)
        RelativeLayout controlElistItemChildPmRl;

        cViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
