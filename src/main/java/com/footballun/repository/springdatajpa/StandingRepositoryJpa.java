/**
 * @author: yen.nt
 * @created on Nov 10, 2015
 */
package com.footballun.repository.springdatajpa;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.footballun.model.Squad;
import com.footballun.model.Standing;
import com.footballun.repository.StandingRepository;

/**
 * @author yen.nt
 *
 */
@Repository
@Transactional
public interface StandingRepositoryJpa extends StandingRepository, JpaRepository<Standing, Integer> {

	@Override
	public List<Standing> findBySquad_CompetitionIdOrderByCurrentPositionAsc(Integer id) throws DataAccessException;
	
	@SuppressWarnings("unchecked")
	@Override
	public Standing save(Standing standing) throws DataAccessException;
	
	/**
	 * Finds a standing by squad
	 */
	@Override
	public Standing findBySquad(Squad squad) throws DataAccessException;
}
