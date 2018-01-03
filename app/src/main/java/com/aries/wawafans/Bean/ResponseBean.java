package com.aries.wawafans.Bean;

/**
 * Created by kyly on 2017/7/20.
 */

public class ResponseBean {
    private String status;
    private String dataObject;
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDataObject() {
        return dataObject;
    }

    public void setDataObject(String dataObject) {
        this.dataObject = dataObject;
    }

    public boolean isSuccess(boolean checkLength){
        if (checkLength){
            return status == "ok" && dataObject.length() > 10;
        } else {
            return status == "ok";
        }
    }
}
