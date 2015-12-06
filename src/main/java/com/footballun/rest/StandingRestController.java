package com.footballun.rest;

import com.footballun.model.Standing;
import com.footballun.service.FootballunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class StandingRestController {

	// Current competition by default
	// TODO: should be getting from settings
	private static final int DEFAULT_COMPETITION = 9;
	private static final String DEFAULT_GENERATION = "First Team";

	private final FootballunService footballunService;

	//private final DataImporter dataImporter;
	
	@Autowired
	public StandingRestController(FootballunService footballunService) {
		this.footballunService = footballunService;
//		this.dataImporter = dataImporter;
	}

	@RequestMapping(value = "/standings", method = RequestMethod.GET)
	public List<Standing> showStandings() {
		
		//dataImporter.updateMatchday();
		
		// Understood default competition if it isn't specified
		return footballunService.findStandingByCompetition(DEFAULT_COMPETITION);
	}
}
