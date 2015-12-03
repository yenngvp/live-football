/**
 * @author: yen.nt
 * @created on Dec 3, 2015
 */
package com.footballun.repository;

import org.springframework.dao.DataAccessException;

import com.footballun.model.Position;

/**
 * @author yen.nt
 *
 */
public interface PositionRepository extends AbstractFootballunRepository<Position> {

	Position findByPosition(String name) throws DataAccessException;
}
