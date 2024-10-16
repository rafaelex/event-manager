package com.rgsoftwares.eventmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rgsoftwares.eventmanager.model.Event;
import com.rgsoftwares.eventmanager.repository.EventRepository;

@Service
@Transactional
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventRepository repository;

	@Override
	public List<Event> getAllEvents() {
		return repository.findAll();
	}

	@Override
	public Optional<Event> getEventById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Event create(Event event) {
		return repository.save(event);
	}

	@Override
	public Event update(Long id, Event eventUpdate) {
		Optional<Event> optionalE = repository.findById(id);
		if(optionalE.isPresent()) {
			Event event = optionalE.get();
			event.setName(eventUpdate.getName());
			event.setDescription(eventUpdate.getDescription());
			event.setDateTimeStart(eventUpdate.getDateTimeStart());
			event.setDateTimeEnd(eventUpdate.getDateTimeEnd());
			event.setLocation(eventUpdate.getLocation());
			event.setOrganizer(eventUpdate.getOrganizer());
			event.setMaximumCapacity(eventUpdate.getMaximumCapacity());
			event.setTicketsAvailable(eventUpdate.getTicketsAvailable());
			event.setPrice(eventUpdate.getPrice());
			event.setStatus(eventUpdate.getStatus());
			
			return repository.save(event);
		}
		
		return null;
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public boolean existesById(Long id) {
		return repository.existsById(id);
	}

	@Override
	public List<Event> getEventsByUserId(Long userId) {
		return repository.findByUserId(userId);
	}

}
