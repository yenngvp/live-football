/**
 * @author: yen.nt
 * @created on Nov 10, 2015
 */
package com.footballun.repository.springdatajpa;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.footballun.model.StandingLive;

/**
 * @author yen.nt
 *
 */
@Transactional
public interface StandingLiveRepository extends StandingBaseRepository<StandingLive>{

	@SuppressWarnings("unchecked")
	@Override
	StandingLive save(StandingLive standing) throws DataAccessException;
}
