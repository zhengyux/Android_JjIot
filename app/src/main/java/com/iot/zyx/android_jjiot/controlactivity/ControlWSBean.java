package com.iot.zyx.android_jjiot.controlactivity;

import java.util.List;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/12/17 14:23
 * 修改人：xuan
 * 修改时间：2018/12/17 14:23
 * 修改备注：
 */
public class ControlWSBean {


    /**
     * result : 1030
     * designation : 设备窗帘控制
     * msg : [{"productkey":"a1UEceRK3Pu","devicename":"deviceName_123","uuid":"29557fe197cd30829ee498a38b76d7ed","deviceuid":"CC-CD","endpoint":"02","type":2,"message":"设备窗帘进度","motorPosi":54}]
     */

    private String result;
    private String designation;
    private List<MsgBean> msg;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * productkey : a1UEceRK3Pu
         * devicename : deviceName_123
         * uuid : 29557fe197cd30829ee498a38b76d7ed
         * deviceuid : CC-CD
         * endpoint : 02
         * type : 2
         * message : 设备窗帘进度
         * motorPosi : 54
         */

        private String productkey;
        private String devicename;
        private String uuid;
        private String deviceuid;
        private String endpoint;
        private int type;
        private String message;
        private int motorPosi;

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

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getDeviceuid() {
            return deviceuid;
        }

        public void setDeviceuid(String deviceuid) {
            this.deviceuid = deviceuid;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getMotorPosi() {
            return motorPosi;
        }

        public void setMotorPosi(int motorPosi) {
            this.motorPosi = motorPosi;
        }
    }
}
