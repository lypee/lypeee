package com.lypee.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;


public class DateTimeUtil {
    /**
     * YYYY-MM-dd hh;mm:ss
     */
    public  static String nowTime()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date  date = new Date() ;
        return simpleDateFormat.format(date) ;
    }
    public static  int compareTimeByMin(String time1 ,String time2 )
    {
        int hours  = 0 ;
        try
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            long from = simpleDateFormat.parse(time1).getTime() ;
            long to = simpleDateFormat.parse(time2).getTime() ;
            hours = Math.abs((int) ((to-from)/(1000*60))) ;
        }catch (ParseException e)
        {
            e.printStackTrace();
        }
        return hours ;
    }
    /**
     * 本月第一天
     */
    public static  String firstDayOfThisMonth()
    {
        LocalDate localDate = LocalDate.now() ;
        //返回int int int 的年月日构造
        LocalDate firstday = localDate.of(localDate.getYear(), localDate.getMonth(),1);
    return firstday+"" ;
    }
    public static String LastDayOfThisMonth()
    {
        LocalDate today = LocalDate.now() ;
       //获取当前月的最后一天
        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth()) ;
    return lastDay+"" ;
    }

}

