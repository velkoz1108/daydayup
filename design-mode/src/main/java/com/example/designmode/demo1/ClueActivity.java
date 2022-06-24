package com.example.designmode.demo1;

import com.example.designmode.demo1.strategy.ClueStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

//模版模式：统一请求参数校验和统一异常数据记录
@Slf4j
public class ClueActivity {

    private ClueStrategy clueStrategy;

    public ClueActivity(ClueStrategy clueStrategy){
        this.clueStrategy = clueStrategy;
    }

    public void exec(){
        if(checkParam()){
            try {
                clueStrategy.doExecuteClue();
            } catch (Exception e) {
                e.printStackTrace();
                //如果失败，记录失败记录
                saveFailRecord();
            }
        }

    }

    private Boolean checkParam(){
        Boolean checkFlag = new Random().nextBoolean();
        log.info("统一校验参数 【checkFlag:{}】",checkFlag);
        return checkFlag;
    }

    private void saveFailRecord(){
        log.info("保存失败记录");
    }



}
