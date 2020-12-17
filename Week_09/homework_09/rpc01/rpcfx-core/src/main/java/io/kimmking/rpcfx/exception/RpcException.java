package io.kimmking.rpcfx.exception;

/**
 * Description:全局RpcException
 * Created on 2020/12/18 0:06.
 *
 * @author yezp
 */
public class RpcException extends RuntimeException {

    private Throwable cause = null;
    private int errorCode;

    private String errorMsg = ""; // 异常描述

    public RpcException(Throwable e) {
        cause = e;
    }

    public RpcException(String message, Throwable cause, int errorCode, String errorMsg) {
        super(message);
        this.cause = cause;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
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
