package com.iot.zyx.android_jjiot.device_managementactivity;

import com.iot.zyx.android_jjiot.API;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/11/29 10:13
 * 修改人：xuan
 * 修改时间：2018/11/29 10:13
 * 修改备注：
 */
public class DeviceGetParameter {

    /**
     * areaId : 10001
     * productkey : a1UEceRK3Pu
     * devicename : deviceName_123
     */

    private String areaId="10001";
    private String productkey= API.Device.productkey;
    private String devicename=API.Device.devicename;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
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
