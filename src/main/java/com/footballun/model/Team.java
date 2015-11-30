/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 21, 2015
 */
package com.footballun.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author yen.nt
 *
 */
@Entity
@Table(name = "team")
public class Team extends NamedEntity {
	
	/**
	 * Fields in relationship
	 */
	@OneToOne
	@JoinColumn
	private Country country; 

	/**
	 * Columns
	 */
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "alias")
	private String alias;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "website")
	private String website;
	
	@Column(name = "type")
	private String type;

	/**
	 * Getters/Setters
	 */
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
