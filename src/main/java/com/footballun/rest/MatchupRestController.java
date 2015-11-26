package com.footballun.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.footballun.model.Matchup;
import com.footballun.model.MatchupDetail;
import com.footballun.model.MatchupRegister;
import com.footballun.service.FootballunService;

@RestController
@RequestMapping("/api")
public class MatchupRestController {
			
	@Autowired
	private FootballunService footballunService;
		
	@RequestMapping(value = "/matchdays", method = RequestMethod.GET)
	public List<List<Matchup>> showMatchDays() {
		
		List<List<Matchup>> groupedMatchupsByDate = new ArrayList<List<Matchup>>();
		
		// Query sorted matches by start time
		List<Matchup> matchups = footballunService.findMatchupByRound("11");
		
		List<Matchup> grouped = new ArrayList<Matchup>();
		Matchup prev = null;
		// Group matches by date
		for (Matchup matchup : matchups) {
			if (matchup.getDetails() != null && matchup.getStartAt() != null) {
				if (prev != null && !matchup.getStartAt().equals(prev.getStartAt())) {
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

		return result.size() > 0 ? result : null;
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
