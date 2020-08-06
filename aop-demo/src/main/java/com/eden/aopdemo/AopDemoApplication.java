package com.eden.aopdemo;

import com.eden.aopdemo.obj.Person;
import com.eden.aopdemo.obj.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AopDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(AopDemoApplication.class, args);
    }

    @Autowired
    private UserService userService;

    @GetMapping("/testService")
    public String testService() {
        userService.testAnnotation();

        userService.testExecution();

        return "OK";
    }

    @GetMapping("/testEntity")
    public String testEntity() {
        Person eden = new Person("eden");

        eden.testAnnotation();

        eden.testExecution();

        return "OK";
    }
}
