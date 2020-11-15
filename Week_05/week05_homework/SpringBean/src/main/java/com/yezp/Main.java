package com.yezp;

import com.yezp.useAnnotation.ZooService;
import com.yezp.useJavaConfig.AnimalConfig;
import com.yezp.useXml.AnimalService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description:
 * Created on 2020/11/15 20:49.
 *
 * @author yezp
 */
@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ZooService zooService = (ZooService) context.getBean("zooService");
        zooService.animalShow();

        AnimalService animalService = (AnimalService) context.getBean("animalService");
        animalService.walkFun();

        System.out.println("--------------------------");
        AnimalConfig animalConfig = (AnimalConfig) context.getBean("animalConfig");
        Sheep sheep = animalConfig.getSheep();
        System.out.println(sheep.say());
    }

}
