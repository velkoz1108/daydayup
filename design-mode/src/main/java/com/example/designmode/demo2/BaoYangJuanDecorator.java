package com.example.designmode.demo2;

import lombok.extern.slf4j.Slf4j;

/**
 * 赠送保养劵装饰器类
 * */
@Slf4j
public class BaoYangJuanDecorator extends IntegralDecorator{
    public BaoYangJuanDecorator(Integral integral) {
        super(integral);
    }

    @Override
    public void addIntegral(Long id,Integer count){
        super.addIntegral(id,count);
        //赠送劵
        addBaoYangJuan(id);
    }

    public void addBaoYangJuan(Long id){
        log.info("赠送客户【{}】保养劵一张",id);
    }

}
