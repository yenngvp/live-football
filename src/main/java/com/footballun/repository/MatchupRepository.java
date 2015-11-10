package com.footballun.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.footballun.model.Matchup;

public interface MatchupRepository {

	List<Matchup> findByMatchday(Integer matchday) throws DataAccessException;
	
	List<Matchup> findByRound(String round) throws DataAccessException;
	
	List<Matchup> findByFeatured(Boolean featured) throws DataAccessException;
	
	Matchup save(Matchup matchup) throws DataAccessException;
}
