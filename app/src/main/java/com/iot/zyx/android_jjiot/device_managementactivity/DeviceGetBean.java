package com.iot.zyx.android_jjiot.device_managementactivity;

import java.util.List;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/11/29 10:01
 * 修改人：xuan
 * 修改时间：2018/11/29 10:01
 * 修改备注：
 */
public class DeviceGetBean {

    /**
     * result : 00
     * message : success
     * data : {"list":[{"id":10000,"productkey":"a1UEceRK3Pu","devicename":"deviceName_123","areaId":"10001","uuid":"2981813c6a063e8d82d9a9d4d45ce371","deviceuid":"4E-C0","endpoint":"01","name":"我的名字","date":"2018-11-28T07:27:41.000+0000","IEEE":"21-22-13-14-15-16-17-18"}]}
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
             * id : 10000
             * productkey : a1UEceRK3Pu
             * devicename : deviceName_123
             * areaId : 10001
             * uuid : 2981813c6a063e8d82d9a9d4d45ce371
             * deviceuid : 4E-C0
             * endpoint : 01
             * name : 我的名字
             * date : 2018-11-28T07:27:41.000+0000
             * IEEE : 21-22-13-14-15-16-17-18
             */

            private int id;
            private String productkey;
            private String devicename;
            private String areaId;
            private String uuid;
            private String deviceuid;
            private String endpoint;
            private String name;
            private String date;
            private String IEEE;

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
