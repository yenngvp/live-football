/**
 * @author: yen.nt
 * @created on Dec 30, 2015
 */
package com.footballun.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author yen.nt
 *
 */
@Entity
@Table(name = "scraped_data_result")
public class ScrapedDataResult extends BaseEntity {

	/**
	 * Relationships
	 */
	@ManyToOne
	@JoinColumn
	private Matchup matchup;
	
	/**
	 * Columns
	 */
	@Column(name = "team1")
	private String team1;
	
	@Column(name = "team2")
	private String team2;
	
	@Column(name = "result")
	private String result;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "matchday")
	private String matchday;
	
	@Column(name = "matchdate")
	private String matchdate;
	
	@Column(name = "competition")
	private String competition;

	@Column(name = "just_update")
	private boolean justUpdate;

	/**
	 * Getters/Setters
	 */
	
	public Matchup getMatchup() {
		return matchup;
	}

	public void setMatchup(Matchup matchup) {
		this.matchup = matchup;
	}

	public String getTeam1() {
		return team1;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public String getTeam2() {
		return team2;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMatchday() {
		return matchday;
	}

	public void setMatchday(String matchday) {
		this.matchday = matchday;
	}

	public String getMatchdate() {
		return matchdate;
	}

	public void setMatchdate(String matchdate) {
		this.matchdate = matchdate;
	}

	public String getCompetition() {
		return competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
	}

	public boolean isJustUpdate() {
		return justUpdate;
	}

	public void setJustUpdate(boolean justUpdate) {
		this.justUpdate = justUpdate;
	}
	
}
