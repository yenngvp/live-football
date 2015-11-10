/**
 * @author: yen.nt
 * @created on Nov 10, 2015
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
@Table(name = "standing")
public class Standing extends BaseEntity {

	/**
	 * Fields in relationship
	 */
	@OneToOne
	@JoinColumn
	private Squad squad;
	
	/**
	 * Columns
	 */
	@Column(name = "point")
	private Integer point;
	
	@Column(name = "played")
	private Integer played;
	
	@Column(name = "won")
	private Integer won;
	
	@Column(name = "lost")
	private Integer lost;
	
	@Column(name = "drawn")
	private Integer drawn;
	
	@Column(name = "current_position")
	private Integer currentPosition;

	@Column(name = "previous_position")
	private Integer previousPosition;
	
	/**
	 * Getters/Setters
	 */
	public Squad getSquad() {
		return squad;
	}

	public void setSquad(Squad squad) {
		this.squad = squad;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getPlayed() {
		return played;
	}

	public void setPlayed(Integer played) {
		this.played = played;
	}

	public Integer getWon() {
		return won;
	}

	public void setWon(Integer won) {
		this.won = won;
	}

	public Integer getLost() {
		return lost;
	}

	public void setLost(Integer lost) {
		this.lost = lost;
	}

	public Integer getDrawn() {
		return drawn;
	}

	public void setDrawn(Integer drawn) {
		this.drawn = drawn;
	}

	public Integer getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Integer currentPosition) {
		this.currentPosition = currentPosition;
	}

	public Integer getPreviousPosition() {
		return previousPosition;
	}

	public void setPreviousPosition(Integer previousPosition) {
		this.previousPosition = previousPosition;
	}

	@Override
	public String toString() {
		return String.format("Standing [%d, %s]", currentPosition, squad.toString());
	}
}
