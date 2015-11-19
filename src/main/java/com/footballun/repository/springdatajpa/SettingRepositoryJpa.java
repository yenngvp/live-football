/**
 * @author: yen.nt
 * @created on Nov 18, 2015
 */
package com.footballun.repository.springdatajpa;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.Setting;
import com.footballun.repository.SettingRepository;

/**
 * @author yen.nt
 *
 */
public interface SettingRepositoryJpa extends SettingRepository,
		CrudRepository<Setting, Integer> {

	@Override
	Setting findOne(Integer id) throws DataAccessException;
	
	@SuppressWarnings("unchecked")
	@Override
	Setting save(Setting setting) throws DataAccessException;
}
