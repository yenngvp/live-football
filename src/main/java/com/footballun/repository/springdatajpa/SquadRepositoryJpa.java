package com.footballun.repository.springdatajpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.Competition;
import com.footballun.model.Squad;
import com.footballun.repository.SquadRepository;

public interface SquadRepositoryJpa extends SquadRepository, CrudRepository<Squad, String> {

	@Override
	List<Squad> findByCompetitionAndGeneration(Competition competition, String generation) throws DataAccessException;
	
	@Override
	List<Squad> findByCompetitionIdAndGeneration(Integer competitionId, String generation) throws DataAccessException;
}
