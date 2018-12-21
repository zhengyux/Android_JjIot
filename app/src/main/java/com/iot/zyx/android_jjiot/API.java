package com.iot.zyx.android_jjiot;

import android.content.Context;

import com.iot.zyx.android_jjiot.util.AppUtil.SharedPreferencesUtils;

import java.net.ContentHandler;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/10/26 10:09
 * 修改人：xuan
 * 修改时间：2018/10/26 10:09
 * 修改备注：
 */
public class API {
    static boolean isTest = true;



    public static String ip(){
        if(isTest){
            return IP="http://192.168.1.29:9090";
        }else {
            return IP="http://192.168.1.146:9090";
        }
    }

    public static String wsIp(){
        if(isTest){
            return WSIP="ws://192.168.1.29:9070";
        }else {
            return WSIP="ws://192.168.1.146:8055";
        }
    }



    public static String IP = "http://192.168.1.29:9090";//测试域名端口

    public static String WSIP="ws://192.168.1.29:9070";//测试WS域名端口

    public static String GET_GATEWAY = IP+"/device/gateway/list";//获取主机
 //   public static String IP = "http://192.168.1.146:9090";//域名端口
 //   public static String WSIP="ws://192.168.1.146:8055";//WS域名端口
    public static String OPEN_NETWORK = IP + "/device/gateway/openNetwork";//打开网络
    public static String DEVICE_ACCESS = IP + "/device/disabled/get";//设备入网
    public static String DEVICE_HOLD=IP+"/device/disabled/change";//保存设备
    public static String DEVICE_GET=IP+"/device/manager/list";//获取设备列表
    public static String SET_DEVICE = IP + "/device/disabled/handle";//设置设备
    public static String DELETE_DEVICE = IP + "/device/delete";//删除设备
    public static String CONTROL_LAMP=IP+"/device/light/onoff";//控制灯开关
    public static String CONTROL_CURTAIN = IP+"/device/curtain/control";//控制窗帘
    public static String LAMP_BRIGHTNESS=IP+"/device/light/brightness";//灯光亮度
    public static String GET_AREA=IP+"/device/area/get";//获取区域
    public static String ADD_AREA=IP+"/device/area/add";//添加区域
    public static String DELETE_AREA=IP+"/device/area/delete";//删除区域
    public static String UPDATE_AREA=IP+"/device/area/update";//更新区域

    public static class Device {
        public static void setDeviceParamter(Context context){
            devicename = (String) SharedPreferencesUtils.getParam(context,"devicename","null");
            productkey = (String) SharedPreferencesUtils.getParam(context,"productkey","null");
        }
        public static String productkey ;
        public static String devicename ;
        public static String Lamp = "1";
        public static String Curtain ="2";

    }
}
