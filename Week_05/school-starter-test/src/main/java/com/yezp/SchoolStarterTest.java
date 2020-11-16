package com.yezp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 * Created on 2020/11/16 22:47.
 *
 * @author yezp
 */
@SpringBootApplication
public class SchoolStarterTest implements CommandLineRunner {

    @Autowired
    private School school;

    public static void main(String[] args) {
        SpringApplication.run(SchoolStarterTest.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(school.getStudent().toString());
        school.ding();
        school.getClass1().dong();
    }
}
