/**
 * @author: yen.nt
 * @created on Nov 5, 2015
 */
package com.footballun.repository;

import java.util.List;

import com.footballun.model.SquadMember;

/**
 * @author yen.nt
 *
 */
public interface SquadMemberRepository {

	List<SquadMember> findBySquadId(Integer squadId);
}
