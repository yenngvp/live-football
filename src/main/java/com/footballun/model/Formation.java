/**
 * 
 *
 * @author: yen.nt
 * @created on Oct 29, 2015
 */
package com.footballun.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author yen.nt
 *
 */
@Entity
@Table(name = "formation")
public class Formation extends NamedEntity {

	/**
	 * Columns
	 */
	@Column(name = "position1")
	private int position1;
	
	@Column(name = "position2")
	private int position2;
	
	@Column(name = "position3")
	private int position3;
	
	@Column(name = "position4")
	private int position4;
	
	@Column(name = "position5")
	private int position5;
	
	@Column(name = "position_array_for_web")
	private String positionArrayForWeb;

	@JsonInclude
	@Transient
	private List<List<Short>> parsedPositions;
	
	/**
	 * Getters/Setters
	 */
	public int getPosition1() {
		return position1;
	}

	public void setPosition1(int position1) {
		this.position1 = position1;
	}

	public int getPosition2() {
		return position2;
	}

	public void setPosition2(int position2) {
		this.position2 = position2;
	}

	public int getPosition3() {
		return position3;
	}

	public void setPosition3(int position3) {
		this.position3 = position3;
	}

	public int getPosition4() {
		return position4;
	}

	public void setPosition4(int position4) {
		this.position4 = position4;
	}

	public int getPosition5() {
		return position5;
	}

	public void setPosition5(int position5) {
		this.position5 = position5;
	}

	public String getPositionArrayForWeb() {
		return positionArrayForWeb;
	}

	public void setPositionArrayForWeb(String positionArrayForWeb) {
		this.positionArrayForWeb = positionArrayForWeb;
	}

	public List<List<Short>> getParsedPositions() {
		
		if (positionArrayForWeb == null) {
			return null;
		}

		parsedPositions = new ArrayList<List<Short>>();

		// Example form of the array (formation 4-2-3-1): 1,2,3,4|5,6|7,8,9|10

		// Splits by group off position top-down 
		String[] parsedGrouped = positionArrayForWeb.split("[|]");
		for (String group : parsedGrouped) {
			List<Short> parsedIntPostition = new ArrayList<Short>();

			group = group.trim();
			if (group != null && group.length() > 0) {
				String[] pos = group.split("[,]");
				for (String numStr : pos) {
					parsedIntPostition.add(Short.valueOf(numStr));
				}
			}

			parsedPositions.add(parsedIntPostition);
		}


		return parsedPositions;
	}
}
