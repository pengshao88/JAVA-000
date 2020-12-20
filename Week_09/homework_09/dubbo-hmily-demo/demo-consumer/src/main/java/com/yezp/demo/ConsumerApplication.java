package com.yezp.demo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 * Created on 2020/12/20 15:04.
 *
 * @author yezp
 */
@EnableDubbo
@SpringBootApplication
@MapperScan("com.yezp.api.mapper")
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
