package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class TestDemoApplicationTests {


    /*java 8 test*/
    @Test
    void test() {
        List<TestBean> list=new ArrayList<>();
        for(int i=0;i<=10;i++){
            TestBean testBean=TestBean.builder().id("id_"+i+",id_"+i).name("name_"+i).build();
            list.add(testBean);
        }
        // 获取对应的平方数
        List<String> squaresList = list.stream().map(TestBean::getId).distinct().collect(Collectors.toList());
        System.out.println(squaresList);

        List<String> vlist=new ArrayList<>();
        vlist.add("id_10");

        List<TestBean> squaresList1=list.stream().filter(ss->vlist.contains(ss.getId())).collect(Collectors.toList());
        System.out.println(squaresList1);


        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
// 获取空字符串的数量
        long count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println("count:"+count);
    }




    @Test
    public void hh(){
        String name = "沉默王二";
        // \u000dname="沉默王三";
        System.out.println(name);


        String hhh="ooo,kkk";
        hhh.split(",");
    }

}
