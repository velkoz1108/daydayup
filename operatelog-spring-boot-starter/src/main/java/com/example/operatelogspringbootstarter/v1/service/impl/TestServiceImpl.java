package com.example.operatelogspringbootstarter.v1.service.impl;


import com.example.operatelogspringbootstarter.v1.annotation.LogRecord;
import com.example.operatelogspringbootstarter.v1.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Override
    @LogRecord(content = "'修改了订单的配送地址:'+ #oldAddress +'修改到' + #newAddress",
            businessId="#businessId", bizType = "#bizType")
//    @LogRecord(content = "'修改了订单的配送地址:'+ #oldAddress +'修改到' + #newAddress")
    public String saveOrder(String oldAddress, String newAddress, String businessId, String bizType ) {
        log.info("TestService saveOrder  -----------oldAddress:{},newAddress:{}",oldAddress, newAddress);
        return "success";
    }

    @Override
    @LogRecord(content = "外呼客户",
            businessId="#businessId", bizType = "#bizType")
    public String call(String businessId, String bizType){
        log.info("TestService call  -----------businessId:{},bizType:{}",businessId, bizType);
        return "success";
    }

}
