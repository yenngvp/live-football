package com.footballun.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.footballun.model.Matchup;
import com.footballun.model.MatchupStatus;

public interface MatchupRepository {

	List<Matchup> findByMatchday(Integer matchday) throws DataAccessException;
	
	List<Matchup> findByRound(String round) throws DataAccessException;
	
	List<Matchup> findByFeatured(Boolean featured) throws DataAccessException;
	
	Matchup save(Matchup matchup) throws DataAccessException;
	
	List<Matchup> findByCompetitionId(Integer competitionId) throws DataAccessException;
	
	List<Matchup> findByCompetitionIdAndStatus_NameIn(Integer competitionId, Collection<String> statuses) throws DataAccessException;
	
	Matchup findById(Integer id) throws DataAccessException;
}
