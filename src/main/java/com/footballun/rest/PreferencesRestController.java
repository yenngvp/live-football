/**
 * @author: yen.nt
 * @created on Dec 14, 2015
 */
package com.footballun.rest;

import java.time.LocalDate;
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
	
	private static final Boolean CLEAR_ALL_CLIENT_LC = true;
	
	@Autowired
	public PreferencesRestController(FootballunService footballunService) {
		this.footballunService = footballunService;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<List<? extends Object>> getPrefs() {
		List<List<? extends Object>> prefs = new ArrayList<>();

		// TODO implement it
		// Loads clear client caches flags
		List<Object> clearFlags = new ArrayList<Object>();
		clearFlags.add(CLEAR_ALL_CLIENT_LC);
		prefs.add((List<? extends Object>) clearFlags);

		// Loads competitions list
		List<Competition> competitions = footballunService.findAllCompetition(LocalDate.now(), "LEAGUE");
		prefs.add((List<? extends Object>) competitions);
		
		
		
		return prefs;
	}
}
