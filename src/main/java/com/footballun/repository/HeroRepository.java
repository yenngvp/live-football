/**
 * @author: yen.nt
 * @created on Nov 12, 2015
 */
package com.footballun.repository;

import org.springframework.dao.DataAccessException;

import com.footballun.model.Hero;

/**
 * @author yen.nt
 *
 */
public interface HeroRepository {

	Hero findById(Integer id) throws DataAccessException;
	Hero findByLastName(String name) throws DataAccessException;
}
