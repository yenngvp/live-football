package com.footballun.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.footballun.model.Squad;
import com.footballun.service.FootballunService;


@RestController
@RequestMapping("/api")
public class StandingRestController {

	// Current competition by default
	// TODO: should be getting from settings
	private static final int DEFAULT_COMPETITION = 9;
	private static final String DEFAULT_GENERATION = "First Team";

	private final FootballunService footballunService;

	@Autowired
	public StandingRestController(FootballunService footballunService) {
		this.footballunService = footballunService;
	}

	@RequestMapping(value = "/standings", method = RequestMethod.GET)
	public List<Squad> showStandings() {
		// Understood default competition if it isn't specified
		return footballunService.findSquadByCompetitionAndGeneration(DEFAULT_COMPETITION, DEFAULT_GENERATION);
	}
}
