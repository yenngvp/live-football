/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 22, 2015
 */
package com.footballun.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Table;

@Entity
@Table(name = "squad")
@PersistenceContext(type = PersistenceContextType.EXTENDED)
public class Squad extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Field in relationships
	 */
	
	@OneToOne
	@JoinColumn
	private Team team;
	
	@OneToOne
	@JoinColumn
	private Group group;
	
	@OneToOne
	@JoinColumn
	private Competition competition;
		
	/**
	 * Columns
	 */
	
	@Column
	private String generation;
	
	/**
	 * Getters/Setters
	 */
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public String getGeneration() {
		return generation;
	}

	public void setGeneration(String generation) {
		this.generation = generation;
	}

	@Override
	public String toString() {
		if ("First Team".equals(generation)) {
			return String.format("%s", team == null ? "" : team.toString());
		} else {
			return String.format("%s (%s)", team == null ? "" : team.toString(), generation == null ? "" : generation);
		}
	}
}
