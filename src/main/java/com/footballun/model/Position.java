/**
 * @author: yen.nt
 * @created on Nov 4, 2015
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
}
