package com.rgsoftwares.eventmanager.model;

import com.rgsoftwares.eventmanager.constants.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
	@NotEmpty(message = "The Username can't be null or empty")
    private String username;
	@NotEmpty(message = "The Password can't be null or empty")
    private String password;
    @Column(length = 100)
	@NotEmpty(message = "The Email can't be null or empty")
	@Pattern(regexp = Constants.EMAIL_REGEX, message = "The email must be valid")
	private String email;
	
	public User() {
	}

	public User(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
		
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
    
}
