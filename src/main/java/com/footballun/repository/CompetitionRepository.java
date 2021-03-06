package com.footballun.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.footballun.model.Competition;

public interface CompetitionRepository {

	Competition findById(Integer id) throws DataAccessException;
	Competition findByName(String name) throws DataAccessException;
	Competition save(Competition competition) throws DataAccessException;
	
	List<Competition> findByYearFromAndYearToAndType(Integer yearFrom, Integer yearTo, String type) throws DataAccessException;
	List<Competition> findByStartAtLessThanEqualAndEndAtGreaterThanEqualAndType(LocalDate now, LocalDate now1, String type) throws DataAccessException;
}
