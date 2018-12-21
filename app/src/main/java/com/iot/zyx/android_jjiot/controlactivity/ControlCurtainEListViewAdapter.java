package com.iot.zyx.android_jjiot.controlactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.iot.zyx.android_jjiot.API;
import com.iot.zyx.android_jjiot.BaseRespone;
import com.iot.zyx.android_jjiot.R;
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
public class ControlCurtainEListViewAdapter extends BaseExpandableListAdapter {

    private ControlActivity mcontext;
    private ControlApiBean controlLampApiBean;

    public ControlCurtainEListViewAdapter(ControlActivity mcontext, ControlApiBean controlLampApiBean) {
        this.mcontext = mcontext;
        this.controlLampApiBean = controlLampApiBean;
    }

    public void update(ControlApiBean controlLampApiBean){
        this.controlLampApiBean = controlLampApiBean;
        this.notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return controlLampApiBean.getData().getList().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return controlLampApiBean.getData().getList().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
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
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.control_elist_curtain_item_group, null);
            ScreenAdapterTools.getInstance().loadView(convertView);
        }
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.controlElistItemGroupTxt.setText(controlLampApiBean.getData().getList().get(groupPosition).getName());
        viewHolder.controlElistItemGroupOpenImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlParameter controlParameter = new ControlParameter();
                controlParameter.setDeviceName(controlLampApiBean.getData().getList().get(groupPosition).getDevicename());
                controlParameter.setProductKey(controlLampApiBean.getData().getList().get(groupPosition).getProductkey());
                controlParameter.setUuid(controlLampApiBean.getData().getList().get(groupPosition).getUuid());
                controlParameter.setMotorPosi(100);
                CurtainOnOff(GsonUtil.GsonString(controlParameter));
                mcontext.showLoading();
            }
        });
        viewHolder.controlElistItemGroupStopImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlParameter controlParameter = new ControlParameter();
                controlParameter.setDeviceName(controlLampApiBean.getData().getList().get(groupPosition).getDevicename());
                controlParameter.setProductKey(controlLampApiBean.getData().getList().get(groupPosition).getProductkey());
                controlParameter.setUuid(controlLampApiBean.getData().getList().get(groupPosition).getUuid());
                controlParameter.setMotorPosi(-1);
                CurtainOnOff(GsonUtil.GsonString(controlParameter));
                mcontext.showLoading();

            }
        });
        viewHolder.controlElistItemGroupCloseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlParameter controlParameter = new ControlParameter();
                controlParameter.setDeviceName(controlLampApiBean.getData().getList().get(groupPosition).getDevicename());
                controlParameter.setProductKey(controlLampApiBean.getData().getList().get(groupPosition).getProductkey());
                controlParameter.setUuid(controlLampApiBean.getData().getList().get(groupPosition).getUuid());
                controlParameter.setMotorPosi(0);
                CurtainOnOff(GsonUtil.GsonString(controlParameter));
                mcontext.showLoading();
            }
        });

        viewHolder.controlElistItemGroupSeek.setProgress(controlLampApiBean.getData().getList().get(groupPosition).getMotorPosi());


        viewHolder.controlElistItemGroupSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                ControlParameter controlParameter = new ControlParameter();
                controlParameter.setDeviceName(controlLampApiBean.getData().getList().get(groupPosition).getDevicename());
                controlParameter.setProductKey(controlLampApiBean.getData().getList().get(groupPosition).getProductkey());
                controlParameter.setUuid(controlLampApiBean.getData().getList().get(groupPosition).getUuid());
                controlParameter.setMotorPosi(seekBar.getProgress());
                CurtainSchedule(GsonUtil.GsonString(controlParameter));
                mcontext.showLoading();
            }
        });

        return convertView;
    }

    public void CurtainSchedule(String josnStr) {
        OkhttpUtil.okHttpPostJson(API.CONTROL_CURTAIN, josnStr, new CallBackUtil.CallBackString() {
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

                }
            }
        });
    }


    public void CurtainOnOff(String josnStr) {
        OkhttpUtil.okHttpPostJson(API.CONTROL_CURTAIN, josnStr, new CallBackUtil.CallBackString() {
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

                }

            }
        });
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    static class ViewHolder {
        @BindView(R.id.control_elist_item_group_txt)
        TextView controlElistItemGroupTxt;
        @BindView(R.id.control_elist_item_group_open_img)
        ImageView controlElistItemGroupOpenImg;
        @BindView(R.id.control_elist_item_group_stop_img)
        ImageView controlElistItemGroupStopImg;
        @BindView(R.id.control_elist_item_group_close_img)
        ImageView controlElistItemGroupCloseImg;
        @BindView(R.id.control_elist_item_group_seek)
        SeekBar controlElistItemGroupSeek;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
