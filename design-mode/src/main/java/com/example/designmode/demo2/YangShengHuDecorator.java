package com.example.designmode.demo2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class YangShengHuDecorator extends IntegralDecorator{
    public YangShengHuDecorator(Integral integral) {
        super(integral);
    }

    @Override
    public void addIntegral(Long id,Integer count){
        super.addIntegral(id,count);
        //赠送劵
        addYangShengHu(id);
    }

    public void addYangShengHu(Long id){
        log.info("赠送客户【{}】养生壶一套",id);
    }

}
