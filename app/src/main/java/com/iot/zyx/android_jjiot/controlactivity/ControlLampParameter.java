package com.iot.zyx.android_jjiot.controlactivity;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/11/29 16:01
 * 修改人：xuan
 * 修改时间：2018/11/29 16:01
 * 修改备注：
 */
public class ControlLampParameter {

    /**
     * productKey : a1UEceRK3Pu
     * deviceName : deviceName_123
     * uuid : Ad5646465sd
     * onoff : 0
     */

    private String productkey;
    private String devicename;
    private String uuid;
    private int onoff;

    public String getProductKey() {
        return productkey;
    }

    public void setProductKey(String productKey) {
        this.productkey = productKey;
    }

    public String getDeviceName() {
        return devicename;
    }

    public void setDeviceName(String deviceName) {
        this.devicename = deviceName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getOnoff() {
        return onoff;
    }

    public void setOnoff(int onoff) {
        this.onoff = onoff;
    }
}
