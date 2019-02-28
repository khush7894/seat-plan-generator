package com.seating.plan.generator.system.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
	public static final String DATE_FORMAT_DEFAULT = "dd/MM/yyyy";

	public static final String DATETIME_FORMAT_DEFAULT = "dd/MM/yyyy hh:mm aa";

	public static final String TIME_FORMAT_DEFAULT = "hh:mm:ss aa";

	public static final String dateToString(Date date) {
		return dateToString(date, DATE_FORMAT_DEFAULT);
	}

	public static final String TimeToString(Date date) {
		return dateToString(date, TIME_FORMAT_DEFAULT);
	}

	public static final String dateToString(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static final Date stringToDate(String dateString) throws ParseException {
		return stringToDate(dateString, DATE_FORMAT_DEFAULT);
	}

	public static final Timestamp stringToTimeStamp(String dateString) throws ParseException {
		return new java.sql.Timestamp(stringToDate(dateString, TIME_FORMAT_DEFAULT).getTime());

	}

	public static final Date changeDateFormat(Date date, String format) throws ParseException {
		DateFormat dateformat = new SimpleDateFormat(format);
		return dateformat.parse(dateformat.format(date));
	}

	/**
	 * Gets the date.
	 * 
	 * @deprecated this method is deprecated use {@code getCurrentDate()}
	 *             instead.
	 * @param format
	 *            the format
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	@Deprecated
	public static Date getDate(String format) throws ParseException {
		// return getCurrentDate();
		DateFormat dateformat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateformat.parse(dateformat.format(date));
	}

	public static Date getCurrentDate() {
		return new Date();

	}

	public static Date stringToDate(String dateString, String pattern) throws ParseException {
		if (dateString == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(dateString);

	}

	public static void main(String[] args) {
		try {
			System.out.println(stringToDate("12-04-2018", "dd-MM-yyyy"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Date stringToDate(java.sql.Date startDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
