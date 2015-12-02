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
	
	@Column(name = "year_from")
	private int yearFrom;
	
	@Column(name = "year_to")
	private int yearTo;
	
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

	public int getYearFrom() {
		return yearFrom;
	}

	public void setYearFrom(int yearFrom) {
		this.yearFrom = yearFrom;
	}

	public int getYearTo() {
		return yearTo;
	}

	public void setYearTo(int yearTo) {
		this.yearTo = yearTo;
	}
	
}
