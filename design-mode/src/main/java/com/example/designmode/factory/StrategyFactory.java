package com.example.designmode.factory;

import com.example.designmode.strategy.ClueStrategy;
import com.example.designmode.strategy.impl.DongCheDiClueStrategy;
import com.example.designmode.strategy.impl.DouYinClueStrategy;
import com.example.designmode.strategy.impl.OtherClueStrategy;

import java.util.HashMap;
import java.util.Map;

//管理策略子类的工厂类
public class StrategyFactory {
    private static Map<String, ClueStrategy> CLUE_STRATEGY_MAP = new HashMap<>();

    // 策略子类为单例模式
    static {
        CLUE_STRATEGY_MAP.put("douyin", new DouYinClueStrategy());
        CLUE_STRATEGY_MAP.put("dongchedi", new DongCheDiClueStrategy());
    }

    private static final ClueStrategy otherClueStrategy = new OtherClueStrategy();

    public StrategyFactory() {

    }

    public static ClueStrategy getClueStrategy(String source) {
        ClueStrategy clueStrategy = CLUE_STRATEGY_MAP.get(source);
        return clueStrategy == null ? otherClueStrategy : clueStrategy;
    }





}
