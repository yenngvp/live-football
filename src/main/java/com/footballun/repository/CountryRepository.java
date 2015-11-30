/**
 * @author: yen.nt
 * @created on Nov 30, 2015
 */
package com.footballun.repository;

import org.springframework.data.repository.CrudRepository;

import com.footballun.model.Country;

/**
 * @author yen.nt
 *
 */
public interface CountryRepository extends AbstractFootballunRepository<Country>, CrudRepository<Country, Integer> {

}
