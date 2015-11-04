/**
 * 
 *
 * @author: yen.nt
 * @created on Nov 3, 2015
 */
package com.footballun.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author yen.nt
 *
 */
@Entity
@Table(name = "stadium")
public class Stadium extends NamedEntity {
//
//	@OneToOne(mappedBy = "stadium", fetch = FetchType.EAGER)
//	private Matchup matchup; 
//	
	@Column(name = "seats")
	private int seats;

//	public List<Matchup> getMatchups() {
//		return matchups;
//	}
//
//	public void setMatchups(List<Matchup> matchups) {
//		this.matchups = matchups;
//	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}
	
}
