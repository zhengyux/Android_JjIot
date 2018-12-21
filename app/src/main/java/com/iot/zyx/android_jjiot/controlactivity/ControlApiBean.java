package com.iot.zyx.android_jjiot.controlactivity;

import java.util.List;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/11/29 15:31
 * 修改人：xuan
 * 修改时间：2018/11/29 15:31
 * 修改备注：
 */
public class ControlApiBean {

    /**
     * result : 00
     * message : success
     * data : {"list":[{"id":100055,"productkey":"a1UEceRK3Pu","devicename":"deviceName_123","areaId":"10003","uuid":"029b440eb361304bb05626866d948bb8","deviceuid":"D4-1D","endpoint":"08","type":8,"name":"device_ASDF","date":"2018-12-12T07:37:13.000+0000","IEEE":"77-44-6F-02-00-4B-12-00"},{"id":100056,"productkey":"a1UEceRK3Pu","devicename":"deviceName_123","areaId":"10002","uuid":"419084cc8d7e34b4b98b3b5b472c5234","deviceuid":"68-BB","endpoint":"01","type":1,"name":"device_sC86","date":"2018-12-12T10:28:04.000+0000","IEEE":"E4-C3-B1-02-00-8D-15-00"}]}
     */

    private String result;
    private String message;
    private DataBean data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 100055
             * productkey : a1UEceRK3Pu
             * devicename : deviceName_123
             * areaId : 10003
             * uuid : 029b440eb361304bb05626866d948bb8
             * deviceuid : D4-1D
             * endpoint : 08
             * type : 8
             * name : device_ASDF
             * date : 2018-12-12T07:37:13.000+0000
             * IEEE : 77-44-6F-02-00-4B-12-00
             */

            private int id;
            private String productkey;
            private String devicename;
            private String areaId;
            private String uuid;
            private String deviceuid;
            private String endpoint;
            private int type;
            private String name;
            private String date;
            private String IEEE;
            private int motorPosi;

            public int getMotorPosi() {
                return motorPosi;
            }

            public void setMotorPosi(int motorPosi) {
                this.motorPosi = motorPosi;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getAreaId() {
                return areaId;
            }

            public void setAreaId(String areaId) {
                this.areaId = areaId;
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

            public String getIEEE() {
                return IEEE;
            }

            public void setIEEE(String IEEE) {
                this.IEEE = IEEE;
            }
        }
    }
}
