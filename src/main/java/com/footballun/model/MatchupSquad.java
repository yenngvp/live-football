package com.footballun.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 28, 2015
 */

@Entity
@Table(name = "matchup_squad")
@AssociationOverrides({
	@AssociationOverride(name = "pk.squad", 
		joinColumns = @JoinColumn(name = "squad_id")),
	@AssociationOverride(name = "pk.matchup", 
		joinColumns = @JoinColumn(name = "matchup_id")) })
public class MatchupSquad implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MatchupSquadId pk = new MatchupSquadId();
	
	@Column(name = "is_home_team")
	private boolean isHomeTeam;

//	@OneToOne
//	@JoinColumn
//	private Formation formation;
	
	public MatchupSquadId getPk() {
		return pk;
	}

	public void setPk(MatchupSquadId pk) {
		this.pk = pk;
	}

	@Transient
	public Matchup getMatchup() {
		return pk.getMatchup();
	}

	public void setMatchup(Matchup matchup) {
		this.pk.setMatchup(matchup);
	}

	@Transient
	public Squad getSquad() {
		return pk.getSquad();
	}

	public void setSquad(Squad squad) {
		this.pk.setSquad(squad);
	}

	public boolean isHomeTeam() {
		return isHomeTeam;
	}

	public void setHomeTeam(boolean isHomeTeam) {
		this.isHomeTeam = isHomeTeam;
	}
//
//	public Formation getFormation() {
//		return formation;
//	}
//
//	public void setFormation(Formation formation) {
//		this.formation = formation;
//	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		MatchupSquad other = (MatchupSquad) obj;

		if (getPk() != null ? !getPk().equals(other.getPk())
				: other.getPk() != null) {
			return false;
		}

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}
