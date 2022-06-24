package com.example.designmode.demo1.strategy.impl;

import com.example.designmode.demo1.strategy.ClueStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class DouYinClueStrategy implements ClueStrategy {

    @Override
    public void doExecuteClue() throws Exception {
        Boolean flag = new Random().nextBoolean();
        log.info("-----DouYinClueStrategy 执行【抖音】过来的线索 【flag:{}】-------",flag);
        if(flag){
            throw new Exception("服务调用异常");
        }
    }
}
