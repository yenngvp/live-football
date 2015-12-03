/**
 * @author: yen.nt
 * @created on Nov 5, 2015
 */
package com.footballun.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.footballun.model.SquadMember;

/**
 * @author yen.nt
 *
 */
public interface SquadMemberRepository {

	List<SquadMember> findBySquadId(Integer squadId)  throws DataAccessException;
	SquadMember findByHero_LastNameAndSquadId(String lastName, Integer squadId)  throws DataAccessException;
	SquadMember save(SquadMember squadMember) throws DataAccessException;
}
