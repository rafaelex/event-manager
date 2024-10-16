package com.rgsoftwares.eventmanager.model;

public class JwtResponse {

	private String token;
	private Long userId;
	private String message;

	public JwtResponse() {
	}

	public JwtResponse(String token, Long userId, String message) {
		this.token = token;
		this.userId = userId;
		this.message = message;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
