package com.iot.zyx.android_jjiot;

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

    public static String productkey ="a1UEceRK3Pu";
    public static String devicename ="deviceName_123";

    public static String IP = "http://192.168.1.146:9090";//域名端口
    public static String WSIP="ws://192.168.1.146:8055";//WS域名端口
    public static String OPEN_NETWORK = IP + "/device/openNetwork";//打开网络
    public static String DEVICE_ACCESS = IP + "/device/disabled/get";//设备入网
    public static String DEVICE_HOLD=IP+"/device/disabled/change";//保存设备
    public static String DEVICE_GET=IP+"/device/get";//获取设备列表
    public static String SET_DEVICE = IP + "/device/disabled/handle";//设置设备
    public static String DELETE_DEVICE = IP + "/device/delete";//删除设备
    public static String CONTROL_LAMP=IP+"/device/light/onoff";//控制灯开关
    public static String LAMP_BRIGHTNESS=IP+"/device/light/brightness";//灯光亮度
    public static String GET_AREA=IP+"/device/area/get";//获取区域
    public static String ADD_AREA=IP+"/device/area/add";//添加区域
    public static String DELETE_AREA=IP+"/device/area/delete";//删除区域
    public static String UPDATE_AREA=IP+"/device/area/update";//更新区域
}
