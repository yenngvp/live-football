/**
 * @author: yen.nt
 * @created on Nov 11, 2015
 */
package com.footballun.repository;

import org.springframework.dao.DataAccessException;

import com.footballun.model.MatchupDetail;

/**
 * @author yen.nt
 *
 */
public interface MatchupDetailRepository {

	MatchupDetail save(MatchupDetail matchupDetail) throws DataAccessException;
}
