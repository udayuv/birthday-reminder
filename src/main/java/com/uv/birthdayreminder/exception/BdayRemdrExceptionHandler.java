package com.uv.birthdayreminder.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice //
@RestController //to be accessed across multiple controllers
public class BdayRemdrExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExpception(Exception ex, WebRequest req){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),req.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**Customising for person not found*/
	@ExceptionHandler(PersonNotFoundException.class)
	public final ResponseEntity<Object> handlePersonNotExpception(PersonNotFoundException ex, WebRequest req){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),req.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	/**Overriding the method if arguments are not valid*/
	@Override
	public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders hearders,HttpStatus status, WebRequest req){
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Validation Failed",ex.getBindingResult().toString());
		//getBIndingResult - used toshow what went wrong
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
