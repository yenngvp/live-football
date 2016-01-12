/**
 * @author: yen.nt
 * @created on Jan 12, 2016
 */
package com.footballun.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.footballun.model.Standing;

/**
 * @author yen.nt
 *
 */
public interface StandingRepository {

	Standing save(Standing standing) throws DataAccessException;
	
	List<Standing> findShortList() throws DataAccessException;
	
	List<Standing> findCurrentStandingsByCompetition(Integer competitionId) throws DataAccessException;

    // Finds by competition and matchday
    List<Standing> findBySquad_CompetitionIdAndMatchdayOrderByCurrentPositionAsc(Integer competitionId, Integer matchday) throws DataAccessException;
	
    // Finds by competition
	List<Standing> findBySquad_CompetitionIdOrderByMatchdayAscCurrentPositionAsc(Integer competitionId) throws DataAccessException;

	// Finds by squad and allowUpdate
    Standing findBySquadIdAndAllowUpdateTrueOrderByCurrentPositionAsc(Integer squadId) throws DataAccessException;
    
    // Finds by squad and allowUpdate
    Standing findBySquadIdAndMatchdayOrderByCurrentPositionAsc(Integer squadId, Integer matchday) throws DataAccessException;
    
    // Finds all by squads
    List<Standing> findAllBySquadIdOrderByMatchdayAscCurrentPositionAsc(Integer squadId) throws DataAccessException;
}
