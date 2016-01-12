package com.footballun.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.footballun.model.Competition;
import com.footballun.model.Country;
import com.footballun.model.Event;
import com.footballun.model.Hero;
import com.footballun.model.HeroRole;
import com.footballun.model.HeroStatus;
import com.footballun.model.Matchup;
import com.footballun.model.MatchupDetail;
import com.footballun.model.MatchupLive;
import com.footballun.model.MatchupRegister;
import com.footballun.model.MatchupStatus;
import com.footballun.model.Position;
import com.footballun.model.ScrapedDataResult;
import com.footballun.model.Setting;
import com.footballun.model.Squad;
import com.footballun.model.SquadMember;
import com.footballun.model.Standing;
import com.footballun.model.Team;

public interface FootballunService {

	/**
	 * Squad services
	 */
	List<Squad> findSquadByCompetitionAndGeneration(Integer competitionId, String generation) throws DataAccessException;
	Squad findSquadById(Integer id) throws DataAccessException;
	Squad findSquadByName(String name, Integer competitionId) throws DataAccessException;
	Squad findSquadByAlias(String name, Integer competitionId) throws DataAccessException;
	Squad findSquadByFullName(String fullname, Integer competitionId) throws DataAccessException;
	Squad saveSquad(Squad squad) throws DataAccessException;
	
	/**
	 * Matchup services
	 */
	// Matchup's schedules
	List<Matchup> findMatchupByMatchdayAndCompetition(Integer matchday, Integer competitionId) throws DataAccessException;
	List<Matchup> findLatestMatchupCalendar(Integer competitionId) throws DataAccessException;
	void saveMatchup(Matchup matchup) throws DataAccessException;
	Matchup findMatchupById(Integer id) throws DataAccessException;
	List<Matchup> findMatchupByStartAtBetween(Integer competitionId, LocalDate from, LocalDate to) throws DataAccessException;
	List<Matchup> findMatchupFeaturedByMatchday() throws DataAccessException;
	
	// Matchup results
	List<Matchup> findMatchupLatestResults(Integer competitionId) throws DataAccessException;
	
	void onStartMatchup(Matchup matchup);
	void onFinishMatchup(Matchup matchup);
	void onUpdateMatchup(Matchup matchup);
	
	
	/**
	 * Matchup Detail services
	 */
	void saveMatchupDetail(MatchupDetail detail) throws DataAccessException;
	
	/**
	 * Matchup Live services
	 */
	// For testing purpose only
	void deleteAllMachupLives() throws DataAccessException; 
	List<MatchupLive> findAllMachupLives() throws DataAccessException; 
	void saveMatchupLive(MatchupLive event) throws DataAccessException;
	
	/**
	 * Matchup status service 
	 */
	MatchupStatus findMatchupStatusByName(String name) throws DataAccessException;
	MatchupStatus getMatchupStatusNotBegin() throws DataAccessException;
	MatchupStatus getMatchupStatusJustBegin() throws DataAccessException;
	MatchupStatus getMatchupStatusLive() throws DataAccessException;
	MatchupStatus getMatchupStatusJustFullTime() throws DataAccessException;
	MatchupStatus getMatchupStatusFullTime() throws DataAccessException;
	MatchupStatus getMatchupStatusCountdown() throws DataAccessException;
	MatchupStatus getMatchupStatusPostponed() throws DataAccessException;
	
	
	/**
	 * Squad Member services
	 */
	List<SquadMember> findSquadMembersBySquad(Integer squadId) throws DataAccessException;
	SquadMember findSquadMemberByLastNameAndSquad(String lastName, Integer squadId) throws DataAccessException;
	SquadMember saveSquadMember(SquadMember squadMember) throws DataAccessException;
	
	/**
	 * Standing services
	 */
	List<Standing> findCurrentStandingsByCompetition(Integer competitionId) throws DataAccessException;
	Standing findStandingBySquadAndMatchday(Squad squad, int matchday) throws DataAccessException;
    Standing findCurrentStandingBySquad(Integer squadId) throws DataAccessException;
    List<Standing> findAllStandingsBySquad(Squad squad) throws DataAccessException;
    List<Standing> findAllStandingsBySquad(Integer squadId) throws DataAccessException;
	void saveStanding(Standing standing) throws DataAccessException;
	List<Standing> refreshStanding(int competitionId, List<Standing> standings, int matchday) throws DataAccessException;
	Standing createStandingForSquad(Squad squad, int matchday);
	void createAllStandingsForSquad(Squad squad);
	void recalculateStandingForTheCompetition(int competitionId)  throws DataAccessException;
	List<Standing> findShortList() throws DataAccessException;
	void accumulateStandingForMatchup(Matchup matchup);
	
	/**
	 * Event services
	 */
	Event findEventByName(String name) throws DataAccessException;
	

	/**
	 * Matchup Register services
	 */
	void saveMatchupRegister(MatchupRegister register) throws DataAccessException;
	List<MatchupRegister> findMatchupRegisterByMatchupId(int matchupId) throws DataAccessException;
	
	/**
	 * Setting services
	 */
	Setting getSetting(int id) throws DataAccessException;
	void saveSetting(Setting setting) throws DataAccessException;
	
	/**
	 * Competition services
	 */
	Competition findCompetitionById(Integer id) throws DataAccessException;
	Competition findCompetitionByName(String name) throws DataAccessException;
	Competition save(Competition competition) throws DataAccessException;
	List<Competition> findAllCompetition(Integer yearFrom, Integer yearTo, String type) throws DataAccessException;
	List<Competition> findAllCompetition(LocalDate currentDate, String type) throws DataAccessException;
	
	/**
	 * Team services
	 */
	Team findTeamById(int id) throws DataAccessException;
	Team findTeamByName(String name) throws DataAccessException;
	Team save(Team team) throws DataAccessException;

	/**
	 * Country services
	 */
	Country findCountryByName(String name) throws DataAccessException;
	
	/**
	 * Hero services
	 */
	Hero findHeroByFullName(String name) throws DataAccessException;
	Hero findHeroByLastName(String name) throws DataAccessException;
	Hero saveHero(Hero hero) throws DataAccessException;
	
	/**
	 * Hero Status services
	 */
	HeroStatus findHeroStatusByName(String name) throws DataAccessException;
	
	/**
	 * Hero Role services
	 */
	HeroRole findHeroRoleByName(String name) throws DataAccessException;
	
	/**
	 * Position services
	 */
	Position findPositionByName(String name) throws DataAccessException;
	
	/**
	 * Scraped data services
	 */
	List<ScrapedDataResult> findAllScrapedDataResults() throws DataAccessException;
	List<ScrapedDataResult> findAllScrapedDataResultsJustUpdated() throws DataAccessException;
	void saveScrapedDataResult(ScrapedDataResult scrapedDataResult) throws DataAccessException;
}
