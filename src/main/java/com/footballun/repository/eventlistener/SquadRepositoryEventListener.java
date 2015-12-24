/**
 * @author: yen.nt
 * @created on Nov 19, 2015
 */
package com.footballun.repository.eventlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;

import com.footballun.model.Squad;
import com.footballun.service.FootballunService;

/**
 * @author yen.nt
 *
 */
public class SquadRepositoryEventListener extends
		AbstractRepositoryEventListener<Squad> {

	@Autowired
	private FootballunService footballunService;
	
	
	@Override
    public void onAfterCreate(Squad squad) {

	}
	
	@Override
    public void onBeforeDelete(Squad squad) {
		// not implemented yet
	}
}
