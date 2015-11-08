/**
 * @author: YenNguyen
 * @date: Nov 7, 2015
 */
package com.footballun.repository.springdatajpa;

import org.springframework.data.repository.CrudRepository;

import com.footballun.model.MatchupLive;
import com.footballun.repository.MatchupLiveRepository;

/**
 * @author YenNguyen
 *
 */
public interface MatchupLiveRepositoryJpa extends MatchupLiveRepository, CrudRepository<MatchupLive, Integer> {

}
