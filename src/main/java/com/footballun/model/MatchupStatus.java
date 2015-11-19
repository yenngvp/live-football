/**
 * @author: yen.nt
 * @created on Nov 12, 2015
 */
package com.footballun.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yen.nt
 *
 */
@Entity
@Table(name = "matchup_status")
public class MatchupStatus extends NamedEntity {

	public enum MatchupStatusCode {
		UNKNOWN,
		NOT_BEGIN,
		ENTER_COUNTDOWN,
		JUST_BEGIN,
		LIVE,
		FIRST_HALF,
		HALF_TIME,
		SECOND_HALF,
		JUST_FULL_TIME,
		FULL_TIME,
		POSTPOSED,
		CANCELLED
	}
	
	@Column(name = "short_name")
	private String shortName;

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public MatchupStatusCode getCode() {
		//if (getName() == null) return MatchupStatusCode.UNKNOWN;
		switch (getName()) {
		case "Not Begin":
			return MatchupStatusCode.NOT_BEGIN;
		case "Just Begin":
			return MatchupStatusCode.JUST_BEGIN;
		case "Live":
			return MatchupStatusCode.LIVE;
		case "First Half":
			return MatchupStatusCode.FIRST_HALF;
		case "Half Time":
			return MatchupStatusCode.HALF_TIME;
		case "Second Half":
			return MatchupStatusCode.SECOND_HALF;
		case "Just Full Time":
			return MatchupStatusCode.JUST_FULL_TIME;
		case "Full Time":
			return MatchupStatusCode.FULL_TIME;
		case "Postposed":
			return MatchupStatusCode.POSTPOSED;
		case "Cancelled":
			return MatchupStatusCode.CANCELLED;
		case "Enter Countdown":
			return MatchupStatusCode.ENTER_COUNTDOWN;
		default:
			return MatchupStatusCode.UNKNOWN;
		}
	}
	
	public static String getNameByCode(MatchupStatusCode code) {
		switch (code) {
		case NOT_BEGIN:
			return "Not Begin";
		case JUST_BEGIN:
			return "Just Begin";
		case LIVE:
			return "Live";
		case FIRST_HALF:
			return "First Half"; 
		case HALF_TIME:
			return "Half Time";
		case SECOND_HALF:
			return "Second Half";
		case JUST_FULL_TIME:
			return "Just Full Time";
		case FULL_TIME:
			return "Full Time";
		case POSTPOSED:
			return "Postposed";
		case ENTER_COUNTDOWN:
			return "Enter Countdown";
		default:
			return "Cancelled";
		}
	}
}
