package com.iot.zyx.android_jjiot.util.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.zyx.android_jjiot.R;
import com.yatoooon.screenadaptation.ScreenAdapterTools;


/**
 * @author vondear
 * @date 2016/7/19
 * Mainly used for confirmation and cancel.
 */
public class RxDialogDeleteCancel extends RxDialog {

    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;


    public RxDialogDeleteCancel(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public RxDialogDeleteCancel(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public RxDialogDeleteCancel(Context context) {
        super(context);
        initView();
    }

    public RxDialogDeleteCancel(Activity context) {
        super(context);
        initView();
    }

    public RxDialogDeleteCancel(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView();
    }

    public TextView getmTv1() {
        return mTv1;
    }

    public void setmTv1(TextView mTv1) {
        this.mTv1 = mTv1;
    }

    public TextView getmTv2() {
        return mTv2;
    }

    public void setmTv2(TextView mTv2) {
        this.mTv2 = mTv2;
    }

    public TextView getmTv3() {
        return mTv3;
    }

    public void setmTv3(TextView mTv3) {
        this.mTv3 = mTv3;
    }

    private void initView() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_delete, null);
        mTv1 = dialogView.findViewById(R.id.dialog_delete_1);
        mTv2 = dialogView.findViewById(R.id.dialog_delete_2);
        mTv3 = dialogView.findViewById(R.id.dialog_delete_3);
        ScreenAdapterTools.getInstance().loadView(dialogView);
        setContentView(dialogView);
    }
}
