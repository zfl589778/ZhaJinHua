package com.eric.utils;

import java.util.Calendar;

/**
 * 日期工具类，返回值默认格式yyyy-MM-dd
 * @author eric
 *
 */
public class CalendarUtils {

	public static String today(){
		return someday(TimeUtils.formatIntToDateString("yyyy-MM-dd", System.currentTimeMillis() / 1000),0);
	}
	
	public static String yesterday(){
		return someday(TimeUtils.formatIntToDateString("yyyy-MM-dd", System.currentTimeMillis() / 1000),-1);
	}
	
	public static String tomorrow(){
		return someday(TimeUtils.formatIntToDateString("yyyy-MM-dd", System.currentTimeMillis() / 1000),1);
	}
	
	public static String someday(String date,int step){
		Calendar c = buildCalendar(date);
		c.add(Calendar.DAY_OF_MONTH, step);
		return TimeUtils.formatIntToDateString("yyyy-MM-dd", c.getTimeInMillis() / 1000);
	}
	
	public static String lastDay(String date){
		return someday(date, -1);
	}
	
	public static String nextDay(String date){
		return someday(date, 1);
	}
	
	public static String firstDayOfMonth(String date){
		Calendar c = buildCalendar(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return TimeUtils.formatIntToDateString("yyyy-MM-dd", c.getTimeInMillis() / 1000);
	}
	
	public static String lastDayOfMonth(String date){
		Calendar c = buildCalendar(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return TimeUtils.formatIntToDateString("yyyy-MM-dd", c.getTimeInMillis() / 1000);
	}
	
	public static String middleDayOfMonth(String date){
		Calendar c = buildCalendar(date);
		c.set(Calendar.DAY_OF_MONTH, 15);
		return TimeUtils.formatIntToDateString("yyyy-MM-dd", c.getTimeInMillis() / 1000);
	}
	
	public static String lastMonth(String date){
		return someMonth(date, -1);
	}
	
	public static String someMonth(String date,int step){
		Calendar c = buildCalendar(date);
		c.add(Calendar.MONTH, step);
		return TimeUtils.formatIntToDateString("yyyy-MM-dd", c.getTimeInMillis() / 1000);
	}
	
	public static String nextMonth(String date){
		return someMonth(date, 1);
	}
	
	public static String lastYear(String date){
		return someYear(date, -1);
	}
	
	public static String someYear(String date,int step){
		Calendar c = buildCalendar(date);
		c.add(Calendar.YEAR, step);
		return TimeUtils.formatIntToDateString("yyyy-MM-dd", c.getTimeInMillis() / 1000);
	}
	
	public static String nextYear(String date){
		return someYear(date, 1);
	}
	
	public static Calendar buildCalendar(String date){
		Calendar c = Calendar.getInstance();
		int year = Integer.parseInt(date.split("-")[0]);
		int month = Integer.parseInt(date.split("-")[1]);
		int day = Integer.parseInt(date.split("-")[2]);
		c.set(year, month - 1, day);
		return c;
	}
	
}
