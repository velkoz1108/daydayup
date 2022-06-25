package com.example.designmode.demo2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BuildUpFactory {
    private  static final Map<String, Map<String,Boolean>> TAO_CAN_MAP = new HashMap<>();

    private static final Map<String,Class> CLASS_MAP = new HashMap<>();

    static{
        CLASS_MAP.put("baoyangjuan",BaoYangJuanDecorator.class);
        CLASS_MAP.put("yangshenghu",YangShengHuDecorator.class);

    }

    static {
        Map baoyangjuan = new HashMap<String,Boolean>();
        baoyangjuan.put("baoyangjuan",true);
        TAO_CAN_MAP.put("taocan1",baoyangjuan);

        Map yangshenghu = new HashMap<String,Boolean>();
        yangshenghu.put("yangshenghu",true);
        TAO_CAN_MAP.put("taocan2",yangshenghu);

        Map all = new HashMap<String,Boolean>();
        all.put("yangshenghu",true);
        all.put("baoyangjuan",true);
        TAO_CAN_MAP.put("taocan3",all);
    }

    public static Integral getIntegral(String taocanName) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
       Map<String,Boolean> map = TAO_CAN_MAP.get(taocanName);
       Integral integral = new BaseIntegral();
        if(map!=null && map.size()>0) {
            for(String key: map.keySet()) {//遍历设置的促销类型，通过装饰器组合促销类型
                integral = protmotion(key,map.get(key),integral);
            }
        }
        return integral;
    }

    private static Integral protmotion(String key, Boolean flag, Integral integral) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if(flag){
            Class integralClass = CLASS_MAP.get(key);
            Constructor constroctor = integralClass.getConstructor(Integral.class);
            Integral integral1 = (Integral) constroctor.newInstance(integral);
            return integral1;
        }
        return integral;
    }
}
