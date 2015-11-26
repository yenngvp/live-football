package com.footballun.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.footballun.model.Matchup;

public interface MatchupRepository {

	List<Matchup> findByMatchdayOrderByStartAtAsc(Integer matchday) throws DataAccessException;
	
	List<Matchup> findByRoundOrderByStartAtAsc(String round) throws DataAccessException;
	
	List<Matchup> findByFeaturedOrderByStartAtAsc(Boolean featured) throws DataAccessException;
	
	Matchup save(Matchup matchup) throws DataAccessException;
	
	List<Matchup> findByCompetitionIdOrderByStartAtAsc(Integer competitionId) throws DataAccessException;
	
	List<Matchup> findByCompetitionIdAndStatus_NameIn(Integer competitionId, Collection<String> statuses) throws DataAccessException;
	
	Matchup findById(Integer id) throws DataAccessException;
}
