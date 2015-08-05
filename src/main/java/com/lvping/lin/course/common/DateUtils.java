package com.lvping.lin.course.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.lvping.lin.course.web.bean.DateArray;

/**
 * 
 * @author beifeng
 * @Date 2015-1-6
 * 
 */
public class DateUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(7) - 1;
        if (dayOfWeek == 1) {
            return 0;
        }
        return (1 - dayOfWeek);
    }

    /**
     * 获取本周第一天
     * @return
     */
    public static String getMondayOfWeek() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(5, mondayPlus);
        Date monday = currentDate.getTime();
        String preMonday = sdf.format(monday);
        return preMonday;
    }

    /**
     * 获取本周最后一天
     * @return
     */
    public static String getCurrentWeekday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(5, mondayPlus + 6);
        Date monday = currentDate.getTime();
        String preMonday = sdf.format(monday);
        return preMonday;
    }
    
    /**
     * 2014-01-06 => 01.06周二
     * @param date
     * @return
     * @throws ParseException
     */
    public static String dateFormat(String date) throws ParseException {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(sdf.parse(date));
        int week = gc.get(GregorianCalendar.DAY_OF_WEEK) - 1;
        String w = "";
        switch (week) {
            case 0 :
                w = "日";
                break;
            case 1 :
                w = "一";
                break;
            case 2 :
                w = "二";
                break;
            case 3 :
                w = "三";
                break;
            case 4 :
                w = "四";
                break;
            case 5 :
                w = "五";
                break;
            case 6 :
                w = "六";
                break;
            default :
                w = "";
                break;
        }
        String[] d = date.split("-");
        return d[1]+"."+d[2]+"周"+w;
    }
    
    public static List<DateArray> dateArray(String beginDate, String endDate) throws ParseException {
        List<DateArray> list = new ArrayList<DateArray>();
        GregorianCalendar begin = new GregorianCalendar();
        begin.setTime(sdf.parse(beginDate));
        GregorianCalendar end = new GregorianCalendar();
        end.setTime(sdf.parse(endDate));
        while (!begin.after(end)) {
            DateArray da = new DateArray();
            String date = sdf.format(begin.getTime());
            da.setDate(date);
            da.setDisplay(dateFormat(date));
            list.add(da);
            begin.add(GregorianCalendar.DAY_OF_YEAR, 1);
        }
        return list;
    }
    
    public static int timestamp(String date) {
        try {
            return (int) (new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime() / 1000);
        } catch (ParseException e) {
            return 0;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(timestamp("2015-08-09"));
        System.out.println(timestamp("2015-08-09") + 86400);
    }

}
