/**
 * @author: yen.nt
 * @created on Dec 3, 2015
 */
package com.footballun.repository.springdatajpa;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.HeroStatus;
import com.footballun.repository.HeroStatusRepository;

/**
 * @author yen.nt
 *
 */
public interface HeroStatusRepositoryJpa extends HeroStatusRepository,
		CrudRepository<HeroStatus, Integer> {

	@Override
	HeroStatus findOne(Integer Id) throws DataAccessException;
	@Override
	HeroStatus findById(Integer id) throws DataAccessException;
	@Override
	HeroStatus findByName(String name) throws DataAccessException;
	@SuppressWarnings("unchecked")
	@Override
	HeroStatus save(HeroStatus heroStatus)throws DataAccessException;
}
