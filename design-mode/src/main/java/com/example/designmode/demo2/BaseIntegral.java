package com.example.designmode.demo2;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BaseIntegral implements Integral{

    // 封装假数据
    private static final Map<Long,Integer> INTEGRAL_MAP = new HashMap<Long,Integer>();
    static {
        INTEGRAL_MAP.put(1L,100);
        INTEGRAL_MAP.put(2L,150);
        INTEGRAL_MAP.put(3L,50);
        INTEGRAL_MAP.put(4L,90);
    }


    @Override
    public void addIntegral(Long id,Integer count) {
        Integer oldCount = INTEGRAL_MAP.get(id);
        INTEGRAL_MAP.put(id,INTEGRAL_MAP.get(id)+count);
        log.info(" 基本操作 加积分 BaseIntegral addIntegral 【ID:{}，原始积分：{}，加完积分分数:{}】",id,oldCount,INTEGRAL_MAP.get(id));

    }
}
