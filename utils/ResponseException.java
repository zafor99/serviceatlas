package com.bn.qa.webservice.exceptions;


public class ResponseException extends ConnectionException {

	private static final long serialVersionUID = 1L;
	public ResponseException(String message) { super(message); }
	public ResponseException(Exception exception) { super(exception); }
	public ResponseException() {}
}
