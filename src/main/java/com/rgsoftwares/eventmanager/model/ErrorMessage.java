package com.rgsoftwares.eventmanager.model;

public class ErrorMessage {
	
	private int code;
	private String message;
	
	public ErrorMessage() {
	}

	public ErrorMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorMessage [code=" + code + ", message=" + message + "]";
	}
	
}
