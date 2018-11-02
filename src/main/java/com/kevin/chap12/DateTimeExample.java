package com.kevin.chap12;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoField;
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

        int y = date.get(ChronoField.YEAR);
        int m = date.get(ChronoField.MONTH_OF_YEAR);
        int d = date.get(ChronoField.DAY_OF_MONTH);

        LocalTime time = LocalTime.of(13, 45, 20);  // 13:45:20
        int hour = time.getHour();  // 13
        int minute =time.getMinute();   // 45
        int second = time.getSecond();  // 20
        System.out.println(time);

        int _h = time.get(ChronoField.HOUR_OF_DAY);
        int _m = time.get(ChronoField.MINUTE_OF_HOUR);
        int _s = time.get(ChronoField.SECOND_OF_MINUTE);

        LocalDateTime dt1 = LocalDateTime.of(2014, 3, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
        System.out.println(dt1);

        LocalDate date1 = dt1.toLocalDate();
        System.out.println(date1);
        LocalTime time1 = dt1.toLocalTime();
        System.out.println(time1);

        Instant instant = Instant.ofEpochSecond(44 * 365 * 86400);
        Instant now = Instant.now();

        Duration d1 = Duration.between(LocalTime.of(13, 45, 10), time);
        Duration d2 = Duration.between(instant, now);
        System.out.println(d1.getSeconds());
        System.out.println(d2.getSeconds());
    }
}
