package com.footballun.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.footballun.model.Matchup;
import com.footballun.model.Matchup.MatchupResult;
import com.footballun.model.Squad;
import com.footballun.model.SquadMember;
import com.footballun.model.Standing;
import com.footballun.repository.MatchupRepository;
import com.footballun.repository.SquadMemberRepository;
import com.footballun.repository.SquadRepository;
import com.footballun.repository.CompetitionRepository;
import com.footballun.repository.StandingRepository;
import com.footballun.repository.springdatajpa.StandingRepositoryJpa;

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
	private StandingRepositoryJpa standingRepositoryJpa;
	
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
	public void updateMatchup(Matchup matchup) throws DataAccessException {
		matchupRepository.save(matchup);
	}
	
	/**
	 * Squad Member's APIs implement
	 */
	
	@Override
	@Transactional(readOnly = true)
	public List<SquadMember> findSquadMembersBySquad(Integer squadId) throws DataAccessException {
		return squadMemberRepository.findBySquadId(squadId);
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
	@Transactional(readOnly = false)
	public void saveStanding(Standing standing) throws DataAccessException {
		standingRepositoryJpa.save(standing);
//		standingRepository.save(standing);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void updateSquadStanding(Squad squad) throws DataAccessException {

		Standing standing = standingRepository.findBySquad(squad);
		if (standing == null) {
			logger.info("updateSquadStanding new");
			standing = new Standing();
			standing.setSquad(squad);
		}
		calculatePoint(standing, squad);
		standingRepositoryJpa.saveAndFlush(standing);
		logger.info("updateSquadStanding saveAndFlush");
	}
	
	@Override
	@Transactional(readOnly = false)
	public void refreshStanding(Integer competitionId) throws DataAccessException {
		if (competitionId == null) competitionId = 9;
		List<Standing> standings = standingRepository.findBySquad_CompetitionIdOrderByCurrentPositionAsc(competitionId);
		
		for (Standing standing : standings) {
			calculatePoint(standing, standing.getSquad());
			
			standingRepositoryJpa.saveAndFlush(standing);
		}
	}

	private void calculatePoint(Standing standing, Squad squad) {
		logger.info("calculatePoint:", standing, squad);
		for (Matchup match : squad.getMatchups()) {
			logger.info("calculatePoint2 ");
			MatchupResult result = match.getResultBySquad(squad);
			logger.info("calculatePoint3 ");
			if (result != MatchupResult.UNKNOWN) {
				standing.setPlayed(standing.getPlayed() + 1);
				if (result == MatchupResult.WIN) {
					standing.setWon(standing.getWon() + 1);
					standing.setPoint(standing.getPoint() + 3);
				} else if (result == MatchupResult.DRAW) {
					standing.setDrawn(standing.getDrawn() + 1);
					standing.setPoint(standing.getPoint() + 1);
				} else {
					standing.setLost(standing.getLost() + 1);
				}
			}
		}
	}
}
