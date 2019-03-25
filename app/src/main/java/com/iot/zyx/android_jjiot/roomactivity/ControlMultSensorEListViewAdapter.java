package com.iot.zyx.android_jjiot.roomactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.controlactivity.ControlApiBean;
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


    private RoomActivity mcontext;
    private ControlApiBean controlApiBean;

    public ControlMultSensorEListViewAdapter(RoomActivity mcontext, ControlApiBean controlApiBean) {
        this.mcontext = mcontext;
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
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.room_elist_multsensor_item_child, null);
            ScreenAdapterTools.getInstance().loadView(convertView);
        }
        cViewHolder viewHolder = new cViewHolder(convertView);

        switch (controlApiBean.getData().getMultNodeSensor().get(groupPosition).getNode().get(childPosition).getType()){
            case 41:

                viewHolder.roomCelsiusLl.setVisibility(View.GONE);
                viewHolder.roomLightLl.setVisibility(View.VISIBLE);

                break;

            case 42:

                viewHolder.roomLightLl.setVisibility(View.GONE);
                viewHolder.roomCelsiusLl.setVisibility(View.VISIBLE);

                break;
        }
        viewHolder.roomElistItemChildCelsiusTxt.setText(controlApiBean.getData().getMultNodeSensor().get(groupPosition).getNode().get(childPosition).getCelsius() + "°C");
        viewHolder.roomElistItemChildHumidityTxt.setText(controlApiBean.getData().getMultNodeSensor().get(groupPosition).getNode().get(childPosition).getHumidity() + "%");
        viewHolder.roomElistItemChildLightTxt.setText(controlApiBean.getData().getMultNodeSensor().get(groupPosition).getNode().get(childPosition).getLight() + "Lux");
        // viewHolder.roomElistItemChildPmTxt.setText(controlApiBean.getData().getMultNodeSensor().get(groupPosition).getNode().get(childPosition).getCelsius());

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
        @BindView(R.id.room_elist_item_child_celsius_txt)
        TextView roomElistItemChildCelsiusTxt;
        @BindView(R.id.room_elist_item_child_humidity_txt)
        TextView roomElistItemChildHumidityTxt;
        @BindView(R.id.room_elist_item_child_light_txt)
        TextView roomElistItemChildLightTxt;
        @BindView(R.id.room_elist_item_child_pm_txt)
        TextView roomElistItemChildPmTxt;
        @BindView(R.id.room_celsius_ll)
        LinearLayout roomCelsiusLl;
        @BindView(R.id.room_light_ll)
        LinearLayout roomLightLl;

        cViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
