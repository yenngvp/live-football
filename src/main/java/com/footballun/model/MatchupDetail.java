/**
 * @author: yen.nt
 * @created on Nov 11, 2015
 */
package com.footballun.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author yen.nt
 *
 */
@Entity
@Table(name = "matchup_detail")
public class MatchupDetail extends NamedEntity {

	/**
	 * Fields in relationship
	 */
	@ManyToOne
	@JoinColumn
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
	private Matchup matchup;
	
	@OneToOne
	@JoinColumn
	private Squad squad;
	
	@OneToOne
	@JoinColumn
	private Formation formation;
	
	
	/**
	 * Columns
	 */
	@Column(name = "is_home_squad")
	private Boolean isHomeSquad;
	
	@Column(name = "is_first_squad")
	private Boolean isFirstSquad;
	
	@Column(name = "goal")
	private Integer goal;

	
	/**
	 * Getters/Setters
	 */
	@Override
	public String getName() {
		if (super.getName() != null) return super.getName();
		
		super.setName(String.format("%s (%s)", squad.toString(), matchup.toString()));
		return super.getName();
	}
	
	public Matchup getMatchup() {
		return matchup;
	}

	public void setMatchup(Matchup matchup) {
		this.matchup = matchup;
	}

	public Squad getSquad() {
		return squad;
	}

	public void setSquad(Squad squad) {
		this.squad = squad;
	}

	public Boolean getIsHomeSquad() {
		return isHomeSquad;
	}

	public void setIsHomeSquad(Boolean isHomeSquad) {
		this.isHomeSquad = isHomeSquad;
	}

	public Boolean getIsFirstSquad() {
		return isFirstSquad;
	}

	public void setIsFirstSquad(Boolean isFirstSquad) {
		this.isFirstSquad = isFirstSquad;
	}

	public Integer getGoal() {
		return goal;
	}

	public void setGoal(Integer goal) {
		if (goal < 0) goal = 0;
		this.goal = goal;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	@Override
	public String toString() {
		return String.format("%s (%s)", squad.toString(), matchup.toString());
	}
	
}
