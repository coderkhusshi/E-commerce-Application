package com.fd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handlerNotFoundException(Exception e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<String> handlerInvalidPasswordException(Exception e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler(AlreadyPresentException.class)
	public ResponseEntity<String> handlerAlreadyPresentException(Exception e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FOUND);	
	}
}
