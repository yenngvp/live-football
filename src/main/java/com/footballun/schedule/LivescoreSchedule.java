/**
 * @author: yen.nt
 * @created on Nov 13, 2015
 */
package com.footballun.schedule;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.footballun.model.Competition;
import com.footballun.model.Matchup;
import com.footballun.model.ScrapedDataResult;
import com.footballun.model.MatchupStatus.MatchupStatusCode;
import com.footballun.service.FootballunService;

/**
 * @author yen.nt
 *
 */

@Component
public class LivescoreSchedule {

	private static Logger logger = LoggerFactory.getLogger(LivescoreSchedule.class);
	
	private static final String TWO_GOALS_DELIMETER_REG = "-";
		
	@Autowired
	private FootballunService footballunService;
	
	/**
	 * Reads scraped live match results from scraped_data_result table which is populating by the Spider and then update the Matchups accordingly
	 */
	public void updateMatchupResults() {
		logger.warn("Call from a scheduled method");
		
		List<ScrapedDataResult> results = footballunService.findAllScrapedDataResultsJustUpdated();
		Matchup matchup;
		int[] goals;
		Set<Competition> foundCompetitions = new HashSet<>();
		
		for (ScrapedDataResult result : results) {
			matchup = result.getMatchup();
			if (matchup != null 
					&& matchup.getStatus().getCode() != MatchupStatusCode.FULL_TIME 
					&& result.isJustUpdate()) {
				
				goals = parseGoalsString(result.getResult());
				if (goals[0] > -1) {
					matchup.getFirstDetail().setGoal(goals[0]);
					matchup.getSecondDetail().setGoal(goals[1]);
					
					footballunService.saveMatchupDetail(matchup.getFirstDetail());
					footballunService.saveMatchupDetail(matchup.getSecondDetail());

					if ("FT".equals(result.getStatus())) {
						matchup.setStatus(footballunService.getMatchupStatusFullTime());
					} else if (matchup.getStatus().getCode() != MatchupStatusCode.LIVE){
						matchup.setStatus(footballunService.getMatchupStatusLive());
					}
					
					matchup.refreshResult();
					// Persists matchup
					footballunService.saveMatchup(matchup);
					
					foundCompetitions.add(matchup.getCompetition());
					
					// Accumulates result to the standing
					footballunService.accumulateStandingForMatchup(matchup);			
				}
			}

			result.setJustUpdate(false);
			footballunService.saveScrapedDataResult(result);
		}
		
		// Refresh standings for current matchday
		if (foundCompetitions.size() > 0) {
			Iterator<Competition> itr = foundCompetitions.iterator();
			while (itr.hasNext()) {
				footballunService.refreshStanding(itr.next().getId(), null, itr.next().getCurrentMatchday());
			}
		}
		
	}
	
	private int[] parseGoalsString(String goalsString) {
		
		if (goalsString == null || goalsString.length() < 3) {
			logger.error("Result is not parsable: " + goalsString);
			return new int[]{-1, -1};
		}
		
		// Splits by non-digit characters
		String[] goals = goalsString.split(TWO_GOALS_DELIMETER_REG);
		if (goals.length != 2) {
			logger.error("Result is not parsable: " + goalsString);
			return new int[]{-1, -1};
		}
		
		int goal1 = -1;
		int goal2 = -1;
		try {
			goal1 = Integer.valueOf(goals[0].trim());
			goal2 = Integer.valueOf(goals[1].trim());
		} catch (NumberFormatException e) {
			logger.error("Result is not parsable: " + goalsString);
			e.printStackTrace();
		}

		return new int[]{goal1, goal2};
	}
}
