package com.footballun.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 28, 2015
 */
@Entity
@Table(name = "matchup")
public class Matchup extends NamedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum MatchupResult {
		WIN (1),
		LOSE (2),
		DRAW (0),
		UNKNOWN (-1);
		
		MatchupResult(int result) {}
	}
	
	@ManyToMany(cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
	@JoinTable(name="matchup_squad",
	joinColumns=@JoinColumn(name = "matchup_id"),
	inverseJoinColumns=@JoinColumn(name = "squad_id"))
	@JsonManagedReference
	private List<Squad> squads = new ArrayList<Squad>();
	
	@Column(name = "start_at")
	private Date startAt;
	
	@Column(name = "end_at")
	private Date endAt;
	
	@Column(name = "result")
	private MatchupResult result;
	
	@Column(name = "matchday")
	private Integer matchday;
	
	@Column(name = "round")
	private String round;
	
	@OneToOne
	@JoinColumn
	private Stadium stadium;
	
	@Column(name = "featured")
	private Boolean featured;
	
	public List<Squad> getSquads() {
		return squads;
	}

	public void setSquads(List<Squad> squads) {
		this.squads = squads;
	}

	public Date getStartAt() {
		return startAt;
	}
	
	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}
	
	public Date getEndAt() {
		return endAt;
	}
	
	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}
	
	public MatchupResult getResult() {
		return result;
	}
	
	public void setResult(Short result) {
		switch(result) {
		case 0: this.result = MatchupResult.DRAW;
		case 1: this.result = MatchupResult.WIN;
		case 2: this.result = MatchupResult.LOSE;
		default: this.result = MatchupResult.UNKNOWN;
		}
	}
	
	public int getMatchday() {
		return matchday;
	}
	
	public void setMatchday(Integer matchday) {
		this.matchday = matchday;
	}
	
	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public Stadium getStadium() {
		return stadium;
	}

	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}

	public Boolean getFeatured() {
		return featured;
	}

	public void setFeatured(Boolean featured) {
		this.featured = featured == null ? Boolean.FALSE : featured;
	}

	@Override
	public String toString() {
		return String.format("Matchup [Begin at %s]",
				startAt != null ? startAt.toString() : "");
	}
}
