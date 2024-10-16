package com.rgsoftwares.eventmanager.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rgsoftwares.eventmanager.constants.Constants;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@JsonInclude(Include.NON_NULL)
public class Participant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 150)
	@NotEmpty(message = "The Name can't be null or empty")
	private String name;
	@Column(length = 100)
	@NotEmpty(message = "The Email can't be null or empty")
	@Pattern(regexp = Constants.EMAIL_REGEX, message = "The Email can't be null or empty")
	private String email;
	@Column(length = 50)
	@NotEmpty(message = "The IdDocumentType can't be null or empty")
	@JsonProperty("id_document_type")
	private String idDocumentType;
	@Column(length = 20)
	@NotEmpty(message = "The IdDocumentNumber can't be null or empty")
	@JsonProperty("id_document_number")
	private String idDocumentNumber;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("registration_date")
	@JsonIgnore
	@CreationTimestamp
	private LocalDateTime registrationDate;
	@JsonIgnore
	private Long userId;
	@JsonProperty("confirmed_presence")
	private boolean confirmedPresence;
	@JsonProperty("tickets_purchased")
	private int ticketsPurchased;

	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;

	public Participant() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdDocumentType() {
		return idDocumentType;
	}

	public void setIdDocumentType(String idDocumentType) {
		this.idDocumentType = idDocumentType;
	}

	public String getIdDocumentNumber() {
		return idDocumentNumber;
	}

	public void setIdDocumentNumber(String idDocumentNumber) {
		this.idDocumentNumber = idDocumentNumber;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public boolean isConfirmedPresence() {
		return confirmedPresence;
	}

	public void setConfirmedPresence(boolean confirmedPresence) {
		this.confirmedPresence = confirmedPresence;
	}

	public int getTicketsPurchased() {
		return ticketsPurchased;
	}

	public void setTicketsPurchased(int ticketsPurchased) {
		this.ticketsPurchased = ticketsPurchased;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Participant [id=" + id + ", name=" + name + ", email=" + email + ", idDocumentType=" + idDocumentType
				+ ", idDocumentNumber=" + idDocumentNumber + ", registrationDate=" + registrationDate
				+ ", confirmedPresence=" + confirmedPresence + ", ticketsPurchased=" + ticketsPurchased + ", event="
				+ event + "]";
	}

}
