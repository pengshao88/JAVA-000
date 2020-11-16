package com.yezp;


import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;

    public Student() {
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Student create(int id, String name){
        return new Student(id,name);
    }
}
