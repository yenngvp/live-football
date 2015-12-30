/**
 * @author: yen.nt
 * @created on Dec 30, 2015
 */
package com.footballun.repository.springdatajpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.ScrapedDataResult;
import com.footballun.repository.ScrapedDataResultRepository;

/**
 * @author yen.nt
 *
 */
public interface ScrapedDataResultRepositoryJpa extends
		ScrapedDataResultRepository, CrudRepository<ScrapedDataResult, Integer> {

	@Override
	List<ScrapedDataResult> findAll() throws DataAccessException;
	
	@Override
	List<ScrapedDataResult> findByJustUpdateTrue() throws DataAccessException;
	
	@SuppressWarnings("unchecked")
	@Override
	ScrapedDataResult save(ScrapedDataResult scrapedDataResult) throws DataAccessException;
}
