package com.example.designmode.demo2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildUpFactory {
    private  static final Map<String, TaoCanDTo> TAO_CAN_MAP = new HashMap<String, TaoCanDTo>();

    private static final Map<String,Class> CLASS_MAP = new HashMap<String,Class>();

    static{
        CLASS_MAP.put(ZengPinKey.ZENGPIN_1,BaoYangJuanDecorator.class);
        CLASS_MAP.put(ZengPinKey.ZENGPIN_2,YangShengHuDecorator.class);

    }

    static {
        //真实从数据库查询 根据taocanName查询到对应TaoCanEntity

        //保养劵套餐
        TaoCanDTo baoYangJuanTaoCanDto = new TaoCanDTo();
        List<ZengPinEntity> zengPinEntityList = new ArrayList<ZengPinEntity>();
        ZengPinEntity zengPinEntity = new ZengPinEntity();
        zengPinEntity.setCode("baoyangjuan");
        zengPinEntityList.add(zengPinEntity);
        baoYangJuanTaoCanDto.setZengPinEntityList(zengPinEntityList);
        TAO_CAN_MAP.put(TaoCanKey.TAOCAN_1,baoYangJuanTaoCanDto);

        //养生壶套餐
        TaoCanDTo yangShengHuTaoCanDto = new TaoCanDTo();
        List<ZengPinEntity> zengPinEntityList1 = new ArrayList<ZengPinEntity>();
        ZengPinEntity zengPinEntity1 = new ZengPinEntity();
        zengPinEntity1.setCode("yangshenghu");
        zengPinEntityList1.add(zengPinEntity1);
        yangShengHuTaoCanDto.setZengPinEntityList(zengPinEntityList1);
        TAO_CAN_MAP.put(TaoCanKey.TAOCAN_2,yangShengHuTaoCanDto);

        //全量套餐
        TaoCanDTo all = new TaoCanDTo();
        List<ZengPinEntity> zengPinEntityList2 = new ArrayList<ZengPinEntity>();
        ZengPinEntity zengPinEntity2 = new ZengPinEntity();
        zengPinEntity2.setCode("yangshenghu");
        ZengPinEntity zengPinEntity3 = new ZengPinEntity();
        zengPinEntity3.setCode("baoyangjuan");
        zengPinEntityList2.add(zengPinEntity2);
        zengPinEntityList2.add(zengPinEntity3);
        all.setZengPinEntityList(zengPinEntityList2);
        TAO_CAN_MAP.put(TaoCanKey.TAOCAN_3,all);
    }

    public static Integral getIntegral(String taocanCode) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TaoCanDTo taoCanDTo = TAO_CAN_MAP.get(taocanCode);
        Integral integral = new BaseIntegral();
        if (taoCanDTo != null) {
            for (ZengPinEntity zengPinEntity : taoCanDTo.getZengPinEntityList()) {//遍历设置的促销类型，通过装饰器组合促销类型
                String zengPinCode = zengPinEntity.getCode();
                integral = protmotion(zengPinCode, integral);
            }
        }
        return integral;
    }

    private static Integral protmotion( String zengPinCode, Integral integral) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class integralClass = CLASS_MAP.get(zengPinCode);
        Constructor constroctor = integralClass.getConstructor(Integral.class);
        Integral integral1 = (Integral) constroctor.newInstance(integral);
        return integral1;
    }

    class TaoCanKey{
        protected static final String TAOCAN_1 = "taocan1";
        protected static final String TAOCAN_2 = "taocan2";
        protected static final String TAOCAN_3 = "taocan3";

    }

    class ZengPinKey {
        protected static final String ZENGPIN_1 = "baoyangjuan";
        protected static final String ZENGPIN_2 = "yangshenghu";
    }
}
