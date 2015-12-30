/**
 * @author: yen.nt
 * @created on Dec 30, 2015
 */
package com.footballun.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.footballun.model.ScrapedDataResult;

/**
 * @author yen.nt
 *
 */
public interface ScrapedDataResultRepository {
	
	List<ScrapedDataResult> findAll() throws DataAccessException;
	List<ScrapedDataResult> findByJustUpdateTrue() throws DataAccessException;
	ScrapedDataResult save(ScrapedDataResult scrapedDataResult) throws DataAccessException;
}
