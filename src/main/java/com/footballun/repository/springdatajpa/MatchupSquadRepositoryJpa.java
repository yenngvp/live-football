/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 28, 2015
 */
package com.footballun.repository.springdatajpa;


import java.io.Serializable;

import org.springframework.data.repository.Repository;

import com.footballun.model.MatchupSquad;
import com.footballun.repository.MatchupSquadRepository;

/**
 * @author yen.nt
 *
 */
public interface MatchupSquadRepositoryJpa extends MatchupSquadRepository, Repository<MatchupSquad, Serializable> {

}
