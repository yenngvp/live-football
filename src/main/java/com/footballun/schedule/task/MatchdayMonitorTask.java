/**
 * @author: yen.nt
 * @created on Dec 23, 2015
 */
package com.footballun.schedule.task;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.footballun.model.Competition;
import com.footballun.model.Matchup;
import com.footballun.model.Squad;
import com.footballun.model.MatchupStatus.MatchupStatusCode;
import com.footballun.model.Standing;
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
		List<Matchup> matchups;
		boolean matchdayStarted;
		boolean matchdayFinished;
		for (Competition competition : competitions) {
			matchups = footballunService.findMatchupByMatchdayAndCompetition(competition.getCurrentMatchday(), competition.getId());
			matchdayStarted = false;
			matchdayFinished = true;
			for (Matchup matchup : matchups) {
				if (!matchdayStarted && (matchup.getStatus().getCode() == MatchupStatusCode.LIVE 
						|| matchup.getStatus().getCode() == MatchupStatusCode.FULL_TIME)) {
					// At least a game has finished
					matchdayStarted = true;
				}
				
				if (matchup.getStatus().getCode() != MatchupStatusCode.FULL_TIME && matchdayFinished) {
					// At least a game has NOT YET finished
					matchdayFinished = false;
				}
			}
			
			// Updates competition
			competition.setMatchdayStarted(matchdayStarted);
			competition.setMatchdayFinished(matchdayFinished);
			if (matchdayFinished) {
				// All matchday's games has finished, go to the next matchday
				if (competition.getCurrentMatchday() < competition.getTotalMatchdays()) {
					competition.setCurrentMatchday(competition.getCurrentMatchday() + 1);
					competition.setMatchdayStarted(false);
					competition.setMatchdayFinished(false);
				}
				
				List<Squad> squads = footballunService.findSquadByCompetitionAndGeneration(competition.getId(), "First Team");
				for (Squad squad : squads) {
					// Copy standings for the current matchday as previous matchday
					Standing prevStanding = footballunService.findStandingBySquadAndMatchday(squad, competition.getCurrentMatchday() - 1);
					Standing currStanding = footballunService.findStandingBySquadAndMatchday(squad, competition.getCurrentMatchday());
					Integer curId = currStanding.getId();
					BeanUtils.copyProperties(prevStanding, currStanding, Standing.class);
					currStanding.setId(curId);

					prevStanding.setAllowUpdate(false);
					currStanding.setAllowUpdate(true);
					
					footballunService.saveStanding(prevStanding);
					footballunService.saveStanding(currStanding);
				}
			}

			footballunService.save(competition);
		}
		
		logger.info("END findAndUpdateCurrentMatchday() method");
	}
}
