/**
 * @author: yen.nt
 * @created on Nov 10, 2015
 */
package com.footballun.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.footballun.util.LocalDateTimePersistenceConverter;

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
	private int point;
	
	@Column(name = "played")
	private int played;
	
	@Column(name = "won")
	private int won;
	
	@Column(name = "lost")
	private int lost;
	
	@Column(name = "drawn")
	private int drawn;
	
	@Column(name = "current_position")
	private int currentPosition;

	@Column(name = "previous_position")
	private int previousPosition;
	
	@Column(name = "timestamp")
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	private LocalDateTime timestamp;
	
	@Column(name = "goals_scored")
	private int goalsScored;
	
	@Column(name = "goals_against")
	private int goalsAgainst;

	@Column(name = "allow_update")
	private boolean allowUpdate;
	
    @Column(name = "matchday")
    private int matchday;

    @Column(name = "entered_live")
	private boolean enteredLive;
	
	/**
	 * Getters/Setters
	 */
	public Squad getSquad() {
		return squad;
	}

	public void setSquad(Squad squad) {
		this.squad = squad;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getPlayed() {
		return played;
	}

	public void setPlayed(int played) {
		this.played = played;
	}

	public int getWon() {
		return won;
	}

	public void setWon(int won) {
		this.won = won;
	}

	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public int getDrawn() {
		return drawn;
	}

	public void setDrawn(int drawn) {
		this.drawn = drawn;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	public int getPreviousPosition() {
		return previousPosition;
	}

	public void setPreviousPosition(int previousPosition) {
		this.previousPosition = previousPosition;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	
	public int getGoalsScored() {
		return goalsScored;
	}

	public void setGoalsScored(int goalsScored) {
		this.goalsScored = goalsScored;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	public void resetAchievement() {
		played = 0;
		won = 0;
		lost = 0;
		drawn = 0;
		point = 0;
		goalsScored = 0;
		goalsAgainst = 0;
	}
	
	public void resetAll() {
		resetAchievement();
		previousPosition = 0;
		currentPosition = 0;
	}
	
	public boolean isAllowUpdate() {
		return allowUpdate;
	}

	public void setAllowUpdate(boolean allowUpdate) {
		this.allowUpdate = allowUpdate;
	}


    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }
    
	@Override
	public String toString() {
		return String.format("Standing [%d, %s]", currentPosition, squad.toString());
	}
	
	public boolean getEnteredLive() {
		return enteredLive;
	}

	public void setEnteredLive(boolean enteredLive) {
		this.enteredLive = enteredLive;
	}
}
