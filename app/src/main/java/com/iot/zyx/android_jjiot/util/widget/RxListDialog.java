package com.iot.zyx.android_jjiot.util.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.iot.zyx.android_jjiot.R;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/11/29 11:08
 * 修改人：xuan
 * 修改时间：2018/11/29 11:08
 * 修改备注：
 */
public class RxListDialog extends RxDialog{



    private RecyclerView recyclerView;

    public RxListDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }


    public RxListDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public RxListDialog(Context context) {
        super(context);
        initView();
    }

    public RxListDialog(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView();
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    private void initView() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_addzigbee_list, null);
        recyclerView = dialogView.findViewById(R.id.dialog_addzigbee_recycler);
        ScreenAdapterTools.getInstance().loadView(dialogView);
        setContentView(dialogView);
    }
}
