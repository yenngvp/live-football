/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 22, 2015
 */
package com.footballun.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "squad")
@PersistenceContext(type = PersistenceContextType.EXTENDED)
public class Squad extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn
	private Team team;
	
	@OneToOne
	@JoinColumn
	private Group group;
	
	@OneToOne
	@JoinColumn
	private Competition competition;
		
	@Column
	private String generation;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pk.squad", fetch = FetchType.EAGER)
	private List<MatchupSquad> matchupSquads = new ArrayList<MatchupSquad>();
	
	public List<MatchupSquad> getMatchupSquads() {
		return matchupSquads;
	}

	public void setMatchupSquads(List<MatchupSquad> matchupSquads) {
		this.matchupSquads = matchupSquads;
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

	@Override
	public String toString() {
		return String.format("Team [Name: %s, Group: %s, Competition: %s, MatchupSquads Count: %d]",
				team != null ? team.getName() : "",
				group != null ? group.getName() : "",
				competition != null ? competition.getName() : "",
				matchupSquads.size());
	}
}
