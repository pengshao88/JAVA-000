package com.yezp;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class School implements ISchool {
    
    // Resource 
    @Autowired(required = true)
    Klass class1;
    
    @Resource(name = "student")
    Student student;
    
    @Override
    public void ding(){
        System.out.println("Class1 have " + this.class1.getStudents().size()
                + " students and one is " + this.student);
    }

    public Klass getClass1() {
        return class1;
    }

    public void setClass1(Klass class1) {
        this.class1 = class1;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
