package com.example.exceldemo.service;


import com.example.operatelogspringbootstarter.v1.annotation.LogRecordFunc;

@LogRecordFunc("bizType")
public class BizTypeFunctionService {

    @LogRecordFunc("order")
    public static String order(){
        return "order";
    }

    @LogRecordFunc("address")
    public static String address(){
        return "address";
    }

    @LogRecordFunc("phone")
    public static String phone(){
        return "phone";
    }

    @LogRecordFunc("jifen")
    public static String jifen(){
        return "jifen";
    }

}
