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
	
	@Query(value = "select s.* from standing s inner join  squad on s.squad_id=squad.id where current_position <= 5 and allow_update order by squad.competition_id, current_position", nativeQuery = true)
	List<Standing> findShortList() throws DataAccessException;

    @Override
	@Query(value = "select st.* from standing st inner join squad sq on st.squad_id=sq.id where sq.competition_id = ?1 and allow_update order by st.current_position asc",
    nativeQuery = true)
    List<Standing> findBySquad_CompetitionIdWithMaxMatchdayOrderByCurrentPositionAsc(Integer id) throws DataAccessException;

//    @Override
//    @Query(value = "select * from standing as st inner join squad sq on st.squad_id=sq.id inner join competition c on sq.competition_id=c.id" +
//                    " where st.matchday <= c.current_matchday and st.squad_id = ?1 order by st.matchday asc",
//            nativeQuery = true)
//    List<Standing> findAllBySquadOrderByMatchdayAscCurrentPositionAsc(Integer squadId) throws DataAccessException;

    @Override
    Standing findBySquadIdAndAllowUpdateTrueOrderByCurrentPositionAsc(Integer squadId) throws DataAccessException;
}
