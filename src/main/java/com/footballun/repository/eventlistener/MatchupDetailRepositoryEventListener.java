/**
 * @author: yen.nt
 * @created on Nov 12, 2015
 */
package com.footballun.repository.eventlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;

import com.footballun.model.MatchupDetail;
import com.footballun.service.FootballunService;

/**
 * @author yen.nt
 *
 */
public class MatchupDetailRepositoryEventListener extends
		AbstractRepositoryEventListener<MatchupDetail> {

	@Autowired
	private FootballunService footballunService;
	
	
	@Override
    public void onAfterSave(MatchupDetail matchDetail) {

		// On save matchupDetail event, do recalculate associated matchup result
		if (matchDetail != null) {
			triggerMatchupUpdate(matchDetail);
		}
	}
	
    private void triggerMatchupUpdate(MatchupDetail matchDetail) {

    	// Updates matchup detail
    	matchDetail.getMatchup().setDetail(matchDetail);
    	// Persists matchup
    	footballunService.saveMatchup(matchDetail.getMatchup());
    	
    	// Recalculate over all standing if user is manually editing the match result
    	footballunService.recalculateStandingForTheCompetition(-1);
    }
}
