package com.bn.qa.webservice.restclient.handler;

import org.apache.http.HttpResponse;

import com.bn.qa.webservice.exceptions.ResponseException;
import com.bn.qa.webservice.restclient.specification.IHandler;
import com.sun.jersey.api.client.ClientResponse;

public class JerseyStringHandler implements IHandler<String>{

	@Override
	public String handleResponse(Object jerseyResponse)
			throws ResponseException {

		ClientResponse response = (ClientResponse) jerseyResponse;
		
		String textEntity = response.getEntity(String.class);
		
		return textEntity;
	}

}
