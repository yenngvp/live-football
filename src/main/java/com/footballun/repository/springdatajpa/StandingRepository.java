/**
 * @author: yen.nt
 * @created on Nov 10, 2015
 */
package com.footballun.repository.springdatajpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
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
	
	@Query(value = "select s.* from standing s inner join  squad  on  s.squad_id=squad.id where current_position <= 5 order by squad.competition_id, current_position", nativeQuery = true)
	List<Standing> findShortList() throws DataAccessException;
}
