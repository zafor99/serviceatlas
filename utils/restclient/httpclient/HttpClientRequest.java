package com.bn.qa.webservice.restclient.httpclient;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bn.qa.webservice.restclient.specification.IAuthenticationSpecification;
import com.bn.qa.webservice.restclient.specification.IRequestSpecification;
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.webservice.restclient.specification.IResponseSpecification;


public abstract class HttpClientRequest implements IRequestSpecification{
	

	
	protected Object requestBody = null;
	protected Object content = null;
	protected Map<String, String> cookies = new HashMap<String, String>();
	//protected Map<String, String> parameters = new HashMap<String, String>();
	protected HashMap<String, List<Object>> parameters = new HashMap<String, List<Object>>();
	protected Map<String, String> headers = new HashMap<String, String>();
	protected String contentType =  "application/xml";
	protected int port = -1;
	
	public void RestRequest(){
	}
	
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
	public IRequestSpecification parameter(String parameterName,
			String parameterValue) {
		
		addParameter(parameterName, parameterValue);
		return this;
	}
	
	@Override
	public IRequestSpecification parameters(String parameterName,
			String... parameterNameValuePairs) {
		/*Map<String,String> parameters = null;
		
		if(parameterNameValuePairs.length > 1){
			parameters = new HashMap<String,String>();
			parameters.put(parameterName, parameterNameValuePairs[0]);
			
			for(int i = 1; i <parameterNameValuePairs.length; i+=2){
				parameters.put(parameterNameValuePairs[i], parameterNameValuePairs[i + 1]);
			}	
		}*/
		
		
		if(parameterNameValuePairs.length > 1){
			addParameter(parameterName, parameterNameValuePairs[0]);
			
			for(int i = 1; i <parameterNameValuePairs.length; i+=2){
				addParameter(parameterNameValuePairs[i], parameterNameValuePairs[i + 1]);
			}	
		}
		
		return parameters(parameters);
	}

	@Override
	public IRequestSpecification parameters(Map<String, String> parametersMap) {
		//this.parameters = parametersMap;
		
		Iterator<Entry<String, String>> it = parametersMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, String> pairs = it.next();
	        
	        List<Object> list;
	        if (this.parameters.containsKey(pairs.getKey())) {
	            list = this.parameters.get(pairs.getKey());
	            list.add(pairs.getValue());
	          } else {
	            list = new ArrayList<Object>();
	            list.add(pairs.getValue());
	            this.parameters.put(pairs.getKey(), list);
	          }
	        //System.out.println(pairs.getKey() + " = " + pairs.getValue());
	    }
		return this;
	}
	
	//@Override
	public IRequestSpecification parameters(HashMap<String, List<Object>> parametersMap) {
		this.parameters = parametersMap;
		
		return this;
	}

	

	
	/*
	@Override
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
	}
	*/
	
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
	public  IRequestSpecification request() {
		return this;
	}



/*	@Override
	public IRequestSpecification spec(
			IRequestSpecification requestSpecificationToMerge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRequestSpecification<T> specification(
			IRequestSpecification requestSpecificationToMerge) {
		// TODO Auto-generated method stub
		return null;
	}*/

	
	
	
	
	
	
	@Override
	public abstract IResponse get(String url);

	@Override
	public abstract IResponse post(String url);

	@Override
	public abstract IResponse put(String path);

	@Override
	public abstract IResponse delete(String path);

	@Override
	public abstract IResponse head(String path);
	
	
	
	
	
	
	
	protected boolean isFullyQualified(String targetUri) {
	    return targetUri.contains("://");
	}
	
	protected String extractRequestParamsIfNeeded(String path) {
	    if(path.contains("?")) {
	      Map<String, String> param = null;
	      int indexOfQuestionMark = path.indexOf("?");
	      String allParamAsString = path.substring(indexOfQuestionMark+1);
	      String[] keyValueParams = allParamAsString.split("&");
	      
	      if(keyValueParams != null && keyValueParams.length >= 2){
	    	  param = new HashMap<String, String>();
	    	  
	    	  for(String vp : keyValueParams ){
	  	        String[] keyValue = vp.split("=");
	  	        if(keyValue.length != 2) {
	  	          throw new IllegalArgumentException("Illegal parameters passed to REST. Parameters was: $keyValueParams");
	  	        }
	  	        param.put(keyValue[0], keyValue[1]);
	  	      }
	    	  
	    	 parameters(param);
	      }
	      
	      path = path.substring(0, indexOfQuestionMark);
	    }
	    return path;
	  }
	
	private void addParameter(String key, Object o) {
	    List<Object> list;
	    if (this.parameters.containsKey(key)) {
	      list = this.parameters.get(key);
	      list.add(o);
	    } else {
	      list = new ArrayList<Object>();
	      list.add(o);
	      this.parameters.put(key, list);
	    }
	}


}
