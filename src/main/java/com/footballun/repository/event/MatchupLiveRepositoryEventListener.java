/**
 * @author: yen.nt
 * @created on Nov 9, 2015
 */
package com.footballun.repository.event;

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
        
    	// Only do automatic live update in non-manual mode
    	if (liveEvent != null 
    			&& !liveEvent.getMatchup().getManualMode()
    			&& "Goal".equals(liveEvent.getEvent().getName())) {
    		triggerMatchupUpdate(liveEvent, false);	
    	}
    }
 
    @Override
    public void onBeforeDelete(MatchupLive liveEvent) {
    	
    	// Only do automatic live update in non-manual mode
    	if (liveEvent != null 
    			&& !liveEvent.getMatchup().getManualMode()
    			&& "Goal".equals(liveEvent.getEvent().getName())) {
    		triggerMatchupUpdate(liveEvent, true);	
    	}
    }
    
    private void triggerMatchupUpdate(MatchupLive liveEvent, boolean isDeleted) {
    	
    	if (!"Goal".equals(liveEvent.getEvent().getName())) {
    		return;
    	}
    	/*
    	 *  Update match result for this live event.
    	 *  
    	 *  Record one goal for one squad in the matchup
    	 */
    	// Update match detail
    	Squad squad = liveEvent.getMatchupRegister().getSquadMember().getSquad();
    	MatchupDetail matchDetail = liveEvent.getMatchup().getDetailBySquad(squad);
    	if (matchDetail != null) {
    		if (isDeleted) {
    			matchDetail.setGoal(matchDetail.getGoal() - 1);
    		} else {
    			matchDetail.setGoal(matchDetail.getGoal() + 1);
    		}
    		footballunService.saveMatchupDetail(matchDetail);

    		// Refresh standing
    		footballunService.refreshStanding(null);
    	}
    }
}
