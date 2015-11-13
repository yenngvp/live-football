package com.footballun.service;

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

import com.footballun.model.Event;
import com.footballun.model.Matchup;
import com.footballun.model.Matchup.MatchupResult;
import com.footballun.model.MatchupDetail;
import com.footballun.model.MatchupLive;
import com.footballun.model.MatchupRegister;
import com.footballun.model.MatchupStatus;
import com.footballun.model.MatchupStatus.MatchupStatusCode;
import com.footballun.model.Squad;
import com.footballun.model.SquadMember;
import com.footballun.model.Standing;
import com.footballun.model.StandingBase;
import com.footballun.model.StandingLive;
import com.footballun.repository.CompetitionRepository;
import com.footballun.repository.EventRepository;
import com.footballun.repository.MatchupDetailRepository;
import com.footballun.repository.MatchupLiveRepository;
import com.footballun.repository.MatchupRegisterRepository;
import com.footballun.repository.MatchupRepository;
import com.footballun.repository.SquadMemberRepository;
import com.footballun.repository.SquadRepository;
import com.footballun.repository.springdatajpa.StandingLiveRepository;
import com.footballun.repository.springdatajpa.StandingRepository;
import com.footballun.util.PositionComparator;

@Service
public class FootballunServiceImpl implements FootballunService {

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
	
	final Logger logger = LoggerFactory.getLogger("FootballunService");

	/**
	 * Squad's APIs implements 
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
		return squadRepository.findByTeam_NameAndCompetitionId(name, competitionId);
	}
	
	/**
	 * Matchup's APIs implements
	 */
	
	@Override
	@Transactional(readOnly = true)
	public List<Matchup> findMatchupByMatchday(Integer matchday) throws DataAccessException {
		
		return  matchupRepository.findByMatchday(matchday);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Matchup> findMatchupByRound(String round) throws DataAccessException {
		
		return  matchupRepository.findByRound(round);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Matchup> findMatchupByFeatured(Boolean featured) throws DataAccessException {
		return  matchupRepository.findByFeatured(featured);
	}
	
	@Override
	public void saveMatchup(Matchup matchup) throws DataAccessException {
		matchupRepository.save(matchup);
	}
	
	@Override
	public List<Matchup> findMatchupByCompetitionId(Integer competitionId) throws DataAccessException {
		return matchupRepository.findByCompetitionId(competitionId);
	}
	
	@Override
	public Matchup findMatchupById(Integer id) throws DataAccessException {
		return matchupRepository.findById(id);
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
	
	@Override
	@Transactional
	public void refreshStanding(boolean liveNow, Integer competitionId) throws DataAccessException {
		if (competitionId == null) competitionId = 9;
		
		// Currently in live mode
		if (liveNow) {
			

			// Gets all (none live) standings
			List<Standing> standings = standingRepository.findBySquad_CompetitionIdOrderByCurrentPositionAsc(competitionId);
			
			// Go over all standing and check for live standings have been created or not, if not create it
			StandingLive live;
			for (Standing standing : standings) {
				if (!standing.getEnteredLive()) {
					standing.setEnteredLive(true);
					
					// Creates standing live instance
					live = new StandingLive();
					live.setStanding(standing);
				} else {
					// Query for existing live standing object
					live = standing.getStandingLive();
				}
				
				// Copy all standing values to live standing against StandingBase class properties,
				// Remember to back live object' ID before copy and restore it after that.
				Integer id = live.getId();
				BeanUtils.copyProperties(standing, live, StandingBase.class);
				live.setId(id);
				standingLiveRepository.save(live);	
			}
			
			// Calculates standing against played matches with status 'FULL_TIME'
			Collection<MatchupStatusCode> matchStatusCodes = new ArrayList<MatchupStatusCode>();
			matchStatusCodes.add(MatchupStatusCode.FIRST_HALF);
			matchStatusCodes.add(MatchupStatusCode.HALF_TIME);
			matchStatusCodes.add(MatchupStatusCode.SECOND_HALF);
			recalculateStandingLive(competitionId, matchStatusCodes);	
			
			// Do live standing update
			/**
			 * Now we need to sort the table against all squads achievement
			 */
			// Gets all standings
			List<StandingLive> liveStandings = standingLiveRepository.findBySquad_CompetitionIdOrderByCurrentPositionAsc(competitionId);
			sortAndSaveStandings(liveStandings);
			
		} else {
			Collection<MatchupStatusCode> matchStatusCodes = new ArrayList<MatchupStatusCode>();
			matchStatusCodes.add(MatchupStatusCode.FULL_TIME);
			// Calculates standing against played matches with status 'FULL_TIME'
			recalculateStanding(competitionId, matchStatusCodes);
			

			/**
			 * Now we need to sort the table against all squads achievement
			 */
			// Gets all standings
			List<Standing> standings = standingRepository.findBySquad_CompetitionIdOrderByCurrentPositionAsc(competitionId);
			sortAndSaveStandings(standings);
		}
	}

	private void recalculateStanding(Integer competitionId, Collection<MatchupStatusCode> matchStatusCodes) {
		
		/**
		 * For each squad in the competition: calculate its result and update standing table accordingly
		 */
		List<Squad> squads = squadRepository.findByCompetitionIdAndGeneration(competitionId, "First Team");
		for (Squad squad : squads) {
			Standing standing = standingRepository.findBySquad(squad);
			if (standing == null) {
				logger.info("createNewStandingforSquad");
				standing = new Standing();
				standing.setSquad(squad);
			}
		
			// Resets current standing
			// TODO: should be accumulated from just finished matches
			standing.reset();
			calculateSquadStanding(standing, squad, matchStatusCodes, competitionId);			
		}
		

	}

	private void recalculateStandingLive(Integer competitionId, Collection<MatchupStatusCode> matchStatusCodes) {
		
		/**
		 * For each squad in the competition: calculate its result and update standing table accordingly
		 */
		List<Squad> squads = squadRepository.findByCompetitionIdAndGeneration(competitionId, "First Team");
		
		for (Squad squad : squads) {
			StandingLive standing = standingLiveRepository.findBySquad(squad);
			if (standing == null) {
				// shouldn't be here 
				throw new RuntimeException("Couldn't find Standing live for squad: " + squad.toString());
			}
	
			calculateSquadStanding(standing, squad, matchStatusCodes, competitionId);			
		}
		

		/**
		 * Now we need to sort the table against all squads achievement
		 */
		// Gets all standings
		List<Standing> standings = standingRepository.findBySquad_CompetitionIdOrderByCurrentPositionAsc(competitionId);
		sortAndSaveStandings(standings);
	}

	private void sortAndSaveStandings(List<? extends StandingBase> standings) {
		// Sorts positions
		Collections.sort(standings, new PositionComparator());
		// Update current position number we already get an ordered list of standings
		int currentPosition = 1;
		for (StandingBase standing : standings) {
			standing.setPreviousPosition(standing.getCurrentPosition());
			standing.setCurrentPosition(currentPosition++);
			// Persists the standing
			if (standing instanceof StandingLive) {
				standingLiveRepository.save((StandingLive) standing);
			} else {
				standingRepository.save((Standing) standing);
			}
		}
	}

	private void calculateSquadStanding(StandingBase standing, Squad squad, Collection<MatchupStatusCode> matchStatusCodes, Integer competitionId) {

		Collection<String> statuses = new ArrayList<String>();
		for (MatchupStatusCode code : matchStatusCodes) {
			statuses.add(MatchupStatus.getNameByCode(code));
		}
		// Finds all matches played by the squad in this competition with specified matchStatus
		List<Matchup> matches = matchupRepository.findByCompetitionIdAndStatus_NameIn(competitionId, statuses);
		for (Matchup matchup : matches) {
			MatchupResult result = matchup.getResultBySquad(squad);
			if (result != MatchupResult.UNKNOWN) {
				// Counts played match
				standing.setPlayed(standing.getPlayed() + 1);
				if (result == MatchupResult.WIN) {
					// Counts WON match
					standing.setWon(standing.getWon() + 1);
					standing.setPoint(standing.getPoint() + 3); // 3 points for a win game
				} else if (result == MatchupResult.DRAW) {
					// Counts DRAWN match
					standing.setDrawn(standing.getDrawn() + 1);
					standing.setPoint(standing.getPoint() + 1); // 1 point for a draw game
				} else {
					// Counts LOST match
					standing.setLost(standing.getLost() + 1);
				}
				
				standing.setGoalsScored(standing.getGoalsScored() + matchup.getGoalsScoredBySquad(squad));
				standing.setGoalsAgainst(standing.getGoalsAgainst() + matchup.getGoalsAgainstBySquad(squad));
			}
		}
		
		// Save the standing
		if (standing instanceof StandingLive) {
			standingLiveRepository.save((StandingLive) standing);
		} else {
			standingRepository.save((Standing) standing);
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
}
