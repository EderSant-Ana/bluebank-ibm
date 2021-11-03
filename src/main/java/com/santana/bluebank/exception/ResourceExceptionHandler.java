package com.santana.bluebank.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(TransacaoException.class)
	public ResponseEntity<ErrorMessage> transacaoException(TransacaoException e, HttpServletRequest request) {

		ErrorMessage err = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), e.getLocalizedMessage());
		return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ContaNaoEncontradaException.class)
	public ResponseEntity<ErrorMessage> contaNaoEncontradaException(ContaNaoEncontradaException e, HttpServletRequest request) {

		ErrorMessage err = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), e.getLocalizedMessage());
		return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	
}
