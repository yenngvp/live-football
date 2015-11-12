/**
 * @author: YenNguyen
 * @date: Nov 7, 2015
 */
package com.footballun.repository;

import java.util.List;

import com.footballun.model.MatchupLive;

/**
 * @author YenNguyen
 *
 */
public interface MatchupLiveRepository {

	MatchupLive save(MatchupLive  matchupLive);
	
	void deleteAll();
	
	List<MatchupLive> findAll();
}
