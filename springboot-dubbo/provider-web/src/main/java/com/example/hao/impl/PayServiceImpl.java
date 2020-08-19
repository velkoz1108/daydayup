package com.example.hao.impl;

import org.apache.dubbo.config.annotation.Service;
import org.example.hao.IPayService;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceName = "payService")
public class PayServiceImpl implements IPayService {


    //执行支付的服务
    @Override
    public String pay(String info) {
        System.out.println("execute pay："+info);
        return "Hello Dubbo :"+info;
    }
}
