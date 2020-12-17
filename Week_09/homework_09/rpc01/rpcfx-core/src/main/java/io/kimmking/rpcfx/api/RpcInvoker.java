package io.kimmking.rpcfx.api;

/**
 * Description:
 * Created on 2020/12/17 23:24.
 *
 * @author yezp
 */
public abstract class RpcInvoker {

    /**
     * rpc 方法执行
     *
     * @param instance
     * @param method
     * @param params
     * @return
     */
    public abstract Object invoke(Object instance, String method, Object[] params);

}
