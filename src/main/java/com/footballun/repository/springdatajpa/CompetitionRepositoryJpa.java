package com.footballun.repository.springdatajpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.Competition;
import com.footballun.repository.CompetitionRepository;

public interface CompetitionRepositoryJpa extends CompetitionRepository, CrudRepository<Competition, Integer> {
	
	@Override
	Competition findById(Integer id) throws DataAccessException;
	@Override
	Competition findByName(String name) throws DataAccessException;
	
	@SuppressWarnings("unchecked")
	@Override
	Competition save(Competition competition) throws DataAccessException;
	
	@Override
	List<Competition> findByYearFromAndYearToAndType(Integer yearFrom, Integer yearTo, String type) throws DataAccessException;
	
	@Override
	List<Competition> findByStartAtLessThanEqualAndEndAtGreaterThanEqualAndType(LocalDate now, LocalDate now1, String type) throws DataAccessException;
}
