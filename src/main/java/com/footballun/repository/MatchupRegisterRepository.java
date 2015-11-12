/**
 * @author: yen.nt
 * @created on Nov 12, 2015
 */
package com.footballun.repository;

import org.springframework.dao.DataAccessException;

import com.footballun.model.MatchupRegister;

/**
 * @author yen.nt
 *
 */
public interface MatchupRegisterRepository {

	MatchupRegister save(MatchupRegister matchupRegister) throws DataAccessException;
}
