/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 22, 2015
 */
package com.footballun.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "competition")
public class Competition extends NamedEntity {

	@OneToOne
	@JoinColumn(name = "host_country_id")
	private Country hostCountry;
	
	@Column(name = "type")
	private String type;
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Country getHostCountry() {
		return hostCountry;
	}

	public void setHostCountry(Country hostCountry) {
		this.hostCountry = hostCountry;
	}
	
}
