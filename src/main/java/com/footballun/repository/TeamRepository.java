package com.footballun.repository;

import org.springframework.dao.DataAccessException;

import com.footballun.model.BaseEntity;
import com.footballun.model.Team;


public interface TeamRepository {
	/**
     * Retrieve <code>Team</code> by name from the data store.
     *
     * @return a <code>Team</code>
     */
    Team findByName(String name) throws DataAccessException;

    /**
     * Retrieve a <code>Team</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>Team</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException
     *          if not found
     */
    Team findById(Integer id) throws DataAccessException;

    /**
     * Save a <code>Team</code> to the data store, either inserting or updating it.
     *
     * @param food the <code>Team</code> to save
     * @see BaseEntity#isNew
     */
    Team save(Team team) throws DataAccessException;
}
