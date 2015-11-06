/**
 * @author yen.nt
 *
 */
package com.footballun.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "hero")
public class Hero extends BaseEntity {

	/**
	 * Field in relationships
	 */
	
	@OneToOne
	@JoinColumn
	private HeroStatus status;
	
	@OneToOne(mappedBy = "hero")
	@JsonBackReference
	private SquadMember squadMember;
		
	/**
	 * Columns
	 */
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "age")
	private Short age;
	
	@Column(name = "weight")
	private Float weight;
	
	@Column(name = "hight")
	private Float hight;
	
	@Column(name = "alias")
	private String alias;
	
	/**
	 * Getters/Setters
	 */
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Short getAge() {
		return age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getHight() {
		return hight;
	}

	public void setHight(Float hight) {
		this.hight = hight;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public HeroStatus getStatus() {
		return status;
	}

	public void setStatus(HeroStatus status) {
		this.status = status;
	}

	public SquadMember getSquadMember() {
		return squadMember;
	}

	public void setSquadMember(SquadMember squadMember) {
		this.squadMember = squadMember;
	}
	
	@Override
	public String toString() {
		return String.format("Hero [%d, %s %s]", id, firstName, lastName);
	}
}
