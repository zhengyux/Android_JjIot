package com.iot.zyx.android_jjiot.add_zigbeeactivity;

import java.util.List;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/11/29 10:28
 * 修改人：xuan
 * 修改时间：2018/11/29 10:28
 * 修改备注：
 */
public class AddZigBeeWSBean {

    /**
     * result : 1010
     * designation : 设备入网
     * msg : [{"productkey":"a1UEceRK3Pu","devicename":"deviceName_123","uuid":"2981813c6a063e8d82d9a9d4d45ce371","deviceuid":"02-03","endpoint":"04","name":"device_1234","IEEE":"11-12-13-14-15-16-17-18"},{"productkey":"a1UEceRK3Pu","devicename":"deviceName_123","uuid":"a43c38adee453d06b95049f3e9e0debb","deviceuid":"4E-C0","endpoint":"01","name":"device_Ecdl","IEEE":"E4-C3-B1-02-00-8D-15-00"}]
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
         * uuid : 2981813c6a063e8d82d9a9d4d45ce371
         * deviceuid : 02-03
         * endpoint : 04
         * name : device_1234
         * IEEE : 11-12-13-14-15-16-17-18
         */

        private String productkey;
        private String devicename;
        private String uuid;
        private String deviceuid;
        private String endpoint;
        private String name;
        private String IEEE;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIEEE() {
            return IEEE;
        }

        public void setIEEE(String IEEE) {
            this.IEEE = IEEE;
        }
    }
}
