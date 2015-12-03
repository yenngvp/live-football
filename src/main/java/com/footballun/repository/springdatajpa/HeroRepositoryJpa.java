/**
 * @author: yen.nt
 * @created on Nov 12, 2015
 */
package com.footballun.repository.springdatajpa;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.Hero;
import com.footballun.repository.HeroRepository;

/**
 * @author yen.nt
 *
 */
public interface HeroRepositoryJpa extends HeroRepository,
		CrudRepository<Hero, Integer> {

	@Override
	Hero findById(Integer id) throws DataAccessException;
	@Override
	Hero findByName(String name) throws DataAccessException;
	@Override
	Hero findByLastName(String name) throws DataAccessException;
	@SuppressWarnings("unchecked")
	@Override
	Hero save(Hero hero) throws DataAccessException;
}
