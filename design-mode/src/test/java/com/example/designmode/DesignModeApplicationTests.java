package com.example.designmode;

import com.example.designmode.factory.StrategyFactory;
import com.example.designmode.strategy.ClueStrategy;
import com.example.designmode.strategy.impl.DongCheDiClueStrategy;
import com.example.designmode.strategy.impl.DouYinClueStrategy;
import com.example.designmode.strategy.impl.OtherClueStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignModeApplicationTests {

    // 没有工厂类的时候
    //需要写大量的if else
    @Test
    void contextLoads() {
        ClueActivity clueActivity = null ;
        String source = "dongchedi";
        if(source.equals("douyin")){
            clueActivity = new ClueActivity(new DouYinClueStrategy());
        }else if(source.equals("dongchedi")){
            clueActivity = new ClueActivity(new DongCheDiClueStrategy());
        }else {
            clueActivity = new ClueActivity(new OtherClueStrategy());
        }
        clueActivity.exec();
    }

    @Test
    void test(){
        String source = "douyin";
        ClueStrategy clueStrategy = StrategyFactory.getClueStrategy(source);
        ClueActivity clueActivity = new ClueActivity(clueStrategy);
        clueActivity.exec();
    }



}
