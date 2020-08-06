package com.eden.aopdemo.obj;

import com.eden.aopdemo.EdenAnnotation;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @EdenAnnotation(name = "from userService")
    public void testAnnotation() {
        System.out.println("testAnnotation -> I am UserService");
    }

    public void testExecution() {
        System.out.println("testExecution I am UserService");
    }
}
