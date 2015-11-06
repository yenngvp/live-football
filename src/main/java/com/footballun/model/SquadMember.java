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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "squad_member")
public class SquadMember extends BaseEntity {
	
	/**
	 * Field in relationships
	 */
	
	@OneToOne
	@JoinColumn
	@JsonManagedReference
	private Hero hero;
	
	@OneToOne
	@JoinColumn(name = "hero_role_id")
	private HeroRole heroRole;
	
	@OneToOne
	@JoinColumn
	private Squad squad;
	
	@OneToOne
	@JoinColumn
	private Position position;

	/**
	 * Columns
	 */
	@Column(name = "shirt_number")
	private Integer shirtNumber;
	
	/**
	 * Getters/Setters
	 */
	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public HeroRole getHeroRole() {
		return heroRole;
	}

	public void setHeroRole(HeroRole heroRole) {
		this.heroRole = heroRole;
	}

	public Squad getSquad() {
		return squad;
	}

	public void setSquad(Squad squad) {
		this.squad = squad;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Integer getShirtNumber() {
		return shirtNumber;
	}

	public void setShirtNumber(Integer shirtNumber) {
		this.shirtNumber = shirtNumber;
	}

	@Override
	public String toString() {
		return String.format("SquadMember [%d, %s, Role: %s, Position: %s, %s]",
				id,
				hero == null ? "" : hero.toString(),
				heroRole == null ? "" : heroRole.getName(),
				position == null ? "" : position.getName(),
				squad == null ? "" : squad.toString());
	}
}
