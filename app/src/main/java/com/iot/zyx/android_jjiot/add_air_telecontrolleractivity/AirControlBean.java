package com.iot.zyx.android_jjiot.add_air_telecontrolleractivity;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2019/3/15 13:37
 * 修改人：xuan
 * 修改时间：2019/3/15 13:37
 * 修改备注：
 */
public class AirControlBean {


    /**
     * uuid : Ad5646465sd
     * airType : 0001
     * control : 050000
     * value : 0
     */

    private String uuid;
    private String airType;
    private String control;
    private int value;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAirType() {
        return airType;
    }

    public void setAirType(String airType) {
        this.airType = airType;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
