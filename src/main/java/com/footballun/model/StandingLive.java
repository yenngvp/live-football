/**
 * @author: yen.nt
 * @created on Nov 10, 2015
 */
package com.footballun.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author yen.nt
 *
 */
@Entity
@Table(name = "standing_live")
public class StandingLive extends StandingBase {


	/**
	 * Fields in relationship
	 */
	
	@OneToOne
	@JoinColumn(nullable = false)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
	private Standing standing;
	

	public Standing getStanding() {
		return standing;
	}

	public void setStanding(Standing standing) {
		this.standing = standing;
	}

}
