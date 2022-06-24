package com.example.designmode;

import com.example.designmode.demo1.ClueActivity;
import com.example.designmode.demo1.factory.StrategyFactory;
import com.example.designmode.demo1.strategy.ClueStrategy;
import com.example.designmode.demo1.strategy.impl.DongCheDiClueStrategy;
import com.example.designmode.demo1.strategy.impl.DouYinClueStrategy;
import com.example.designmode.demo1.strategy.impl.OtherClueStrategy;
import com.example.designmode.demo2.BaoYangJuanDecorator;
import com.example.designmode.demo2.BaseIntegral;
import com.example.designmode.demo2.Integral;
import com.example.designmode.demo2.YangShengHuDecorator;
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
    void testClue(){
        String source = "douyin";
        ClueStrategy clueStrategy = StrategyFactory.getClueStrategy(source);
        ClueActivity clueActivity = new ClueActivity(clueStrategy);
        clueActivity.exec();
    }

    @Test
    void testIntegral(){
        //仅赠送积分
        Integral integral = new BaseIntegral();
        integral.addIntegral(1L,20);
        System.out.println("------------------------------------------------------------------------------");

        //积分+保养劵
        Integral integral1 = new BaoYangJuanDecorator(integral);
        integral1.addIntegral(2L,20);
        System.out.println("------------------------------------------------------------------------------");

        //积分+养生壶
        Integral integral2 = new YangShengHuDecorator(integral);
        integral2.addIntegral(3L,20);
        System.out.println("------------------------------------------------------------------------------");


        //积分+养生壶+保养劵
        Integral integral3 = new YangShengHuDecorator(integral);
        Integral integral4 = new BaoYangJuanDecorator(integral3);
        integral4.addIntegral(4L,20);
        System.out.println("------------------------------------------------------------------------------");

    }


    @Test
    void testIntegral1(){
        Boolean baoyangjuanFlag = false;
        Boolean yangshenghuFlag = true;

        Integral integral = new BaseIntegral();
        if(baoyangjuanFlag){
            integral = new BaoYangJuanDecorator(integral);
        }
        if(yangshenghuFlag){
            integral = new YangShengHuDecorator(integral);
        }
        integral.addIntegral(1L,30);

    }




}
