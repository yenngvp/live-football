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
@RequestMapping("/api/bang-xep-hang")
public class StandingRestController {

	private final FootballunService footballunService;

	@Autowired
	public StandingRestController(FootballunService footballunService) {
		this.footballunService = footballunService;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<List<Standing>> showStandings(@RequestParam(value = "giai_dau", required = false) Integer competitionId) {

		return groupStandingByCompetition((competitionId == null || competitionId == 0) ? footballunService.findShortList()
																						: footballunService.findCurrentStandingsByCompetition(competitionId));
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

    @RequestMapping(value = "/all/team/{squadId}", method = RequestMethod.GET)
    public List<Standing> showStandingsBySquad(@PathVariable("squadId") Integer squadId) {
        return  footballunService.findAllStandingsBySquad(squadId);
    }

    @RequestMapping(value = "/team/{squadId}", method = RequestMethod.GET)
    public Standing showLatestStandingBySquad(@PathVariable("squadId") Integer squadId) {
        return footballunService.findCurrentStandingBySquad(squadId);
    }
}
