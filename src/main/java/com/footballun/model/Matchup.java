package com.footballun.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static final short SHORT_NUM_ZERO = (short) 0;
	private static final short SHORT_NUM_ONE = (short) 1;
	
	public enum MatchupResult {
		SQUAD1_WIN (1),
		SQUAD1_LOSE (2),
		DRAW (0),
		UNKNOWN (-1);
		
		MatchupResult(int result) {}
	}
	
	/**
	 * Field in relationships
	 */
	
	@ManyToMany(cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
	@JoinTable(name="matchup_squad",
	joinColumns=@JoinColumn(name = "matchup_id"),
	inverseJoinColumns=@JoinColumn(name = "squad_id"))
	@JsonManagedReference
	private Set<Squad> squads = new LinkedHashSet<Squad>();
	
	@OneToOne
	@JoinColumn
	private Stadium stadium;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "matchup", fetch = FetchType.EAGER)
	@OrderBy("position ASC")
	private Set<MatchupRegister> matchupRegisters = new LinkedHashSet<MatchupRegister>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "matchup", fetch = FetchType.EAGER)
	@OrderBy("updateMinute DESC, timestamp DESC")
	private Set<MatchupLive> matchupLives = new LinkedHashSet<MatchupLive>();
	
	@OneToOne
	@JoinColumn(name = "home_squad_id")
	private Squad homeSquad;
	
	/**
	 * Columns
	 */
	
	@Column(name = "start_at")
	private Date startAt;
	
	@Column(name = "end_at")
	private Date endAt;
	
	@Column(name = "matchday")
	private Integer matchday;
	
	@Column(name = "round")
	private String round;
	
	@Column(name = "featured")
	private Boolean featured;
	
	@Column(name = "squad1_goal")
	private Short squad1Goal;
	
	@Column(name = "squad2_goal")
	private Short squad2Goal;
	
	@Column(name = "result")
	private MatchupResult result;
	
	@Column(name = "status")
	private Short status;
	
	
	/**
	 * Getters/Setters
	 */
	@Override
	public String getName() {
		if (super.getName() != null) return super.getName();
		if (squads == null || squads.size() < 2) return ""; 
		Iterator<Squad> itr = squads.iterator();
		StringBuilder matchName = new StringBuilder();
		while (itr.hasNext()) {
			matchName.append(itr.next().getTeam().getName());
			if (itr.hasNext()) {
				matchName.append(" vs ");
				matchName.append(itr.next().getTeam().getName());
			}
		}
		return matchName.toString();
	}
	
	public Set<Squad> getSquads() {
		return squads;
	}

	public void setSquads(LinkedHashSet<Squad> squads) {
		this.squads = squads;
	}

	public Squad getSquad1() {
		if (squads == null || squads.size() < 2) {
			return null;
		}
		Iterator<Squad> itr = squads.iterator();
		return itr.next();
	}
	
	public Squad getSquad2() {
		if (squads == null || squads.size() < 2) {
			return null;
		}
		Iterator<Squad> itr = squads.iterator();
		itr.next(); // Strip the first element
		return itr.next();
	}
	
	public Set<MatchupRegister> getMatchupRegisters() {
		return matchupRegisters;
	}

	public void setMatchupRegisters(LinkedHashSet<MatchupRegister> matchupRegisters) {
		this.matchupRegisters = matchupRegisters;
	}

	public Set<MatchupLive> getMatchupLives() {
		return matchupLives;
	}

	public void setMatchupLives(LinkedHashSet<MatchupLive> matchupLives) {
		this.matchupLives = matchupLives;
	}

	public Squad getHomeSquad() {
		return homeSquad;
	}

	public void setHomeSquad(Squad homeSquad) {
		this.homeSquad = homeSquad;
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
		refreshResult();
		return result;
	}
	
	public void setResult(Short result) {
		switch(result) {
		case 0: this.result = MatchupResult.DRAW;
			return;
		case 1: this.result = MatchupResult.SQUAD1_WIN;
			return;
		case 2: this.result = MatchupResult.SQUAD1_LOSE;
			return;
		default: this.result = MatchupResult.UNKNOWN;
			return;
		}
	}
	
	/**
	 * @return: -1 Not started, 0: Full time, 1: First Half, 2: Second Half, 3: Half time, 4: Delayed or Cancelled
	 */
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
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

	public Short getSquad1Goal() {
		return squad1Goal;
	}

	public void setSquad1Goal(Short squad1Goal) {
		this.squad1Goal = squad1Goal == null ? 0 : squad1Goal;
	}

	public Short getSquad2Goal() {
		return squad2Goal;
	}

	public void setSquad2Goal(Short squad2Goal) {
		this.squad2Goal = squad2Goal == null ? 0 : squad2Goal;
	}

	public void recalculateResult() {

		if (getMatchupLives().size() > 0 && (getStatus() == null || getStatus() < 0)) {
			setStatus((short) 1);
		}
		
		/*
		 * Loops over all events off the match and counts for goals of both squads 
		 */
		short goal1 = SHORT_NUM_ZERO;
		short goal2 = SHORT_NUM_ZERO;
		for (MatchupLive live : getMatchupLives()) {
			
			if ("Goal".equals(live.getEvent().getName())) {
				if (live.getMatchupRegister().getSquadMember().getSquad() == getSquad1()) {
					// Found an scoring goal event for squad 1, count for one
					goal1 += SHORT_NUM_ONE;
				}
				else if (live.getMatchupRegister().getSquadMember().getSquad() == getSquad2()) {
					// Found an scoring goal event for squad 2, count for one
					goal2 += SHORT_NUM_ONE;
				}
				else /* Some errors */ ;
			}
		}
	
		setSquad1Goal(goal1);
		setSquad2Goal(goal2);
		
		refreshResult();
		
		final Logger logger = LoggerFactory.getLogger(Matchup.class);
		String log = String.format("recalculateResult: goal1:%d saved %d, goal2:%d saved %d with result=%s",
				goal1, getSquad1Goal(), goal2, getSquad2Goal(),  getResult().toString());
		logger.info(log);
	}
	
	/**
	 * Refreshes match result.
	 * 
	 * If it's not started, result is unknown.
	 */
	private void refreshResult() {
		if (getSquad1Goal() == null || getSquad2Goal() == null) {
			setResult((short) -1); // result UNKNOWN 
			return;
		}
		
		if (getSquad1Goal() > getSquad2Goal()) {
			// Squad 1 WIN
			setResult((short) 1);
		} else if (getSquad1Goal() < getSquad2Goal()) {
			// Squad 1 LOSE
			setResult((short) 2);
		} else {
			// DRAWN
			setResult((short) 0);
		}
	}
	
	@Override
	public String toString() {
		return String.format("Matchup [Begin at %s]",
				startAt != null ? startAt.toString() : "");
	}
}
