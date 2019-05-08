package com.iot.zyx.android_jjiot.add_zigbeeactivity;

import java.util.List;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/11/29 14:18
 * 修改人：xuan
 * 修改时间：2018/11/29 14:18
 * 修改备注：
 */
public class AddZigBeeAPIBean {


    /**
     * result : 00
     * message : success
     * data : {"list":[{"productkey":"a1UEceRK3Pu","devicename":"deviceName_123","name":"灯控开关_7007","date":"2019-01-24","uuid":"8b6ad1d9ec593b2fb12d9b3c4d1e9ca9","deviceuid":"03-01","networkuid":"28-88","endpoint":"02","type":3,"node":[{"productkey":"a1UEceRK3Pu","devicename":"deviceName_123","name":"回路1","date":"2019-01-24","uuid":"0bc586aed1f73a4d82150403e8c3cc04","deviceuid":"03-01","networkuid":"28-88","endpoint":"02","type":3,"upUuid":"8b6ad1d9ec593b2fb12d9b3c4d1e9ca9","IEEE":"96-26-BA-1B-00-4B-12"},{"productkey":"a1UEceRK3Pu","devicename":"deviceName_123","name":"回路2","date":"2019-01-24","uuid":"1bda27abddae3c78ae411bd65d2ab78b","deviceuid":"03-01","networkuid":"28-88","endpoint":"01","type":3,"upUuid":"8b6ad1d9ec593b2fb12d9b3c4d1e9ca9","IEEE":"96-26-BA-1B-00-4B-12"}],"IEEE":"96-26-BA-1B-00-4B-12"}]}
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
             * name : 灯控开关_7007
             * date : 2019-01-24
             * uuid : 8b6ad1d9ec593b2fb12d9b3c4d1e9ca9
             * deviceuid : 03-01
             * networkuid : 28-88
             * endpoint : 02
             * type : 3
             * node : [{"productkey":"a1UEceRK3Pu","devicename":"deviceName_123","name":"回路1","date":"2019-01-24","uuid":"0bc586aed1f73a4d82150403e8c3cc04","deviceuid":"03-01","networkuid":"28-88","endpoint":"02","type":3,"upUuid":"8b6ad1d9ec593b2fb12d9b3c4d1e9ca9","IEEE":"96-26-BA-1B-00-4B-12"},{"productkey":"a1UEceRK3Pu","devicename":"deviceName_123","name":"回路2","date":"2019-01-24","uuid":"1bda27abddae3c78ae411bd65d2ab78b","deviceuid":"03-01","networkuid":"28-88","endpoint":"01","type":3,"upUuid":"8b6ad1d9ec593b2fb12d9b3c4d1e9ca9","IEEE":"96-26-BA-1B-00-4B-12"}]
             * IEEE : 96-26-BA-1B-00-4B-12
             */

            private String productkey;
            private String devicename;
            private String areaId;
            private String name;
            private String date;
            private String uuid;
            private String deviceuid;
            private String networkuid;
            private String endpoint;
            private int type;
            private String IEEE;
            private String action;
            private List<NodeBean> node;

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

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

            public String getNetworkuid() {
                return networkuid;
            }

            public void setNetworkuid(String networkuid) {
                this.networkuid = networkuid;
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

            public String getIEEE() {
                return IEEE;
            }

            public void setIEEE(String IEEE) {
                this.IEEE = IEEE;
            }

            public List<NodeBean> getNode() {
                return node;
            }

            public void setNode(List<NodeBean> node) {
                this.node = node;
            }

            public static class NodeBean {
                /**
                 * productkey : a1UEceRK3Pu
                 * devicename : deviceName_123
                 * name : 回路1
                 * date : 2019-01-24
                 * uuid : 0bc586aed1f73a4d82150403e8c3cc04
                 * deviceuid : 03-01
                 * networkuid : 28-88
                 * endpoint : 02
                 * type : 3
                 * upUuid : 8b6ad1d9ec593b2fb12d9b3c4d1e9ca9
                 * IEEE : 96-26-BA-1B-00-4B-12
                 */

                private String productkey;
                private String devicename;
                private String name;
                private String date;
                private String uuid;
                private String deviceuid;
                private String networkuid;
                private String endpoint;
                private int type;
                private String upUuid;
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

                public String getNetworkuid() {
                    return networkuid;
                }

                public void setNetworkuid(String networkuid) {
                    this.networkuid = networkuid;
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

                public String getUpUuid() {
                    return upUuid;
                }

                public void setUpUuid(String upUuid) {
                    this.upUuid = upUuid;
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
}
