package com.rgsoftwares.eventmanager.Exceptions;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.access.AccessDeniedException;  


import com.rgsoftwares.eventmanager.model.ErrorMessage;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleNotValidException(MethodArgumentNotValidException e){
		var errors = e.getAllErrors();
		
		if(errors != null && !errors.isEmpty()) {
			return ResponseEntity.badRequest().body(new ErrorMessage(400, errors.get(0).getDefaultMessage()));
		}
		
		return ResponseEntity.badRequest().body(new ErrorMessage(400, "Bad Request"));
	}
	
	@ExceptionHandler({ ConstraintViolationException.class, DataIntegrityViolationException.class })
	public ResponseEntity<ErrorMessage> handleViolations(Exception e) {
		return ResponseEntity.badRequest().body(new ErrorMessage(400, e.getMessage()));
	}
	
	@ExceptionHandler({ NoSuchElementException.class, NumberFormatException.class })
	public ResponseEntity<ErrorMessage> handleNoSuchElement(Exception e) {
		return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(new ErrorMessage(404, e.getMessage()));
	}
	
	@ExceptionHandler({ HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class })
	public ResponseEntity<ErrorMessage> handleHttpMessageNotReadable(Exception e){
		return ResponseEntity.badRequest().body(new ErrorMessage(400, e.getMessage()));
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorMessage> handleUnauthorizedException(Exception e){
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(403, e.getMessage()), HttpStatusCode.valueOf(403));
	}
	
	@ExceptionHandler({ ExpiredJwtException.class, SignatureException.class })
	public ResponseEntity<ErrorMessage> handleExpiredJwtException(Exception e){
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(401, e.getMessage()), HttpStatusCode.valueOf(401));
	}
}
