/**
 * @author: yen.nt
 * @created on Nov 10, 2015
 */
package com.footballun.repository.springdatajpa;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.footballun.model.Standing;

/**
 * @author yen.nt
 *
 */
@Transactional
public interface StandingRepository  extends StandingBaseRepository<Standing> {

	@SuppressWarnings("unchecked")
	@Override
	Standing save(Standing standing) throws DataAccessException;
}
