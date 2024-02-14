package com.essentials.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date exceptionHours;
	private String message;
	private String details;
	
	
	
	public ExceptionResponse(Date exceptionHours, String message, String details) {
		this.exceptionHours = exceptionHours;
		this.message = message;
		this.details = details;
	}
	public Date getExceptionHours() {
		return exceptionHours;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	
	
}
