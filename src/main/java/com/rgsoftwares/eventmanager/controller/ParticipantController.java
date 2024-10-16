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
import com.rgsoftwares.eventmanager.model.Participant;
import com.rgsoftwares.eventmanager.model.User;
import com.rgsoftwares.eventmanager.security.JwtRequestFilter;
import com.rgsoftwares.eventmanager.service.EventService;
import com.rgsoftwares.eventmanager.service.ParticipantService;
import com.rgsoftwares.eventmanager.service.UserDetailsServiceImpl;

import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/events")
public class ParticipantController {

	@Autowired
	private ParticipantService participantService;
	@Autowired
	private EventService eventService;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	// Returns the list of all participants of an event
	@GetMapping("/{eventId}/participants")
	public ResponseEntity<List<Participant>> getAllParticipants(@PathVariable Long eventId) {
		if (eventService.existesById(eventId)) {
			List<Participant> participants = participantService.getAllParticipants(eventId);

			return ResponseEntity.ok(participants);
		}

		throw new NoSuchElementException("Resource not found!");
	}
	
	// Returns the list of all user's participants for any event
	@GetMapping("/participants/user-participants")
	public ResponseEntity<List<Participant>> getAllUserParticipants(@RequestHeader("Authorization") String authHeader) {
		
		User user = userDetailsService.findUserByUsername(jwtRequestFilter.getUsernameFromJwt(authHeader));
		
		System.out.println(user.getUsername());
		System.out.println(user.getId());
		
		if (user != null) {
			List<Participant> participants = participantService.getAllUserParticipants(user.getId());
			System.out.println(participants.size());
			return ResponseEntity.ok(participants);
		}

		throw new NoSuchElementException("Resource not found!");
	}

	// Returns the details of an event participant by ID
	@GetMapping("/{eventId}/participants/{participantId}")
	public ResponseEntity<Participant> getParticipant(@PathVariable Long eventId, @PathVariable Long participantId) {
		Optional<Participant> participant = participantService.getParticipantByIdAndEventId(participantId, eventId);

		return participant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Create a new event
	@PostMapping("/{eventId}/participants")
	public ResponseEntity<Participant> createParticipant(@RequestHeader("Authorization") String authHeader,
			@PathVariable Long eventId, @Valid @RequestBody Participant participant) {
		User user = userDetailsService.findUserByUsername(jwtRequestFilter.getUsernameFromJwt(authHeader));

		if(user != null) {
			
			participant.setUserId(user.getId());
			Participant createdParticipant = participantService.create(eventId, participant);

			if (createdParticipant != null) {
				
				URI location;
				try {
					location = new URI("/api/events/" + eventId + "/participants/" + createdParticipant.getId());

				} catch (URISyntaxException e) {
					return ResponseEntity.internalServerError().build();
				}
				
				return ResponseEntity.created(location).body(createdParticipant);
			}

		} else {
			throw new SignatureException("Invalid token!");
		}
		
		return ResponseEntity.notFound().build();
	}

	// Update an existing event
	@PutMapping("/{eventId}/participants/{participantId}")
	public ResponseEntity<Participant> updateParticipant(@RequestHeader("Authorization") String authHeader, 
			@PathVariable Long eventId, @PathVariable Long participantId, @Valid @RequestBody Participant participant) {
		
		User user = userDetailsService.findUserByUsername(jwtRequestFilter.getUsernameFromJwt(authHeader));

		Optional<Participant> currentParticipant = participantService.getParticipantByIdAndEventId(participantId, eventId);
		if (currentParticipant.isPresent()) {
			if (currentParticipant.get().getUserId() != user.getId()) {
				throw new AccessDeniedException("User do not have permission to access this resource.");
			}
		}
		
		Participant updatedParticipant = participantService.update(participantId, participant);

		if (updatedParticipant != null) {
			return ResponseEntity.ok(updatedParticipant);
		}

		throw new NoSuchElementException("The participant does not exist!");
	}

	// Remove an existing event
	@DeleteMapping("/{eventId}/participants/{participantId}")
	public ResponseEntity<Participant> deleteParticipant(@RequestHeader("Authorization") String authHeader, 
			@PathVariable Long eventId, @PathVariable Long participantId) {
		
		if (participantService.existesById(participantId)) {
			User user = userDetailsService.findUserByUsername(jwtRequestFilter.getUsernameFromJwt(authHeader));

			Optional<Participant> currentParticipant = participantService.getParticipantByIdAndEventId(participantId, eventId);
			if (currentParticipant.isPresent()) {
				if (currentParticipant.get().getUserId() != user.getId()) {
					throw new AccessDeniedException("User do not have permission to access this resource.");
				}
			}

			participantService.delete(participantId);

			return ResponseEntity.noContent().build();
		}

		throw new NoSuchElementException("The participant does not exist!");
	}

}
