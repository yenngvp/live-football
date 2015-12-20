package com.footballun.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.footballun.model.Squad;
import com.footballun.service.FootballunService;

@RestController
@RequestMapping("/api/teams")
public class SquadRestController {

	// Current competition by default
	private static final String DEFAULT_GENERATION = "First Team";
	
	private final FootballunService footballunService;
	
	@Autowired
	public SquadRestController(FootballunService footballunService) {
		this.footballunService = footballunService;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Squad> showTeams(@RequestParam(value = "competition", required = false) Integer competitionId) {
		if (competitionId == null) competitionId = 9;
		List<Squad> squads = footballunService.findSquadByCompetitionAndGeneration(competitionId, DEFAULT_GENERATION);
		return squads;
	}
}
