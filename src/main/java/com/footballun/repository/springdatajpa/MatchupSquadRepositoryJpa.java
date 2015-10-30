/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 28, 2015
 */
package com.footballun.repository.springdatajpa;


import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.footballun.model.MatchupSquad;
import com.footballun.model.MatchupSquadId;
import com.footballun.repository.MatchupSquadRepository;

/**
 * @author yen.nt
 *
 */
public interface MatchupSquadRepositoryJpa extends MatchupSquadRepository, Repository<MatchupSquad, Serializable> {

	@Override
	@Transactional
	List<MatchupSquad> findByPk(MatchupSquadId pk);
}
