/**
 * @author: yen.nt
 * @created on Nov 9, 2015
 */
package com.footballun.repository;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;

import com.footballun.model.Matchup;
import com.footballun.model.MatchupLive;
import com.footballun.model.Squad;
import com.footballun.service.FootballunService;

/**
 * @author yen.nt
 *
 */
public class MatchupLiveRepositoryEventListener extends 
	AbstractRepositoryEventListener<MatchupLive> {

	private final Logger logger = LoggerFactory.getLogger(MatchupLiveRepositoryEventListener.class);

	@Autowired
	private FootballunService footballunService;
	
	@Override
    public void onBeforeSave(MatchupLive liveEvent) {
  
    }
 
    @Override
    public void onAfterSave(MatchupLive liveEvent) {
              
        triggerMatchupUpdate(liveEvent);
    }
 
    @Override
    public void onBeforeDelete(MatchupLive liveEvent) {
    	
    	triggerMatchupUpdate(liveEvent);
    }
    
    private void triggerMatchupUpdate(MatchupLive liveEvent) {
    	
    	if (liveEvent != null) {
    		/*
    		 *  Update match result for this live event.
    		 *  
    		 *  Record one goal for one squad in the matchup
    		 */
    		Matchup match = liveEvent.getMatchup(); 
    		match.recalculateResult();
    		footballunService.updateMatchup(match);
    		Iterator<Squad> itr = match.getSquads().iterator();
    		while (itr.hasNext()) {
    			logger.debug("triggerSquadStaningUpdate");
    			footballunService.updateSquadStanding(itr.next());
    		}
    	}
    }
}
