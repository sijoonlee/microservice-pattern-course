package com.course.microservice.exception.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.course.microservice.api.response.ErrorMessage;
import com.course.microservice.exception.BadInputRequestException;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { BadInputRequestException.class, NumberFormatException.class })
	public ResponseEntity<ErrorMessage> handleBadInputRequestException(Exception ex) {
		var message = ex.getClass().getSimpleName() + " : " + ex.getMessage();
		var response = new ErrorMessage(message, "Make sure your input value is correct");

		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(value = { IOException.class })
	public ResponseEntity<ErrorMessage> handleIoException(Exception ex) {
		var message = "Server error, cause " + ex.getClass().getSimpleName() + " : " + ex.getMessage();
		var response = new ErrorMessage(message, "Try again in a few minutes");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

}
