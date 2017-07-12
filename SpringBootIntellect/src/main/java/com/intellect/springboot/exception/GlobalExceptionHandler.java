package com.intellect.springboot.exception;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.intellect.springboot.model.AppError;
import com.intellect.springboot.model.AppResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value=ValidationException.class)
	public ResponseEntity<AppResponse> handleValidationException(HttpServletRequest request, ValidationException ex) {
		AppResponse app = new AppResponse();
		List<AppError> appErrorList = new ArrayList<>();
		AppError appError = new AppError(ex.getCode(),ex.getFieldName(),ex.getMessage());
		appErrorList.add(appError);
		app.setValErrors(appErrorList);
		app.setResMsg("Failure");
		return new ResponseEntity<>(app,HttpStatus.PARTIAL_CONTENT);
	}

}
