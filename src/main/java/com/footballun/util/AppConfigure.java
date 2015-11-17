/**
 * @author: yen.nt
 * @created on Nov 17, 2015
 */
package com.footballun.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;


/**
 * @author yen.nt
 *
 */
public class AppConfigure {
	
	/*
	 * Default competition
	 */
	public static final int DEFAULT_COMPETITION = 9;

	/*
	 * Default matchup checking refresh interval
	 */
	public static final int MATCHUP_CHECK_INTERVAL_SECONDS = 5;
	
	private static AppConfigure instance;
	
	/*
	 * Current user time zone setting
	 */
	private TimeZone timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
	
	/*
	 * Current user's country locale applied
	 */
	private Locale locale = Locale.ENGLISH;
	
	/*
 	 * Starts point to do matchup countdown (in miliseconds) 
	 */
	private int startCountDownInMiliseconds = 24 * 7;
	
	private AppConfigure() {}
	
	
	public synchronized  static AppConfigure getSingleton() {
		if (instance == null) {
			instance = new AppConfigure();
		}
		return instance;
	}
	
	public int getCurrentCompetition() {
		return DEFAULT_COMPETITION;
	}
	
	public TimeZone getTimeZone() {
		return timeZone;
	}
	
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}
	
	public Calendar getCurrentCalendar() {
		return GregorianCalendar.getInstance(timeZone, getLocale());
	}


	public Locale getLocale() {
		return locale;
	}


	public void setLocale(Locale locale) {
		this.locale = locale;
	}


	public int getStartCountDownInMiliseconds() {
		return startCountDownInMiliseconds;
	}


	public void setStartCountDownInMiliseconds(int startCountDown) {
		this.startCountDownInMiliseconds = startCountDown;
	}
}
