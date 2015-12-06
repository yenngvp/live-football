/**
 * @author: yen.nt
 * @created on Nov 18, 2015
 */
package com.footballun.model;

import com.footballun.util.LocalDateTimePersistenceConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author yen.nt
 *
 */
@Entity
@Table(name = "setting")
public class Setting extends NamedEntity {

	/**
	 * Fields in relationship
	 */
	@OneToOne
	@JoinColumn
	private Competition competition;
		
	/**
	 * Columns
	 */
	@Column(name = "override_server_time")
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	private LocalDateTime overrideServerTime;

	@Column(name = "timezone_id")
	private String timeZoneId;
	
	@Column(name = "start_countdown")
	private long startCountdown;
	
	@Column(name = "match_status_tracker_interval")
	private int matchStatusTrackerInterval;
	
	@Column(name = "default_match_duration")
	private int defaultMatchDuration;
	
	/**
	 * Getters/Setters
	 */
	public Competition getCompetition() {
		return competition;
	}


	public void setCompetition(Competition competition) {
		this.competition = competition;
	}


	public String getTimeZoneId() {
		return timeZoneId;
	}


	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}


	public LocalDateTime getOverrideServerTime() {
		return overrideServerTime;
	}


	public void setOverrideServerTime(LocalDateTime overrideServerTime) {
		this.overrideServerTime = overrideServerTime;
	}


	public long getStartCountdown() {
		return startCountdown;
	}


	public void setStartCountdown(long startCountdown) {
		this.startCountdown = startCountdown;
	}


	public int getMatchStatusTrackerInterval() {
		return matchStatusTrackerInterval;
	}


	public void setMatchStatusTrackerInterval(int matchStatusTrackerInterval) {
		this.matchStatusTrackerInterval = matchStatusTrackerInterval;
	}


	public int getDefaultMatchDuration() {
		return defaultMatchDuration;
	}


	public void setDefaultMatchDuration(int defaultMatchDuration) {
		this.defaultMatchDuration = defaultMatchDuration;
	}
}
