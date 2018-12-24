/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.baihua.core.common.utils;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;


/**
 * 日期处理
 * 
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)){
            return null;
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        return Date.from(LocalDate.parse(strDate,fmt).atTime(LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 字符串转换成日期
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDateTime(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)){
            return null;
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        return Date.from(LocalDateTime.parse(strDate,fmt).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 字符串转换成日期
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToTime(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)){
            return null;
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        return Date.from(LocalTime.parse(strDate,fmt).atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date 日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return Date.from(dateTime.plusSeconds(seconds).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date 日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return Date.from(dateTime.plusMinutes(minutes).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date 日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return Date.from(dateTime.plusHours(hours).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return Date.from(dateTime.plusDays(days).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date 日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return Date.from(dateTime.plusWeeks(weeks).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date 日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return Date.from(dateTime.plusMonths(months).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date 日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return Date.from(dateTime.plusYears(years).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取周
     * @param date
     * @return
     */
    public static String getWeek(Date date){
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return "周" + dateTime.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.CHINA);
    }


    /**
     * 获取时分
     * @param date
     * @return
     */
    public static String getTime(Date date){
        LocalTime localTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime();
        return transferToTwoDig(localTime.getHour()) + ":" + transferToTwoDig(localTime.getMinute());
    }


    /**
     * 获取日期周
     * @param date
     * @return
     */
    public static String getDateWeek(Date date){
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return  transferToTwoDig(dateTime.getMonthValue()) + "-" + transferToTwoDig(dateTime.getDayOfMonth()) + " 周" + dateTime.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.CHINA);
    }

    /**
     * 获取年周
     * @param date
     * @return
     */
    public static String getYearDateWeek(Date date){
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return  dateTime.getYear() + "-" + transferToTwoDig(dateTime.getMonthValue()) + "-" + transferToTwoDig(dateTime.getDayOfMonth()) + " 周" + dateTime.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.CHINA);
    }

    /**
     * 获取当前周
     *
     * @return
     */
    public static Date[] getCurentWeek(){
        LocalDate curentMonday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate currentSunday = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        Instant curentMondayInstant = curentMonday.atTime(LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant();
        Instant currentSundayInstant = currentSunday.atTime(LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant();
        Date[] result = new Date[2];
        result[0] = Date.from(curentMondayInstant);
        result[1] = Date.from(currentSundayInstant);
        return result;
    }

    /**
     * 获取
     * @param date
     * @return
     */
    public static String getYearMonthDate(LocalDate date){
        return date.getYear() + "-" + getMonthDate(date);
    }

    public static String getMonthDate(LocalDate date){
        return transferToTwoDig(date.getMonthValue()) + "-" + transferToTwoDig(date.getDayOfMonth());
    }

    /**
     * 获取周
     * @param date
     * @return
     */
    public static String getWeek(LocalDate date){
        return "周" + date.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.CHINA);
    }


    /**
     * 获取下周日期
     *
     * @return
     */
    public static LocalDate[] getNextWeekDays(Date date){
        LocalDate[] weekDates = new LocalDate[7];
        LocalDate localDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate nextMonday = localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDate nextTuesday = nextMonday.plusDays(1);
        LocalDate nextWednesday = nextTuesday.plusDays(1);
        LocalDate nextThursday = nextWednesday.plusDays(1);
        LocalDate nextFriday = nextThursday.plusDays(1);
        LocalDate nextSaturday = nextFriday.plusDays(1);
        LocalDate nextSunday = nextSaturday.plusDays(1);

        weekDates[0] = nextMonday;
        weekDates[1] = nextTuesday;
        weekDates[2] = nextWednesday;
        weekDates[3] = nextThursday;
        weekDates[4] = nextFriday;
        weekDates[5] = nextSaturday;
        weekDates[6] = nextSunday;
        return weekDates;
    }

    public static String transferToTwoDig(int input) {
        if(input < 10){
            return "0" + input;
        }
        return "" + input;
    }
}
