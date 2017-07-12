package com.intellect.springboot.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class ValidationException extends Exception{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private final String fieldName;
	private final String code;
	
	public String getCode() {
		return code;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public ValidationException(String code,String field, String reason){
		super(reason);
		this.fieldName = field;
		this.code = code;
	}

}
