package com.footballun.repository.springdatajpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.Matchup;
import com.footballun.repository.MatchupRepository;

public interface MatchupRepositoryJpa extends MatchupRepository, CrudRepository<Matchup, Integer> {

	@Override
	List<Matchup> findByMatchday(Integer matchday) throws DataAccessException;
	
	@Override
	List<Matchup> findByRound(String round) throws DataAccessException;
	
	@Override
	List<Matchup> findByFeatured(Boolean featured) throws DataAccessException;
	
	@SuppressWarnings("unchecked")
	@Override
	Matchup save(Matchup matchup) throws DataAccessException;
	
	@Override
	List<Matchup> findByCompetitionId(Integer competitionId) throws DataAccessException;
}
