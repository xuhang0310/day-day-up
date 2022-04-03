package com.day.webview.leecode;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class TestDate {

    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime localDateTime = LocalDateTime.parse("2019-07-31 00:00:00",dateTimeFormatter1);
        LocalDate localDate = LocalDate.parse("2019-07-31",dateTimeFormatter2);
        Date date = Date.from(LocalDateTime.parse("2019-07-31 00:00:00",dateTimeFormatter1).atZone(ZoneId.systemDefault()).toInstant());


        String strDateTime = "2019-07-31 00:00:00";
        String strDate = "2019-07-31";
        Long timestamp= 1564502400000L;

/** LocalDateTime 转 LocalDate */
        System.out.println("LocalDateTime 转 LocalDate: "+localDateTime.toLocalDate());
/** LocalDateTime 转 Long */
        System.out.println("LocalDateTime 转 Long: "+localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
/** LocalDateTime 转 Date */
        System.out.println("LocalDateTime 转 Date: "+Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
/** LocalDateTime 转 String */
        System.out.println("LocalDateTime 转 String: "+localDateTime.format(dateTimeFormatter1));

        System.out.println("-------------------------------");

/** LocalDate 转 LocalDateTime */
        System.out.println("LocalDate 转 LocalDateTime: "+LocalDateTime.of(localDate,LocalTime.parse("00:00:00")));
/** LocalDate 转 Long */
        System.out.println("LocalDate 转 Long: "+localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
/** LocalDate 转 Date */
        System.out.println("LocalDate 转 Date: "+Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
/** LocalDate 转 String */
        System.out.println("LocalDateTime 转 String: "+localDateTime.format(dateTimeFormatter2));

        System.out.println("-------------------------------");

/** Date 转 LocalDateTime */
        System.out.println("Date 转 LocalDateTime: "+LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
/** Date 转 Long */
        System.out.println("Date 转 Long: "+date.getTime());
/** Date 转 LocalDate */
        System.out.println("Date 转 LocalDateTime: "+LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate());
/** Date 转 String */
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS" );
        System.out.println("Date 转 String: "+sdf.format(date));

        System.out.println("-------------------------------");

/** String 转 LocalDateTime */
        System.out.println("String 转 LocalDateTime: "+LocalDateTime.parse(strDateTime,dateTimeFormatter1));
/** String 转 LocalDate */
        System.out.println("String 转 LocalDate: "+LocalDateTime.parse(strDateTime,dateTimeFormatter1).toLocalDate());
        System.out.println("String 转 LocalDate: "+LocalDate.parse(strDate,dateTimeFormatter2));
/** String 转 Date */
        System.out.println("String 转 Date: "+Date.from(LocalDateTime.parse(strDateTime,dateTimeFormatter1).atZone(ZoneId.systemDefault()).toInstant()));

        System.out.println("-------------------------------");

/** Long 转 LocalDateTime */
        System.out.println("Long 转 LocalDateTime:"+LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()));
/** Long 转 LocalDate */
        System.out.println("Long 转 LocalDate:"+LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()).toLocalDate());
    }

    public static void main1(String[] args) {

        long time = 1649030400000L;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        System.out.println(sdf.format(new Date(time)));

    }
}
