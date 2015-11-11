/**
 * 
 *
 * @author: yen.nt
 * @created on Nov 3, 2015
 */
package com.footballun.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yen.nt
 *
 */
@Entity
@Table(name = "stadium")
public class Stadium extends NamedEntity {
	
	@Column(name = "seats")
	private int seats;

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}
	
}
