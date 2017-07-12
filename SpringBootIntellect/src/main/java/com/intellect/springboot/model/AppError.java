package com.intellect.springboot.model;

public class AppError {
	
	
	private String code;
	private String field;
	private String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public AppError(String code, String field, String message) {
		super();
		this.code = code;
		this.field = field;
		this.message = message;
	}
	@Override
	public String toString() {
		return "AppError [code=" + code + ", field=" + field + ", message=" + message + "]";
	}

}
