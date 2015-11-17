/**
 * @author: yen.nt
 * @created on Nov 13, 2015
 */
package com.footballun.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yen.nt
 *
 */

@Component("livescoreTask")
public class LivescoreSchedule {

	private static Logger logger = LoggerFactory.getLogger(LivescoreSchedule.class);
	
	public void testMethod() {
		logger.warn("Call from a scheduled method ");
	}
}
