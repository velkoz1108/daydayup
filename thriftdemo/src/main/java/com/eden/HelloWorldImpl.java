package com.eden;

import org.apache.thrift.TException;

public class HelloWorldImpl implements HelloWorldService.Iface {
    @Override
    public String sayHello(String username) throws TException {
        System.out.println("request from " + username);
        return "Hello " + username + " !";
    }
}
