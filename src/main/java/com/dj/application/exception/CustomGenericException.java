package com.dj.application.exception;

import org.springframework.http.HttpStatus;

public class CustomGenericException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String exceptionMsg;
	
	private HttpStatus exceptionCode;

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public HttpStatus getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(HttpStatus exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public CustomGenericException(String exceptionMsg, HttpStatus exceptionCode) {
		super();
		this.exceptionMsg = exceptionMsg;
		this.exceptionCode = exceptionCode;
	}

	public CustomGenericException() {
		super();
	}
	
	

}
