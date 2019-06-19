package com.buddha.component.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public  class DateHelper {
	private static List<String> DateFormatStrings;
	static
	{
		DateFormatStrings = new ArrayList<String>();
		DateFormatStrings.add("yyyy-MM-dd HH:mm:ss");
		DateFormatStrings.add("yyyy-MM-dd HH:mm");
		DateFormatStrings.add("yyyy-MM-dd HH:");
		DateFormatStrings.add("yyyy-MM-dd");
		DateFormatStrings.add("yyyy/MM/dd");
		DateFormatStrings.add("yyyy/MM/dd HH");
		DateFormatStrings.add("yyyy/MM/dd HH:mm");
		DateFormatStrings.add("yyyy/MM/dd HH:mm:ss");
		DateFormatStrings.add("HH:mm:ss");
		DateFormatStrings.add("HH:mm");
	}
	public static Date addYear(Date Now,int Year)
	{
		Calendar theCalendar = Calendar.getInstance();
		theCalendar.setTime(Now);
		theCalendar.add(Calendar.YEAR, Year);
		return theCalendar.getTime();
	}
	public static Date addMonth(Date Now,int Month)
	{
		Calendar theCalendar = Calendar.getInstance();
		theCalendar.setTime(Now);
		theCalendar.add(Calendar.MONTH, Month);
		return theCalendar.getTime();
	}
	public static Date addDay(Date Now,int Day)
	{
		Calendar theCalendar = Calendar.getInstance();
		theCalendar.setTime(Now);
		theCalendar.add(Calendar.DATE, Day);
		return theCalendar.getTime();
	}
	public static Date addHour(Date Now,int Hour)
	{
		Calendar theCalendar = Calendar.getInstance();
		theCalendar.setTime(Now);
		theCalendar.add(Calendar.HOUR, Hour);
		return theCalendar.getTime();
	}
	public static Date addMinute(Date Now,int Minute)
	{
		Calendar theCalendar = Calendar.getInstance();
		theCalendar.setTime(Now);
		theCalendar.add(Calendar.MINUTE, Minute);
		return theCalendar.getTime();
	}
	public static Date addSecond(Date Now,int Second)
	{
		Calendar theCalendar = Calendar.getInstance();
		theCalendar.setTime(Now);
		theCalendar.add(Calendar.SECOND, Second);
		return theCalendar.getTime();
	}
	public static Date addMillsencod(Date Now,int Millsencod)
	{
		Calendar theCalendar = Calendar.getInstance();
		theCalendar.setTime(Now);
		theCalendar.add(Calendar.MILLISECOND, Millsencod);
		return theCalendar.getTime();
	}
	private static  Date parseByFormat(String Value,String Format)
	{
		try
		{
			return new SimpleDateFormat(Format).parse(Value);
		}
		catch (Exception e)
		{
            return  null;
		}
	}
	public   static Date parseUinxDateFromString(String DateStr)
	{
		Date theDate = null;
		try
		{
			Long theLongTime = Long.parseLong(DateStr);
			theDate = new Date(theLongTime);
		}
		catch (Exception e)
		{
		}
		return theDate;
	}
	public static Date parseDateFromString(String dateStr,Date nullDateVal)
	{
		 Date theDate = parseFromString(dateStr);
		if(theDate==null)
		{
			theDate=nullDateVal;
		}
		return theDate;
	}
	public static Date parseFromString(String Value)
	{
		if(Value==null || Value.isEmpty())
		{
			return  null;
		}
		Date theDateRet = null;
        //替换T
        Value = Value.replace("T"," ");
		theDateRet = parseUinxDateFromString(Value);
		if(theDateRet==null) {
			for (String theFormat : DateFormatStrings) {
				theDateRet = parseByFormat(Value, theFormat);
				if (theDateRet != null) {
					break;
				}
			}
		}
		return theDateRet;
	}
}
