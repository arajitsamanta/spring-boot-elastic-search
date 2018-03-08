package com.arajit.coding.challenge.aspect;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.arajit.coding.challenge.domain.ErrorMessage;

/**
 * Global exception handler using controller advice
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage handleException(final Exception ex) {
		return error("error",HttpStatus.INTERNAL_SERVER_ERROR, ex.fillInStackTrace().toString());
	}

	private ErrorMessage error(String errorType, final HttpStatus status, final String logRef) {
		ErrorMessage error=new ErrorMessage();
		error.setType(errorType);
		error.setCode(status.name());
		error.setTimestamp(new Date());
		error.setErrorDetails(logRef);
		return error;
	}
}
