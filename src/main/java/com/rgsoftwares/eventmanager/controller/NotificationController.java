package com.rgsoftwares.eventmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rgsoftwares.eventmanager.model.request.EmailRequest;
import com.rgsoftwares.eventmanager.model.response.EmailResponse;
import com.rgsoftwares.eventmanager.service.EmailService;

@RestController
@RequestMapping("api/events")
public class NotificationController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/send-invite-email")
	@ResponseStatus(HttpStatus.OK)
	public EmailResponse sendEmail(@RequestBody EmailRequest emailRequest) {
		
		String to = emailRequest.getTo();
        String subject = emailRequest.getSubject();
        String body = emailRequest.getBody();

        return emailService.sendEmail(to, subject, body);
		
	}

	@PostMapping("/send-invite-html-email")
	@ResponseStatus(HttpStatus.CREATED)
	public EmailResponse sendHtmlEmail(@RequestBody EmailRequest emailRequest) {
		EmailResponse response = new EmailResponse();
		
		try {
			String to = emailRequest.getTo();
	        String subject = emailRequest.getSubject();
	        String body = emailRequest.getBody();

	        response = emailService.sendHtmlEmail(to, subject, body);
		} catch (Exception e) {
			e.printStackTrace();
			
			response.setTo(emailRequest.getTo());
			response.setSubject(emailRequest.getSubject());
			response.setBody(emailRequest.getBody());
			response.setSuccess(false);
			response.setStatus(500);
			response.setMessage(e.getMessage());
		}
		
		return response;
	}
	
}
