/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 22, 2015
 */
package com.footballun.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "squad")
@PersistenceContext(type = PersistenceContextType.EXTENDED)
public class Squad extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Field in relationships
	 */
	
	@ManyToMany(mappedBy = "squads",  fetch = FetchType.EAGER)
	@JsonBackReference
	private Set<Matchup> matchups = new LinkedHashSet<Matchup>();
	
	@OneToOne
	@JoinColumn
	private Team team;
	
	@OneToOne
	@JoinColumn
	private Group group;
	
	@OneToOne
	@JoinColumn
	private Competition competition;
	
	/*
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "squad", fetch = FetchType.EAGER)
	private List<SquadMember> members;*/
		
	/**
	 * Columns
	 */
	
	@Column
	private String generation;
	
	/**
	 * Getters/Setters
	 */
	
	public Set<Matchup> getMatchups() {
		return matchups;
	}

	public void setMatchups(LinkedHashSet<Matchup> matchups) {
		this.matchups = matchups;
	}

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

	/*
	public List<SquadMember> getMembers() {
		return members;
	}

	public void setMembers(List<SquadMember> members) {
		this.members = members;
	}*/

	@Override
	public String toString() {
		if ("First Team".equals(generation)) {
			return String.format("%s", team == null ? "" : team.toString());
		} else {
			return String.format("%s (%s)", team == null ? "" : team.toString(), generation == null ? "" : generation);
		}
	}
}
