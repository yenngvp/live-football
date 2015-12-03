/**
 * @author: yen.nt
 * @created on Dec 3, 2015
 */
package com.footballun.repository.springdatajpa;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.HeroRole;
import com.footballun.repository.HeroRoleRepository;

/**
 * @author yen.nt
 *
 */
public interface HeroRoleRepositoryJpa extends CrudRepository<HeroRole, Integer>,
		HeroRoleRepository {


	@Override
	HeroRole findOne(Integer Id) throws DataAccessException;
	@Override
	HeroRole findById(Integer id) throws DataAccessException;
	@Override
	HeroRole findByName(String name) throws DataAccessException;
	@SuppressWarnings("unchecked")
	@Override
	HeroRole save(HeroRole heroRole)throws DataAccessException;
}
