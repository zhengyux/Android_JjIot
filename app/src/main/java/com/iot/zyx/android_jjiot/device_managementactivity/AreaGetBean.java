package com.iot.zyx.android_jjiot.device_managementactivity;

import java.util.List;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/12/3 14:59
 * 修改人：xuan
 * 修改时间：2018/12/3 14:59
 * 修改备注：
 */
public class AreaGetBean {

    /**
     * result : 00
     * message : success
     * data : {"list":[{"id":10001,"name":"一楼大厅22","encode":"A1234"}]}
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
             * id : 10001
             * name : 一楼大厅22
             * encode : A1234
             */

            private int id;
            private String name;
            private String encode;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEncode() {
                return encode;
            }

            public void setEncode(String encode) {
                this.encode = encode;
            }
        }
    }
}
