package com.example.consumer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    public static String formatDate(String pat, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pat) ;        // 实例化模板对象
        Date d = sdf.parse(date) ;   // 将给定的字符串中的日期提取出来
        String newDay = sdf.format(d);
        return newDay;
    }
}
