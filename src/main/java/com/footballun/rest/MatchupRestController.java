package com.footballun.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.footballun.model.Matchup;
import com.footballun.model.Squad;
import com.footballun.service.FootballunService;

@RestController
@RequestMapping("/api")
public class MatchupRestController {
	
	// Current competition by default
	// TODO: should be getting from settings
	private static final int DEFAULT_COMPETITION = 9;
	private static final String DEFAULT_GENERATION = "First Team";
	private static final int CURRENT_MATCDAY = 1;
		
	private final FootballunService footballunService;
	
	@Autowired
	public MatchupRestController(FootballunService footballunService) {
		this.footballunService = footballunService;
	}
	
	@RequestMapping(value = "/matchdays", method = RequestMethod.GET)
	public List<Matchup> showMatchDays() {
		// Understood default competition if it isn't specified
//		return footballunService.findMatchupByMatchday(CURRENT_MATCDAY);
		return footballunService.findMatchupByRound("11");
	}
	
	@RequestMapping(value = "/featured-matchups", method = RequestMethod.GET)
	public List<Matchup> showFeaturedMatchups() {
		// Understood default competition if it isn't specified
		return footballunService.findMatchupByFeatured(Boolean.TRUE);
	}
}
