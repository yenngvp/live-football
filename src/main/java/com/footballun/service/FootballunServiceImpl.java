package com.footballun.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.footballun.model.Competition;
import com.footballun.model.Country;
import com.footballun.model.Event;
import com.footballun.model.Hero;
import com.footballun.model.HeroRole;
import com.footballun.model.HeroStatus;
import com.footballun.model.Matchup;
import com.footballun.model.Matchup.MatchupResult;
import com.footballun.model.MatchupDetail;
import com.footballun.model.MatchupLive;
import com.footballun.model.MatchupRegister;
import com.footballun.model.MatchupStatus;
import com.footballun.model.MatchupStatus.MatchupStatusCode;
import com.footballun.model.NamedEntity;
import com.footballun.model.Position;
import com.footballun.model.Setting;
import com.footballun.model.Squad;
import com.footballun.model.SquadMember;
import com.footballun.model.Standing;
import com.footballun.model.StandingBase;
import com.footballun.model.StandingLive;
import com.footballun.model.Team;
import com.footballun.repository.AbstractFootballunRepository;
import com.footballun.repository.CompetitionRepository;
import com.footballun.repository.CountryRepository;
import com.footballun.repository.EventRepository;
import com.footballun.repository.HeroRepository;
import com.footballun.repository.HeroRoleRepository;
import com.footballun.repository.HeroStatusRepository;
import com.footballun.repository.MatchupDetailRepository;
import com.footballun.repository.MatchupLiveRepository;
import com.footballun.repository.MatchupRegisterRepository;
import com.footballun.repository.MatchupRepository;
import com.footballun.repository.MatchupStatusRepository;
import com.footballun.repository.PositionRepository;
import com.footballun.repository.SettingRepository;
import com.footballun.repository.SquadMemberRepository;
import com.footballun.repository.SquadRepository;
import com.footballun.repository.TeamRepository;
import com.footballun.repository.springdatajpa.StandingLiveRepository;
import com.footballun.repository.springdatajpa.StandingRepository;
import com.footballun.util.PositionComparator;

@Service
@Transactional
public class FootballunServiceImpl implements FootballunService {

	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private SquadRepository squadRepository;
	@Autowired
	private CompetitionRepository competitionRepository;
	@Autowired
	private MatchupRepository matchupRepository;
	@Autowired
	private SquadMemberRepository squadMemberRepository;
	@Autowired
	private StandingRepository standingRepository;
	@Autowired
	private StandingLiveRepository standingLiveRepository;
	@Autowired
	private MatchupDetailRepository matchupDetailRepository;
	@Autowired
	private MatchupLiveRepository matchupLiveRepository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private MatchupRegisterRepository matchupRegisterRepository;
	@Autowired
	private MatchupStatusRepository matchupStatusRepository;
	@Autowired
	private SettingRepository settingRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private HeroRepository heroRepository;
	@Autowired
	private HeroRoleRepository heroRoleRepository;
	@Autowired
	private HeroStatusRepository heroStatusRepository;
	@Autowired
	private PositionRepository positionRepository;
	
	private MatchupStatus statusCountdown;
	private MatchupStatus statusJustBegin;
	private MatchupStatus statusNotBegin;
	private MatchupStatus statusLive;
	private MatchupStatus statusJustFullTime;
	private MatchupStatus statusFullTime;
	private MatchupStatus statusPostponed;
	
	final Logger logger = LoggerFactory.getLogger("FootballunService");

	/**
	 * Squad services 
	 */
	
	@Override
	@Transactional(readOnly = true)
	public List<Squad> findSquadByCompetitionAndGeneration(Integer competitionId, String generation)
			throws DataAccessException {
		return squadRepository.findByCompetitionIdAndGeneration(competitionId, generation);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Squad findSquadById(Integer id) throws DataAccessException {
		return squadRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Squad findSquadByName(String name, Integer competitionId) throws DataAccessException {
		return squadRepository.findByNameAndCompetitionId(name, competitionId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Squad findSquadByFullName(String fullname, Integer competitionId) throws DataAccessException {
		return squadRepository.findByFullNameAndCompetitionId(fullname, competitionId);
	}
	
	@Override
	public Squad saveSquad(Squad squad) throws DataAccessException {
		return squadRepository.save(squad);
	}
	
	/**
	 * Matchup's APIs implements
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Matchup> findMatchupByMatchdayAndCompetition(Integer matchday, Integer competitionId) throws DataAccessException {
		
		return  matchupRepository.findByMatchdayAndCompetitionIdOrderByStartAtAsc(matchday, competitionId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Matchup> findMatchupByRound(String round, Integer competitionId) throws DataAccessException {
		
		return  null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Matchup> findMatchupByFeatured(Boolean featured) throws DataAccessException {
		return  matchupRepository.findByFeaturedOrderByStartAtAsc(featured);
	}
	
	@Override
	public void saveMatchup(Matchup matchup) throws DataAccessException {
		matchupRepository.save(matchup);
	}
	
	@Override
	public List<Matchup> findMatchupByCompetitionId(Integer competitionId) throws DataAccessException {
		return matchupRepository.findByCompetitionIdOrderByStartAtAsc(competitionId);
	}
	
	@Override
	public Matchup findMatchupById(Integer id) throws DataAccessException {
		return matchupRepository.findById(id);
	}
	
	@Override
	public List<Matchup> findMatchupByStartAtBetween(Integer competitionId, LocalDate from, LocalDate to) throws DataAccessException {
		return matchupRepository.findByCompetitionIdAndStartAtBetweenOrderByStartAtAsc(competitionId, from, to);
	}
	
	
	@Override
	public List<Matchup> findMatchupFeaturedByMatchday() throws DataAccessException {
		return matchupRepository.findByFeaturedMatchday();
	}
	
	@Override
	public List<Matchup> findAllMatchupMatchday() throws DataAccessException {
		return matchupRepository.findAllByMatchday();
	}
	
	@Override
	public List<Matchup> findMatchupByMatchday(Integer matchday) throws DataAccessException {
		return matchupRepository.findByMatchday(matchday);
	}
	
	/**
	 * Matchup Detail's APIs
	 */
	@Override
	public void saveMatchupDetail(MatchupDetail detail) throws DataAccessException {
		matchupDetailRepository.save(detail);
	}
	
	/**
	 * Matchup Live's APIs
	 */
	// For testing purpose only
	@Override
	public void deleteAllMachupLives() throws DataAccessException {
		matchupLiveRepository.deleteAll();
	}
	
	@Override
	@Transactional
	public void saveMatchupLive(MatchupLive event) throws DataAccessException {
		matchupLiveRepository.save(event);
	}
	
	@Override
	public List<MatchupLive> findAllMachupLives() throws DataAccessException {
		return matchupLiveRepository.findAll();
	}
	
	/**
	 * Squad Member's APIs implement
	 */
	
	@Override
	@Transactional(readOnly = true)
	public List<SquadMember> findSquadMembersBySquad(Integer squadId) throws DataAccessException {
		return squadMemberRepository.findBySquadId(squadId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public SquadMember findSquadMemberByLastNameAndSquad(String lastName, Integer squadId) throws DataAccessException {
		return squadMemberRepository.findByHero_LastNameAndSquadId(lastName, squadId);
	}
	
	@Override
	public SquadMember saveSquadMember(SquadMember squadMember) throws DataAccessException {
		return squadMemberRepository.save(squadMember);
	}
	
	/**
	 * Standing's APIs
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Standing> findStandingByCompetition(Integer competitionId) throws DataAccessException {
		
		return standingRepository.findBySquad_CompetitionIdOrderByCurrentPositionAsc(competitionId);
	}

	@Override
	@Transactional(readOnly = true)
	public Standing findStandingBySquad(Squad squad) throws DataAccessException {
		return standingRepository.findBySquad(squad);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void saveStanding(Standing standing) throws DataAccessException {
		standingRepository.save(standing);
	}
	
	/**
	 * One start matchup, copy current standing to standing live 
	 * 
	 * @param matchup
	 */
	@Override
	@Transactional
	public void onStartMatchup(Matchup matchup) {
		// testing only
		createStandingForCompetition(matchup.getCompetition().getId());
		
		// Checks status
		if (matchup.getStatus().getCode() != MatchupStatusCode.JUST_BEGIN) {
			return;
		}
		
		Squad[] squads = {matchup.getFirstDetail().getSquad(), matchup.getSecondDetail().getSquad()};
		for (Squad squad : squads) {
			copyStandingToStandingLive(squad);
		}
		
		// Update matchup's status
		matchup.setStatus(getMatchupStatusLive());
		matchupRepository.save(matchup);
	}
	
	/**
	 * Refreshes the standing for the squad and overall
	 * @param matchup
	 */
	@Override
	@Transactional
	public void onFinishMatchup(Matchup matchup) {
		// Checks status
		if (matchup.getStatus().getCode() != MatchupStatusCode.JUST_FULL_TIME) {
			return;
		}
		
		// Update matchup result
		onUpdateMatchup(matchup);
		
		Squad[] squads = {matchup.getFirstDetail().getSquad(), matchup.getSecondDetail().getSquad()};
		for (Squad squad : squads) {
			copyStandingToStandingLive(squad);
		}
		
		matchup.setStatus(getMatchupStatusFullTime());
		matchupRepository.save(matchup);
	}
	
	@Override
	@Transactional
	public void onUpdateMatchup(Matchup matchup) {
		// Checks status (only accepts LIVE or JUST_FULL_TIME)
		if (matchup.getStatus().getCode() != MatchupStatusCode.LIVE
				&& matchup.getStatus().getCode() != MatchupStatusCode.JUST_FULL_TIME) {
			return;
		}
		
		accumulateStandingForMatchup(matchup, false);
		refreshStanding(matchup.getCompetition().getId(), null);
	}
	
	private void copyStandingToStandingLive(Squad squad) {
		Standing standing = standingRepository.findBySquad(squad);
		if (standing != null && standing.getStandingLive() != null) {
			// Syncs
			Integer id = standing.getStandingLive().getId(); // don't want to copy its ID, keeps a backup
			BeanUtils.copyProperties(standing, standing.getStandingLive(), StandingBase.class);
			standing.getStandingLive().setId(id);
			standingLiveRepository.save(standing.getStandingLive());
		}
	}
	
	@Override
	@Transactional
	public List<Standing> refreshStanding(int competitionId, List<Standing> standings) throws DataAccessException {
		
		// Gets all current squads standing
		if (standings == null) {
			standings = standingRepository.findBySquad_CompetitionIdOrderByCurrentPositionAsc(competitionId);
		}
		sortAndSaveStandings(competitionId, standings);
		
		return standings;
	}
	
	/**
	 * Creates table for all squads off the competition (do only once)
	 * @return
	 */
	public List<Standing> createStandingForCompetition(int competitionId) {
		
		List<Standing> standings = new ArrayList<Standing>();
		
		List<Squad> squads = squadRepository.findByCompetitionIdAndGeneration(competitionId, "First Team");
		for (Squad squad : squads) {
			createStandingForSquad(squad);
		}
				
		return standings;
	}

	/**
	 * Creates table for a new squad off the competition (do only once)
	 * @return
	 */
	@Override
	public Standing createStandingForSquad(Squad squad) {
		
		// Creates main standing table
		Standing standing = standingRepository.findBySquad(squad);
		if (standing == null) {
			standing = new Standing();
			standing.setSquad(squad);
			standingRepository.save(standing);
		}
		
		// Also creates live standing table
		StandingLive standingLive = standingLiveRepository.findBySquad(squad);
		if (standingLive == null) {
			standingLive = new StandingLive();
			standingLive.setStanding(standing);
			standingLive.setSquad(squad);
			standing.setStandingLive(standingLive);
			standingLiveRepository.save(standingLive);
		}
		
		return standing;
	}
	
	private void accumulateStandingForMatchup(Matchup matchup, boolean isManual) {
		
		/*
		 * Accumulate standing table = achievement from previous matches (saved on standingLive) + livescore
		 */
		Squad[] squads = {matchup.getFirstDetail().getSquad(), matchup.getSecondDetail().getSquad()};
		for (Squad squad : squads) {
			
			Standing standing = standingRepository.findBySquad(squad);
			StandingBase based;
			if (!isManual) {
				standing.resetAchievement();
				based = standing.getStandingLive();
			} else {
				based = standing;
			}
			
			MatchupResult result = matchup.getResultBySquad(squad);
			if (result != MatchupResult.UNKNOWN) {
				// Counts played match
				standing.setPlayed(based.getPlayed() + 1);
				if (result == MatchupResult.WIN) {
					// Counts WON match
					standing.setWon(based.getWon() + 1);
					standing.setPoint(based.getPoint() + 3); // 3 points for a win game
				} else if (result == MatchupResult.DRAW) {
					// Counts DRAWN match
					standing.setDrawn(based.getDrawn() + 1);
					standing.setPoint(based.getPoint() + 1); // 1 point for a draw game
				} else {
					// Counts LOST match
					standing.setLost(based.getLost() + 1);
				}
				
				standing.setGoalsScored(based.getGoalsScored() + matchup.getGoalsScoredBySquad(squad));
				standing.setGoalsAgainst(based.getGoalsAgainst() + matchup.getGoalsAgainstBySquad(squad));
			}
			
			standingRepository.save(standing);
		}
	}
	

	private void sortAndSaveStandings(Integer competitionId, List<Standing> standings) {
		/**
		 * Now we need to sort the table against all squads achievement
		 */
											  
		// Sorts positions
		Collections.sort(standings, new PositionComparator());
		
		// Update current position number we already get an ordered list of standings
		int currentPosition = 1;
		
		// But still needs further check for squads stand the same position due to all rules are identical
		Standing prev = null;
		for (Standing standing : standings) {
			if (prev != null) {
				if (PositionComparator.doCompare(standing, prev) != 0) {
					// Increases position only if two squads are not identical results
					currentPosition++;
				}
			}
			
			standing.setPreviousPosition(standing.getCurrentPosition());
			standing.setCurrentPosition(currentPosition);
			
			// Persists the standing
			standingRepository.save(standing);
			prev = standing;
		}
	}

	/**
	 * 
	 */
	@Override
	@Transactional
	public void recalculateStandingForTheCompetition(int competitionId) {
		
		if (competitionId < 0) competitionId = getSetting(1).getCompetition().getId();

		/*
		 * Rejects to reset the standing if there is still have matchups in LIVE mode.
		 */
		//findByCompetitionIdAndStatus_NameIn
		Collection<String> gettingStatuses = new ArrayList<String>();
		gettingStatuses.add(MatchupStatus.getNameByCode(MatchupStatusCode.LIVE));
		gettingStatuses.add(MatchupStatus.getNameByCode(MatchupStatusCode.FULL_TIME));
		
		List<Matchup> matchups = matchupRepository.findByCompetitionIdAndStatus_NameIn(competitionId, gettingStatuses);
		
		for (Matchup matchup : matchups) {
			if (matchup.getStatus().getCode() == MatchupStatusCode.LIVE) {
				logger.warn("Attemping to recalculate standing but match " + matchup.getName() + " still in LIVE status. Request rejected!");
				return;
			}
		}
		
		// Reset all standings
		List<Standing> standings = standingRepository.findBySquad_CompetitionIdOrderByCurrentPositionAsc(competitionId);
		if (standings == null || standings.size() == 0) {
			createStandingForCompetition(competitionId);
		} else {
			for (Standing standing : standings) {
				// backup standing to standing temp table
				Integer id = standing.getStandingLive().getId(); // don't want to copy its ID, keeps a backup
				BeanUtils.copyProperties(standing, standing.getStandingLive(), StandingBase.class);
				standing.getStandingLive().setId(id);
				standingLiveRepository.save(standing.getStandingLive());
				
				standing.resetAll();
				standingRepository.save(standing);
			}
		}
		
		for (Matchup matchup : matchups) {
			accumulateStandingForMatchup(matchup, true);
		}
		
		standings = refreshStanding(competitionId, null);
		
		// Due to the recalculation lost all squad's previous positions so bring them back from back up
		for (Standing standing : standings) {
			standing.setPreviousPosition(standing.getStandingLive().getPreviousPosition());
		}
	}
	
	/**
	 * Standing Live's APIs
	 */
	@Override
	public List<StandingLive> findStandingLiveByCompetition(Integer competitionId) throws DataAccessException {
		return standingLiveRepository.findBySquad_CompetitionIdOrderByCurrentPositionAsc(competitionId);
	}
	
	@Override
	public StandingLive findStandingLiveBySquad(Squad squad) throws DataAccessException {
		return standingLiveRepository.findBySquad(squad);
	}
	
	@Override
	public void saveStandingLive(StandingLive standing) throws DataAccessException {
		standingLiveRepository.save(standing);
	}
	

	/**
	 * Event's repo APIs
	 */
	@Override
	public Event findEventByName(String name) throws DataAccessException {
		return eventRepository.findByName(name);
	}
	

	/**
	 * Matchup Register's APIs
	 */
	@Override
	public void saveMatchupRegister(MatchupRegister register) throws DataAccessException {
		matchupRegisterRepository.save(register);
	}
	
	@Override
	public List<MatchupRegister> findMatchupRegisterByMatchupId(int matchupId) throws DataAccessException {
		return matchupRegisterRepository.findByMatchupIdOrderByMatchupDetailIdAsc(matchupId);
	}
	
	
	/**
	 * Matchup status service 
	 */
	@Override
	public MatchupStatus findMatchupStatusByName(String name) throws DataAccessException {
		return matchupStatusRepository.findByName(name);
	}

	
	/**
	 * Setting services
	 */
	@Override
	public Setting getSetting(int id) throws DataAccessException {

		return settingRepository.findOne(id);
	}
	
	@Override
	public void saveSetting(Setting setting) throws DataAccessException {
		settingRepository.save(setting);
	}
	
	/**
	 * Competition services
	 */
	@Override
	public Competition findCompetitionById(Integer id) throws DataAccessException {
		return competitionRepository.findById(id);
	}
	
	@Override
	public Competition findCompetitionByName(String name) throws DataAccessException {
		return competitionRepository.findByName(name);
	}
	
	@Override
	public Competition save(Competition competition) throws DataAccessException {
		return competitionRepository.save(competition);
	}

	@Override
	public MatchupStatus getMatchupStatusCountdown() throws DataAccessException {
		if (statusCountdown == null) {
			statusCountdown = matchupStatusRepository.findByName(MatchupStatus.getNameByCode(MatchupStatusCode.ENTER_COUNTDOWN));
		}
		return statusCountdown;
	}

	@Override
	public MatchupStatus getMatchupStatusNotBegin() throws DataAccessException {
		if (statusNotBegin == null) {
			statusNotBegin = matchupStatusRepository.findByName(MatchupStatus.getNameByCode(MatchupStatusCode.NOT_BEGIN));
		}
		return statusNotBegin;
	}
	
	@Override
	public MatchupStatus getMatchupStatusJustBegin() throws DataAccessException {
		if (statusJustBegin == null) {
			statusJustBegin = matchupStatusRepository.findByName(MatchupStatus.getNameByCode(MatchupStatusCode.JUST_BEGIN));
		}
		return statusJustBegin;
	}

	@Override
	public MatchupStatus getMatchupStatusLive() throws DataAccessException {
		if (statusLive == null) {
			statusLive = matchupStatusRepository.findByName(MatchupStatus.getNameByCode(MatchupStatusCode.LIVE));
		}
		return statusLive;
	}

	@Override
	public MatchupStatus getMatchupStatusJustFullTime() throws DataAccessException {
		if (statusJustFullTime == null) {
			statusJustFullTime = matchupStatusRepository.findByName(MatchupStatus.getNameByCode(MatchupStatusCode.JUST_FULL_TIME));
		}
		return statusJustFullTime;
	}

	@Override
	public MatchupStatus getMatchupStatusFullTime() throws DataAccessException {
		if (statusFullTime == null) {
			statusFullTime = matchupStatusRepository.findByName(MatchupStatus.getNameByCode(MatchupStatusCode.FULL_TIME));
		}
		return statusFullTime;
	}
	
	
	@Override
	public MatchupStatus getMatchupStatusPostponed() throws DataAccessException {
		if (statusPostponed == null) {
			statusPostponed = matchupStatusRepository.findByName(MatchupStatus.getNameByCode(MatchupStatusCode.POSTPOSED));
		}
		return statusPostponed;
	}
	
	/**
	 * Team services
	 */
	@Override
	public Team findTeamById(int id) throws DataAccessException {
		return teamRepository.findById(id);
	}
	
	@Override
	public Team findTeamByName(String name) throws DataAccessException {
		return teamRepository.findByName(name);
	}
	
	@Override
	public Team save(Team team) throws DataAccessException {
		return teamRepository.save(team);
	}


	/**
	 * Country services
	 */
	@Override
	public Country findCountryByName(String name) throws DataAccessException {
		return countryRepository.findByName(name);
	}
	 
	
	/**
	 * Hero services
	 */
	@Override
	public Hero findHeroByFullName(String name) throws DataAccessException {
		return heroRepository.findByName(name);
	}
	
	@Override
	public Hero findHeroByLastName(String name) throws DataAccessException {
		return heroRepository.findByLastName(name);
	}
	
	@Override
	public Hero saveHero(Hero hero) throws DataAccessException {
		return heroRepository.save(hero);
	}
	
	/**
	 * Hero Status services
	 */
	@Override
	public HeroStatus findHeroStatusByName(String name) throws DataAccessException {
		return heroStatusRepository.findByName(name);
	}
	
	/**
	 * Hero Role services
	 */
	@Override
	public HeroRole findHeroRoleByName(String name) throws DataAccessException {
		return heroRoleRepository.findByName(name);
	}
	
	/**
	 * Position services
	 */
	@Override
	public Position findPositionByName(String name) throws DataAccessException {
		return positionRepository.findOneByPosition(name);
	}
	
}
