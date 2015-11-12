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
		JUST_BEGIN,
		FIRST_HALF,
		HALF_TIME,
		SECOND_HALF,
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
		case "First Half":
			return MatchupStatusCode.FIRST_HALF;
		case "Half Time":
			return MatchupStatusCode.HALF_TIME;
		case "Second Half":
			return MatchupStatusCode.SECOND_HALF;
		case "Full Time":
			return MatchupStatusCode.FULL_TIME;
		case "Postposed":
			return MatchupStatusCode.POSTPOSED;
		case "Cancelled":
			return MatchupStatusCode.CANCELLED;
		default:
			return MatchupStatusCode.UNKNOWN;
		}
	}
	
	public String getNameByCode(MatchupStatusCode code) {
		switch (code) {
		case NOT_BEGIN:
			return "Not Begin";
		case JUST_BEGIN:
			return "Just Begin";
		case FIRST_HALF:
			return "First Half"; 
		case HALF_TIME:
			return "Half Time";
		case SECOND_HALF:
			return "Second Half";
		case FULL_TIME:
			return "Full Time";
		case POSTPOSED:
			return "Postposed";
		default:
			return "Cancelled";
		}
	}
}
