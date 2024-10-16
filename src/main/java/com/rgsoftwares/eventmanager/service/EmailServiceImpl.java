package com.rgsoftwares.eventmanager.service;

import java.time.LocalDateTime;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.rgsoftwares.eventmanager.constants.Constants;
import com.rgsoftwares.eventmanager.model.response.EmailResponse;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	
	private final JavaMailSender mailSender;
	
	public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

	@Override
	public EmailResponse sendEmail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);

		return new EmailResponse(to, subject, body, LocalDateTime.now().toString(), true, 200,
				Constants.SUCCESS_MESSAGE_EMAIL);
	}

	@Override
	public EmailResponse sendHtmlEmail(String to, String subject, String htmlBody) {
        
        try {
        	MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // true indicates multipart message
	
			helper.setTo(to);
			helper.setSubject(subject);
	        helper.setText(htmlBody, true); // true indicates HTML content
	        mailSender.send(message);
	        
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        
        
        return new EmailResponse(to, subject, htmlBody, LocalDateTime.now().toString(), true, 201, 
        		Constants.SUCCESS_MESSAGE_EMAIL);
    }

}
