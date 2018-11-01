package com.kevin.chap12;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

/**
 * 类名: DateTimeExample<br/>
 * 包名：com.kevin.chap12<br/>
 * 作者：kevin[wangqi2017@xinhua.org]<br/>
 * 时间：2018/11/1 18:39<br/>
 * 版本：1.0<br/>
 * 描述：<br/>
 */
public class DateTimeExample {

    private static final ThreadLocal<DateFormat> formatters = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static void main(String[] args) {
//        useOldDate();
        useLocalDate();
    }

    private static void useOldDate() {
        Date date = new Date(114, 2, 18);
        System.out.println(date);

        System.out.println(formatters.get().format(date));

        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, Calendar.MARCH, 18);
        System.out.println(calendar);
    }

    private static void useLocalDate() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();  // 2014
        Month month = date.getMonth();  // MARCH
        int day = date.getDayOfMonth(); // 18
        DayOfWeek dow = date.getDayOfWeek();    // TUESDAY
        int len = date.lengthOfMonth(); // 31 (days in March)
        boolean leap = date.isLeapYear();   // false (not a leap year)
        System.out.println(date);   // 2014-03-18
    }
}
