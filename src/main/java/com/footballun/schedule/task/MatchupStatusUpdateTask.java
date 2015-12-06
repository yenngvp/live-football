/**
 * @author: yen.nt
 * @created on Nov 17, 2015
 */
package com.footballun.schedule.task;

import com.footballun.model.Matchup;
import com.footballun.service.FootballunService;
import com.footballun.util.AppConfigure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
	
	@Autowired
	private AppConfigure appConfigure;

	public synchronized void update() {
		if (updating) {
			// Protect update submitted into multiple threads
			return;
		}
		updating = true;
		
		List<Matchup> matchups = footballunService.findMatchupByCompetitionId(appConfigure.getCurrentCompetition());
		for (Matchup matchup : matchups) {
			
			updateMatchupStatus(matchup);
		}
		
		updating = false;
	}

	public void updateMatchupStatus(Matchup matchup) {

/*
		long currentTime = calendar.getTimeInMillis();
		long matchStartTime;
		long diffTime;
		long startCountdown = appConfigure.getStartCountDownInMiliseconds();

		if (matchup.getStartAt() != null) {
//			matchCalendar.setTime(matchup.getStartAt());
			matchStartTime = matchCalendar.getTimeInMillis();
			diffTime = currentTime - matchStartTime;
			
			if (diffTime < 0 && Math.abs(diffTime) > startCountdown) {
				; // Do nothing, still too far to the match begin
			} else { 
				if (diffTime < 0 && Math.abs(diffTime) <= startCountdown) {
					// Match status "ENTER COUNTDOWN"
					matchup.setStatus(footballunService.getMatchupStatusCountdown());
				} else {
					// Match is taking place, needs to check for fulltime or still in live
					long matchDuration;
					if (matchup.getEndAt() != null) {
//						matchDuration = matchup.getEndAt().getTime() - matchStartTime;
						matchDuration = 0;
					} else {
						// Maximum for 2 hours long game
						matchDuration = appConfigure.getDefaultMatchDuration() / 60 * AppConfigure.HOUR_TO_MILLISECONDS ;
					}

					if (diffTime <= matchDuration 
							&& matchup.getStatus().getCode() != MatchupStatusCode.LIVE
							&& matchup.getStatus().getCode() != MatchupStatusCode.FULL_TIME) {
						// Match status 'is about to start' 
						matchup.setStatus(footballunService.getMatchupStatusJustBegin());
						footballunService.onStartMatchup(matchup);
					} else if (diffTime > matchDuration 
							&& matchup.getStatus().getCode() == MatchupStatusCode.LIVE) {
						// Match status "JSUT FULL TIME"
						matchup.setStatus(footballunService.getMatchupStatusJustFullTime());
						footballunService.onFinishMatchup(matchup);
					} 
						
					// Otherwise the status shouldn't be changed
				}
				
				footballunService.saveMatchup(matchup);
			}

			logger.info("calendar: " + calendar.getTime().toString());
			logger.info("matchCalendar: " + matchCalendar.getTime().toString());
			logger.info("diffTime: " + diffTime);
		}

		*/
		return;
	}
	
	
}
