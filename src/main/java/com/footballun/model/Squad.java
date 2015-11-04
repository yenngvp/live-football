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
		
	@ManyToMany(mappedBy = "squads",  fetch = FetchType.EAGER)
	@JsonBackReference
	private List<Matchup> matchups = new ArrayList<Matchup>();
	
	public List<Matchup> getMatchups() {
		return matchups;
	}

	public void setMatchups(List<Matchup> matchups) {
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

	@Override
	public String toString() {
		return String.format("Team [Name: %s, Group: %s, Competition: %s, MatchupSquads Count: %d]",
				team != null ? team.getName() : "",
				group != null ? group.getName() : "",
				competition != null ? competition.getName() : "",
				matchups.size());
	}
}
