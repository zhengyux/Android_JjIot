package com.iot.zyx.android_jjiot.air_conditioningactivity;

import java.util.List;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2019/3/15 9:22
 * 修改人：xuan
 * 修改时间：2019/3/15 9:22
 * 修改备注：
 */
public class RemoteListBean {

    /**
     * result : 00
     * message : success
     * data : {"list":[{"id":10000,"areaId":"10002","name":"大厅空调遥控器","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f30","type":"0500"},{"id":10002,"areaId":"10002","name":"遥控器","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f30","type":"0500"},{"id":10003,"areaId":"10002","name":"大调遥控器","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f30","type":"0500"},{"id":10004,"areaId":"10002","name":"大调遥控器","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f30","type":"0500"},{"id":10005,"areaId":"10002","name":"大调遥器","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f30","type":"0500"},{"id":10006,"areaId":"10002","name":"大调遥器","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f30","type":"0500"},{"id":10007,"areaId":"10002","name":"大遥器","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f30","type":"0500"},{"id":10008,"areaId":"10002","name":"大222遥器","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f30","type":"0500"},{"id":10009,"areaId":"10002","name":"大222666遥器","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f30","type":"0500"},{"id":10010,"areaId":"10002","name":"大222666遥器","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f30","type":"0500"},{"id":10011,"areaId":"10002","name":"大调555遥器","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f30","type":"0500"},{"id":10013,"areaId":"10002","name":"大调555遥器","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f30","type":"0500"},{"id":10014,"areaId":"10002","name":"大调555遥器","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f30","type":"0500"},{"id":10015,"areaId":"10002","name":"大调遥器","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f30","type":"0500"},{"id":10016,"areaId":"10002","name":"大调遥器","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f30","type":"0500"},{"id":10017,"areaId":"10002","name":"大调遥器","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f30","type":"0500"},{"id":10018,"areaId":"10002","name":"啊啊啊啊","date":"2019-03-13","deviceUuid":"5526e32295c23f15a368ab5129f91f32","type":"0500"},{"id":10019,"areaId":"10002","name":"啊路","date":"2019-03-15","deviceUuid":"5526e32295c23f15a368ab5129f91f32","type":"0500"},{"id":10020,"areaId":"10002","name":"123","date":"2019-03-15","deviceUuid":"5526e32295c23f15a368ab5129f91f32","type":"0500"}]}
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
             * areaId : 10002
             * name : 大厅空调遥控器
             * date : 2019-03-13
             * deviceUuid : 5526e32295c23f15a368ab5129f91f30
             * type : 0500
             */

            private int id;
            private String areaId;
            private String name;
            private String date;
            private String deviceUuid;
            private String type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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
    }
}
