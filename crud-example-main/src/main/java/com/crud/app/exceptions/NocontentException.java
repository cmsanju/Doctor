package com.crud.app.exceptions;

public class NocontentException extends RuntimeException
{

	private static final long serialVersionUID = 1L;
	
	public NocontentException(String msg)
	{
		super(msg);
	}
}
