/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 22, 2015
 */
package com.footballun.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.footballun.model.Squad;
import com.footballun.model.Competition;

/**
 * @author yen.nt
 *
 */
public interface SquadRepository {

    /**
     * Retrieve <code>all Squads</code> from the data store.
     *
     * @return a list of all<code>Squads</code>
     */
    List<Squad> findByCompetitionAndGeneration(Competition competition, String generation) throws DataAccessException;
    
    /**
     * Retrieve <code>all Squad</code> from the data store.
     *
     * @return a list of all<code>Squads</code>
     */
    List<Squad> findByCompetitionIdAndGeneration(Integer competitionId, String generation) throws DataAccessException;
    
    /**
     * Retrieve a <code>Squad</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>Squad</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException
     *          if not found
     */
    Squad findById(Integer id) throws DataAccessException;
    Squad findByNameAndCompetitionId(String name, Integer competitionId) throws DataAccessException;
    Squad findByFullNameAndCompetitionId(String fullname, Integer competitionId) throws DataAccessException;
    Squad save(Squad squad) throws DataAccessException;
}
