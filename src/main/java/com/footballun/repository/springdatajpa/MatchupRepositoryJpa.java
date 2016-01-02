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
	
	@SuppressWarnings("unchecked")
	@Override
	Matchup save(Matchup matchup) throws DataAccessException;
	
	@Override
	@Query(value = "SELECT m.* FROM matchup m INNER JOIN competition c ON m.competition_id=c.id  WHERE m.competition_id=?1 AND m.start_at >= CURDATE() AND (m.matchday=c.current_matchday OR m.matchday=c.current_matchday+1) ORDER BY m.matchday ASC, m.start_at ASC, m.kickoff ASC", nativeQuery = true)
	List<Matchup> findByCompetitionIdOrderByStartAtAsc(Integer competitionId) throws DataAccessException;
	
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
	
	@Override
	@Query(value = "SELECT m.* FROM matchup m INNER JOIN competition c ON m.competition_id=c.id AND m.matchday=c.current_matchday + 1 WHERE m.start_at >= CURDATE() ORDER BY m.competition_id ASC, m.start_at ASC, m.kickoff ASC", nativeQuery = true)
	List<Matchup> findAllByMatchday() throws DataAccessException;
	
	@Override	@Query(value = "SELECT m.* FROM matchup m INNER JOIN competition c ON m.competition_id=c.id AND m.matchday=c.current_matchday + 1 WHERE m.start_at >= CURDATE() AND matchday=?0 ORDER BY m.competition_id ASC, m.start_at ASC, m.kickoff ASC", nativeQuery = true)

	List<Matchup> findByMatchday(Integer matchday) throws DataAccessException;
	
	@Override
	@Query(value = "SELECT * FROM matchup WHERE competition_id = ?1 AND start_at = CURDATE() ORDER BY matchday DESC LIMIT 1", nativeQuery = true)
	Matchup findOneByTodayAndCompetitionId(Integer competitionId) throws DataAccessException;
	
	/*
	 * Matchup results
	 */
	@Override
	@Query(value = "(SELECT * from matchup where start_at <= CURDATE() and status = 6 and competition_id = 9 order by start_at desc,kickoff desc  limit 0, 10) "
			+ "union "  
			+ "(SELECT * from matchup where start_at <= CURDATE() and status = 6  and competition_id = 72 order by start_at desc,kickoff desc limit 0, 10) "  
			+ "union   "  
			+ "(SELECT * from matchup where start_at <= CURDATE() and status = 6 and competition_id = 73 order by start_at desc,kickoff desc limit 0, 10) "  
			+ "union   "  
			+ "(SELECT * from matchup where start_at <= CURDATE() and status = 6  and competition_id = 74 order by start_at desc,kickoff desc limit 0, 10) "  
			+ "union   "  
			+ "(SELECT * from matchup where start_at <= CURDATE() and status = 6 and competition_id = 75 order by start_at desc,kickoff desc limit 0, 10)", nativeQuery = true)
	List<Matchup> findNearestTenResults() throws DataAccessException;
	
	@Override
	List<Matchup> findTop10ByCompetitionIdAndStatus_NameInOrderByStartAtDescKickoffDesc(Integer competitionId, Collection<String> statuses) throws DataAccessException;
	
	@Override
	List<Matchup> findByCompetitionIdAndMatchdayAndStatus_NameInOrderByStartAtDescKickoffDesc(Integer competitionId, Integer matchday, Collection<String> statuses) throws DataAccessException;
	
	@Query(value= "select * from (SELECT CONCAT(s.name, ' vs ', s2.name) as matchname, d.result, d.status, d.matchday, s.id squad_id FROM scraped_data_result d inner join squad s on d.team1=s.alias inner join squad s2 on d.team2=s2.alias and d.competition='?1') as s inner join matchup m on s.matchname=m.name"
			, nativeQuery = true)
	List<Matchup> resultExtServiceUpdate(Integer competitionId);
}
