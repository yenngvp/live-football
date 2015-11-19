/**
 * @author: yen.nt
 * @created on Nov 19, 2015
 */
package com.footballun.repository.eventlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;

import com.footballun.model.Standing;
import com.footballun.service.FootballunService;

/**
 * @author yen.nt
 *
 */
public class StandingRepositoryEventListener extends
		AbstractRepositoryEventListener<Standing> {

	@Autowired
	private FootballunService footballunService;
	
	
	@Override
    public void onAfterSave(Standing standing) {

		// A tricky goes here, we don't allow to manually edit standing table to protect data always in consistent status.
		// But react this action to enforce recalculate standing for the whole competition (just in case the website got serious errors)
		footballunService.recalculateStandingForTheCompetition(-1);
	}
}
