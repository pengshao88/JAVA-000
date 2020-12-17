package io.kimmking.rpcfx.exception;

/**
 * Description:
 * Created on 2020/12/18 0:14.
 *
 * @author yezp
 */
public enum ResponseCode {

    CLASS_NOT_FOUND(1000, "找不到对应的Invoker"),
    ;
    int code;
    String desc;
    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
