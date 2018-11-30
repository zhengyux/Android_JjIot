package com.iot.zyx.android_jjiot;

/**
 * 项目名称：Android_JjIot
 * 类描述：
 * 创建人：xuan
 * 创建时间：2018/11/30 8:45
 * 修改人：xuan
 * 修改时间：2018/11/30 8:45
 * 修改备注：
 */
public class BaseRespone {

    /**
     * result : 00
     * message : success
     */

    private String result;
    private String message;

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
}
