/**
 * @author: yen.nt
 * @created on Dec 9, 2015
 */
package com.footballun.util;

import java.util.Comparator;

import com.footballun.model.Matchup;

/**
 * @author yen.nt
 *
 */
public class MatchupComparator implements Comparator<Matchup> {

	@Override
	public int compare(Matchup o1, Matchup o2) {
		if ((o1.getIsToday() && !o2.getIsToday())
				|| o1.getIsThisWeek() && !o2.getIsThisWeek()) {
			return 1;
		} if ((!o1.getIsToday() && o2.getIsToday())
				|| !o1.getIsThisWeek() && o2.getIsThisWeek()) {
			return -1;
		} else if (o1.getStartAt() != null && o1.getStartAt().isBefore(o2.getStartAt())) {
			return 1;
		} else if (o1.getStartAt() == null || o1.getStartAt().isAfter(o2.getStartAt())) {
			return -1;
		} else {
			return 0;
		}
	}

}
