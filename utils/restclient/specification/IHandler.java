package com.bn.qa.webservice.restclient.specification;

import org.apache.http.HttpResponse;

import com.bn.qa.webservice.exceptions.ResponseException;


public interface IHandler<T> {
	//public T handleResponse(HttpResponse response) throws ResponseException;
	public T handleResponse(Object response) throws ResponseException;
}