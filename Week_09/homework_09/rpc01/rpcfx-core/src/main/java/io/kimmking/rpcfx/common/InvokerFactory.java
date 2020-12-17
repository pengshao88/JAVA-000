package io.kimmking.rpcfx.common;

import io.kimmking.rpcfx.api.RpcInvoker;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Description:
 * https://github.com/LiveOrange/JAVA-000/commit/8ed7b49ba6afbae8922eec0a1e4aed983c0cdb83
 *
 * 一个同学用javassist实现的代替服务端反射，很棒
 * Created on 2020/12/17 23:16.
 */
public class InvokerFactory {

    private final static Logger logger = LoggerFactory.getLogger(InvokerFactory.class);

    private final static ConcurrentHashMap<Class<?>, RpcInvoker> INVOKER_MAP = new ConcurrentHashMap<>();
    private static final AtomicLong CLASS_COUNTER = new AtomicLong(0);

    public static RpcInvoker getInvoker(Class<?> clazz) throws Exception {
        RpcInvoker methodInvoker = INVOKER_MAP.get(clazz);
        if (methodInvoker != null) {
            return methodInvoker;
        }

        String className = clazz.getName();
        // javassist
        ClassPool pool = ClassPool.getDefault();
        // 获取声明方法
        Method[] methods = clazz.getDeclaredMethods();
        CtClass invokerClass = pool.makeClass(RpcInvoker.class.getName() + CLASS_COUNTER.getAndIncrement(),
                pool.getCtClass("io.kimmking.rpcfx.api.RpcInvoker"));

        // 用字符串拼接整个类？ 再构造出来？？
        StringBuilder methodStr = new StringBuilder("public Object invoke(Object service, String method, Object[] params){");
        for (Method method : methods) {
            Class<?> returnType = method.getReturnType();
            String methodName = method.getName();
            methodStr.append("if(method.equals(\"").append(methodName).append("\"))");
            if (returnType.getName().equals("void")) {
                methodStr.append("{((").append(className).append(")service).").append(methodName).append("(");
            } else {
                methodStr.append("return ((").append(className).append(")service).").append(methodName).append("(");
            }
            int paramCount = method.getParameterCount();
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int j = 0; j < paramCount; j++) {
                Class<?> cls = parameterTypes[j];
                methodStr.append("(").append(cls.getName()).append(")params[").append(j).append("]");
                if ((j + 1) < paramCount)
                    methodStr.append(",");
            }
            methodStr.append(");");
            if (returnType.getName().equals("void")) {
                methodStr.append("return null;");
                methodStr.append("}");
            }
        }

        methodStr.append("return null;}");
        invokerClass.addMethod(CtNewMethod.make(methodStr.toString(), invokerClass));
        methodInvoker = (RpcInvoker) invokerClass.toClass().newInstance();
        INVOKER_MAP.putIfAbsent(clazz, methodInvoker);
        return methodInvoker;
    }



}