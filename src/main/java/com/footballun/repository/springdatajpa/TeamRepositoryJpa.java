/**
 * @author: yen.nt
 * @created on Nov 30, 2015
 */
package com.footballun.repository.springdatajpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.Team;
import com.footballun.repository.TeamRepository;

/**
 * @author yen.nt
 *
 */
public interface TeamRepositoryJpa extends TeamRepository, CrudRepository<Team, Integer> {

	@Override
	Team findByName(String name) throws DataAccessException;

	@Override
	Team findById(Integer id) throws DataAccessException;

	@SuppressWarnings("unchecked")
	@Override
	Team save(Team team) throws DataAccessException;
}
