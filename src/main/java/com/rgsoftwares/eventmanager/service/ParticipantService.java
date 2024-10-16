package com.rgsoftwares.eventmanager.service;

import java.util.List;
import java.util.Optional;

import com.rgsoftwares.eventmanager.model.Participant;

public interface ParticipantService {

	public List<Participant> getAllParticipants(Long eventId);
	public List<Participant> getAllUserParticipants(Long userId);
	public Optional<Participant> getParticipantByIdAndEventId(Long id, Long eventId);
	public Participant create(Long eventId, Participant participant);
	public Participant update(Long id, Participant participant);
	public void delete(Long participantId);
	public boolean existesById(Long id);
}
