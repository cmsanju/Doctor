package com.crud.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NocontentException.class)
	public ResponseEntity<ErrorMessage> noDataException(NocontentException exp, WebRequest request)
	{
		ErrorMessage message = new ErrorMessage();
		
		message.setDate(new Date());
		
		String url = request.getDescription(false);
		
		message.setUrl(url);
		
		message.setMessage(exp.getMessage());
		
		message.setStatusCode(HttpStatus.NO_CONTENT.value());
		
		return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(NoElementException.class)
	public ResponseEntity<ErrorMessage> noElementException(NoElementException exp, WebRequest request)
	{
		ErrorMessage message = new ErrorMessage();
		
		message.setDate(new Date());
		
		String url = request.getDescription(false);
		
		message.setUrl(url);
		
		message.setMessage(exp.getMessage());
		
		message.setStatusCode(HttpStatus.NO_CONTENT.value());
		
		return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> allExceptions(Exception exp, WebRequest request)
	{
		ErrorMessage message = new ErrorMessage();
		
		message.setDate(new Date());
		
		String url = request.getDescription(false);
		
		message.setUrl(url);
		
		message.setMessage(exp.getMessage());
		
		message.setStatusCode(HttpStatus.BAD_REQUEST.value());
		
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
}
