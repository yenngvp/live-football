/**
 * @author: yen.nt
 * @created on Nov 10, 2015
 */
package com.footballun.repository.springdatajpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.footballun.model.Standing;
import com.footballun.repository.StandingRepository;

/**
 * @author yen.nt
 *
 */
@Transactional
public interface StandingRepositoryJpa extends  StandingRepository, CrudRepository<Standing, Integer> {

	@SuppressWarnings("unchecked")
	@Override
	Standing save(Standing standing) throws DataAccessException;
	
	@Override
	@Query(value = "select s.* from standing s inner join  squad on s.squad_id=squad.id where current_position < 5 and allow_update order by squad.competition_id, current_position", nativeQuery = true)
	List<Standing> findShortList() throws DataAccessException;
   
	@Override
	@Query(value = "select st.* from standing st inner join squad sq on st.squad_id=sq.id "
			+ " inner join competition c on sq.competition_id=c.id "
			+ " where c.id=?1 and st.matchday=c.current_matchday"
			+ " order by st.current_position", nativeQuery = true)
	List<Standing> findCurrentStandingsByCompetition(Integer competitionId) throws DataAccessException;
	
	@Override
	List<Standing> findBySquad_CompetitionIdOrderByMatchdayAscCurrentPositionAsc(Integer competitionId) throws DataAccessException;

	@Override
    Standing findBySquadIdAndAllowUpdateTrueOrderByCurrentPositionAsc(Integer squadId) throws DataAccessException;
	
	@Override
	List<Standing> findBySquad_CompetitionIdAndMatchdayOrderByCurrentPositionAsc(Integer competitionId, Integer matchday) throws DataAccessException;
	
	@Override
	Standing findBySquadIdAndMatchdayOrderByCurrentPositionAsc(Integer squadId, Integer matchday) throws DataAccessException;
	
	@Override
	@Query(value = "select st.* from standing st inner join squad sq on st.squad_id=sq.id "
			+ " inner join competition c on sq.competition_id=c.id "
			+ " where sq.id=?1 and st.matchday<=c.current_matchday"
			+ " order by st.matchday,st.current_position", nativeQuery = true)
    List<Standing> findAllBySquadIdOrderByMatchdayAscCurrentPositionAsc(Integer squadId) throws DataAccessException;
	
}
