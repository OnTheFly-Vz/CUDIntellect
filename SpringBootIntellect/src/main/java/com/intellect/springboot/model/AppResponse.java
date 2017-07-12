package com.intellect.springboot.model;

import java.util.List;

public class AppResponse {
	
	
	@Override
	public String toString() {
		return "AppResp [resMsg=" + resMsg + ", valErrors=" + valErrors + "]";
	}

	private String resMsg;
	private Long userId;
	
	
	public AppResponse(String resMsg, Long userId, List<AppError> valErrors) {
		super();
		this.resMsg = resMsg;
		this.userId = userId;
		this.valErrors = valErrors;
	}
	
	public AppResponse(){
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	private List<AppError> valErrors;

	public List<AppError> getValErrors() {
		return valErrors;
	}

	public void setValErrors(List<AppError> valErrors) {
		this.valErrors = valErrors;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	

}
