package com.santana.bluebank.exception;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ResourceExceptionHandler {
	
    @Autowired
    private MessageSource messageSource;

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

	@ExceptionHandler(ClienteJaCadastradoException.class)
	public ResponseEntity<ErrorMessage> clienteJaCadastradoException(ClienteJaCadastradoException e, HttpServletRequest request) {

		ErrorMessage err = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), e.getLocalizedMessage());
		return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> contaNaoEncontradaException(MethodArgumentNotValidException e, HttpServletRequest request) {

		String mensagem = null;
		ErrorMessage err = null;
		
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors(); 

        for(FieldError fieldError : fieldErrors) {
        	
            mensagem = messageSource.getMessage(fieldError.getCode(), fieldError.getArguments(), fieldError.getDefaultMessage(), LocaleContextHolder.getLocale());
    		err = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), mensagem);
        }
        
		return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorMessage> contaNaoEncontradaException(HttpMessageNotReadableException e, HttpServletRequest request) {

		ErrorMessage err = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), 
				e.getLocalizedMessage());
        
		return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.BAD_REQUEST);    
	}
	
}
