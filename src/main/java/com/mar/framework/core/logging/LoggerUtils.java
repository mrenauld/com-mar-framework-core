package com.mar.framework.core.logging;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class LoggerUtils {

	/**
	 * Returns an instance of the calendar.
	 * 
	 * @return
	 */
	public static GregorianCalendar getCalendar() {
		final GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar
				.getInstance();
		return calendar;
	}

	/**
	 * Returns a string representation of the current date.
	 * 
	 * @return
	 */
	public static String getDateString() {
		final GregorianCalendar c = getCalendar();
		String date = Integer.toString(c.get(Calendar.YEAR));
		date += "-" + (c.get(Calendar.MONTH) + 1); // WTF Calendar??
		date += "-" + c.get(Calendar.DAY_OF_MONTH);
		return date;
	}

	/**
	 * Returns a string representation of the current time.
	 * 
	 * @return
	 */
	public static String getTimeString() {
		final GregorianCalendar c = getCalendar();
		String time = "";
		time += c.get(Calendar.HOUR_OF_DAY) + ":";
		time += c.get(Calendar.MINUTE) + ":";
		time += c.get(Calendar.SECOND);
		return time;
	}
}