/**
 * @author: yen.nt
 * @created on Dec 14, 2015
 */
package com.footballun.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.footballun.model.Competition;
import com.footballun.service.FootballunService;

/**
 * @author yen.nt
 *
 */
@RestController
@RequestMapping("/api/_____/prefs")
public class PreferencesRestController {
	
	private final FootballunService footballunService;
	
	@Autowired
	public PreferencesRestController(FootballunService footballunService) {
		this.footballunService = footballunService;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<List<? extends Object>> getPrefs() {
		
		List<Competition> competitions = footballunService.findAllCompetition(2015, 2016, "LEAGUE");
		
		List<List<? extends Object>> prefs = new ArrayList<>();
		prefs.add((List<? extends Object>) competitions);
		
		return prefs;
	}
}
