package com.bn.qa.webservice.restclient.Jerseyclient;

import java.util.HashMap;
import java.util.Map;

import com.bn.qa.webservice.restclient.specification.IAuthenticationSpecification;
import com.bn.qa.webservice.restclient.specification.IRequestSpecification;
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.webservice.restclient.specification.IResponseSpecification;

public abstract class JerseyClientRequest implements IRequestSpecification{

	protected Object requestBody = null;
	protected Object content = null;
	protected Map<String, String> cookies = null;
	protected Map<String, String> parameters = new HashMap<String, String>();
	protected Map<String, String> headers = null;
	protected String contentType =  "application/xml";
	protected int port = -1;
	
	
	@Override
	public abstract IResponse get(String path);

	@Override
	public abstract IResponse post(String path);

	@Override
	public abstract IResponse put(String path);

	@Override
	public abstract IResponse delete(String path);

	@Override
	public abstract IResponse head(String path);

	
	
	@Override
	public IRequestSpecification body(String body) {
		this.requestBody = body;
		return this;
	}

	@Override
	public IRequestSpecification body(byte[] body) {
		this.requestBody = body;
		return null;
	}

	@Override
	public IRequestSpecification content(String content) {
		this.content = content;
		return this;
	}

	@Override
	public IRequestSpecification content(byte[] content) {
		this.content = content;
		return null;
	}

	@Override
	public IRequestSpecification cookies(String cookieName,
			String... cookieNameValuePairs) {
		Map<String,String> cookies = null;
		
		if(cookieNameValuePairs.length > 1){
			cookies = new HashMap<String,String>();
			cookies.put(cookieName, cookieNameValuePairs[0]);
			
			for(int i = 1; i <cookieNameValuePairs.length; i+=2){
				cookies.put(cookieNameValuePairs[i], cookieNameValuePairs[i + 1]);
			}	
		}
		
		return cookies(cookies);
	}

	@Override
	public IRequestSpecification cookies(Map<String, String> cookies) {
		this.cookies  = cookies;
		
		return this;
	}

	@Override
	public IRequestSpecification cookie(String key, String value) {
		Map<String, String> cookie = new HashMap<String, String>();
		cookie.put(key, value);
		this.cookies = cookie;
		
		return this;
	}

	@Override
	public IRequestSpecification parameters(String parameterName,
			String... parameterNameValuePairs) {

		Map<String,String> parameters = null;
		
		if(parameterNameValuePairs.length > 1){
			parameters = new HashMap<String,String>();
			parameters.put(parameterName, parameterNameValuePairs[0]);
			
			for(int i = 1; i <parameterNameValuePairs.length; i+=2){
				parameters.put(parameterNameValuePairs[i], parameterNameValuePairs[i + 1]);
			}	
		}
		
		return parameters(parameters);
	}

	@Override
	public IRequestSpecification parameters(Map<String, String> parametersMap) {
		this.parameters = parametersMap;
		
		return this;
	}

	@Override
	public IRequestSpecification parameter(String parameterName,
			String parameterValue) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(parameterName, parameterValue);
		this.parameters = parameter;
		
		return this;
	}

	/*@Override
	public IRequestSpecification params(String parameterName,
			String... parameterNameValuePairs) {
		return parameters(parameterName,parameterNameValuePairs);
	}

	@Override
	public IRequestSpecification params(Map<String, String> parametersMap) {
		return parameters(parametersMap);
	}

	@Override
	public IRequestSpecification param(String parameterName,
			String parameterValue) {
		return parameter(parameterName,parameterValue);
	}*/

	@Override
	public IRequestSpecification headers(String headerName,
			String... headerNameValuePairs) {

		Map<String,String> headers = null;
		
		if(headerNameValuePairs.length > 1){
			headers = new HashMap<String,String>();
			headers.put(headerName, headerNameValuePairs[0]);
			
			for(int i = 1; i <headerNameValuePairs.length; i+=2){
				headers.put(headerNameValuePairs[i], headerNameValuePairs[i + 1]);
			}	
		}
		
		return headers(headers);
	}

	@Override
	public IRequestSpecification headers(Map<String, String> headers) {
		this.headers = headers;
		return this;
	}

	@Override
	public IRequestSpecification header(String headerName, String headerValue) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(headerName, headerValue);
		this.headers = headers;
		
		return this;
	}

	@Override
	public IRequestSpecification contentType(String contentType) {
		this.contentType = contentType;
		
		return this;
	}

	@Override
	public IAuthenticationSpecification authentication() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAuthenticationSpecification auth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRequestSpecification port(int port) {
		if(port < 1) {
		      throw new IllegalArgumentException("Port must be greater than 0");
		}
		this.port = port;
		
		return this;
	}

	@Override
	public IResponseSpecification response() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRequestSpecification and() {
		return this;
	}

	@Override
	public IRequestSpecification with() {
		return this;
	}

	@Override
	public IResponseSpecification then() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponseSpecification expect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRequestSpecification when() {
		return this;
	}

	@Override
	public IRequestSpecification given() {
		return this;
	}

	@Override
	public IRequestSpecification that() {
		return this;
	}

	@Override
	public IRequestSpecification request() {
		return this;
	}

}
