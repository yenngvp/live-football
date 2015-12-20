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
public class Squad extends NamedEntity implements Serializable {

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
		
	@OneToOne
	@JoinColumn
	private Formation formation;
	
	/**
	 * Columns
	 */
	
	@Column
	private String generation;
	
	@Column(name = "shirt_sponsor")
	private String shirtSponsor;
	
	@Column(name = "kit_manufacturer")
	private String kitManufacturer;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "logo")
	private String logo;
	
	
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

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public String getShirtSponsor() {
		return shirtSponsor;
	}

	public void setShirtSponsor(String shirtSponsor) {
		this.shirtSponsor = shirtSponsor;
	}

	public String getKitManufacturer() {
		return kitManufacturer;
	}

	public void setKitManufacturer(String kitManufacturer) {
		this.kitManufacturer = kitManufacturer;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public String toString() {
		if ("First Team".equals(generation)) {
			return String.format("%s", getName() == null ? getName() : team.toString());
		} else {
			return String.format("%s (%s)", getName() == null ? getName() : team.toString(),
					generation == null ? "" : generation);
		}
	}
}
