package com.rgsoftwares.eventmanager.model;

public class SuccessMessage {

	private int code;
	private String message;
	
	public SuccessMessage() {
	}

	public SuccessMessage(int code, String message) {
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
		return "SuccessMessage [code=" + code + ", message=" + message + "]";
	}
	
}
