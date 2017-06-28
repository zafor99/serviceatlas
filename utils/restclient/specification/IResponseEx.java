package com.bn.qa.webservice.restclient.specification;

import java.util.Map;

public interface IResponseEx extends IResponseBody{

	IResponseEx andReturn();
	IResponseEx thenReturn();
	
	IResponseEx body();
	IResponseEx getBody();
	
    Map<String, String> headers();
    Map<String, String> getHeaders();
    
    String header(String name);
    String getHeader(String name);
    
    Map<String, String> cookies();
    Map<String, String> getCookies();
    
    String cookie(String name);
    String getCookie(String name);
    
    String statusLine();
    String getStatusLine();
    
    int statusCode();
    int getStatusCode();
    
}
