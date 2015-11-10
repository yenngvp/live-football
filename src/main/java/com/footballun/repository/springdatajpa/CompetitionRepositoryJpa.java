package com.footballun.repository.springdatajpa;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.Competition;
import com.footballun.repository.CompetitionRepository;

public interface CompetitionRepositoryJpa extends CompetitionRepository, CrudRepository<Competition, Integer> {
	
	@Override
	Competition findById(Integer id) throws DataAccessException;
}
