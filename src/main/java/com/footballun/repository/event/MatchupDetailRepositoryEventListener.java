/**
 * @author: yen.nt
 * @created on Nov 12, 2015
 */
package com.footballun.repository.event;

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
		
		// Only do trigger update if in manualMode only or it will be duplicated with MatchupLive event listener already do this
		if (matchDetail != null && matchDetail.getMatchup().getManualMode()) {
			triggerMatchupUpdate(matchDetail);
		}
	}
	
	@Override
    public void onBeforeDelete(MatchupDetail matchDetail) {
    	
		// Shouldn't go here, we don't expect match detail deletion but still handle it to enforce data consistency
		if (matchDetail != null
				&& matchDetail.getMatchup().getManualMode()) {
			triggerMatchupUpdate(matchDetail);
		}
    }
    
    private void triggerMatchupUpdate(MatchupDetail matchDetail) {

    	// Refresh standing
    	footballunService.refreshStanding(null);
    }
}
