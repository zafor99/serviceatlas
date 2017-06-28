package com.bn.qa.webservice.restclient;

import com.bn.qa.webservice.restclient.Jerseyclient.BNJerseyClient;
import com.bn.qa.webservice.restclient.httpclient.BNHttpClient;
import com.bn.qa.webservice.restclient.specification.IProxy;
import com.bn.qa.webservice.restclient.specification.IRequestSender;


public class ClientFactory {
	public static IRequestSender getDefaultProxy() {		
		return BNHttpClient.getInstance();
		//return BNJerseyClient.getInstance();
	}
}
