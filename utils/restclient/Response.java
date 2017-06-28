package com.bn.qa.webservice.restclient;

import java.util.Map;

import com.bn.qa.webservice.restclient.specification.IResponse;


public class Response implements IResponse {
	private int statusCode;
	private String statusLine = null;
	private Object value;
	private Map<String, String> header = null;
	private Map<String, String> cookies = null;
	
	/**
	 * Full constructor
	 * @param code
	 * @param value
	 */
	public Response(int statusCode, String statusLine,Map<String, String> header,Map<String, String> cookies, Object value) {
		this.statusCode = statusCode;
		this.statusLine = statusLine;
		this.header = header;
		this.cookies = cookies;
		this.value = value;
	}
	


	@Override
	public Object getValue() 
	{ 
		return this.value; 
	}

	@Override
	public String  getBody() {
		return this.value.toString(); 
	}
	

	@Override
	public int getStatusCode() {
		return statusCode;
	}

	
	@Override
	public String getStatusLine() {
		return statusLine;
	}

	
	@Override
	public Map<String, String> getHeaders() {
		return header;
	}


	@Override
	public String getHeader(String name) {
		return getHeaders().get(name);
	}
	

	@Override
	public Map<String, String> getCookies() {
		return cookies;
	}

	
	@Override
	public String getCookie(String name) {
		return getCookies().get(name);
	}
}