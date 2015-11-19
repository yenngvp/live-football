/**
 * @author: yen.nt
 * @created on Nov 18, 2015
 */
package com.footballun.repository.springdatajpa;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.MatchupStatus;
import com.footballun.repository.MatchupStatusRepository;

/**
 * @author yen.nt
 *
 */
public interface MatchupStatusRepositoryJpa extends MatchupStatusRepository,
		CrudRepository<MatchupStatus, Integer> {

	@Override
	MatchupStatus findByName(String name) throws DataAccessException;
}
