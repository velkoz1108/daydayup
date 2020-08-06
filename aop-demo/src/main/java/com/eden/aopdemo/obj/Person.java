package com.eden.aopdemo.obj;

import com.eden.aopdemo.EdenAnnotation;

public class Person {
    private String name;

    public String getName() {
        return name;
    }

    public Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @EdenAnnotation(name = "from person")
    public void testAnnotation() {
        System.out.println("testAnnotation -> My name is " + this.name + ".");
    }

    public void testExecution() {
        System.out.println("testExecution -> My name is " + this.name + ".");
    }
}
