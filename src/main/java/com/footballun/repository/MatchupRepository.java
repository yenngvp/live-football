package com.footballun.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.footballun.model.Matchup;

public interface MatchupRepository {
	
	List<Matchup> findByMatchdayAndCompetitionIdOrderByStartAtAsc(Integer matchday, Integer competitionId) throws DataAccessException;
	
	List<Matchup> findByFeaturedOrderByStartAtAsc(Boolean featured) throws DataAccessException;
	
	Matchup save(Matchup matchup) throws DataAccessException;
	
	List<Matchup> findByCompetitionIdOrderByStartAtAsc(Integer competitionId) throws DataAccessException;
	
	List<Matchup> findByCompetitionIdAndStatus_NameInOrderByMatchdayAsc(Integer competitionId, Collection<String> statuses) throws DataAccessException;
	
	Matchup findById(Integer id) throws DataAccessException;
	
	List<Matchup> findByCompetitionIdAndStartAtBetweenOrderByStartAtAsc(Integer competitionId, LocalDate from, LocalDate to) throws DataAccessException;
	
	List<Matchup> findByFeaturedTrueOrderByCompetitionIdAscStartAtAscKickoffAsc() throws DataAccessException;
	
	List<Matchup> findAllByMatchday() throws DataAccessException;
	
	List<Matchup> findByMatchday(Integer matchday) throws DataAccessException;
	
	/**
	 * Gets a matchup today for a competition that has greatest matchday
	 * 
	 * @param today
	 * @param competitionId
	 * @return
	 * @throws DataAccessException
	 */
	Matchup findOneByTodayAndCompetitionId(Integer competitionId) throws DataAccessException;
	
	/*
	 * Matchup results
	 */
	List<Matchup> findNearestTenResults() throws DataAccessException;
	List<Matchup> findTop10ByCompetitionIdAndStatus_NameInOrderByStartAtDescKickoffDesc(Integer competitionId, Collection<String> statuses) throws DataAccessException;
	List<Matchup> findByCompetitionIdAndMatchdayAndStatus_NameInOrderByStartAtDescKickoffDesc(Integer competitionId, Integer matchday, Collection<String> statuses) throws DataAccessException;
}
