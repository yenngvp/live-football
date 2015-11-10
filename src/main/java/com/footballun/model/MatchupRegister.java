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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "matchup_register")
public class MatchupRegister extends BaseEntity {

	/**
	 * Field in relationships
	 */
	
	@OneToOne
	@JoinColumn(name = "squad_member_id", nullable = false)
	private SquadMember squadMember;
	
	@OneToOne
	@JoinColumn
	private Position position;
	
	@OneToOne
	@JoinColumn(nullable = false)
//	@JsonBackReference
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
	private Matchup matchup;
	
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

	public Matchup getMatchup() {
		return matchup;
	}

	public void setMatchup(Matchup matchup) {
		this.matchup = matchup;
	}

	public Boolean getIsLineup() {
		return isLineup;
	}

	public void setIsLineup(Boolean isLineup) {
		this.isLineup = isLineup;
	}
	
	@Override
	public String toString() {
		return String.format("MatchupRegister [%s, %s, %s, linenup=%b]",
				squadMember == null ? "" : squadMember.toString(),
				matchup.toString(),
				position == null ? "" : position.toString(),
				isLineup == null ? false : isLineup);
	}
}
