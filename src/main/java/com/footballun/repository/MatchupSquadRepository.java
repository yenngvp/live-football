package com.footballun.repository;

import java.util.List;

import com.footballun.model.MatchupSquad;
import com.footballun.model.MatchupSquadId;

/**
 * @author yen.nt
 *
 */
public interface MatchupSquadRepository {
	
	List<MatchupSquad> findByPk(MatchupSquadId pk);
}
