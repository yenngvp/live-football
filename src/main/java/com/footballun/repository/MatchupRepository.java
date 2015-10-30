package com.footballun.repository;

import java.util.List;

import com.footballun.model.Matchup;

public interface MatchupRepository {

	List<Matchup> findByMatchday(Integer matchday);
	
	List<Matchup> findByRound(String round);
}
