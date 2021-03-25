package com.uv.birthdayreminder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //if it is not mentioned in status it will show internal server error
public class DuplicatePersonFoundException extends RuntimeException{

	public DuplicatePersonFoundException(String message){
		super(message);
	}
}
