/**
 * @author: yen.nt
 * @created on Nov 11, 2015
 */
package com.footballun.util;

import java.util.Comparator;

import com.footballun.model.StandingBase;

/**
 * @author yen.nt
 *
 */
public class PositionComparator implements Comparator<StandingBase> {

	@Override
	public int compare(StandingBase s1, StandingBase s2) {
		// Compares point first
		if (s1.getPoint() > s2.getPoint()) {
			return -1;
		}
		if (s1.getPoint() < s2.getPoint()) {
			return 1;
		}
		
		// The same point, compare goals difference
		int goalsDiff1 = s1.getGoalsScored() - s1.getGoalsAgainst();
		int goalsDiff2 = s2.getGoalsScored() - s2.getGoalsAgainst();
		if (goalsDiff1 > goalsDiff2) {
			return -1;
		}
		else if (goalsDiff1 < goalsDiff2) {
			return 1;
		}
		
		// Still the same goals difference, do compare goals scored
		if (s1.getGoalsScored() > s2.getGoalsScored()) {
			return -1;
		}
		if (s1.getGoalsScored() < s2.getGoalsScored()) {
			return 1;
		}
		
		// The same position
		return 0;
	}

}
