package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.RpcfxResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DemoResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private static final Map<String, Object> beanMap = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object resolve(String serviceClass) {
        Object bean = beanMap.get(serviceClass);
        try {
            if (bean == null) {
                bean = applicationContext.getBean(Class.forName(serviceClass));
                beanMap.putIfAbsent(serviceClass, bean);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return bean;
    }
}
