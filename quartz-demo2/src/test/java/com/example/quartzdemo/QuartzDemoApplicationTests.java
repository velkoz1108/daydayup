package com.example.quartzdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class QuartzDemoApplicationTests {

    @Test
    void contextLoads() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//        Date date = new Date();

        Date date = sdf.parse("2023-00-08");

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);      //当前月的上个月  （-1改为1的话，为取当前月                                                                                            的下个月）


        System.out.println("========"+sdf.format( cal.getTime()));


        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        cal1.add(Calendar.MONTH, -1);
        cal1.set(Calendar.DAY_OF_MONTH,
                cal1.getActualMinimum(Calendar.DAY_OF_MONTH));

        System.out.println("当月时间上月1号-->"+sdf.format(cal.getTime()));



        Calendar calendar = Calendar.getInstance();
        System.out.println("获取当前月:"+calendar.get(Calendar.MONTH)+" month ");



    }


    @Test
    public void testDate(){
        YearMonth y = YearMonth.parse("2023-01");
//        YearMonth y = YearMonth.now();
        System.out.println("本月日期是："+y.toString());

        YearMonth y2 = y.minusMonths(1);
        System.out.println("上月日期是："+y2.toString());


        String str = DateTimeFormatter.ofPattern("yyyyM").format(y2);
        System.out.println(str); //2021年04月

        LocalDate date2 = LocalDate.now().minusMonths(1);
        System.out.println(date2.getYear()+","+date2.getMonth());

    }





}
