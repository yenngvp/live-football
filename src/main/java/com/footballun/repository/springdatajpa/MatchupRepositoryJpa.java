package com.footballun.repository.springdatajpa;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.Matchup;
import com.footballun.repository.MatchupRepository;

public interface MatchupRepositoryJpa extends MatchupRepository, CrudRepository<Matchup, Integer> {

	@Override
	@Query(value = "SELECT m FROM Matchup m GROUP BY competition ORDER BY competition Asc, startAt Asc", 
		   countQuery = "SELECT COUNT(1) FROM (SELECT COUNT(1) FROM Matchup m GROUP BY competition")
	List<Matchup[]> findByMatchdayOrderByStartAtAsc(Integer matchday) throws DataAccessException;
	
	@Override
	List<Matchup> findByMatchdayAndCompetitionIdOrderByStartAtAsc(Integer matchday, Integer competitionId) throws DataAccessException;
	
//	@Override
//	List<Matchup> findByRoundOrderByStartAtAsc(String round) throws DataAccessException;
	
	@Override
	List<Matchup> findByFeaturedOrderByStartAtAsc(Boolean featured) throws DataAccessException;
	
	@SuppressWarnings("unchecked")
	@Override
	Matchup save(Matchup matchup) throws DataAccessException;
	
	@Override
	List<Matchup> findByCompetitionIdOrderByStartAtAsc(Integer competitionId) throws DataAccessException;
	
	@Override
	List<Matchup> findByCompetitionIdAndStatus_NameIn(Integer competitionId, Collection<String> statuses) throws DataAccessException;
	
	@Override
	Matchup findById(Integer id) throws DataAccessException;
}
