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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
	
	/**
	 * Field in relationships
	 */
	
	@OneToMany(mappedBy = "matchup", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
	@OrderBy("id ASC")
	private Set<MatchupDetail> details = new LinkedHashSet<MatchupDetail>();
	

	@OneToOne
	@JoinColumn(name = "home_squad_id")
	private Squad homeSquad;

	@OneToOne
	@JoinColumn
	private Stadium stadium;
	
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
	private Short result;
	
	@Column(name = "status")
	private Short status;
	
	
	/**
	 * Getters/Setters
	 */
	@Override
	public String getName() {
		if (super.getName() != null) return super.getName();
		if (details == null || details.size() < 2) return ""; 
		Iterator<MatchupDetail> itr = details.iterator();
		StringBuilder matchName = new StringBuilder();
		while (itr.hasNext()) {
			matchName.append(itr.next().getSquad().getTeam().getName());
			if (itr.hasNext()) {
				matchName.append(" vs ");
				matchName.append(itr.next().getSquad().getTeam().getName());
			}
		}
		return matchName.toString();
	}
	
	public Set<MatchupDetail> getDetails() {
		return details;
	}

	public void setDetails(LinkedHashSet<MatchupDetail> details) {
		this.details = details;
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
	
	public Short getResult() {
		refreshResult();
		return result;
	}
	
	public void setResult(Short result) {
		this.result = result;
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
	
	/**
	 * Refreshes match result.
	 * 
	 * If it's not started, result is unknown.
	 */
	private void refreshResult() {
		if (getDetails().size() < 2) return;
		
		Iterator<MatchupDetail> itr = getDetails().iterator();
		MatchupDetail firstSquad = itr.next();
		MatchupDetail secondSquad = itr.next();
		
		if (firstSquad.getGoal() > secondSquad.getGoal()) {
			setResult((short) 1); 
		} else if (firstSquad.getGoal() < secondSquad.getGoal()) {
			setResult((short) 2); 
		} else {
			setResult((short) 0); 
		}
	}
	
	public MatchupDetail getFirstDetail() {
		Iterator<MatchupDetail> itr = getDetails().iterator();
		return itr.next();
	}
	
	public MatchupDetail getSecondDetail() {
		Iterator<MatchupDetail> itr = getDetails().iterator();
		itr.next();
		return itr.next();
	}
	
	public MatchupDetail getDetailBySquad(Squad squad) {
		return getFirstDetail().getSquad().equals(squad) ? getFirstDetail() : getSecondDetail();
	}
	
	public MatchupResult getResultBySquad(Squad squad) {
		
		if (getFirstDetail().getSquad().equals(squad)) {
			return getMatchupResult();
		} else if (getSecondDetail().getSquad().equals(squad)) {
			return getOppositeResult(getMatchupResult());
		}
		return MatchupResult.UNKNOWN;
	}
	
	private MatchupResult getOppositeResult(MatchupResult r) {
		if (r == MatchupResult.WIN) {
			return MatchupResult.LOSE;
		} else if (r == MatchupResult.LOSE) {
			return MatchupResult.WIN;
		} else if (r == MatchupResult.DRAW) {
			return MatchupResult.DRAW;
		} else {
			return MatchupResult.UNKNOWN;
		}
	}
	
	private MatchupResult getMatchupResult() {
		short r = getResult() == null ? -1 : getResult();
		switch(r) {
		case 1: 
			return MatchupResult.WIN;
		case 2: 
			return MatchupResult.LOSE;
		case 0: 
			return MatchupResult.DRAW;
		default:
			return MatchupResult.UNKNOWN;
		}
	}
	
	@Override
	public String toString() {
		return String.format("Matchup [%s]", getName());
	}
}
