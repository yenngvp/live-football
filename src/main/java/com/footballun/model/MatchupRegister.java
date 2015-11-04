/**
 * @author: yen.nt
 * @created on Nov 4, 2015
 */
package com.footballun.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "matchup_register")
public class MatchupRegister extends BaseEntity {

	/**
	 * Field in relationships
	 */
	
	@OneToOne
	@JoinColumn
	private SquadMember squadMember;
	
	@OneToOne
	@JoinColumn
	private Position position;
	
	/**
	 * Columns
	 */
	
	@Column(name = "is_lineup")
	private Boolean isLineup;

	/**
	 * Getters/Setters
	 */
	
	public SquadMember getSquadMember() {
		return squadMember;
	}

	public void setSquadMember(SquadMember squadMember) {
		this.squadMember = squadMember;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Boolean getIsLineup() {
		return isLineup;
	}

	public void setIsLineup(Boolean isLineup) {
		this.isLineup = isLineup;
	}
}
