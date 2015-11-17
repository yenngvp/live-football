package com.footballun.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.footballun.model.Event;
import com.footballun.model.Matchup;
import com.footballun.model.MatchupDetail;
import com.footballun.model.MatchupLive;
import com.footballun.model.MatchupRegister;
import com.footballun.model.MatchupStatus;
import com.footballun.model.Squad;
import com.footballun.model.SquadMember;
import com.footballun.model.Standing;
import com.footballun.model.StandingLive;

public interface FootballunService {

	/**
	 * Squad's APIs
	 */
	List<Squad> findSquadByCompetitionAndGeneration(Integer competitionId, String generation) throws DataAccessException;
	Squad findSquadById(Integer id) throws DataAccessException;
	Squad findSquadByName(String name, Integer competitionId) throws DataAccessException;
	
	/**
	 * Matchup's APIs
	 */
	List<Matchup> findMatchupByMatchday(Integer matchday) throws DataAccessException;
	List<Matchup> findMatchupByRound(String round) throws DataAccessException;
	List<Matchup> findMatchupByFeatured(Boolean featured) throws DataAccessException;
	List<Matchup> findMatchupByCompetitionId(Integer competitionId) throws DataAccessException;
	void saveMatchup(Matchup matchup) throws DataAccessException;
	Matchup findMatchupById(Integer id) throws DataAccessException;
	
	/**
	 * Matchup Detail's APIs
	 */
	void saveMatchupDetail(MatchupDetail detail) throws DataAccessException;
	
	/**
	 * Matchup Live's APIs
	 */
	// For testing purpose only
	void deleteAllMachupLives() throws DataAccessException; 
	List<MatchupLive> findAllMachupLives() throws DataAccessException; 
	void saveMatchupLive(MatchupLive event) throws DataAccessException;
	
	/**
	 * Matchup status service 
	 */
	MatchupStatus findMatchupStatusByName(String name) throws DataAccessException;
	
	
	/**
	 * Squad Member's APIs
	 */
	List<SquadMember> findSquadMembersBySquad(Integer squadId) throws DataAccessException;
	SquadMember findSquadMemberByLastNameAndSquad(String lastName, Integer squadId) throws DataAccessException;
	
	/**
	 * Standing's APIs
	 */
	List<Standing> findStandingByCompetition(Integer competitionId) throws DataAccessException;
	Standing findStandingBySquad(Squad squad) throws DataAccessException;
	void saveStanding(Standing standing) throws DataAccessException;
	void refreshStanding(boolean liveNow, Integer competitionId) throws DataAccessException;
	
	/**
	 * Standing Live's APIs
	 */
	List<StandingLive> findStandingLiveByCompetition(Integer competitionId) throws DataAccessException;
	StandingLive findStandingLiveBySquad(Squad squad) throws DataAccessException;
	void saveStandingLive(StandingLive standing) throws DataAccessException;
	
	/**
	 * Event's repo APIs
	 */
	Event findEventByName(String name) throws DataAccessException;
	

	/**
	 * Matchup Register's APIs
	 */
	void saveMatchupRegister(MatchupRegister register) throws DataAccessException;
}
