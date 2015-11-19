package com.footballun.schedule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.footballun.model.Matchup;
import com.footballun.model.MatchupStatus;
import com.footballun.model.MatchupStatus.MatchupStatusCode;
import com.footballun.schedule.task.MatchupStatusUpdateTask;
import com.footballun.service.FootballunService;
import com.footballun.util.AppConfigure;


@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("spring-data-jpa")
@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
public class MatchupStatusUpdateTaskTest {

	@Autowired
	private FootballunService footballunService;
	
	@Autowired
	private MatchupStatusUpdateTask updater;
	
	private AppConfigure appConfigure;
	private Matchup matchup;
	
	@Before
    public void setupMock() {
		appConfigure = mock(AppConfigure.class);
		matchup = mock(Matchup.class);
    }
	
	@Test
	public void testSetup() {
		assertNotNull(updater);
		assertNotNull(appConfigure);
		assertNotNull(matchup);
		assertNotNull(footballunService);
	}
	
	@Test
	public void testUpdateMatchupStatus() {
		
		when(appConfigure.getTimeZone()).thenReturn(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
		when(appConfigure.getLocale()).thenReturn(Locale.US);
		when(appConfigure.getStartCountDownInMiliseconds()).thenReturn((long) 7 * 24 * 60 * 3600);
		
		// Now
		Calendar now = new GregorianCalendar(appConfigure.getTimeZone(), appConfigure.getLocale());
		long startCountdownInMillis = appConfigure.getStartCountDownInMiliseconds();
		// Future = Now + startCountdownInMillis (start match)
		Calendar matchStartAt = new GregorianCalendar(appConfigure.getTimeZone(), appConfigure.getLocale());
		matchStartAt.setTimeInMillis(now.getTimeInMillis() + startCountdownInMillis); 
		// Future = startMatch time + duration (finish match)
		long matchStartAtMillis = matchStartAt.getTimeInMillis();
		long matchDurationMillis = 120 * 60 * 60; // 2 hours
		Calendar matchEndAt = new GregorianCalendar(appConfigure.getTimeZone(), appConfigure.getLocale());
		matchEndAt.setTimeInMillis(matchStartAtMillis + matchDurationMillis);
				
		matchup.setStartAt(matchStartAt.getTime());
		matchup.setEndAt(matchEndAt.getTime());
		
		MatchupStatus notBegin = footballunService.findMatchupStatusByName(MatchupStatus.getNameByCode(MatchupStatusCode.NOT_BEGIN));
		MatchupStatus statusCountdown = footballunService.findMatchupStatusByName(MatchupStatus.getNameByCode(MatchupStatusCode.ENTER_COUNTDOWN));
		MatchupStatus statusJustBegin = footballunService.findMatchupStatusByName(MatchupStatus.getNameByCode(MatchupStatusCode.JUST_BEGIN));
		MatchupStatus statusFullTime = footballunService.findMatchupStatusByName(MatchupStatus.getNameByCode(MatchupStatusCode.FULL_TIME));
		assertNotNull(notBegin);
		assertNotNull(statusCountdown);
		assertNotNull(statusJustBegin);
		assertNotNull(statusFullTime);
		

		//when(appConfigure.getServerCalendar()).thenReturn(now);
		//updater.updateMatchupStatus(statusCountdown, statusJustBegin, statusFullTime, matchup);
		
		assertEquals(MatchupStatusCode.JUST_BEGIN, matchup.getStatus().getCode());
		
	}
}
