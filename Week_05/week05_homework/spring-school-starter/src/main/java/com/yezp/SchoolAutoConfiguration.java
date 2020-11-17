package com.yezp;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:自动配置类
 * Created on 2020/11/16 22:07.
 *
 * @author yezp
 */
@EnableConfigurationProperties(SchoolProperties.class)
@Configuration
public class SchoolAutoConfiguration {

    @Bean
    Student student(SchoolProperties schoolProperties) {
        Student student = new Student(schoolProperties.getStuId(), schoolProperties.getStuName());
        return student;
    }

    @Bean
    Klass klass(SchoolProperties schoolProperties) {
        Klass klass = new Klass();
        List<Student> students = new ArrayList<>();
        students.add(student(schoolProperties));
        klass.setStudents(students);
        return klass;
    }

    @Bean
    School school(SchoolProperties schoolProperties) {
        School school = new School();
        school.setClass1(klass(schoolProperties));
        school.setStudent(student(schoolProperties));
        return school;
    }

}
