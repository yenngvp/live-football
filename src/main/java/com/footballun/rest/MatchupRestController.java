package com.footballun.rest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.footballun.model.Matchup;
import com.footballun.model.MatchupRegister;
import com.footballun.model.Position;
import com.footballun.service.FootballunService;

@RestController
@RequestMapping("/api")
public class MatchupRestController {
			
	@Autowired
	private FootballunService footballunService;
		
	@RequestMapping(value = "/matchdays", method = RequestMethod.GET)
	public List<List<Matchup>> showMatches() {
		
		return getMatchesByMatchdayAndCompetition(15, 9);
	}
	
	
	@RequestMapping(value = "/matchdays/competition/{id}", method = RequestMethod.GET)
	public List<List<Matchup>> showMatchesByCompetition(@PathVariable("id") int id) {
		
		return getMatchesByMatchdayAndCompetition(15, id);
	}

	@RequestMapping(value = "/matchdays/{week}/competition/{id}", method = RequestMethod.GET)
	public List<List<Matchup>> showMatchesByDayAndCompetition(@PathVariable("week") int week, @PathVariable("id") int id) {
		
		return getMatchesByMatchdayAndCompetition(week, id);
	}
	
	private List<List<Matchup>> getMatchesByMatchdayAndCompetition(Integer matchday, Integer competitionId) {
		List<List<Matchup>> groupedMatchupsByDate = new ArrayList<List<Matchup>>();
		
		// Query sorted matches by start time
		List<Matchup> matchups = footballunService.findMatchupByMatchday(matchday, competitionId);
		
		List<Matchup> grouped = new ArrayList<Matchup>();
		Matchup prev = null;
		
	
		// Group matches by date
		for (Matchup matchup : matchups) {
			if (matchup.getDetails() != null && matchup.getStartAt() != null) {
			
				if (prev != null
						&& !matchup.getStartAt().isEqual(prev.getStartAt())) {

					// Adds the matchup to the new group
					groupedMatchupsByDate.add(grouped);
					// Prepare to create a new group
					grouped = new ArrayList<Matchup>();
				}
				
				grouped.add(matchup);
				prev = matchup;
			}
		}
		
		// The last piece
		if (grouped.size() > 0) {
			groupedMatchupsByDate.add(grouped);
			grouped = null;
		}
		
		// Adds matchups that haven't scheduled yet to the end of the list
		
		for (Matchup matchup : matchups) {
			if (matchup.getDetails() != null && matchup.getStartAt() == null) {
				if (grouped == null) {
					grouped = new ArrayList<Matchup>();
					groupedMatchupsByDate.add(grouped);
				}
				grouped.add(matchup);
			}
		}

		return groupedMatchupsByDate;
	}
	
	@RequestMapping(value = "/featured-matchups", method = RequestMethod.GET)
	public List<Matchup> showFeaturedMatchups() {
		// Understood default competition if it isn't specified
		return footballunService.findMatchupByFeatured(Boolean.TRUE);
	}
	
	/**
	 * Get matchup register
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match-register/{id}", method = RequestMethod.GET)
	public @ResponseBody List<List<MatchupRegister>> getMatchupRegisters(@PathVariable("id") int id) {
		
		List<List<MatchupRegister>> result = new ArrayList<List<MatchupRegister>>();
		List<MatchupRegister> matchupRegisters =  footballunService.findMatchupRegisterByMatchupId(id);
		
		// Group match register by squad (home/away),
		// For non-squad involved, put them all to a group.
		// Make sure this matchupRegisters has been sorted by ASCENDING BY MATCHUP DETAIL
		MatchupRegister prev = null;
		List<MatchupRegister> register = new ArrayList<MatchupRegister>();
		for (MatchupRegister matchupRegister : matchupRegisters) {
			if (prev != null 
					&& ((matchupRegister.getMatchupDetail() != null && !matchupRegister.getMatchupDetail().equals(prev.getMatchupDetail()))
							|| (matchupRegister.getMatchupDetail() == null && prev.getMatchupDetail() != null))) {
				result.add(register);
				// Get prepare for a new group
				register = new ArrayList<MatchupRegister>();
			}
			
			register.add(matchupRegister);
			prev = matchupRegister;
		}
		
		// The last piece
		if (register.size() > 0) {
			result.add(register);
		}

		// Sorts registration lists
		for (List<MatchupRegister> registers : result) {
			sortFormation(registers);
			for (MatchupRegister r : registers) {
				if (r.getSquadMember().getPosition() != null)
				System.out.println(r.getSquadMember().getPosition().toString());
			}
			
		}
		
		return result.size() > 0 ? result : null;
	}
	
	/**
	 * Do sort registers by their positions: top-down, right-left
	 * 
	 * @param registers
	 */
	private void sortFormation(List<MatchupRegister> registers) {

		// Register positions comparator
		final Comparator<MatchupRegister> FORMATION_ORDER = new Comparator<MatchupRegister>() {
			public int compare(MatchupRegister reg1, MatchupRegister reg2) {
				Position p1 = reg1.getSquadMember().getPosition();
				Position p2 = reg2.getSquadMember().getPosition();
				
				if (p1 == null || p2 == null) return 0;
				
				// Compares top-down position
				if (p1.getAreaTopdown() < p2.getAreaTopdown()) {
					return -1;
				} else if (p1.getAreaTopdown() > p2.getAreaTopdown()) {
					return 1;
				} else {
					// Compares left-right position
					return p1.getAreaLeftright() - p2.getAreaLeftright();
				}
			}
		};
		
		Collections.sort(registers, FORMATION_ORDER);
	}
	
	/**
	 * Get matchup
	 * 
	 * @return
	 */
	@RequestMapping(value = "/match/{id}", method = RequestMethod.GET)
	public @ResponseBody Matchup getMatchup(@PathVariable("id") int id) {
		return footballunService.findMatchupById(id);
	}
	
}
