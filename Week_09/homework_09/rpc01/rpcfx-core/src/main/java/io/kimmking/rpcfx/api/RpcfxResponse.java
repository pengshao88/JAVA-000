package io.kimmking.rpcfx.api;

import io.kimmking.rpcfx.exception.RpcException;

public class RpcfxResponse {

    private Object result;

    private boolean success;

    private RpcException exception;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public RpcException getException() {
        return exception;
    }

    public void setException(RpcException exception) {
        this.exception = exception;
    }
}
