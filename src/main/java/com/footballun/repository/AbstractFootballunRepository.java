/**
 * @author: yen.nt
 * @created on Nov 18, 2015
 */
package com.footballun.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * @author yen.nt
 *
 */
public interface AbstractFootballunRepository<T> {
	
	T findOne(Integer Id) throws DataAccessException;
	T findById(Integer id) throws DataAccessException;
	T findByName(String name) throws DataAccessException;
	List<T> findAll() throws DataAccessException;
	<S extends T> S save(S entity)throws DataAccessException;
}
