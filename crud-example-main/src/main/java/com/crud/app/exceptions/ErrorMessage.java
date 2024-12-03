package com.crud.app.exceptions;

import java.util.Date;

public class ErrorMessage {
	
	private Date date;
	
	private String url;
	
	private String message;
	
	private int statusCode;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "ErrorMessage [date=" + date + ", url=" + url + ", message=" + message + ", statusCode=" + statusCode
				+ "]";
	}
}
