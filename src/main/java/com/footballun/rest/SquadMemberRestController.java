/**
 * @author: yen.nt
 * @created on Nov 5, 2015
 */
package com.footballun.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.footballun.model.SquadMember;
import com.footballun.service.FootballunService;

/**
 * @author yen.nt
 *
 */
@RestController
@RequestMapping("/api/thong-tin-doi-bong")
public class SquadMemberRestController {
	
	private final FootballunService footballunService;
	
	@Autowired
	public SquadMemberRestController(FootballunService footballunService) {
		this.footballunService = footballunService;
	}
	
	@RequestMapping(value = "/{id}/danh-sach-cau-thu-va-ban-huan-luyen", method = RequestMethod.GET)
	public List<SquadMember> showSquadMembers(@PathVariable int id) {
		return footballunService.findSquadMembersBySquad(id);
	}
}
