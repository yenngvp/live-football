/**
 * @author: yen.nt
 * @created on Nov 11, 2015
 */
package com.footballun.util;

import java.util.Comparator;

import com.footballun.model.Standing;

/**
 * @author yen.nt
 *
 */
public class PositionComparator implements Comparator<Standing> {

	@Override
	public int compare(Standing s1, Standing s2) {
		return PositionComparator.doCompare(s1, s2);
	}
	
	/**
	 * Sharing this comparison to out site of this comparator 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int doCompare(Standing s1, Standing s2) {
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
