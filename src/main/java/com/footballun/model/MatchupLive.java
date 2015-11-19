/**
 * @author: yen.nt
 * @created on Nov 4, 2015
 */
package com.footballun.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author yen.nt
 *
 */
@Entity
@Table(name = "matchup_live")
public class MatchupLive extends BaseEntity {

	/**
	 * Field in relationships
	 */
	
	@ManyToOne
	@JoinColumn
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
	private Matchup matchup;
	
	@ManyToOne
	@JoinColumn(name = "matchup_register_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
	private MatchupRegister matchupRegister;
	
	@ManyToOne
	@JoinColumn(name = "matchup_detail_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
	private MatchupDetail matchupDetail;
	
	@OneToOne
	@JoinColumn
	private Event event;
	
	/**
	 * Columns
	 */
	
	@Column(name = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	@Column(name = "update_minute")
	private Short updateMinute;

	/**
	 * Getters/Setters
	 */
	
	public Matchup getMatchup() {
		return matchup;
	}

	public void setMatchup(Matchup matchup) {
		this.matchup = matchup;
	}

	public MatchupRegister getMatchupRegister() {
		return matchupRegister;
	}

	public void setMatchupRegister(MatchupRegister matchupRegister) {
		this.matchupRegister = matchupRegister;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Short getUpdateMinute() {
		return updateMinute;
	}

	public void setUpdateMinute(Short updateMinute) {
		this.updateMinute = updateMinute;
	}
	
	public MatchupDetail getMatchupDetail() {
		return matchupDetail;
	}

	public void setMatchupDetail(MatchupDetail matchupDetail) {
		this.matchupDetail = matchupDetail;
	}

	@Override
	public String toString() {
		return String.format("MatchupLive [%s %s at minute %d in %s at %s]", 
				matchupRegister == null ? "" : matchupRegister.toString(),
				event == null ? "" : event.toString(),
				updateMinute,
				matchup == null ? "" : matchup.toString(),
				timestamp == null ? "" : timestamp.toString());
	}
}
