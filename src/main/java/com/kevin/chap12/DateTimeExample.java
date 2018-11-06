package com.kevin.chap12;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

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
//        useLocalDate();
//        useTemporalAdjuster();
//        useDateFormatter();
        useZone();
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
        int minute = time.getMinute();   // 45
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

        Period tenDays = Period.between(LocalDate.of(2014, 3, 8),
                LocalDate.of(2014, 3, 18));
        System.out.println(tenDays);

        Duration threeMinutes = Duration.ofMinutes(3);
        Duration fiveMinutes = Duration.of(5, ChronoUnit.MINUTES);
        Period fiveDays = Period.ofDays(5);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
    }

    private static void useTemporalAdjuster() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        date = date.with(nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(date);
        date = date.with(lastDayOfMonth());
        System.out.println(date);

        date = date.with(new NextWorkingDay());
        System.out.println(date);
        date = date.with(nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date);
        date = date.with(new NextWorkingDay());
        System.out.println(date);

        date = date.with(nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date);
        // 使用Lambda表达式封装逻辑
        date = date.with(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            }
            if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        System.out.println(date);

        date = date.with(nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date);
        // 使用TemporalAdjusters.ofDateAdjuster静态工厂方法封装逻辑
        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
                temporal -> {
                    DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                    int dayToAdd = 1;
                    if (dow == DayOfWeek.FRIDAY) {
                        dayToAdd = 3;
                    }
                    if (dow == DayOfWeek.SATURDAY) {
                        dayToAdd = 2;
                    }
                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
                }
        );
        date = date.with(nextWorkingDay);
        System.out.println(date);
    }

    // 单独定义一个类封装逻辑
    private static class NextWorkingDay implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal temporal) {
            // 读取当前日期
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            // 正常情况增加1天
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {  // 如果当天是周五，增加3天
                dayToAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) { // 如果当天是周六，增加2天
                dayToAdd = 2;
            }
            // 增加恰当的天数后，返回修改的日期
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }
    }

    private static void useDateFormatter() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(formatter.format(date)); // 18/03/2014
        System.out.println(date.format(formatter)); // 18/03/2014

        date = LocalDate.parse("18/03/2014", formatter);
        System.out.println(date);

        DateTimeFormatter complexFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.US);
        System.out.println(date.format(complexFormatter));  //18. March 2014
    }

    private static void useZone() {
        // 罗马时间2014-03-18 00:00:00
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        LocalDate date = LocalDate.of(2014, 3, 18);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
        System.out.println(zdt1);

        // 罗马时间2014-03-18 13:45:00
        LocalDateTime dateTime = LocalDateTime.of(2014, 3, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);
        System.out.println(zdt2);

        // 将当前时间转换成罗马时间
        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);
        System.out.println(zdt3);

        // 将当前时间转换成罗马时间
        LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant, romeZone);
        System.out.println(timeFromInstant);

        // 将罗马时间转换成格林威治时间
        Instant instantFromDateTime = dateTime.atZone(romeZone).toInstant();
        System.out.println(instantFromDateTime);

        ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");
        OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(dateTime, newYorkOffset);
        System.out.println(dateTimeInNewYork);

    }
}
