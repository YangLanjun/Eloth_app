package cn.yanglj65.www.ecloth_app.Entity;

import java.io.Serializable;

public class Result implements Serializable {
    private int errCode;
    private String msg;
    private Object data;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "errCode=" + errCode +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}