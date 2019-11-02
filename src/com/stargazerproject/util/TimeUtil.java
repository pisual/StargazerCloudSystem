package com.stargazerproject.util;

import com.google.common.base.Optional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtil {

	public static String IDTime(String timeForm) {
		DateFormat dateFormat = new SimpleDateFormat(timeForm);
		return dateFormat.format(new Date());
	}

	public static Optional<TimeUnit> conversionTimeUnit(String waitTimeoutUnit){
		switch (waitTimeoutUnit){
			case "NANOSECONDS":
				return Optional.of(TimeUnit.NANOSECONDS);
			case "MICROSECONDS":
				return Optional.of(TimeUnit.MICROSECONDS);
			case "MILLISECONDS":
				return Optional.of(TimeUnit.MILLISECONDS);
			case "SECONDS":
				return Optional.of(TimeUnit.SECONDS);
			case "HOURS":
				return Optional.of(TimeUnit.HOURS);
			case "DAYS":
				return Optional.of(TimeUnit.DAYS);
			default:
				throw new IllegalArgumentException("waitTimeoutUnit Error");
		}
	}
}
