package io.kimmking.rpcfx.exception;

/**
 * Description:全局RpcException
 * Created on 2020/12/18 0:06.
 *
 * @author yezp
 */
public class RpcException extends RuntimeException {


    private int errorCode;

    private String errorMsg = ""; // 异常描述

    public RpcException() {}

    public RpcException(String message , int errorCode, String errorMsg) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
