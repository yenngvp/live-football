/**
 * @author: yen.nt
 * @created on Nov 10, 2015
 */
package com.footballun.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.footballun.model.Squad;
import com.footballun.model.Standing;

/**
 * @author yen.nt
 *
 */
public interface StandingRepository {

	List<Standing> findBySquad_CompetitionIdOrderByCurrentPositionAsc(Integer id) throws DataAccessException;
	
	//Standing save(Standing standing) throws DataAccessException;
	
	/**
	 * Finds a standing by squad
	 */
	Standing findBySquad(Squad squad) throws DataAccessException;
}
