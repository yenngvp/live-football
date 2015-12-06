/**
 * @author: yen.nt
 * @created on Nov 17, 2015
 */
package com.footballun.util;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.footballun.model.Setting;
import com.footballun.service.FootballunService;


/**
 * @author yen.nt
 *
 */
@Component
public class AppConfigure {
	
	private final Logger logger = LoggerFactory.getLogger(AppConfigure.class);
	
	@Autowired
	private FootballunService footballunService;
	
	public static long HOUR_TO_MILLISECONDS = 60 * 60 * 60 * 1000;
		
	public Setting getSetting() {
		
		return footballunService.getSetting(1);
	}
	
	public int getCurrentCompetition() {
		return getSetting().getCompetition().getId();
	}
	
	public TimeZone getTimeZone() {
		return TimeZone.getTimeZone(getSetting().getTimeZoneId());
	}
	
	public LocalDateTime getServerCalendarUTC() {
		if (getSetting().getOverrideServerTime() != null) {
			logger.warn("The server time used for the calendar has been overriden. Watch out!");
			return getSetting().getOverrideServerTime();
		} else {
			// Current server time
			return LocalDateTime.now();
		}
	}

	public Locale getLocale() {
		return Locale.US;
	}

	public long getStartCountDownInMiliseconds() {
		return getSetting().getStartCountdown() * HOUR_TO_MILLISECONDS;
	}
	
	public int getMatchStatusTrackerInterval() {
		return getSetting().getMatchStatusTrackerInterval();
	}
	
	public int getDefaultMatchDuration() {
		return getSetting().getDefaultMatchDuration();
	}
}
