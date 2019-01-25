package com.iot.zyx.android_jjiot;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.yatoooon.screenadaptation.ScreenAdapterTools;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/10/30 10:23
 * 修改人：xuan
 * 修改时间：2018/10/30 10:23
 * 修改备注：
 */
public class BaseApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();

        ScreenAdapterTools.init(this);
        mContext = getApplicationContext();
        API.Device.setDeviceParamter(mContext);

    }

    public static Context getContext(){
        return mContext;
    }

}
