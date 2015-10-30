/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 28, 2015
 */
package com.footballun.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * @author yen.nt
 *
 */
@Embeddable
public class MatchupSquadId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Squad squad;
	@ManyToOne
	private Matchup matchup;
 
	public Squad getSquad() {
		return squad;
	}
	
	public void setSquad(Squad squad) {
		this.squad = squad;
	}
 
	public Matchup getMatchup() {
		return matchup;
	}
 
	public void setMatchup(Matchup matchup) {
		this.matchup = matchup;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + matchup.id;
		result = prime * result + squad.id;
		return result;
	}
 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchupSquadId other = (MatchupSquadId) obj;
		if (matchup.id != other.matchup.id)
			return false;
		if (squad.id != other.squad.id)
			return false;
		return true;
	}

}
