/**
 * @author: yen.nt
 * @created on Nov 4, 2015
 */
package com.footballun.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author yen.nt
 *
 */
@Entity
@Table(name = "position")
public class Position extends NamedEntity {

	
	/**
	 * Columns
	 */
	@Column(name = "position")
	private String position;
	
	@Column(name = "area_topdown")
	private int areaTopdown;
	
	@Column(name = "area_leftright")
	private int areaLeftright;

	/**
	 * Getters/Setters
	 */
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}

	public int getAreaTopdown() {
		return areaTopdown;
	}

	public void setAreaTopdown(int areaTopdown) {
		this.areaTopdown = areaTopdown;
	}

	public int getAreaLeftright() {
		return areaLeftright;
	}

	public void setAreaLeftright(int areaLeftright) {
		this.areaLeftright = areaLeftright;
	}

	@Override
	public String getName() {
		return String.format("%s (%s %d,%d)",
				super.getName() == null ? "" : super.getName(),
				position == null ? "" : position,
				areaTopdown, areaLeftright);
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
