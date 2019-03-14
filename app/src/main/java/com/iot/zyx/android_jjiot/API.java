package com.iot.zyx.android_jjiot;

import android.content.Context;
import android.util.Log;

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


    public static String IP = setip();//测试域名端口
    public static String WSIP=setwsip();//测试WS域名端口
    public static String Lanip;

    public static String setip (){
        if(isTest){
             WSIP="ws://192.168.1.29:9070";
             IP="http://192.168.1.29:9090";
        }else {
             WSIP="ws://192.168.1.146:9070";
             IP="http://192.168.1.146:9090";
        }
        if(!"null".equals((String) SharedPreferencesUtils.getParam(BaseApplication.getContext(),"lan","null"))){
            IP = (String) SharedPreferencesUtils.getParam(BaseApplication.getContext(),"lan","null");
        }
        Log.e("ip", "ip: "+IP );
        return IP;
    }

    public static String setip2 (){
        if(isTest){
            WSIP="ws://192.168.1.29:9070";
            IP="http://192.168.1.29:9090";
        }else {
            WSIP="ws://192.168.1.146:8055";
            IP="http://192.168.1.146:9090";
        }
        Log.e("ip", "ip: "+IP );
        return IP;
    }

   public static String setwsip (){
        if(isTest){
            WSIP="ws://192.168.1.29:9070";
        }else {
            WSIP="ws://192.168.1.146:8055";
        }
//        if(!"null".equals((String) SharedPreferencesUtils.getParam(BaseApplication.getContext(),"ip","null"))){
//            IP = (String) SharedPreferencesUtils.getParam(BaseApplication.getContext(),"ip","null");
//        }
        Log.e("ip", "wsip: "+WSIP );
        return WSIP;
    }


    public static String GET_GATEWAY = "/device/gateway/list";//获取主机
    public static String OPEN_NETWORK = "/device/gateway/openNetwork";//打开网络
    public static String DEVICE_ACCESS = "/device/disabled/get";//设备入网
    public static String DEVICE_HOLD="/device/disabled/change";//保存设备
    public static String DEVICE_UPDATE="/device/update";//更新设备
    public static String DEVICE_GET="/device/manager/list";//获取设备列表
    public static String ADD_REMOTE = "/remote_control/add";//保存遥控器
    public static String REMOTE_GET ="/remote_control/list";//获取遥控器列表
    public static String AIR_GET="/device/air_conditioner/brand"; //获取空调品牌列表
    public static String SET_DEVICE ="/device/disabled/handle";//设置设备
    public static String DELETE_DEVICE ="/device/delete";//删除设备
    public static String CONTROL_AIR="/device/air_conditioner/control";//控制空调
    public static String CONTROL_LAMP="/device/light/onoff";//控制灯开关
    public static String CONTROL_CURTAIN ="/device/curtain/control";//控制窗帘
    public static String CONTROL_SWITCH ="/device/switch/control";//开关控制
    public static String LAMP_BRIGHTNESS="/device/light/brightness";//灯光亮度
    public static String GET_AREA="/device/area/get";//获取区域
    public static String ADD_AREA="/device/area/add";//添加区域
    public static String DELETE_AREA="/device/area/delete";//删除区域
    public static String UPDATE_AREA="/device/area/update";//更新区域

    public static class Device {
        public static void setDeviceParamter(Context context){
            devicename = (String) SharedPreferencesUtils.getParam(context,"devicename","null");
            productkey = (String) SharedPreferencesUtils.getParam(context,"productkey","null");
            Log.e("ip", "devicename: "+devicename );
        }
        public static String productkey ;
        public static String devicename ;
        public static String Lamp = "1";
        public static String Curtain ="2";
        public static String Switch ="3";
        public static String Environment = "4";
        public static String RemoteControlDevice ="5";

    }
}
