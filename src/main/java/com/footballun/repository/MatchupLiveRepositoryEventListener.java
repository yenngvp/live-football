/**
 * @author: yen.nt
 * @created on Nov 9, 2015
 */
package com.footballun.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;

import com.footballun.model.Matchup;
import com.footballun.model.MatchupLive;
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
  
       logger.info("#handleBeforeSaveLiveEvent");
    }
 
    @Override
    public void onAfterSave(MatchupLive liveEvent) {
        logger.info("#handleAfterSaveLiveEvent: ");
              
        triggerMatchupUpdate(liveEvent);
    }
 
    @Override
    public void onBeforeDelete(MatchupLive liveEvent) {
    	
    	logger.info("#handleonBeforeDeleteLiveEvent: ");
    	triggerMatchupUpdate(liveEvent);
    }
    
    private void triggerMatchupUpdate(MatchupLive liveEvent) {
    	
    	if (liveEvent != null) {
    		/*
    		 *  Update match result for this live event.
    		 *  
    		 *  Record one goal for one squad in the matchup
    		 */
    		logger.info("#handleAfterSaveLiveEvent: 1");
    		liveEvent.getMatchup().recalculateResult();
    		logger.info("#handleAfterSaveLiveEvent: 2: ", footballunService);
    		footballunService.saveMatchup(liveEvent.getMatchup());
    		logger.info("#handleAfterSaveLiveEvent: 3");
    	}
    }
}
