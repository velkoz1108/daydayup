package com.example.operatelogspringbootstarter.v2.service.impl;


import com.example.operatelogspringbootstarter.v1.annotation.LogRecord;
import com.example.operatelogspringbootstarter.v1.context.LogRecordContext;
import com.example.operatelogspringbootstarter.v2.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Override
    @LogRecord(message = "'修改了订单的配送地址:'+ #oldAddress +'修改到' + #newAddress",
            businessId="#businessId", bizType = "#bizType_address()")
    @LogRecord(message = "'修改了订单的签收手机号码:'+ #oldPhone +'修改到' + #newPhone",
            businessId="#businessId", bizType = "#bizType_phone()")
    public String saveOrder(String newAddress,  String newPhone, String businessId ) {
        //读数据库老的地址
        String oldAddress = "望江";
        LogRecordContext.putVariables("oldAddress",oldAddress);
        String oldPhone = "17612176886";
        LogRecordContext.putVariables("oldPhone",oldPhone);

        log.info("TestService saveOrder  -----------oldAddress:{},newAddress:{}", oldAddress,newAddress);
        return "success";
    }

    @Override
    @LogRecord(message = "'修改了订单的配送地址:'+ #oldAddress +'修改到' + #newAddress",
            businessId="#businessId", bizType = "#bizType")
//    @LogRecord(content = "'修改了订单的配送地址:'+ #oldAddress +'修改到' + #newAddress")
    public String saveOrder(String oldAddress, String newAddress, String businessId, String bizType ) {
        log.info("TestService saveOrder  -----------oldAddress:{},newAddress:{}",oldAddress, newAddress);
        return "success";
    }

    @Override
    @LogRecord(message = "'外呼客户'",
            businessId="#businessId", bizType = "#bizType")
    public String call(String businessId, String bizType){
        log.info("TestService call  -----------businessId:{},bizType:{}",businessId, bizType);
        return "success";
    }

}
