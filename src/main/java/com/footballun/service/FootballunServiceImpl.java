package com.footballun.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.footballun.model.Position;
import com.footballun.model.ScrapedDataResult;
import com.footballun.model.Setting;
import com.footballun.model.Squad;
import com.footballun.model.SquadMember;
import com.footballun.model.Standing;
import com.footballun.model.StandingLive;
import com.footballun.model.Team;
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
import com.footballun.repository.ScrapedDataResultRepository;
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
	@Autowired
	private ScrapedDataResultRepository scrapedDataResultRepository;
	
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
	 * Matchup services implements
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Matchup> findMatchupByMatchdayAndCompetition(Integer matchday, Integer competitionId) throws DataAccessException {
		
		return  matchupRepository.findByMatchdayAndCompetitionIdOrderByStartAtAscKickoffAsc(matchday, competitionId);
	}
	
	@Override
	public void saveMatchup(Matchup matchup) throws DataAccessException {
		matchupRepository.save(matchup);
	}
	
	@Override
	public List<Matchup> findLatestMatchupCalendar(Integer competitionId) throws DataAccessException {
		return matchupRepository.findLatestMatchupCalendar(competitionId);
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
		return matchupRepository.findByFeaturedTrueOrderByCompetitionIdAscStartAtAscKickoffAsc();
	}

	@Override
	public List<Matchup> findMatchupLatestResults(Integer competitionId) throws DataAccessException {
		return matchupRepository.findMatchupLatestResults(competitionId);
	}
	
	
	/**
	 * Matchup Detail services
	 */
	@Override
	public void saveMatchupDetail(MatchupDetail detail) throws DataAccessException {
		matchupDetailRepository.save(detail);
	}
	
	/**
	 * Matchup Live services
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
	 * Squad Member services implement
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
	 * Standing services
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Standing> findCurrentStandingsByCompetition(Integer competitionId) throws DataAccessException {
		
		return standingRepository.findBySquad_CompetitionIdWithMaxMatchdayOrderByCurrentPositionAsc(competitionId);
	}

	@Override
	@Transactional(readOnly = true)
	public Standing findStandingBySquadAndMatchday(Squad squad, int matchday) throws DataAccessException {
		return standingRepository.findBySquadAndMatchdayOrderByCurrentPositionAsc(squad.getId(), matchday);
	}

    @Override
    @Transactional(readOnly = true)
    public Standing findCurrentStandingBySquad(Integer squadId) throws DataAccessException {
        return standingRepository.findBySquadIdAndAllowUpdateTrueOrderByCurrentPositionAsc(squadId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Standing> findAllStandingsBySquad(Squad squad) throws DataAccessException {
        return standingRepository.findAllBySquadOrderByMatchdayAscCurrentPositionAsc(squad.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Standing> findAllStandingsBySquad(Integer squadId) throws DataAccessException {
        return standingRepository.findAllBySquadOrderByMatchdayAscCurrentPositionAsc(squadId);
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
		// createStandingForCompetition(matchup.getCompetition().getId(), matchup.getMatchday());
		
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
		
		accumulateStandingForMatchup(matchup);
		refreshStanding(matchup.getCompetition().getId(), null, matchup.getMatchday());
	}
	
	private void copyStandingToStandingLive(Squad squad) {
//		Standing standing = standingRepository.findBySquad(squad);
//		if (standing != null && standing.getStandingLive() != null) {
//			// Syncs
//			Integer id = standing.getStandingLive().getId(); // don't want to copy its ID, keeps a backup
//			BeanUtils.copyProperties(standing, standing.getStandingLive(), StandingBase.class);
//			standing.getStandingLive().setId(id);
//			standingLiveRepository.save(standing.getStandingLive());
//		}
	}
	
	@Override
	@Transactional
	public List<Standing> refreshStanding(int competitionId, List<Standing> standings, int matchday) throws DataAccessException {
		
		// Gets all current squads standing
		if (standings == null) {
			standings = standingRepository.findBySquad_CompetitionIdAndMatchdayOrderByCurrentPositionAsc(competitionId, matchday);
		}
		sortAndSaveStandings(competitionId, standings);
		
		return standings;
	}
	
	/**
	 * Creates table for all squads off the competition and matchday(do only once)
	 * @return
	 */
	public List<Standing> createStandingForCompetition(int competitionId) {
		
		List<Standing> standings = new ArrayList<Standing>();
		
		List<Squad> squads = squadRepository.findByCompetitionIdAndGeneration(competitionId, "First Team");
		for (Squad squad : squads) {
			createAllStandingsForSquad(squad);
		}
				
		return standings;
	}
	
	/**
	 * Creates table for a new squad off the competition (do only once)
	 * @return
	 */
	@Override
	public Standing createStandingForSquad(Squad squad, int matchday) {
		
		// Creates main standing table
		Standing standing = standingRepository.findBySquadAndMatchdayOrderByCurrentPositionAsc(squad.getId(), matchday);
		if (standing == null) {
			standing = new Standing();
			standing.setSquad(squad);
			standing.setMatchday(matchday);
			standing = standingRepository.save(standing);
		}
		
		// Also creates live standing table
		StandingLive standingLive = standingLiveRepository.findBySquadAndMatchdayOrderByCurrentPositionAsc(squad.getId(), matchday);
		if (standingLive == null) {
			standingLive = new StandingLive();
			standingLive.setStanding(standing);
			standingLive.setSquad(squad);
			standing.setMatchday(matchday);
			standing.setStandingLive(standingLive);
			standingLiveRepository.save(standingLive);
		}
		
		return standing;
	}
	
	/**
	 * Creates table for a new squad off the competition (do only once)
	 * @return
	 */
	@Override
	public void createAllStandingsForSquad(Squad squad) {
		int totalMatchdays = squad.getCompetition().getTotalMatchdays();
		for (int matchday = 1; matchday <= totalMatchdays; matchday++) {
			createStandingForSquad(squad, matchday);
		}
	}
	
	/**
	 * Updates standings from matchup getting from the spider process
	 * 
	 * @param matchup
	 */
	@Override
	public void accumulateStandingForMatchup(Matchup matchup) {
		
		Squad[] squads = {matchup.getFirstDetail().getSquad(), matchup.getSecondDetail().getSquad()};
		for (Squad squad : squads) {
			
			// Current standing
			Standing standing = standingRepository.findBySquadAndMatchdayOrderByCurrentPositionAsc(squad.getId(), matchup.getMatchday());
			// Previous matchday standing
			Standing prevStanding;
			if (matchup.getMatchday() == 1) {
				prevStanding = null;
			} else {
				prevStanding = standingRepository.findBySquadAndMatchdayOrderByCurrentPositionAsc(squad.getId(), matchup.getMatchday() - 1);
			}
			
			if (standing == null || !standing.isAllowUpdate()) {
				logger.error("Cannot find or update standing for squad: " + squad.getFullName() + " and matchday: " + matchup.getMatchday());
				return;
			}
			
			MatchupResult result = matchup.getResultBySquad(squad);
			if (result != MatchupResult.UNKNOWN) {
				// Counts played match
				standing.setPlayed(prevStanding == null ? 1 : prevStanding.getPlayed() + 1);
				if (result == MatchupResult.WIN) {
					// Counts WON match
					standing.setWon(prevStanding == null ? 1 : prevStanding.getWon() + 1);
					standing.setPoint(prevStanding == null ? 3 : prevStanding.getPoint() + 3); // 3 points for a win game
				} else if (result == MatchupResult.DRAW) {
					// Counts DRAWN match
					standing.setDrawn(prevStanding == null ? 1 : prevStanding.getDrawn() + 1);
					standing.setPoint(prevStanding == null ? 1 : prevStanding.getPoint() + 1); // 1 point for a draw game
				} else {
					// Counts LOST match
					standing.setLost(prevStanding == null ? 1 : prevStanding.getLost() + 1);
				}
			
				standing.setGoalsScored(prevStanding == null ? matchup.getGoalsScoredBySquad(squad)
															 : prevStanding.getGoalsScored() + matchup.getGoalsScoredBySquad(squad));
				standing.setGoalsAgainst(prevStanding == null ? matchup.getGoalsAgainstBySquad(squad) 
															  : prevStanding.getGoalsAgainst() + matchup.getGoalsAgainstBySquad(squad));
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
	public void recalculateStandingForTheCompetition(int competitionId) {
		
		recalculateStandingsHistoryForCompetition(competitionId);
	}
	
	public void recalculateStandingsHistoryForCompetition(int competitionId) {
		if (competitionId < 0) competitionId = getSetting(1).getCompetition().getId();

		Collection<String> matchupStatus = new ArrayList<String>();
		matchupStatus.add(MatchupStatus.getNameByCode(MatchupStatusCode.FULL_TIME));
		
		List<Matchup> matchups = matchupRepository.findByCompetitionIdAndStatus_NameInOrderByMatchdayAsc(competitionId, matchupStatus);
			
		List<Standing> standings = standingRepository.findBySquad_CompetitionIdAndMatchdayOrderByCurrentPositionAsc(competitionId, 1);
		if (standings == null || standings.size() == 0) {
			createStandingForCompetition(competitionId);
		} else {
			for (Standing standing : standings) {
				standing.resetAll();
				standingRepository.save(standing);
			}
		}
		
		for (Matchup matchup : matchups) {
			
			accumulateStandingForMatchup(matchup);
		}

		int latestMatchday = matchups.get(matchups.size() - 1).getMatchday();
		
		// Gets all squads
		List<Squad> squads = squadRepository.findByCompetitionIdAndGeneration(competitionId, "First Team");
		for (Squad squad : squads) {
			// Gets all standings from matchdays by squad
			Standing prev = null;
			standings = standingRepository.findAllBySquadOrderByMatchdayAscCurrentPositionAsc(squad.getId());
			for (Standing standing : standings) {
				if (standing.getMatchday() > latestMatchday) {
					break;
				}
				if (prev != null) {
					// Counts played match
					standing.setPlayed(prev.getPlayed() + standing.getPlayed());
					standing.setWon(prev.getWon() + standing.getWon());
					standing.setDrawn(prev.getDrawn() + standing.getDrawn());
					standing.setLost(prev.getLost() + standing.getLost());
					standing.setPoint(prev.getPoint() + standing.getPoint());					
					standing.setGoalsScored(prev.getGoalsScored() + standing.getGoalsScored());
					standing.setGoalsAgainst(prev.getGoalsAgainst() + standing.getGoalsAgainst());
                    if (standing.getMatchday() < latestMatchday) {
                        standing.setAllowUpdate(false);
                    } else {
                        standing.setAllowUpdate(true);
                    }

					standing = standingRepository.save(standing);
				}
				prev = standing;
			}
		}
		
		
		int currentMatchday;
		int previousMatchday = 0;
		List<Standing> currMatchdayStandings = new ArrayList<Standing>();
		
		// Sorts positions for each of the matchdays
		standings = standingRepository.findBySquad_CompetitionIdOrderByMatchdayAscCurrentPositionAsc(competitionId);
		for (Standing standing : standings) {
			if (standing.getMatchday() > latestMatchday) {
				break;
			}
			currentMatchday = standing.getMatchday();
			if (currentMatchday != previousMatchday && previousMatchday > 0) {
				sortAndSaveStandings(competitionId, currMatchdayStandings);
				currMatchdayStandings.removeAll(currMatchdayStandings);
			}

			currMatchdayStandings.add(standing);

			previousMatchday = currentMatchday;
		}

		// The last group
		if (currMatchdayStandings.size() > 0) {
			sortAndSaveStandings(competitionId, currMatchdayStandings);
		}
	}
	
	
	private boolean isAllowToFurtherUpdateOnStanding(List<Standing> standings) {
	
		for (Standing standing : standings) {
			if (!standing.isAllowUpdate()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public List<Standing> findShortList() throws DataAccessException {
		return standingRepository.findShortList();
	}
	
	/**
	 * Standing Live services
	 */
	@Override
	public List<StandingLive> findStandingLiveByCompetition(Integer competitionId, int matchday) throws DataAccessException {
		return standingLiveRepository.findBySquad_CompetitionIdAndMatchdayOrderByCurrentPositionAsc(competitionId, matchday);
	}
	
	@Override
	public StandingLive findStandingLiveBySquad(Squad squad, int matchday) throws DataAccessException {
		return standingLiveRepository.findBySquadAndMatchdayOrderByCurrentPositionAsc(squad.getId(), matchday);
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
	 * Matchup Register services
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
	public List<Competition> findAllCompetition(Integer yearFrom, Integer yearTo, String type) throws DataAccessException {
		return competitionRepository.findByYearFromAndYearToAndType(yearFrom, yearTo, type);
	}
	
	@Override
	public List<Competition> findAllCompetition(LocalDate currentDate, String type) throws DataAccessException {
		return competitionRepository.findByStartAtLessThanEqualAndEndAtGreaterThanEqualAndType(currentDate, currentDate, type);
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
	
	/**
	 * Scraped data services
	 */
	@Override
	public List<ScrapedDataResult> findAllScrapedDataResults() throws DataAccessException {
		return scrapedDataResultRepository.findAll();
	}
	
	@Override
	public List<ScrapedDataResult> findAllScrapedDataResultsJustUpdated() throws DataAccessException {
		return scrapedDataResultRepository.findByJustUpdateTrue();
	}
	
	
	@Override
	public void saveScrapedDataResult(ScrapedDataResult scrapedDataResult) throws DataAccessException {
		scrapedDataResultRepository.save(scrapedDataResult);
	}
}
