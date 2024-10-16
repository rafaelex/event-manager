package com.rgsoftwares.eventmanager.model.response;

public class EmailResponse {

	private String to;
	private String subject;
	private String body;
	private String sendDateTime;
	private boolean success;
	private int status;
	private String message;
	
	public EmailResponse() {
	}

	public EmailResponse(String to, String subject, String body, String sendDateTime, boolean success,
			int status, String message) {
		this.to = to;
		this.subject = subject;
		this.body = body;
		this.sendDateTime = sendDateTime;
		this.success = success;
		this.status = status;
		this.message = message;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSendDateTime() {
		return sendDateTime;
	}

	public void setSendDateTime(String sendDateTime) {
		this.sendDateTime = sendDateTime;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
