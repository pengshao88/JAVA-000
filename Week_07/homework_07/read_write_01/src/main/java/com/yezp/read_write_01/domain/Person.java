package com.yezp.read_write_01.domain;

/**
 * Description:
 * Created on 2020/12/3 0:42.
 *
 * @author yezp
 */
public class Person {

    private long id;

    private String name;

    public Person(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
