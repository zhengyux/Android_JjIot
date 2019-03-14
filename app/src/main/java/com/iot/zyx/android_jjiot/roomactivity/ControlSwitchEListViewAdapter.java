package com.iot.zyx.android_jjiot.roomactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseRespone;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.controlactivity.ControlActivity;
import com.iot.zyx.android_jjiot.controlactivity.ControlApiBean;
import com.iot.zyx.android_jjiot.controlactivity.ControlParameter;
import com.iot.zyx.android_jjiot.util.network.CallBackUtil;
import com.iot.zyx.android_jjiot.util.network.GsonUtil;
import com.iot.zyx.android_jjiot.util.network.OkhttpUtil;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/11/16 14:04
 * 修改人：xuan
 * 修改时间：2018/11/16 14:04
 * 修改备注：
 */
public class ControlSwitchEListViewAdapter extends BaseExpandableListAdapter {

    private RoomActivity mcontext;
    private ControlApiBean controlApiBean;

    public ControlSwitchEListViewAdapter(RoomActivity mcontext, ControlApiBean controlApiBean) {
        this.mcontext = mcontext;
        this.controlApiBean = controlApiBean;
    }


    public void update(ControlApiBean controlApiBean) {
        this.controlApiBean = controlApiBean;
        this.notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return controlApiBean.getData().getOnoffSwitch().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return controlApiBean.getData().getOnoffSwitch().get(groupPosition).getNode().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return controlApiBean.getData().getOnoffSwitch().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return controlApiBean.getData().getOnoffSwitch().get(groupPosition).getNode().get(childPosition);
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
        viewHolder.controlElistItemGroupTxt.setText(controlApiBean.getData().getOnoffSwitch().get(groupPosition).getName());


        return convertView;
    }


    public void SwitchOnOff(String josnStr) {
        OkhttpUtil.okHttpPostJson(API.IP+API.CONTROL_SWITCH, josnStr, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                mcontext.toastShort("服务器连接失败");
                mcontext.closeLoading();
            }

            @Override
            public void onResponse(String response) {
                try {
                    BaseRespone baseRespone = GsonUtil.GsonToBean(response, BaseRespone.class);
                    if (baseRespone.getResult().equals("00")) {
                        mcontext.toastShort("操作成功");
                    } else {
                        mcontext.toastShort(baseRespone.getMessage());
                    }
                    mcontext.closeLoading();
                } catch (Exception e) {
                    mcontext.toastShort(e.getMessage());
                    mcontext.closeLoading();
                }
            }
        });
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.control_elist_socket_item_child, null);
            ScreenAdapterTools.getInstance().loadView(convertView);
        }
        cViewHolder viewHolder = new cViewHolder(convertView);
        viewHolder.controlElistItemChildTxt.setText(controlApiBean.getData().getOnoffSwitch().get(groupPosition).getNode().get(childPosition).getName());

        if (1 == controlApiBean.getData().getOnoffSwitch().get(groupPosition).getNode().get(childPosition).getOnoff()) {
            viewHolder.controlElistItemChildSwitch.setChecked(true);
        } else {
            viewHolder.controlElistItemChildSwitch.setChecked(false);
        }

        viewHolder.controlElistItemChildSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!buttonView.isPressed()) {
                    return;
                }

                ControlParameter controlParameter = new ControlParameter();
                controlParameter.setDeviceName(controlApiBean.getData().getOnoffSwitch().get(groupPosition).getNode().get(childPosition).getDevicename());
                controlParameter.setProductKey(controlApiBean.getData().getOnoffSwitch().get(groupPosition).getNode().get(childPosition).getProductkey());
                controlParameter.setUuid(controlApiBean.getData().getOnoffSwitch().get(groupPosition).getNode().get(childPosition).getUuid());
                if (isChecked) {
                    controlParameter.setOnoff(1);
                } else {
                    controlParameter.setOnoff(0);
                }
                mcontext.showLoading();
                SwitchOnOff(GsonUtil.GsonString(controlParameter));

            }

        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    static class cViewHolder {
        @BindView(R.id.control_elist_item_child_txt)
        TextView controlElistItemChildTxt;

        @BindView(R.id.control_elist_item_child_switch)
        Switch controlElistItemChildSwitch;

        cViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder {
        @BindView(R.id.control_elist_switch_item_group_txt)
        TextView controlElistItemGroupTxt;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
