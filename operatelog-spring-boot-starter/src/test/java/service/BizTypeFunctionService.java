package service;


import com.example.operatelogspringbootstarter.v1.annotation.LogRecordFunc;

@LogRecordFunc("bizType")
public class BizTypeFunctionService {

    @LogRecordFunc("order")
    public static String order(){
        return "order";
    }

    @LogRecordFunc("jifen")
    public static String jifen(){
        return "jifen";
    }

}
