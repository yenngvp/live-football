package com.footballun.model;

import java.io.Serializable;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.footballun.model.MatchupStatus.MatchupStatusCode;
import com.footballun.util.JsonDateDeserializer;
import com.footballun.util.JsonDateSerializer;
import com.footballun.util.JsonTimeDeserializer;
import com.footballun.util.JsonTimeSerializer;
import com.footballun.util.LocalDatePersistenceConverter;
import com.footballun.util.LocalTimePersistenceConverter;

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
	@JoinColumn
	private Stadium stadium;
	
	@OneToOne
	@JoinColumn
	private Competition competition;
	
	@OneToOne
	@JoinColumn(name = "status")
	private MatchupStatus status;
	
	/**
	 * Columns
	 */
	
	@Column(name = "start_at")
	@Convert(converter = LocalDatePersistenceConverter.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    private LocalDate startAt;
	
	@Column(name = "end_at")
	@Convert(converter = LocalDatePersistenceConverter.class)
    @Transient
	private LocalDate endAt;
	
	@Column(name = "matchday")
	private Integer matchday;
	
	@Column(name = "round")
    @Transient
	private String round;
	
	@Column(name = "featured")
	private Boolean featured;
	
	@Column(name = "result")
	private Short result;
	
	@Column(name = "manual_mode")
    @Transient
	private Boolean manualMode;

	@Column(name = "kickoff")
	@Convert(converter = LocalTimePersistenceConverter.class)
	@JsonDeserialize(using = JsonTimeDeserializer.class)
    @JsonSerialize(using = JsonTimeSerializer.class)
	private LocalTime kickoff;

	/**
	 * Getters/Setters
	 */
	@Override
	public String getName() {
		if (super.getName() != null) return super.getName();
		if (details == null || details.size() < 2) return ""; 
		
		super.setName(getFirstDetail().getSquad().getTeam().getName() + " vs " + getSecondDetail().getSquad().getTeam().getName());
		return super.getName();
	}
	
	public Set<MatchupDetail> getDetails() {
		return details;
	}

	public void setDetails(LinkedHashSet<MatchupDetail> details) {
		this.details = details;
		refreshResult(); // Refreshes match result whenever match's details has changed
	}

	public void setDetail(MatchupDetail detail) {
		if (getFirstDetail().equals(detail)) {
			// Replaces the first detail width new detail
			MatchupDetail second = getSecondDetail();
			getDetails().removeAll(getDetails());
			getDetails().add(detail);
			getDetails().add(second);
		} else if (getSecondDetail().equals(detail)) {
			// Replaces the second detail width new detail
			getDetails().remove(getSecondDetail());
			getDetails().add(detail);
		}
		refreshResult(); // Refreshes match result whenever match's details has changed
	}
	
	public LocalDate getStartAt() {
		return startAt;
	}
	
	public void setStartAt(LocalDate startAt) {
		this.startAt = startAt;
	}
	
	public LocalDate getEndAt() {
		return endAt;
	}
	
	public void setEndAt(LocalDate endAt) {
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
	public MatchupStatus getStatus() {
		return status;
	}

	public void setStatus(MatchupStatus status) {
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
	public void refreshResult() {
		
		if (getDetails() == null || getDetails().size() < 2) return;
			
		if (getStatus() == null 
				|| MatchupStatusCode.NOT_BEGIN == getStatus().getCode()
				|| MatchupStatusCode.UNKNOWN == getStatus().getCode()
				|| MatchupStatusCode.POSTPOSED == getStatus().getCode()
				|| MatchupStatusCode.CANCELLED == getStatus().getCode()) {
			setResult((short) -1); // match is not started yet or cancelled
		} else if (MatchupStatusCode.FULL_TIME == getStatus().getCode()
				|| MatchupStatusCode.LIVE == getStatus().getCode()) {

			if (getFirstDetail().getGoal() > getSecondDetail().getGoal()) {
				setResult((short) 1); 
			} else if (getFirstDetail().getGoal() < getSecondDetail().getGoal()) {
				setResult((short) 2); 
			} else {
				setResult((short) 0); 
			}
		} else {
			setResult((short) -1); // Unknown match status
		}
	}
	
	public MatchupDetail getFirstDetail() {
		if (getDetails() == null || getDetails().size() < 2) return null;
		
		Iterator<MatchupDetail> itr = getDetails().iterator();
		MatchupDetail first = itr.next();
		return first; // believe this has been sorted by detail id
	}
	
	public MatchupDetail getSecondDetail() {
		if (getDetails() == null || getDetails().size() < 2) return null;
		
		Iterator<MatchupDetail> itr = getDetails().iterator();
		itr.next();
		MatchupDetail second = itr.next();
		return second; // believe this has been sorted by detail id
	}
	
	public MatchupDetail getDetailBySquad(Squad squad) {
		if (getDetails() == null || getDetails().size() < 2) return null;
		
		if (getFirstDetail().getSquad().equals(squad)) {
			return getFirstDetail();
		}
		else if (getSecondDetail().getSquad().equals(squad)) {
			return getSecondDetail();
		} else {
			return null;
		}
	}
	
	public Integer getGoalsScoredBySquad(Squad squad) {
		MatchupDetail detail = getDetailBySquad(squad);
		if (detail != null) {
			return detail.getGoal();
		} else {
			return 0;
		}
	}
	
	public Integer getGoalsAgainstBySquad(Squad squad) {
		if (getDetails() == null || getDetails().size() < 2) return 0;
		
		// Gets squad goals conceded in the match (it's equals to the apponent's goals scored)
		if (getFirstDetail().getSquad().equals(squad)) {
			return getSecondDetail().getGoal();
		} else if (getSecondDetail().getSquad().equals(squad)) {
			return getFirstDetail().getGoal();
		} else {
			return 0;
		}
	}
	
	public MatchupResult getResultBySquad(Squad squad) {
		if (getDetails() == null || getDetails().size() < 2) return MatchupResult.UNKNOWN;
		
		if (getFirstDetail().getSquad().equals(squad)) {
			return getMatchupResult();
		} else if (getSecondDetail().getSquad().equals(squad)) {
			return getOppositeResult(getMatchupResult());
		} else {
			return MatchupResult.UNKNOWN;
		}
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
	
	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public Boolean getManualMode() {
		return manualMode;
	}

	public void setManualMode(Boolean manualMode) {
		this.manualMode = manualMode;
	}

	public LocalTime getKickoff() {
		return kickoff;
	}

	public void setKickoff(LocalTime kickoff) {
		this.kickoff = kickoff;
	}

    public long getCountdown() {
        if (getStartAt() != null && getKickoff() != null) {
            LocalDateTime matchBegin = LocalDateTime.of(getStartAt(), getKickoff());
            return  matchBegin.toEpochSecond(ZoneOffset.of("+07:00"));
        }
        return 0;
    }
    
    public boolean getIsToday() {
    	if (getStartAt() == null) return false;
    	return LocalDate.now().equals(getStartAt());
    }
    
    public boolean getIsThisWeek() {
    	if (getStartAt() == null) return false;
    	LocalDate now = LocalDate.now();
    	LocalDate lastSunday = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
    	LocalDate nextModay = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
    	if (getStartAt().isAfter(lastSunday) && getStartAt().isBefore(nextModay)) {
    		return true;
    	} else {
    		return false;
    	}
    }

    public boolean getIsNextWeek() {
    	if (getStartAt() == null) return false;
    	LocalDate now = LocalDate.now();
    	LocalDate thisSunday = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    	LocalDate mondayAfterNextSunDay = thisSunday.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).with(TemporalAdjusters.next(DayOfWeek.MONDAY));
    	if (getStartAt().isAfter(thisSunday) && getStartAt().isBefore(mondayAfterNextSunDay)) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
	@Override
	public String toString() {
		return String.format("[%s]", getName());
	}
}
