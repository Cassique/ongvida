package com.ongvida.domain.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private BusinessException(String message){
	super(message);
	
	}
}
