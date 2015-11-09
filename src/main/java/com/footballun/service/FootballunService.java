package com.footballun.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.footballun.model.Matchup;
import com.footballun.model.Squad;
import com.footballun.model.SquadMember;

public interface FootballunService {

	/**
	 * Gets Squads
	 */
	List<Squad> findSquadByCompetitionAndGeneration(Integer competitionId, String generation) throws DataAccessException;
	
	/**
	 * Gets Matchups
	 */
	List<Matchup> findMatchupByMatchday(Integer matchday) throws DataAccessException;
	List<Matchup> findMatchupByRound(String round) throws DataAccessException;
	List<Matchup> findMatchupByFeatured(Boolean featured) throws DataAccessException;
	
	/**
	 * Gets Squad Members
	 */
	List<SquadMember> findSquadMembersBySquad(Integer squadId) throws DataAccessException;
}