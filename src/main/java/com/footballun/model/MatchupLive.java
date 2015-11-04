/**
 * @author: yen.nt
 * @created on Nov 4, 2015
 */
package com.footballun.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	@OneToOne
	@JoinColumn
	private Matchup matchup;
	
	@OneToOne
	@JoinColumn
	private MatchupRegister matchupRegister;
	
	@OneToOne
	@JoinColumn
	private Event event;
	
	/**
	 * Columns
	 */
	
	@Column(name = "timestamp")
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
}
