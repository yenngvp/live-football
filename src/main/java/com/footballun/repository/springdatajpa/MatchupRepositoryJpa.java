package com.footballun.repository.springdatajpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.footballun.model.Matchup;
import com.footballun.repository.MatchupRepository;

public interface MatchupRepositoryJpa extends MatchupRepository, Repository<Matchup, Serializable> {

	@Override
	List<Matchup> findByMatchday(Integer matchday);
	
	@Override
	List<Matchup> findByRound(String round);
}
