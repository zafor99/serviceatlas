package com.bn.qa.webservice.restclient.httpclient;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.protocol.HTTP;

import com.bn.qa.webservice.exceptions.ConnectionException;
import com.bn.qa.webservice.exceptions.ResponseException;
import com.bn.qa.webservice.restclient.Response;
import com.bn.qa.webservice.restclient.specification.IHandler;
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.ctc.wstx.util.URLUtil;

public class HttpClientRequestExecutor<T> {

	private BNHttpClient bnHttpClient;
	private HttpUriRequest request;
	private IHandler<T> handler;

	public HttpClientRequestExecutor(BNHttpClient bnHttpClient, HttpUriRequest request, IHandler<T> handler) { 
		this.bnHttpClient = bnHttpClient;
		this.request = request;
		this.handler = handler;
	}

	public IResponse execute() throws ConnectionException, ResponseException {
		try {	
			// Client API specific headers
			request.setHeader("User-Agent", BNHttpClient.BN_USER_AGENT);
			request.setHeader("Referer", BNHttpClient.BN_REFERRER_NAME);
			
			Map<String,String> cookies = new HashMap<String, String>();
			
			Object[] allcookies = this.bnHttpClient.client.getCookieStore().getCookies().toArray();
			for (Object obj : allcookies) {
				Cookie cookie = (Cookie) obj;
				System.out.println("COOKIE: " 
					+ cookie.getName() + " " 
					+ cookie.getValue() + " " 
					+ cookie.getDomain() + " " 
					+ cookie.getPath() + " " 
					+ cookie.getExpiryDate() 
				);	
				
				cookies.put(cookie.getName(), cookie.getValue());
			}	
			
			// Execute request
			HttpResponse response = null;
			
			response = this.bnHttpClient.client.execute(request);
			
			Map<String,String> headers = new HashMap<String, String>();
			Header[] allHeader = response.getAllHeaders();
			for(Header header :allHeader){
				System.out.println("HEADER: " 
						+ header.getName() + " "
						+ header.getValue()
						);
				headers.put( header.getName(),header.getValue());
			}
	
			//return new Response(response.getStatusLine().getStatusCode(),response.getStatusLine().getReasonPhrase(),headers,cookies, handler.handleResponse(response));
			return new Response(response.getStatusLine().getStatusCode(),response.getStatusLine().getReasonPhrase(),headers,cookies, handler.handleResponse(response));
		
		} catch (ClientProtocolException e) {
			throw new ConnectionException(e);
		} catch (IOException e) {
			throw new ResponseException(e);
		} 
	}
}
