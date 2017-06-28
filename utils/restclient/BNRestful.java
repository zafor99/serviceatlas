package com.bn.qa.webservice.restclient;

import com.bn.qa.webservice.restclient.httpclient.BNHttpClient;
import com.bn.qa.webservice.restclient.httpclient.HttpClientRequest;
import com.bn.qa.webservice.restclient.specification.IRequestSpecification;

public class BNRestful {

	public static IRequestSpecification given(){
		//return new BNHttpClient();
		
		return (IRequestSpecification) ClientFactory.getDefaultProxy();
	}
	
	public  static IRequestSpecification getNewInstance(){
		//return (IRequestSpecification) new BNHttpClient();
		return (IRequestSpecification) ClientFactory.getDefaultProxy();
		
	}
}
