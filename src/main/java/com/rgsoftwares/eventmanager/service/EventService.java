package com.rgsoftwares.eventmanager.service;

import java.util.List;
import java.util.Optional;

import com.rgsoftwares.eventmanager.model.Event;

public interface EventService {

	public List<Event> getAllEvents();
	public Optional<Event> getEventById(Long id);
	public Event create(Event event);
	public Event update(Long id, Event event);
	public void delete(Long id); 
	public boolean existesById(Long id);
	public List<Event> getEventsByUserId(Long userId);
}
