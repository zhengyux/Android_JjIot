package com.iot.zyx.android_jjiot.add_air_telecontrolleractivity;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2019/3/14 10:02
 * 修改人：xuan
 * 修改时间：2019/3/14 10:02
 * 修改备注：
 */
public class TelecontrollerBean {

    /**
     * areaId : 10002
     * name : 遥控器
     * date : 2019-03-13
     * deviceUuid : 选择的红外 uuid
     * type : 0500
     */

    private String areaId;
    private String name;
    private String date;
    private String deviceUuid;
    private String type;
    private String brandType;

    public String getBrandType() {
        return brandType;
    }

    public void setBrandType(String brandType) {
        this.brandType = brandType;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
