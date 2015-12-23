/**
 * @author: yen.nt
 * @created on Dec 23, 2015
 */
package com.footballun.schedule.task;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.footballun.model.Competition;
import com.footballun.model.Matchup;
import com.footballun.service.FootballunService;

/**
 * @author yen.nt
 *
 */
@Component
public class MatchdayMonitorTask {

	private final Logger logger = LoggerFactory.getLogger(MatchdayMonitorTask.class);
	
	@Autowired
	private FootballunService footballunService;
	
	/**
	 * Finds and updates current matchday for competitions
	 */
	public void findAndUpdateCurrentMatchday() {
		logger.info("BEGIN findAndUpdateCurrentMatchday() method");
		
		List<Competition> competitions = footballunService.findAllCompetition(LocalDate.now(), "LEAGUE");
		
		// Gets a matchup of every competition that happening today with greatest matchday
		for (Competition competition : competitions) {
			Matchup matchup = footballunService.findMatchupByTodayAndCompetition(competition.getId());
			if (matchup != null) {
				// Updates current competition matchday as matchup's matchday
				competition.setCurrentMatchday(matchup.getMatchday());
				footballunService.save(competition);
			}
		}
		
		logger.info("END findAndUpdateCurrentMatchday() method");
	}
}
