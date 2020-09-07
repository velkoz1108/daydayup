package com.example.demo;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;
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
        List<String[]> squaresList = list.stream().map(TestBean::getId).map(s ->s.split(",")).collect(Collectors.toList());

       for(String[] strings:squaresList){
           for(String string:strings){
               System.out.print(string);
               System.out.print(",");
           }
       }


//        List<String> vlist=new ArrayList<>();
//        vlist.add("id_10");
//
//        List<TestBean> squaresList1=list.stream().filter(ss->vlist.contains(ss.getId())).collect(Collectors.toList());
//        System.out.println(squaresList1);
//
//
//        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
//// 获取空字符串的数量
//        long count = strings.stream().filter(string -> string.isEmpty()).count();
//        System.out.println("count:"+count);
    }


    @Test
    public void hh(){
        List<String> list=new ArrayList<>();
        list.add("a,b");
        list.add("c,d");
        list.add("e,f");
        list.add("g,h");

        List<String> l=new ArrayList<>();
        list.stream().map(s->Arrays.asList(s.split(","))).map(s->l.addAll(s)).count();
        System.out.println(l);

        List<String> l1=new ArrayList<>();
        list.forEach(s->l1.addAll(Arrays.asList(s.split(","))));
        System.out.println("-----"+l1);
    }




    @Test
    public void hh1(){
        List<TestBean> list=new ArrayList<>();
        for(int i=0;i<=10;i++){
            TestBean testBean=TestBean.builder().id("id_"+i+",id_"+i).name("name_"+i).build();
            list.add(testBean);
        }

        /*List<String> l=new ArrayList<>();
        list.stream().map(s->Arrays.asList(s.split(","))).map(s->l.addAll(s)).count();
        System.out.println(l);*/

        Set<String> l1=new HashSet<>();
        list.stream().map(TestBean::getId).forEach(s->l1.addAll(Arrays.asList(s.split(","))));
        System.out.println(l1);

        List<String> l2=new ArrayList<>(l1);
        System.out.println(l2);
    }


    @Test
    public void hh2(){
        List<String> list=new ArrayList<>(Arrays.asList("series-1176","series-2"));

        List<String> list1=new ArrayList<>(Arrays.asList("series-2","series-1176","series-1176","series-2817"));

        list1.retainAll(list);

        System.out.println(list);
        System.out.println(list1);

    }

    @Test
    public void testDate(){
        Date endDate = this.getToday();
        Date beginDate = this.calculateTime(endDate, Calendar.DAY_OF_MONTH, -1);
        System.out.println("---------------");
    }


    public static Date getToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date calculateTime(Date date, int field, int amount) {
        if (date == null) {
            throw new NullPointerException("date is null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }
}
