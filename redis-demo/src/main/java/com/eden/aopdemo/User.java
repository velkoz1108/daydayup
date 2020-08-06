package com.eden.aopdemo;

import java.io.Serializable;

public class User implements Serializable {
    private String name;

    private Integer age;

    private Boolean locked;

    private String address;

    public User() {
    }

    public User(String name, Integer age, Boolean locked, String address) {
        this.name = name;
        this.age = age;
        this.locked = locked;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void testAnnotation() {
        System.out.println("name = " + name);
    }
}
