/**
 * @author: yen.nt
 * @created on Nov 5, 2015
 */
package com.footballun.repository.springdatajpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.SquadMember;
import com.footballun.repository.SquadMemberRepository;

/**
 * @author yen.nt
 *
 */
public interface SquadMemberRepositoryJpa extends SquadMemberRepository, CrudRepository<SquadMember, Integer> {
	
	@Override
	List<SquadMember> findBySquadId(Integer squadId) throws DataAccessException;
	
	@Override
	SquadMember findByHero_LastNameAndSquadId(String lastName, Integer squadId) throws DataAccessException;
}
