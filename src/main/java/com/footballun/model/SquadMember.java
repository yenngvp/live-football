/**
 * @author: yen.nt
 * @created on Nov 4, 2015
 */

package com.footballun.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "squad_member")
public class SquadMember extends BaseEntity {
	
	/**
	 * Field in relationships
	 */
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn
	private List<Hero> heros;
	
	@OneToOne
	@JoinColumn
	private HeroRole heroRole;
	
	@OneToOne
	@JoinColumn
	private Squad squad;
	
	@OneToOne
	@JoinColumn
	private Position position;

	public List<Hero> getHeros() {
		return heros;
	}

	public void setHeros(List<Hero> heros) {
		this.heros = heros;
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
	
	
}
