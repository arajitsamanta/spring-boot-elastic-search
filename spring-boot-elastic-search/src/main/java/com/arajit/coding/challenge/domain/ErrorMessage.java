package com.arajit.coding.challenge.domain;

import java.io.Serializable;
import java.util.Date;

public class ErrorMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6976419370842956585L;
	
	String type;
	String code;
	Date timestamp;
	String errorDetails;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	
	
}
