package com.iot.zyx.android_jjiot.util.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2019/3/19 14:10
 * 修改人：xuan
 * 修改时间：2019/3/19 14:10
 * 修改备注：
 */
public class ScrExpandableListView extends ExpandableListView{
    public ScrExpandableListView(Context context) {
        super(context);
    }

    public ScrExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
