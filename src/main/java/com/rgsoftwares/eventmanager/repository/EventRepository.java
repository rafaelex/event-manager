package com.rgsoftwares.eventmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rgsoftwares.eventmanager.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	@Query("SELECT e FROM Event e order by e.dateTimeStart asc")
	List<Event> findAll();
	
	@Query("SELECT e FROM Event e WHERE e.userId = :userId order by e.dateTimeStart asc")
	List<Event> findByUserId(@Param("userId") Long userId);
}
