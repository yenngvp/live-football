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
	List<Matchup> findByMatchdayAndCompetitionIdOrderByStartAtAscKickoffAsc(Integer matchday, Integer competitionId) throws DataAccessException;
	
	@SuppressWarnings("unchecked")
	@Override
	Matchup save(Matchup matchup) throws DataAccessException;
	
	/*
	 * Find latest matches of the competition by:
	 * + the first matchday, or
	 * + the current_matchday has not started or not finished yet,
	 * + the last matchday
	 */
	@Override
	@Query(value = "SELECT * FROM matchup m inner join competition c on m.competition_id=c.id"
					+ " WHERE c.id = ?1 AND ((c.current_matchday = 1 AND m.matchday = 1)"
					+ "							OR (c.current_matchday > 1 AND c.matchday_started = 0 AND c.current_matchday = m.matchday)"
					+ "							OR (c.current_matchday > 1 AND c.matchday_finished = 0 AND c.current_matchday = m.matchday)"
					+ "							OR (c.matchday_finished=1 AND c.current_matchday = m.matchday AND c.current_matchday=c.total_matchdays))"
					+ " ORDER BY m.start_at ASC, m.kickoff ASC", nativeQuery = true)
	List<Matchup> findLatestMatchupCalendar(Integer competitionId) throws DataAccessException;
	
	@Override
	List<Matchup> findByCompetitionIdAndStatus_NameInOrderByMatchdayAsc(Integer competitionId, Collection<String> statuses) throws DataAccessException;
	
	@Override
	Matchup findById(Integer id) throws DataAccessException;
	
	@Override
	List<Matchup> findByCompetitionIdAndStartAtBetweenOrderByStartAtAsc(Integer competitionId, LocalDate from, LocalDate to) throws DataAccessException;
	
	@Override
	@Query(value = "(SELECT * from matchup where featured and start_at >= CURDATE() and status = 1 and competition_id = 9 order by start_at asc,kickoff asc  limit 0, 4) "
			+ "union "
			+ "(SELECT * from matchup where featured and start_at >= CURDATE() and status = 1  and competition_id = 72 order by start_at asc,kickoff asc limit 0, 4) "
			+ "union   "
			+ "(SELECT * from matchup where featured and start_at >= CURDATE() and status = 1 and competition_id = 73 order by start_at asc,kickoff asc limit 0, 4) "
			+ "union   "
			+ "(SELECT * from matchup where featured and start_at >= CURDATE() and status = 1  and competition_id = 74 order by start_at asc,kickoff asc limit 0, 4) "
			+ "union   "
			+ "(SELECT * from matchup where featured and start_at <= CURDATE() and status = 1 and competition_id = 75 order by start_at asc,kickoff asc limit 0, 4)", nativeQuery = true)
	List<Matchup> findByFeaturedTrueOrderByCompetitionIdAscStartAtAscKickoffAsc() throws DataAccessException;

	/*
	 * Find results of matchday of the competition if:
	 * + the current_matchday has not started, get results for the previous matchday, or
	 * + if current_matchday has started but not yet finished, or
	 * + the first matchday
	 */
	@Override
	@Query(value = "SELECT * FROM matchup m inner join competition c on m.competition_id=c.id"
					+ " WHERE c.id = ?1 AND ((c.current_matchday = 1 AND m.matchday = 1)"
					+ "							OR (c.current_matchday > 1 AND c.matchday_started = 0 AND c.current_matchday - 1 = m.matchday)"
					+ "							OR (c.current_matchday > 1 AND c.matchday_started = 1 AND c.current_matchday = m.matchday))"
					+ " ORDER BY m.start_at ASC, m.kickoff ASC", nativeQuery = true)
	List<Matchup> findMatchupLatestResults(Integer competitionId) throws DataAccessException;
	
}
