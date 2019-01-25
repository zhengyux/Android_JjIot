package com.iot.zyx.android_jjiot.switchover_hostactivity;

import java.util.List;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/11/5 15:57
 * 修改人：xuan
 * 修改时间：2018/11/5 15:57
 * 修改备注：
 */
public class SwitchoverHostBean {

    /**
     * result : 00
     * message : success
     * data : {"list":[{"productkey":"a1UEceRK3Pu","devicename":"deviceName_123","name":"hello","date":"2018-12-19"},{"productkey":"a1UEceRK3Pu","devicename":"device_555","name":"主机1","date":"2019-01-25"}]}
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
             * productkey : a1UEceRK3Pu
             * devicename : deviceName_123
             * name : hello
             * date : 2018-12-19
             */

            private String productkey;
            private String devicename;
            private String name;
            private String date;

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
        }
    }
}
