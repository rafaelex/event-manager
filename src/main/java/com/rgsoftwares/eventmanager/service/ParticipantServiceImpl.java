package com.rgsoftwares.eventmanager.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rgsoftwares.eventmanager.model.Event;
import com.rgsoftwares.eventmanager.model.Participant;
import com.rgsoftwares.eventmanager.repository.EventRepository;
import com.rgsoftwares.eventmanager.repository.ParticipantRepository;

@Service
@Transactional
public class ParticipantServiceImpl implements ParticipantService {
	
	@Autowired
	private ParticipantRepository repository;
	@Autowired
	private EventRepository eventRepository;

	@Override
	public List<Participant> getAllParticipants(Long eventId) {
		return repository.findByEventId(eventId);
	}

	@Override
	public List<Participant> getAllUserParticipants(Long userId) {
		return repository.findByUserId(userId);
	}
	
	@Override
	public Optional<Participant> getParticipantByIdAndEventId(Long id, Long eventId) {
		return repository.findByIdAndEventId(id, eventId);
	}

	@Override
	public Participant create(Long eventId, Participant participant) {
		Optional<Event> optionalE = eventRepository.findById(eventId);
		if(optionalE.isPresent()) {
			participant.setEvent(optionalE.get());
			participant.setRegistrationDate(LocalDateTime.now());
			
			return repository.save(participant);
		}
		
		return null;
	}

	@Override
	public Participant update(Long id, Participant participantUpdate) {
		Optional<Participant> optionalP = repository.findById(id);
		if(optionalP.isPresent()) {
			Participant participant = optionalP.get();
			participant.setName(participantUpdate.getName());
			participant.setEmail(participantUpdate.getEmail());
			participant.setIdDocumentType(participantUpdate.getIdDocumentType());
			participant.setIdDocumentNumber(participantUpdate.getIdDocumentNumber());
			participant.setTicketsPurchased(participantUpdate.getTicketsPurchased());
			participant.setConfirmedPresence(participantUpdate.isConfirmedPresence());
			
			return repository.save(participant);
		}
		return null;
	}

	@Override
	public void delete(Long participantId) {
		repository.deleteById(participantId);
	}

	@Override
	public boolean existesById(Long id) {
		return repository.existsById(id);
	}
	
}
