package com.footballun.repository.springdatajpa;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.footballun.model.Event;
import com.footballun.repository.EventRepository;

public interface EventRepositoryJpa extends CrudRepository<Event, Integer>, EventRepository {

	@Override
	public Event findByName(String name) throws DataAccessException;
}
