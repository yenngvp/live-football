/**
 * @author: YenNguyen
 * @date: Nov 7, 2015
 */
package com.footballun.repository.springdatajpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.footballun.model.MatchupLive;
import com.footballun.repository.MatchupLiveRepository;

/**
 * @author YenNguyen
 *
 */
public interface MatchupLiveRepositoryJpa extends MatchupLiveRepository, CrudRepository<MatchupLive, Integer> {

	@SuppressWarnings("unchecked")
	@Override
	public MatchupLive save(MatchupLive matchupLive);
	
	@Override
	public void deleteAll();
	
	@Override
	public List<MatchupLive> findAll();
}
