package com.example.designmode.strategy.impl;

import com.example.designmode.strategy.ClueStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class OtherClueStrategy implements ClueStrategy {

    @Override
    public void doExecuteClue() throws Exception {
        Boolean flag = new Random().nextBoolean();
        log.info("-----OtherClueStrategy 执行【其他】过来的线索 【flag:{}】-------",flag);
        if(flag){
            throw new Exception("未知异常");
        }
    }
}
