package com.footballun.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.footballun.model.Standing;
import com.footballun.service.FootballunService;
import com.footballun.util.DataImporter;


@RestController
@RequestMapping("/api/standings")
public class StandingRestController {

	private final FootballunService footballunService;

	private final DataImporter dataImporter;
	
	@Autowired
	public StandingRestController(FootballunService footballunService, DataImporter dataImporter) {
		this.footballunService = footballunService;
		this.dataImporter = dataImporter;
//		dataImporter.importExcel();
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<List<Standing>> showStandings(@RequestParam(value = "competition", required = false) Integer competitionId) {
		
		return groupStandingByCompetition((competitionId == null || competitionId == 0) ? footballunService.findShortList() 
																						: footballunService.findStandingByCompetition(competitionId));
	}
	
	private List<List<Standing>> groupStandingByCompetition(List<Standing> standings) {

		List<List<Standing>> groupedStandingsByCompetition = new ArrayList<List<Standing>>();
		List<Standing> grouped = new ArrayList<Standing>();
		Standing prev = null;


		// Group matches by competition
		for (Standing standing : standings) {
			if (prev != null
					&& !standing.getSquad().getCompetition().equals(prev.getSquad().getCompetition())) {

				// Adds the Standing to the new group
				groupedStandingsByCompetition.add(grouped);
				// Prepare to create a new group
				grouped = new ArrayList<Standing>();
			}

			grouped.add(standing);
			prev = standing;
		}

		// The last piece
		if (grouped.size() > 0) {
			groupedStandingsByCompetition.add(grouped);
			grouped = null;
		}
		
				
		return groupedStandingsByCompetition;
	}
}
