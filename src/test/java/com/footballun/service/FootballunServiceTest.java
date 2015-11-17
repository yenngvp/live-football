/**
 * @author: yen.nt
 * @created on Nov 12, 2015
 */
package com.footballun.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.footballun.model.Event;
import com.footballun.model.Matchup;
import com.footballun.model.MatchupDetail;
import com.footballun.model.MatchupLive;
import com.footballun.model.MatchupRegister;
import com.footballun.model.Squad;
import com.footballun.model.SquadMember;
import com.footballun.model.Standing;

/**
 * @author yen.nt
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("spring-data-jpa")
@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
public class FootballunServiceTest  {

	@Autowired
	private FootballunService service;
	
	private static Logger logger = LoggerFactory.getLogger(FootballunServiceTest.class);
	
	private static final int DEFAULT_COMPETITION = 9;
	private static List<Matchup> matchups;
	private static List<Squad> squads;
	private static MatchupRegister citizenRegister;
	private static MatchupRegister gunnerRegister;
	private static Event goalEvent;
	private static Squad mancity;
	private static Squad arsenal;
	
	@Test
	@Transactional
	public void testCase1() {
		
		// Fetches all matches scheduled
		matchups = service.findMatchupByCompetitionId(DEFAULT_COMPETITION);
		squads = service.findSquadByCompetitionAndGeneration(DEFAULT_COMPETITION, "First Team");

		// Removes matches have no squads involve
		for (Matchup matchup : matchups) {
			if (matchup.getDetails() == null || matchup.getDetails().size() == 0) {
				matchups.remove(matchup);
			}
		}

		assertTrue(matchups.size() > 0);
		assertTrue(squads.size() > 0);
		
		// Reset matchup details
		for (Matchup matchup : matchups) {
			matchup.getFirstDetail().setGoal(0);
			matchup.getFirstDetail().setIsFirstSquad(false);
			matchup.getFirstDetail().setIsHomeSquad(false);
			matchup.getSecondDetail().setGoal(0);
			matchup.getSecondDetail().setIsFirstSquad(false);
			matchup.getSecondDetail().setIsHomeSquad(false);
		}
		
		// Ensure all matches are in non-manual mode
		for (Matchup matchup : matchups) {
			matchup.setManualMode(false);
		}
		
		
		// Deletes all existing live events
		service.deleteAllMachupLives();
		List<MatchupLive> lives = service.findAllMachupLives();
		assertTrue(lives.size() == 0);
		
		// Finds all standing
		List<Standing> standings = service.findStandingByCompetition(DEFAULT_COMPETITION);
		
		// Make sure standing is 'Empty'
		for (Standing standing : standings) {
			assertEquals(0, standing.getPoint());
			assertEquals(0, standing.getPlayed());
			assertEquals(0, standing.getWon());
			assertEquals(0, standing.getDrawn());
			assertEquals(0, standing.getLost());
			assertEquals(0, standing.getGoalsScored());
			assertEquals(0, standing.getGoalsAgainst());
			assertEquals(0, standing.getCurrentPosition());
			assertEquals(0, standing.getPreviousPosition());
		}
		
		/*
		 *  Setups match and squads
		 */
		final String firstSquadName = "Man City";
		final String secondSquadName = "Arsenal";
		final String firstHeroName = "Aguero";
		final String secondHeroName = "Sanchez";
		
		Matchup matchup = service.findMatchupById(40);
		MatchupDetail mancityDetail = matchup.getFirstDetail();
		MatchupDetail arsenalDetail = matchup.getSecondDetail();
		// are real ones
		assertEquals(firstSquadName, mancityDetail.getSquad().getTeam().getName());
		assertEquals(secondSquadName, arsenalDetail.getSquad().getTeam().getName());
		
		mancity = service.findSquadByName(firstSquadName, DEFAULT_COMPETITION);
		arsenal = service.findSquadByName(secondSquadName, DEFAULT_COMPETITION);
		// Creates matchup register for the matchup
		SquadMember citizen = service.findSquadMemberByLastNameAndSquad(firstHeroName, mancity.getId());
		SquadMember gunner = service.findSquadMemberByLastNameAndSquad(secondHeroName, arsenal.getId());
		
		assertTrue(matchup != null);
		assertEquals(mancity, matchup.getFirstDetail().getSquad());
		assertEquals("Man City", mancity.getTeam().getName());
		assertEquals("Arsenal", arsenal.getTeam().getName());
		assertEquals(arsenal, matchup.getSecondDetail().getSquad());
		assertTrue(citizen != null);
		assertTrue(gunner != null);
		assertEquals(firstHeroName, citizen.getHero().getLastName());
		assertEquals(secondHeroName, gunner.getHero().getLastName());
		
		citizenRegister = new MatchupRegister();
		citizenRegister.setMatchupDetail(mancityDetail);
		citizenRegister.setSquadMember(citizen);
		service.saveMatchupRegister(citizenRegister);
	
		gunnerRegister = new MatchupRegister();
		gunnerRegister.setMatchupDetail(arsenalDetail);
		gunnerRegister.setSquadMember(gunner);
		service.saveMatchupRegister(gunnerRegister);
		
		/*
		 * Test 1: Create a new event: Man City scores a goal
		 */
		goalEvent = service.findEventByName("Goal");
		assertEquals("Goal", goalEvent.getName());
		
		MatchupLive event1 = new MatchupLive();
		event1.setEvent(goalEvent);
		event1.setMatchup(matchup);
		event1.setMatchupRegister(citizenRegister);
		service.saveMatchupLive(event1);
		
		// Checks standing update
		// Finds all standing
		standings = service.findStandingByCompetition(DEFAULT_COMPETITION);
		int lastPosition = standings.size();
		for (Standing standing : standings) {
			if (standing.getSquad().equals(mancity)) {
				assertEquals(3, standing.getPoint());
				assertEquals(1, standing.getPlayed());
				assertEquals(1, standing.getWon());
				assertEquals(0, standing.getDrawn());
				assertEquals(0, standing.getLost());
				assertEquals(1, standing.getGoalsScored());
				assertEquals(0, standing.getGoalsAgainst());
				assertEquals(1, standing.getCurrentPosition());
				assertEquals(0, standing.getPreviousPosition());
			} else if (standing.getSquad().equals(arsenal)) {
				assertEquals(0, standing.getPoint());
				assertEquals(1, standing.getPlayed());
				assertEquals(0, standing.getWon());
				assertEquals(0, standing.getDrawn());
				assertEquals(1, standing.getLost());
				assertEquals(0, standing.getGoalsScored());
				assertEquals(1, standing.getGoalsAgainst());
				assertEquals(lastPosition, standing.getCurrentPosition());
				assertEquals(0, standing.getPreviousPosition());
			} else {
				assertEquals(0, standing.getPoint());
				assertEquals(0, standing.getPlayed());
				assertEquals(0, standing.getWon());
				assertEquals(0, standing.getDrawn());
				assertEquals(0, standing.getLost());
				assertEquals(0, standing.getGoalsScored());
				assertEquals(0, standing.getGoalsAgainst());
				assertEquals(2, standing.getCurrentPosition());
				assertEquals(0, standing.getPreviousPosition());
			}
		}
		
		/*
		 * Test 2: Create a new event: Arsenal scores a goal
		 */
		//matchup = service.findMatchupById(40);
		
		MatchupLive event2 = new MatchupLive();
		event2.setEvent(goalEvent);
		event2.setMatchup(matchup);
		event2.setMatchupRegister(gunnerRegister);
		service.saveMatchupLive(event2);
		
		// Checks standing update
		// Finds all standing
		standings = service.findStandingByCompetition(DEFAULT_COMPETITION);
		lastPosition = standings.size();
		for (Standing standing : standings) {
			if (standing.getSquad().equals(mancity)) {
				assertEquals(1, standing.getPoint());
				assertEquals(1, standing.getPlayed());
				assertEquals(0, standing.getWon());
				assertEquals(1, standing.getDrawn());
				assertEquals(0, standing.getLost());
				assertEquals(1, standing.getGoalsScored());
				assertEquals(1, standing.getGoalsAgainst());
				assertEquals(1, standing.getCurrentPosition());
				assertEquals(0, standing.getPreviousPosition());
			} else if (standing.getSquad().equals(arsenal)) {
				assertEquals(1, standing.getPoint());
				assertEquals(1, standing.getPlayed());
				assertEquals(0, standing.getWon());
				assertEquals(1, standing.getDrawn());
				assertEquals(0, standing.getLost());
				assertEquals(1, standing.getGoalsScored());
				assertEquals(1, standing.getGoalsAgainst());
				assertEquals(2, standing.getCurrentPosition());
				assertEquals(lastPosition, standing.getPreviousPosition());
			} else {
				assertEquals(0, standing.getPoint());
				assertEquals(0, standing.getPlayed());
				assertEquals(0, standing.getWon());
				assertEquals(0, standing.getDrawn());
				assertEquals(0, standing.getLost());
				assertEquals(0, standing.getGoalsScored());
				assertEquals(0, standing.getGoalsAgainst());
				assertEquals(3, standing.getCurrentPosition());
				assertEquals(2, standing.getPreviousPosition());
			}
		}
		
		
		/*
		 * Test 3: Create a new event: Arsenal scores one more goal
		 */
	
		event2 = new MatchupLive();
		event2.setEvent(goalEvent);
		event2.setMatchup(matchup);
		event2.setMatchupRegister(gunnerRegister);
		service.saveMatchupLive(event2);
		
		// Checks standing update
		// Finds all standing
		standings = service.findStandingByCompetition(DEFAULT_COMPETITION);
		lastPosition = standings.size();
		for (Standing standing : standings) {
			if (standing.getSquad().equals(mancity)) {
				assertEquals(0, standing.getPoint());
				assertEquals(1, standing.getPlayed());
				assertEquals(0, standing.getWon());
				assertEquals(0, standing.getDrawn());
				assertEquals(1, standing.getLost());
				assertEquals(1, standing.getGoalsScored());
				assertEquals(2, standing.getGoalsAgainst());
				assertEquals(lastPosition, standing.getCurrentPosition());
				assertEquals(2, standing.getPreviousPosition());
			} else if (standing.getSquad().equals(arsenal)) {
				assertEquals(3, standing.getPoint());
				assertEquals(1, standing.getPlayed());
				assertEquals(1, standing.getWon());
				assertEquals(0, standing.getDrawn());
				assertEquals(0, standing.getLost());
				assertEquals(2, standing.getGoalsScored());
				assertEquals(1, standing.getGoalsAgainst());
				assertEquals(1, standing.getCurrentPosition());
				assertEquals(2, standing.getPreviousPosition());
			} else {
				assertEquals(0, standing.getPoint());
				assertEquals(0, standing.getPlayed());
				assertEquals(0, standing.getWon());
				assertEquals(0, standing.getDrawn());
				assertEquals(0, standing.getLost());
				assertEquals(0, standing.getGoalsScored());
				assertEquals(0, standing.getGoalsAgainst());
				assertEquals(2, standing.getCurrentPosition());
				assertEquals(3, standing.getPreviousPosition());
			}
		}
		
	}
	
	@Test
	@Transactional
	public void testCase2() {
		
		
	}
}
