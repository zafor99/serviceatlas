package com.bn.qa.webservice.restclient.Jerseyclient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;

import org.apache.http.message.BasicNameValuePair;

import com.bn.qa.webservice.exceptions.ResponseException;
import com.bn.qa.webservice.restclient.Response;
import com.bn.qa.webservice.restclient.httpclient.BNHttpClient;
import com.bn.qa.webservice.restclient.specification.IHandler;
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientRequestExecutor<T> {

	private BNJerseyClient bnJerseyClient;
	private WebResource webResource;
	private IHandler<T> handler;

	public JerseyClientRequestExecutor(BNJerseyClient bnJerseyClient,WebResource webResource,IHandler<T> handler){
		this.bnJerseyClient = bnJerseyClient;
		this.webResource = webResource;
		this.handler = handler;
	}
	
	public IResponse execute() throws ResponseException {
		
		
		ClientResponse response = webResource.get(ClientResponse.class);
		
		List<NewCookie> jerseyCookies =  response.getCookies();
		Map<String,String> cookies = new HashMap<String, String>();
		for(NewCookie cookie :jerseyCookies){
			cookies.put(cookie.getName(), cookie.getValue());
		}
		
		MultivaluedMap<String,String> jersyheaders =   response.getHeaders();
		Map<String,String> headers = new HashMap<String, String>();
		for (String key : jersyheaders.keySet()) {
			//LoggerFactory.getDefaultInstance().information(TAG, "POST: " + key + "=" + parameters.get(key));
			headers.put(key, jersyheaders.get(key).toString());
		}
		
		
		return new Response(response.getStatus(),response.getClientResponseStatus().getReasonPhrase(),headers,cookies,handler.handleResponse(response));
		
	}
	
}
