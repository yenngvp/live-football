/**
 * @author: yen.nt
 * @created on Nov 9, 2015
 */
package com.footballun.repository.eventlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;

import com.footballun.model.MatchupDetail;
import com.footballun.model.MatchupLive;
import com.footballun.model.Squad;
import com.footballun.service.FootballunService;

/**
 * @author yen.nt
 *
 */
public class MatchupLiveRepositoryEventListener extends 
	AbstractRepositoryEventListener<MatchupLive> {

	@Autowired
	private FootballunService footballunService;
	
    @Override
    public void onAfterCreate(MatchupLive liveEvent) {
        
    	// Trigger livescore update for a Goal event
    	if (liveEvent != null
    			&& "Goal".equals(liveEvent.getEvent().getName())) {
    		triggerMatchupDetailUpdate(liveEvent, false);	
    	}
    }
 
    @Override
    public void onBeforeDelete(MatchupLive liveEvent) {
    	
    	// Trigger livescore update for a Goal event
    	if (liveEvent != null
    			&& "Goal".equals(liveEvent.getEvent().getName())) {
    		triggerMatchupDetailUpdate(liveEvent, true);	
    	}
    }
    
    private void triggerMatchupDetailUpdate(MatchupLive liveEvent, boolean isDeleted) {
    	
    	// a goal just scored must be counted for a specific squad or player 
    	if (!"Goal".equals(liveEvent.getEvent().getName()) 
    			|| (liveEvent.getMatchupDetail() == null && liveEvent.getMatchupRegister() == null)) {
    		return;
    	}
    	
    	/*
    	 *  Update match result for this live event.
    	 *  
    	 *  Record one goal for one squad in the matchup
    	 */
    	// Update match detail
    	MatchupDetail matchDetail;
    	if (liveEvent.getMatchupDetail() != null) {
    		matchDetail = liveEvent.getMatchupDetail();
    	} else if (liveEvent.getMatchupRegister() != null && liveEvent.getMatchupRegister().getMatchupDetail() != null) {
    		matchDetail = liveEvent.getMatchupRegister().getMatchupDetail();
    	} else {
    		return; // can't find the detail for the matchup
    	}
    	
    	// Verify that the current detail is matched with the matchup's detail
    	if (!matchDetail.equals(liveEvent.getMatchup().getFirstDetail())
    			&& !matchDetail.equals(liveEvent.getMatchup().getSecondDetail())) {
    		return; // the detail belongs to who?
    	}
    	
    	
    	if (isDeleted) {
    		matchDetail.setGoal(matchDetail.getGoal() - 1);
    	} else {
    		matchDetail.setGoal(matchDetail.getGoal() + 1);
    	}
    	footballunService.saveMatchupDetail(matchDetail);

    	// Trigger update matchup
    	// Updates matchup detail
    	matchDetail.getMatchup().setDetail(matchDetail);
    	// Persists matchup
    	footballunService.saveMatchup(matchDetail.getMatchup());

    	// Trigger service update
    	footballunService.onUpdateMatchup(matchDetail.getMatchup());
    }
}
