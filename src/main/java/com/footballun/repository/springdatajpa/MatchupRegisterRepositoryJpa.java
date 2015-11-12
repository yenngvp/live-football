/**
 * @author: yen.nt
 * @created on Nov 12, 2015
 */
package com.footballun.repository.springdatajpa;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.MatchupRegister;
import com.footballun.repository.MatchupRegisterRepository;

/**
 * @author yen.nt
 *
 */
public interface MatchupRegisterRepositoryJpa extends
		MatchupRegisterRepository, CrudRepository<MatchupRegister, Integer> {

	@SuppressWarnings("unchecked")
	@Override
	MatchupRegister save(MatchupRegister matchupRegister) throws DataAccessException;
}
