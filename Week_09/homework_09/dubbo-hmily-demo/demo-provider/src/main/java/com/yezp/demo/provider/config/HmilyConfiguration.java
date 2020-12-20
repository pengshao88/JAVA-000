package com.yezp.demo.provider.config;

import org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Description:
 * Created on 2020/12/20 14:11.
 *
 * @author yezp
 */
@Configuration
public class HmilyConfiguration {

    @Bean
    @Primary
    public BeanPostProcessor refererAnnotationBeanPostProcessor() {
        return new ReferenceAnnotationBeanPostProcessor();
    }
}
