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
		if (s1.getPoint() > s2.getPoint()) {
			return -1;
		}
		if (s1.getPoint() < s2.getPoint()) {
			return 1;
		}
		return 0;
	}

}
