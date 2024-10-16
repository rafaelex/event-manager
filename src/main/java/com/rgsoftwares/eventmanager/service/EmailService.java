package com.rgsoftwares.eventmanager.service;

import com.rgsoftwares.eventmanager.model.response.EmailResponse;

public interface EmailService {

	public EmailResponse sendEmail(String to, String subject, String body);
	public EmailResponse sendHtmlEmail(String to, String subject, String htmlBody);
}
