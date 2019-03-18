package com.iot.zyx.android_jjiot.roomactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseRespone;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.add_air_telecontrolleractivity.AirControlBean;
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
public class ControlRemoteEListViewAdapter extends BaseExpandableListAdapter {

    private RoomActivity mcontext;
    private ControlApiBean controlApiBean;

    public ControlRemoteEListViewAdapter(RoomActivity mcontext, ControlApiBean controlApiBean) {
        this.mcontext = mcontext;
        this.controlApiBean = controlApiBean;
    }


    public void update(ControlApiBean controlApiBean) {
        this.controlApiBean = controlApiBean;
        this.notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return controlApiBean.getData().getRemoteControlDevice().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(controlApiBean.getData().getRemoteControlDevice().get(groupPosition).getRemoteControl()==null){
            return 0;
        }
        return controlApiBean.getData().getRemoteControlDevice().get(groupPosition).getRemoteControl().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return controlApiBean.getData().getRemoteControlDevice().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return controlApiBean.getData().getRemoteControlDevice().get(groupPosition).getRemoteControl().get(childPosition);
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
        viewHolder.controlElistItemGroupTxt.setText(controlApiBean.getData().getRemoteControlDevice().get(groupPosition).getName());


        return convertView;
    }


    public void controlAllIn(AirControlBean airControlBean){

        OkhttpUtil.okHttpPostJson(API.IP + API.CONTROL_ALLIN, GsonUtil.GsonString(airControlBean), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                mcontext.closeLoading();
                mcontext.toastShort("网络连接异常");
            }

            @Override
            public void onResponse(String response) {
                mcontext.closeLoading();
                try {
                    BaseRespone baseRespone = GsonUtil.GsonToBean(response, BaseRespone.class);
                    if (baseRespone.getResult().equals("00")) {
                        mcontext.toastShort("操作成功");
                    } else {
                        mcontext.toastShort(baseRespone.getMessage());
                    }
                } catch (Exception e) {
                    mcontext.toastShort(e.getMessage());
                }
            }
        });

    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.control_elist_remote_item_child, null);
            ScreenAdapterTools.getInstance().loadView(convertView);
        }
        cViewHolder viewHolder = new cViewHolder(convertView);
        viewHolder.controlElistItemChildTxt.setText(controlApiBean.getData().getRemoteControlDevice().get(groupPosition).getRemoteControl().get(childPosition).getName());

        viewHolder.controlElistItemChildImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcontext.showLoading();
                AirControlBean airControlBean = new AirControlBean();
                airControlBean.setValue(0);
                airControlBean.setUuid(controlApiBean.getData().getRemoteControlDevice().get(groupPosition).getRemoteControl().get(childPosition).getDeviceUuid());
                controlAllIn(airControlBean);
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

        @BindView(R.id.control_elist_item_child_img)
        ImageView controlElistItemChildImageview;

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
