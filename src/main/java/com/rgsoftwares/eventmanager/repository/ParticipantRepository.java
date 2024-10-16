package com.rgsoftwares.eventmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rgsoftwares.eventmanager.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long>{

	@Query("SELECT p FROM Participant p WHERE p.event.id = :eventId")
	List<Participant> findByEventId(@Param("eventId") Long eventId);
	
	List<Participant> findByUserId(@Param("userId") Long userId);
	
	@Query("SELECT p FROM Participant p WHERE p.id = :id and p.event.id = :eventId")
    Optional<Participant> findByIdAndEventId(@Param("id") Long id, @Param("eventId") Long eventId);
}
