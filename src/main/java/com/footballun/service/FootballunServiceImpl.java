package com.footballun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.footballun.model.Matchup;
import com.footballun.model.Squad;
import com.footballun.model.SquadMember;
import com.footballun.repository.MatchupRepository;
import com.footballun.repository.SquadMemberRepository;
import com.footballun.repository.SquadRepository;
import com.footballun.repository.CompetitionRepository;

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
	
	@Override
	@Transactional(readOnly = true)
	public List<Squad> findSquadByCompetitionAndGeneration(Integer competitionId, String generation)
			throws DataAccessException {
		return squadRepository.findByCompetitionIdAndGeneration(competitionId, generation);
		
	}

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
	@Transactional(readOnly = true)
	public List<SquadMember> findSquadMembersBySquad(Integer squadId) throws DataAccessException {
		return squadMemberRepository.findBySquadId(squadId);
	}
	
}
