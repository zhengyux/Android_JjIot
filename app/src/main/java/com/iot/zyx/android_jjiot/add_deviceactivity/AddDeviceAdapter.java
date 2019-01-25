package com.iot.zyx.android_jjiot.add_deviceactivity;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AbsListView;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iot.zyx.android_jjiot.BaseApplication;
import com.iot.zyx.android_jjiot.R;
import com.iot.zyx.android_jjiot.add_zigbeeactivity.AddZigBeeAPIBean;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.List;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/11/29 13:55
 * 修改人：xuan
 * 修改时间：2018/11/29 13:55
 * 修改备注：
 */
public class AddDeviceAdapter extends BaseQuickAdapter<AddZigBeeAPIBean.DataBean.ListBean.NodeBean,BaseViewHolder>{


    public AddDeviceAdapter(int layoutResId, @Nullable List<AddZigBeeAPIBean.DataBean.ListBean.NodeBean> data) {
        super(layoutResId, data);
        ScreenAdapterTools.getInstance().loadView(mLayoutInflater.from(BaseApplication.getContext()).inflate(layoutResId,null));
    }

    @Override
    protected void convert(BaseViewHolder helper, final AddZigBeeAPIBean.DataBean.ListBean.NodeBean item) {
        helper.setText(R.id.add_device_circuit_recycler_edt,item.getName());
        EditText editText = (EditText) helper.getView(R.id.add_device_circuit_recycler_edt);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setName(s.toString());
            }
        });
    }

}
