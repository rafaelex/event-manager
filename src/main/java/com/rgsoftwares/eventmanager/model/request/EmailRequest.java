package com.rgsoftwares.eventmanager.model.request;

public class EmailRequest {

	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String body;
	private boolean success;
	private String responseMessage;
	
	public EmailRequest() {
	}

	public EmailRequest(String to, String cc, String bcc, String subject, String body) {
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.body = body;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
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

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	@Override
	public String toString() {
		return "EmailRequest [to=" + to + ", cc=" + cc + ", bcc=" + bcc + ", subject=" + subject + ", body=" + body
				+ "]";
	}
}
