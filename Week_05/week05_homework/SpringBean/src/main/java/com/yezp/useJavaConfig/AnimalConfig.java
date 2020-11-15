package com.yezp.useJavaConfig;

import com.yezp.Sheep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Created on 2020/11/15 21:21.
 *
 * @author yezp
 */
@Configuration
public class AnimalConfig {

    @Bean
    public Sheep getSheep() {
        return new Sheep();
    }

}