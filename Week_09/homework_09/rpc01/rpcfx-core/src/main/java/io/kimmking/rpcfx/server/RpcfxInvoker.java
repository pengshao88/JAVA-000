package io.kimmking.rpcfx.server;

import io.kimmking.rpcfx.api.RpcInvoker;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResolver;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.common.InvokerFactory;
import io.kimmking.rpcfx.exception.ResponseCode;
import io.kimmking.rpcfx.exception.RpcException;

import java.lang.reflect.Method;
import java.util.Arrays;

public class RpcfxInvoker {

    private RpcfxResolver resolver;

    public RpcfxInvoker(RpcfxResolver resolver){
        this.resolver = resolver;
    }

    public RpcfxResponse invoke(RpcfxRequest request) {
        RpcfxResponse response = new RpcfxResponse();
        String serviceClass = request.getServiceClass();

        try {
            // 作业1 使用javassist替换
            Object service = resolver.resolve(serviceClass);
            RpcInvoker rpcInvoker = InvokerFactory.getInvoker(service.getClass());

            Object result = rpcInvoker.invoke(service, request.getMethod(), request.getParams());
            response.setResult(result);
            response.setSuccess(true);
            return response;
        } catch (Exception e) {
            // 2.封装一个统一的RpcfxException
            RpcException exception = new RpcException(e);
            exception.setErrorCode(ResponseCode.CLASS_NOT_FOUND.getCode());
            exception.setErrorMsg("InvokerFactory getInvoker fail.");
            response.setException(exception);
            response.setSuccess(false);
            return response;
        }


//        // 作业1：改成泛型和反射
//        Object service = resolver.resolve(serviceClass);//this.applicationContext.getBean(serviceClass);
//
//        try {
//            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
//            Object result = method.invoke(service, request.getParams()); // dubbo, fastjson,
//            // 两次json序列化能否合并成一个
//            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
//            response.setStatus(true);
//            return response;
//        } catch ( IllegalAccessException | InvocationTargetException e) {
//
//            // 3.Xstream
//
//            // 2.封装一个统一的RpcfxException
//            // 客户端也需要判断异常
//            e.printStackTrace();
//            response.setException(e);
//            response.setStatus(false);
//            return response;
//        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }

}
