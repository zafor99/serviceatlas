package com.bn.qa.webservice.exceptions;

public class ConnectionException extends Exception {
	public ConnectionException(String message) { super(message); }
	public ConnectionException(Exception exception) { super(exception); }
	public ConnectionException() {}
}
