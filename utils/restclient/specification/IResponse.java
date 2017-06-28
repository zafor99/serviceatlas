package com.bn.qa.webservice.restclient.specification;

import java.util.Map;

public interface IResponse {

	/**
	 * Get the body
	 * @return
	 */
	public abstract Object getValue();

	public abstract String getBody();

	public abstract int getStatusCode();

	public abstract String getStatusLine();

	public abstract Map<String, String> getHeaders();

	public abstract String getHeader(String name);

	public abstract Map<String, String> getCookies();

	public abstract String getCookie(String name);

}