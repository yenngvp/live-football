/**
 * @author: yen.nt
 * @created on Nov 10, 2015
 */
package com.footballun.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author yen.nt
 *
 */
@Entity
@Table(name = "standing")
public class Standing extends StandingBase {

	/**
	 * Fields in relationship
	 */

    @Transient
	@OneToOne(mappedBy = "standing", cascade = CascadeType.ALL)
	//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
	private StandingLive standingLive;

	public StandingLive getStandingLive() {
		return standingLive;
	}

	public void setStandingLive(StandingLive standingLive) {
		this.standingLive = standingLive;
	}
	
	/**
	 * Columns
	 */
	@Column(name = "entered_live")
	private boolean enteredLive;
	
	/**
	 * Getters/Setters
	 * 
	 */
	
	public boolean getEnteredLive() {
		return enteredLive;
	}

	public void setEnteredLive(boolean enteredLive) {
		this.enteredLive = enteredLive;
	}
}
