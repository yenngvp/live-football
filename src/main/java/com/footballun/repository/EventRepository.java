/**
 * @author: yen.nt
 * @created on Nov 12, 2015
 */
package com.footballun.repository;

import com.footballun.model.Event;

/**
 * @author yen.nt
 *
 */
public interface EventRepository {

	Event findByName(String name);
}
