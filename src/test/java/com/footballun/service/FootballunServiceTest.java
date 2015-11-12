/**
 * @author: yen.nt
 * @created on Nov 12, 2015
 */
package com.footballun.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.AssertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mvel2.ast.AssertNode;
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
@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("spring-data-jpa")
public class FootballunServiceTest {

	@Autowired
	private FootballunService footballunService;
	
	private static final Logger logger = LoggerFactory.getLogger(FootballunServiceTest.class);
	
	private static final int DEFAULT_COMPETITION = 9;
	
	private static List<Matchup> matchups;
	private static List<Squad> squads;
	
	@BeforeClass
	public static void setUp() {
		
		
	}
	
	@AfterClass
	public static void tearDown() {
		
		
	}
	
	@Test
	@Transactional
	public void testStandingAutomaticUpdate() {
		// Fetches all matches scheduled
		matchups = footballunService.findMatchupByCompetitionId(DEFAULT_COMPETITION);
		squads = footballunService.findSquadByCompetitionAndGeneration(DEFAULT_COMPETITION, "First Team");

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
		footballunService.deleteAllMachupLives();
		List<MatchupLive> lives = footballunService.findAllMachupLives();
		assertTrue(lives.size() == 0);
		
		// Finds all standing
		List<Standing> standings = footballunService.findStandingByCompetition(DEFAULT_COMPETITION);
		
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
		
		Matchup matchup = footballunService.findMatchupById(40);
		MatchupDetail mancityDetail = matchup.getFirstDetail();
		MatchupDetail arsenalDetail = matchup.getSecondDetail();
		// are real ones
		assertEquals(firstSquadName, mancityDetail.getSquad().getTeam().getName());
		assertEquals(secondSquadName, arsenalDetail.getSquad().getTeam().getName());
		
		Squad mancity = footballunService.findSquadByName(firstSquadName, DEFAULT_COMPETITION);
		Squad arsenal = footballunService.findSquadByName(secondSquadName, DEFAULT_COMPETITION);
		// Creates matchup register for the matchup
		SquadMember citizen = footballunService.findSquadMemberByLastNameAndSquad(firstHeroName, mancity.getId());
		SquadMember gunner = footballunService.findSquadMemberByLastNameAndSquad(secondHeroName, mancity.getId());
		
		assertTrue(matchup != null);
		assertEquals(mancity, matchup.getFirstDetail().getSquad());
		assertEquals("Man City", mancity.getTeam().getName());
		assertEquals("Arsenal", mancity.getTeam().getName());
		assertEquals(arsenal, matchup.getSecondDetail().getSquad());
		assertTrue(citizen != null);
		assertTrue(gunner != null);
		assertEquals(firstHeroName, citizen.getHero().getLastName());
		assertEquals(secondHeroName, gunner.getHero().getLastName());
		
		MatchupRegister citizenRegister = new MatchupRegister();
		citizenRegister.setMatchupDetail(mancityDetail);
		citizenRegister.setSquadMember(citizen);
		footballunService.saveMatchupRegister(citizenRegister);
	
		MatchupRegister gunnerRegister = new MatchupRegister();
		gunnerRegister.setMatchupDetail(arsenalDetail);
		gunnerRegister.setSquadMember(citizen);
		footballunService.saveMatchupRegister(gunnerRegister);
		
		
		/*
		 * Test 1: Create a new event: Man City scores a goal
		 */
		Event goalEvent = footballunService.findEventByName("Goal");
		assertEquals("Goal", goalEvent.getName());
		
		MatchupLive event1 = new MatchupLive();
		event1.setEvent(goalEvent);
		event1.setMatchup(matchup);
		event1.setMatchupRegister(citizenRegister);
		footballunService.saveMatchupLive(event1);
		
		// Checks standing update
		// Finds all standing
		standings = footballunService.findStandingByCompetition(DEFAULT_COMPETITION);
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

		MatchupLive event2 = new MatchupLive();
		event2.setEvent(goalEvent);
		event2.setMatchup(matchup);
		event2.setMatchupRegister(gunnerRegister);
		footballunService.saveMatchupLive(event2);
		
		// Checks standing update
		// Finds all standing
		standings = footballunService.findStandingByCompetition(DEFAULT_COMPETITION);
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
	}
	
	@Test
	@Transactional
	public void testStandingManualUpdate() {
		
	}
}
