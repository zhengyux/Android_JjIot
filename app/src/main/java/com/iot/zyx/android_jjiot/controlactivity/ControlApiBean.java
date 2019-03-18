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
     * data : {"light":[{"productkey":"a1UEceRK3Pu","devicename":"deviceName_123","areaId":"10002","name":"device_wIM2","date":"2018-12-17T06:39:22.000+0000","uuid":"29557fe197cd30829ee498a38b76d7ed","deviceuid":"CC-CD","endpoint":"02","type":1,"onoff":0,"value":50,"IEEE":"77-44-6F-02-00-4B-12-00"}],"curtain":[{"productkey":"a1UEceRK3Pu","devicename":"deviceName_123","areaId":"10002","name":"device_wIM2","date":"2018-12-17T06:39:22.000+0000","uuid":"29557fe197cd30829ee498a38b76d7ed","deviceuid":"CC-CD","endpoint":"02","type":2,"motorPosi":100,"IEEE":"77-44-6F-02-00-4B-12-00"}]}
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
        private List<LightBean> light;
        private List<CurtainBean> curtain;
        private List<OnoffSwitchBean> onoffSwitch;
        private List<MultNodeSensorBean> multNodeSensor;
        private List<RemoteControlDeviceBean> remoteControlDevice;

        public List<RemoteControlDeviceBean> getRemoteControlDevice() {
            return remoteControlDevice;
        }

        public void setRemoteControlDevice(List<RemoteControlDeviceBean> remoteControlDevice) {
            this.remoteControlDevice = remoteControlDevice;
        }

        public List<MultNodeSensorBean> getMultNodeSensor() {
            return multNodeSensor;
        }

        public void setMultNodeSensor(List<MultNodeSensorBean> multNodeSensor) {
            this.multNodeSensor = multNodeSensor;
        }

        public List<LightBean> getLight() {
            return light;
        }

        public void setLight(List<LightBean> light) {
            this.light = light;
        }

        public List<CurtainBean> getCurtain() {
            return curtain;
        }

        public void setCurtain(List<CurtainBean> curtain) {
            this.curtain = curtain;
        }

        public List<OnoffSwitchBean> getOnoffSwitch() {
            return onoffSwitch;
        }

        public void setOnoffSwitch(List<OnoffSwitchBean> onoffSwitch) {
            this.onoffSwitch = onoffSwitch;
        }



        public static class LightBean {
            /**
             * productkey : a1UEceRK3Pu
             * devicename : deviceName_123
             * areaId : 10002
             * name : device_wIM2
             * date : 2018-12-17T06:39:22.000+0000
             * uuid : 29557fe197cd30829ee498a38b76d7ed
             * deviceuid : CC-CD
             * endpoint : 02
             * type : 1
             * onoff : 0
             * value : 50
             * IEEE : 77-44-6F-02-00-4B-12-00
             */

            private String productkey;
            private String devicename;
            private String areaId;
            private String name;
            private String date;
            private String uuid;
            private String deviceuid;
            private String endpoint;
            private int type;
            private int onoff;
            private int value;
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

            public int getOnoff() {
                return onoff;
            }

            public void setOnoff(int onoff) {
                this.onoff = onoff;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public String getIEEE() {
                return IEEE;
            }

            public void setIEEE(String IEEE) {
                this.IEEE = IEEE;
            }
        }

        public static class CurtainBean {
            /**
             * productkey : a1UEceRK3Pu
             * devicename : deviceName_123
             * areaId : 10002
             * name : device_wIM2
             * date : 2018-12-17T06:39:22.000+0000
             * uuid : 29557fe197cd30829ee498a38b76d7ed
             * deviceuid : CC-CD
             * endpoint : 02
             * type : 2
             * motorPosi : 100
             * IEEE : 77-44-6F-02-00-4B-12-00
             */

            private String productkey;
            private String devicename;
            private String areaId;
            private String name;
            private String date;
            private String uuid;
            private String deviceuid;
            private String endpoint;
            private int type;
            private int motorPosi;
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

            public int getMotorPosi() {
                return motorPosi;
            }

            public void setMotorPosi(int motorPosi) {
                this.motorPosi = motorPosi;
            }

            public String getIEEE() {
                return IEEE;
            }

            public void setIEEE(String IEEE) {
                this.IEEE = IEEE;
            }
        }

        public static class OnoffSwitchBean {
            /**
             * productkey : a1UEceRK3Pu
             * devicename : deviceName_123
             * areaId : 10002
             * name : device_wIM2
             * date : 2018-12-17T06:39:22.000+0000
             * uuid : 29557fe197cd30829ee498a38b76d7ed
             * deviceuid : CC-CD
             * endpoint : 02
             * type : 3
             * IEEE : 77-44-6F-02-00-4B-12-00
             * node : [{"productkey":"a1UEceRK3Pu","devicename":"deviceName_123","name":"回路1","date":"2019-01-24","uuid":"0bc586aed1f73a4d82150403e8c3cc04","deviceuid":"03-01","networkuid":"28-88","endpoint":"02","type":3,"upUuid":"8b6ad1d9ec593b2fb12d9b3c4d1e9ca9","IEEE":"96-26-BA-1B-00-4B-12"},{"productkey":"a1UEceRK3Pu","devicename":"deviceName_123","name":"回路2","date":"2019-01-24","uuid":"1bda27abddae3c78ae411bd65d2ab78b","deviceuid":"03-01","networkuid":"28-88","endpoint":"01","type":3,"upUuid":"8b6ad1d9ec593b2fb12d9b3c4d1e9ca9","IEEE":"96-26-BA-1B-00-4B-12"}]
             */

            private String productkey;
            private String devicename;
            private String areaId;
            private String name;
            private String date;
            private String uuid;
            private String deviceuid;
            private String endpoint;
            private int type;
            private String IEEE;
            private List<NodeBean> node;

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
                private int onoff;


                public int getOnoff() {
                    return onoff;
                }

                public void setOnoff(int onoff) {
                    this.onoff = onoff;
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

        public static class MultNodeSensorBean{
            private String productkey;
            private String devicename;
            private String areaId;
            private String name;
            private String date;
            private String uuid;
            private String deviceuid;
            private String endpoint;
            private int type;
            private String IEEE;
            private List<NodeBean> node;

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
                private int humidity;
                private int celsius;
                private int fahrenheit;
                private int light;

                public int getHumidity() {
                    return humidity;
                }

                public void setHumidity(int humidity) {
                    this.humidity = humidity;
                }

                public int getCelsius() {
                    return celsius;
                }

                public void setCelsius(int celsius) {
                    this.celsius = celsius;
                }

                public int getFahrenheit() {
                    return fahrenheit;
                }

                public void setFahrenheit(int fahrenheit) {
                    this.fahrenheit = fahrenheit;
                }

                public int getLight() {
                    return light;
                }

                public void setLight(int light) {
                    this.light = light;
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

        public static class RemoteControlDeviceBean{

            private String productkey;
            private String devicename;
            private String name;
            private String date;
            private String uuid;
            private String deviceuid;
            private String networkuid;
            private String endpoint;
            private String type;
            private String areaId;
            private String IEEE;
            private List<RemoteControlBean> remoteControl;

            public List<RemoteControlBean> getRemoteControl() {
                return remoteControl;
            }

            public void setRemoteControl(List<RemoteControlBean> remoteControl) {
                this.remoteControl = remoteControl;
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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAreaId() {
                return areaId;
            }

            public void setAreaId(String areaId) {
                this.areaId = areaId;
            }

            public String getIEEE() {
                return IEEE;
            }

            public void setIEEE(String IEEE) {
                this.IEEE = IEEE;
            }

            public static class RemoteControlBean {
                /**
                 * id : 10025
                 * areaId : 1
                 * name : 一体机
                 * date : 2019-03-15
                 * deviceUuid : e8a13ad5e30e3b25b44f041cd0237429
                 * type : 0501
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
}
