/**
 * @author: yen.nt
 * @created on Nov 11, 2015
 */
package com.footballun.repository.springdatajpa;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.footballun.model.MatchupDetail;
import com.footballun.repository.MatchupDetailRepository;

/**
 * @author yen.nt
 *
 */
public interface MatchupDetailRepositoryJpa extends MatchupDetailRepository,
		JpaRepository<MatchupDetail, Integer> {

	@SuppressWarnings("unchecked")
	@Override
	public MatchupDetail save(MatchupDetail matchupDetail) throws DataAccessException;
}
