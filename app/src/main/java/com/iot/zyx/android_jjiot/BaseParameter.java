package com.iot.zyx.android_jjiot;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/11/29 9:35
 * 修改人：xuan
 * 修改时间：2018/11/29 9:35
 * 修改备注：
 */
public class BaseParameter {

    /**
     * productkey : a1UEceRK3Pu
     * devicename : deviceName_123
     */

    private String productkey= API.Device.productkey;
    private String devicename= API.Device.devicename;
    private String type ;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductkey() {
        return productkey;
    }

    public void setProductkey(String productkey) {
        this.productkey = productkey;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }
}
