package com.iot.zyx.android_jjiot.add_air_telecontrolleractivity;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2019/3/13 15:41
 * 修改人：xuan
 * 修改时间：2019/3/13 15:41
 * 修改备注：
 */
public class AirBean {


    /**
     * result : 00
     * message : success
     * data : {"list":[{"brand":"海尔","list":["0001","0002","0003","0004","0005","0006","0007","0008","0009","0010","0011","0012","0013","0014","0015","0016","0017","0018","0019"]},{"brand":"美的","list":["0040","0041","0042","0043","0044","0045","0046","0047","0048","0049","0050","0051","0052","0053","0054","0055","0056","0057","0058","0059"]}]}
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


        public static class ListBean implements IPickerViewData{
            /**
             * brand : 海尔
             * list : ["0001","0002","0003","0004","0005","0006","0007","0008","0009","0010","0011","0012","0013","0014","0015","0016","0017","0018","0019"]
             */

            private String brand;
            private List<String> list;

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public List<String> getList() {
                return list;
            }

            public void setList(List<String> list) {
                this.list = list;
            }

            @Override
            public String getPickerViewText() {
                return this.brand;
            }
        }
    }
}
