package com.searcin.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import com.searcin.exception.BadRequestException;

public class TimeUtils {

	private static final String FORMAT = "hh:mm a";

	public static Time getTime(String time) {
		return Optional.ofNullable(time).map(item -> {
			try {
				return new Time(new SimpleDateFormat(FORMAT).parse(item).getTime());
			} catch (ParseException e) {
				throw new BadRequestException("Can't convert the time format {}", e);
			}
		}).orElse(null);
	}

	public static String getString(Time time) {
		return Optional.ofNullable(time).map(item -> new SimpleDateFormat(FORMAT).format(item)).orElse(null);
	}
}
