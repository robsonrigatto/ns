package br.com.rr.campanha.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class LocalDateUtils {
	
	public static LocalDate dateToLocalDate(Date date) {
		Date roundingDate = DateUtils.round(date, Calendar.DATE);
		LocalDate localDate = roundingDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();//.plusDays(1);
		return localDate;
	}
	
	public static Date localDateToDate(LocalDate localDate) {
		Date date = java.sql.Date.valueOf(localDate);
		return new Date(date.getTime());
	}

}
