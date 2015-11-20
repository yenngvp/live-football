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
	@Column(name = "particular_name")
	private String particularName;
	
	@Column(name = "area_topdown")
	private int areaTopdown;
	
	@Column(name = "area_leftright")
	private int areaLeftright;

	public String getParticularName() {
		return particularName;
	}

	
	/**
	 * Getters/Setters
	 */
	public void setParticularName(String particularName) {
		this.particularName = particularName;
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
