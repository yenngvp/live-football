/**
 * @author: yen.nt
 * @created on Nov 17, 2015
 */
package com.footballun.schedule.task;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.footballun.model.Matchup;
import com.footballun.model.MatchupStatus;
import com.footballun.model.MatchupStatus.MatchupStatusCode;
import com.footballun.service.FootballunService;
import com.footballun.util.AppConfigure;

/**
 * @author yen.nt
 *
 */
@Component
public class MatchupStatusUpdateTask {

	private static Logger logger = LoggerFactory.getLogger(MatchupStatusUpdateTask.class);
	
	private volatile boolean updating;
	
	@Autowired
	private FootballunService footballunService;

	public void update() {
		if (updating) {
			// Protect update submitted into multiple threads
			return;
		}
		updating = true;
		
		
		Calendar calendar = AppConfigure.getSingleton().getCurrentCalendar();
		Calendar matchCalendar = new GregorianCalendar(AppConfigure.getSingleton().getTimeZone(), AppConfigure.getSingleton().getLocale());
		
		long currentTime = calendar.getTimeInMillis();
		int startCountdown = AppConfigure.getSingleton().getStartCountDownInMiliseconds();
		long matchStartTime;
		long diffTime;
		
		MatchupStatus statusCountdown = footballunService.findMatchupStatusByName(MatchupStatus.getNameByCode(MatchupStatusCode.ENTER_COUNTDOWN));
		MatchupStatus statusJustBegin = footballunService.findMatchupStatusByName(MatchupStatus.getNameByCode(MatchupStatusCode.JUST_BEGIN));
		MatchupStatus statusFullTime = footballunService.findMatchupStatusByName(MatchupStatus.getNameByCode(MatchupStatusCode.FULL_TIME));
		
		List<Matchup> matchups = footballunService.findMatchupByCompetitionId(AppConfigure.getSingleton().getCurrentCompetition());
		for (Matchup matchup : matchups) {
			
			/*
			 * Checks for match's statuses
			 */
			if (matchup.getStartAt() != null) {
				matchCalendar.setTime(matchup.getStartAt());
				matchStartTime = matchCalendar.getTimeInMillis();
				diffTime = currentTime - matchStartTime;
				
				if (diffTime < 0 && Math.abs(diffTime) > startCountdown) {
					; // Do nothing, still too far to the match begin
				} else { 
					if (diffTime < 0 && Math.abs(diffTime) <= startCountdown) {
						// Match status "ENTER COUNTDOWN"
						matchup.setStatus(statusCountdown);
					} else {
						// Match is taking place, needs to check for fulltime or still in live
						long matchDuration;
						if (matchup.getEndAt() != null) {
							matchDuration = matchup.getEndAt().getTime() - matchStartTime; 
						} else {
							// Maximum for 2 hours long game
							matchDuration = 2 * 60 * 60;
						}

						if (diffTime <= matchDuration) {
							// Match status 'LIVE' 
							matchup.setStatus(statusJustBegin);
						} else if (diffTime > matchDuration && matchup.getStatus().getCode() == MatchupStatusCode.JUST_BEGIN) {
							// Match status "FULL TIME"
							matchup.setStatus(statusJustBegin);
						} else {
							// Match maybe full time, postposed, cancelled or unknown status
							; // Do nothing
						}
					}
				}

				logger.info("matchCalendar: " + matchCalendar.getTime().toString());
				logger.info("diffCal: " + matchCalendar.compareTo(calendar));
			}
		}
		
		updating = false;
	}
}
