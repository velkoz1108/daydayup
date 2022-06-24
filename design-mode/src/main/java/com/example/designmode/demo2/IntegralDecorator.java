package com.example.designmode.demo2;

/**
 * 积分装饰器类
 * */
public abstract class IntegralDecorator implements Integral {
    private Integral integral;

    public IntegralDecorator(Integral integral){
        this.integral = integral;
    }

    @Override
    public void addIntegral(Long id, Integer count){
        integral.addIntegral(id, count);
    }
}
