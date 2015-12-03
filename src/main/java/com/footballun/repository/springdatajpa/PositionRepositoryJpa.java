/**
 * @author: yen.nt
 * @created on Dec 3, 2015
 */
package com.footballun.repository.springdatajpa;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.Position;
import com.footballun.repository.PositionRepository;

/**
 * @author yen.nt
 *
 */
public interface PositionRepositoryJpa extends PositionRepository,
		CrudRepository<Position, Integer> {


	@Override
	Position findOne(Integer Id) throws DataAccessException;
	@Override
	Position findById(Integer id) throws DataAccessException;
	@Override
	Position findByName(String name) throws DataAccessException;
	@SuppressWarnings("unchecked")
	@Override
	Position save(Position position)throws DataAccessException;
	@Override
	Position findByPosition(String name) throws DataAccessException;
}
