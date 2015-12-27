package com.footballun.repository.springdatajpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.footballun.model.StandingBase;

@NoRepositoryBean	
public interface StandingBaseRepository<T extends StandingBase> extends CrudRepository<T, Integer> {

	@Query("select s from #{#entityName} as s where s.squad.competition.id = ?1 and s.matchday = ?2 order by currentPosition asc")
	List<T> findBySquad_CompetitionIdAndMatchdayOrderByCurrentPositionAsc(Integer id, Integer matchday) throws DataAccessException;
	
	@Query("select s from #{#entityName} as s where s.squad.competition.id = ?1 order by matchday asc, currentPosition asc")
	List<T> findBySquad_CompetitionIdOrderByMatchdayAscCurrentPositionAsc(Integer id) throws DataAccessException;

	@Query("select s, MAX(s.played) from #{#entityName} as s where s.squad.competition.id = ?1 group by s.squad order by currentPosition asc")
	List<T> findBySquad_CompetitionIdWithMaxMatchdayOrderByCurrentPositionAsc(Integer id) throws DataAccessException;

    @Query("select s, MAX(s.played) from #{#entityName} as s where s.squad.id = ?1")
    T findBySquadWithLatestMatchdayOrderByCurrentPositionAsc(Integer squadId) throws DataAccessException;

	@Query("select s from #{#entityName} as s where s.squad.id = ?1 and s.matchday = ?2")
	T findBySquadAndMatchdayOrderByCurrentPositionAsc(Integer squadId, Integer matchday) throws DataAccessException;
	
    @Query("select s from #{#entityName} as s where s.squad.id = ?1 and s.played > 0 order by matchday asc")
    List<T> findAllBySquadOrderByMatchdayAscCurrentPositionAsc(Integer squadId) throws DataAccessException;

}
