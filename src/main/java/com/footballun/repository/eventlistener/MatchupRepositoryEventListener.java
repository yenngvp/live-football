/**
 * @author: yen.nt
 * @created on Nov 18, 2015
 */
package com.footballun.repository.eventlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;

import com.footballun.model.Matchup;
import com.footballun.service.FootballunService;

/**
 * @author yen.nt
 *
 */
public class MatchupRepositoryEventListener extends
		AbstractRepositoryEventListener<Matchup> {

	@Autowired
	private FootballunService footballunService;
	
	
	@Override
    public void onAfterSave(Matchup matchup) {

		// On matchup update event, do recalculate standings of the competition
		if (matchup != null) {
			// Recalculate over all standing if user is manually editing the match result
	    	footballunService.recalculateStandingForTheCompetition(-1);
		}
	}
}
