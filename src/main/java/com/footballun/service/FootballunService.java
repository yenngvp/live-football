package com.footballun.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.footballun.model.Matchup;
import com.footballun.model.MatchupDetail;
import com.footballun.model.Squad;
import com.footballun.model.SquadMember;
import com.footballun.model.Standing;

public interface FootballunService {

	/**
	 * Squad's APIs
	 */
	List<Squad> findSquadByCompetitionAndGeneration(Integer competitionId, String generation) throws DataAccessException;
	
	/**
	 * Matchup's APIs
	 */
	List<Matchup> findMatchupByMatchday(Integer matchday) throws DataAccessException;
	List<Matchup> findMatchupByRound(String round) throws DataAccessException;
	List<Matchup> findMatchupByFeatured(Boolean featured) throws DataAccessException;
	void saveMatchup(Matchup matchup) throws DataAccessException;
	
	/**
	 * Matchup Detail's APIs
	 */
	void saveMatchupDetail(MatchupDetail detail);
	
	/**
	 * Squad Member's APIs
	 */
	List<SquadMember> findSquadMembersBySquad(Integer squadId) throws DataAccessException;
	
	/**
	 * Standing's APIs
	 */
	List<Standing> findStandingByCompetition(Integer competitionId) throws DataAccessException;
	void saveStanding(Standing standing) throws DataAccessException;
	void refreshStanding(Integer competitionId) throws DataAccessException;
}
