package com.footballun.repository.springdatajpa;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.Matchup;
import com.footballun.repository.MatchupRepository;

public interface MatchupRepositoryJpa extends MatchupRepository, CrudRepository<Matchup, Integer> {

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
	@Query(value = "SELECT m.* FROM matchup m INNER JOIN competition c ON m.competition_id=c.id AND m.matchday=c.next_matchday WHERE m.competition_id=?0 ORDER BY m.start_at ASC", nativeQuery = true)
	List<Matchup> findByCompetitionIdOrderByStartAtAsc(Integer competitionId) throws DataAccessException;
	
	@Override
	List<Matchup> findByCompetitionIdAndStatus_NameIn(Integer competitionId, Collection<String> statuses) throws DataAccessException;
	
	@Override
	Matchup findById(Integer id) throws DataAccessException;
	
	@Override
	List<Matchup> findByCompetitionIdAndStartAtBetweenOrderByStartAtAsc(Integer competitionId, LocalDate from, LocalDate to) throws DataAccessException;
	
	@Override
	@Query(value = "SELECT m.* FROM matchup m INNER JOIN competition c ON m.competition_id=c.id AND m.matchday=c.next_matchday WHERE m.start_at >= CURDATE() AND featured=1 ORDER BY m.competition_id ASC, m.start_at ASC, m.kickoff ASC", nativeQuery = true)
	List<Matchup> findByFeaturedMatchday() throws DataAccessException;
	
	@Override
	@Query(value = "SELECT m.* FROM matchup m INNER JOIN competition c ON m.competition_id=c.id AND m.matchday=c.next_matchday WHERE m.start_at >= CURDATE() ORDER BY m.competition_id ASC, m.start_at ASC, m.kickoff ASC", nativeQuery = true)
	List<Matchup> findAllByMatchday() throws DataAccessException;
	
	@Override
	@Query(value = "SELECT m.* FROM matchup m INNER JOIN competition c ON m.competition_id=c.id AND m.matchday=c.next_matchday WHERE m.start_at >= CURDATE() AND matchday=?0 ORDER BY m.competition_id ASC, m.start_at ASC, m.kickoff ASC", nativeQuery = true)
	List<Matchup> findByMatchday(Integer matchday) throws DataAccessException;
	
	/*
	 * Matchup results
	 */
	@Override
	List<Matchup> findTop10ByStatus_NameInOrderByCompetitionAscStartAtDescKickoffDesc(Collection<String> statuses) throws DataAccessException;
	
	@Override
	List<Matchup> findTop10ByCompetitionIdAndStatus_NameInOrderByStartAtDescKickoffDesc(Integer competitionId, Collection<String> statuses) throws DataAccessException;
	
	@Override
	List<Matchup> findByCompetitionIdAndMatchdayAndStatus_NameInOrderByStartAtDescKickoffDesc(Integer competitionId, Integer matchday, Collection<String> statuses) throws DataAccessException;
}
