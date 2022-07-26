package com.example.exceldemo;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class Test2022 {

    @Test
    public void test(){
        String str = "aasffrfewew dverferfswee";
        char[] chars = str.toLowerCase().toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        for(int i = 0 ; i < chars.length; i++){
            char c = chars[i];
            Integer count = map.get(c);
            map.put(c,count==null? 1:count+1);
        }
        System.out.println(map);
    }


    @Test
    public void ff(){
        Map<String, BigDecimal> transportDeviceTypeMap = new HashMap<>();
        transportDeviceTypeMap.put("aa",new BigDecimal(2));
        Map<String, BigDecimal> newTransportDeviceTypeMap = new HashMap<>();
        newTransportDeviceTypeMap.putAll(transportDeviceTypeMap);
        BigDecimal value = newTransportDeviceTypeMap.get("aa");
        newTransportDeviceTypeMap.put("aa",value.subtract(new BigDecimal(1)));
        System.out.println(newTransportDeviceTypeMap);
        System.out.println(transportDeviceTypeMap);
    }


    @Test
    public void hh(){
        String attachSn = "b54d8c0e-1343-4e7b-8c4f-2867f70abf84";
        List<String> attachSns = Arrays.asList(attachSn);
        System.out.println(attachSns);
    }





}
