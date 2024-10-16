package com.rgsoftwares.eventmanager.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rgsoftwares.eventmanager.model.Event;
import com.rgsoftwares.eventmanager.model.User;
import com.rgsoftwares.eventmanager.security.JwtRequestFilter;
import com.rgsoftwares.eventmanager.service.EventService;
import com.rgsoftwares.eventmanager.service.UserDetailsServiceImpl;

import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventController {

	@Autowired
	private EventService eventService;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	// Returns the list of all events
	@GetMapping
	public ResponseEntity<List<Event>> getAllEvents(@RequestHeader("Authorization") String authHeader) {
		List<Event> events = eventService.getAllEvents();
		
		return ResponseEntity.ok(events);
	}

	// Returns the list of all users events
	@GetMapping("/user-events")
	public ResponseEntity<List<Event>> getAllUserEvents(@RequestHeader("Authorization") String authHeader) {

		User user = userDetailsService.findUserByUsername(jwtRequestFilter.getUsernameFromJwt(authHeader));

		if (user != null) {
			List<Event> events = eventService.getEventsByUserId(user.getId());
			return ResponseEntity.ok(events);
		} else {
			throw new SignatureException("Invalid token!");
		}

	}

	// Returns the details of an event by ID
	@GetMapping("/{id}")
	public ResponseEntity<Event> getEvent(@PathVariable Long id) {
		Optional<Event> event = eventService.getEventById(id);
		return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Create a new event
	@PostMapping
	public ResponseEntity<Event> createEvent(@RequestHeader("Authorization") String authHeader,
			@Valid @RequestBody Event event) {

		User user = userDetailsService.findUserByUsername(jwtRequestFilter.getUsernameFromJwt(authHeader));

		event.setUserId(user.getId());
		Event createdEvent = eventService.create(event);

		URI location;
		try {
			location = new URI("/api/events/" + createdEvent.getId());
		} catch (URISyntaxException e) {
			return ResponseEntity.internalServerError().build();
		}

		return ResponseEntity.created(location).body(createdEvent);
	}

	// Update an existing event
	@PutMapping("/{id}")
	public ResponseEntity<Event> updateEvent(@RequestHeader("Authorization") String authHeader, @PathVariable Long id,
			@Valid @RequestBody Event event) {
		User user = userDetailsService.findUserByUsername(jwtRequestFilter.getUsernameFromJwt(authHeader));

		Optional<Event> currentEvent = eventService.getEventById(id);
		if (currentEvent.isPresent()) {
			if (currentEvent.get().getUserId() != user.getId()) {
				throw new AccessDeniedException("User do not have permission to access this resource.");
			}
		}

		Event updatedEvent = eventService.update(id, event);

		if (updatedEvent != null) {
			return ResponseEntity.ok(updatedEvent);
		}

		throw new NoSuchElementException("The event does not exist!");
	}

	// Remove an existing event
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEvent(@RequestHeader("Authorization") String authHeader, @PathVariable Long id) {
		if (eventService.existesById(id)) {
			User user = userDetailsService.findUserByUsername(jwtRequestFilter.getUsernameFromJwt(authHeader));

			Optional<Event> currentEvent = eventService.getEventById(id);
			if (currentEvent.isPresent()) {
				if (currentEvent.get().getUserId() != user.getId()) {
					throw new AccessDeniedException("User do not have permission to access this resource.");
				}
			}

			eventService.delete(id);
			return ResponseEntity.noContent().build();
		}

		throw new NoSuchElementException("The event does not exist!");
	}

}
