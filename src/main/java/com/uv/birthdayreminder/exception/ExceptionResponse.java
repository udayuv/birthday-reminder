package com.uv.birthdayreminder.exception;

import java.util.Date;

public class ExceptionResponse {
	
	private Date timestamp;
	private String message;
	private String details;
	
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	//we do not need setters here
	
	//only creating the getters
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}

	
	
}
