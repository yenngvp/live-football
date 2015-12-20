package com.footballun.repository.springdatajpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.footballun.model.Squad;
import com.footballun.model.StandingBase;

@NoRepositoryBean	
public interface StandingBaseRepository<T extends StandingBase> extends CrudRepository<T, Integer> {

	@Query("select s from #{#entityName} as s where s.squad.competition.id = ?1 order by currentPosition asc")
	List<T> findBySquad_CompetitionIdOrderByCurrentPositionAsc(Integer id) throws DataAccessException;
	
	@Query("select s, MAX(s.matchday) from #{#entityName} as s where s.squad = ?1")
	T findBySquad(Squad squad) throws DataAccessException;

    @Query("select s, MAX(s.matchday) from #{#entityName} as s where s.squad.id = ?1")
    T findBySquad(Integer squadId) throws DataAccessException;

    @Query("select s from #{#entityName} as s where s.squad = ?1")
    List<T> findAllBySquad(Squad squad) throws DataAccessException;

    @Query("select s from #{#entityName} as s where s.squad.id = ?1")
    List<T> findAllBySquad(Integer squadId) throws DataAccessException;

}
