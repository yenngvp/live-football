package com.footballun.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.footballun.model.Matchup;

public interface MatchupRepository {

    List<Matchup> findByMatchdayAndCompetitionIdOrderByStartAtAscKickoffAsc(Integer matchday, Integer competitionId) throws DataAccessException;
	
	List<Matchup> findByFeaturedOrderByStartAtAsc(Boolean featured) throws DataAccessException;
	
	Matchup save(Matchup matchup) throws DataAccessException;
	
	List<Matchup> findLatestMatchupCalendar(Integer competitionId) throws DataAccessException;
	
	List<Matchup> findByCompetitionIdAndStatus_NameInOrderByMatchdayAsc(Integer competitionId, Collection<String> statuses) throws DataAccessException;
	
	Matchup findById(Integer id) throws DataAccessException;
	
	List<Matchup> findByCompetitionIdAndStartAtBetweenOrderByStartAtAsc(Integer competitionId, LocalDate from, LocalDate to) throws DataAccessException;
	
	List<Matchup> findByFeaturedTrueOrderByCompetitionIdAscStartAtAscKickoffAsc() throws DataAccessException;

	
	/*
	 * Matchup results
	 */
	List<Matchup> findMatchupLatestResults(Integer competitionId) throws DataAccessException;
}
