package com.bn.qa.webservice.restclient.Jerseyclient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import com.bn.qa.webservice.exceptions.ConnectionException;
import com.bn.qa.webservice.exceptions.ResponseException;
import com.bn.qa.webservice.restclient.handler.JerseyStringHandler;
import com.bn.qa.webservice.restclient.handler.StringHandler;
import com.bn.qa.webservice.restclient.httpclient.BNHttpClient;
import com.bn.qa.webservice.restclient.httpclient.HttpClientRequestExecutor;
import com.bn.qa.webservice.restclient.specification.IRequestSpecification;
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class BNJerseyClient extends JerseyClientRequest{

	Client client;
	private static BNJerseyClient instance;
	
	public BNJerseyClient(){
		client = Client.create();
	}
	
	public static BNJerseyClient getInstance() {
		if (instance == null) instance = new BNJerseyClient();
		return instance;
	}
	
	
	@Override
	public IResponse get(String path) {

		
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		
		if (parameters != null && parameters.keySet().size() > 0) {
			for (String key : parameters.keySet()) {
				if (parameters.get(key) != null) {
					queryParams.add(key, URLEncoder.encode(parameters.get(key)));
				}
			}
		}
		
		//webResource.queryParams(queryParams);
		//webResource.accept(contentType);
		
		WebResource webResource = client.resource(path);
		Builder b = webResource.accept(contentType);
		webResource = webResource.queryParams(queryParams);
		String s = webResource.get(String.class);
		
		//String s = webResource.queryParams(queryParams).get(String.class);

		JerseyClientRequestExecutor<String> request = new JerseyClientRequestExecutor<String>(this, webResource, new JerseyStringHandler());
		
		try {
			IResponse result = request.execute();
			
			System.out.println("Code:" + result.getStatusCode());
			System.out.println("Value:" + result.getValue());
			
			return result;
			
		} catch (ResponseException e) {
			e.printStackTrace();
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public IResponse post(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponse put(String path) {

		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		
		if (parameters != null && parameters.keySet().size() > 0) {
			for (String key : parameters.keySet()) {
				if (parameters.get(key) != null) {
					queryParams.add(key, URLEncoder.encode(parameters.get(key)));
				}
			}
		}
		
		//webResource.queryParams(queryParams);
		//webResource.accept(contentType);
		
		
		
		WebResource webResource = client.resource(path);
		Builder b = webResource.accept(contentType);
		
		
		Map mp = this.headers;
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        System.out.println(pairs.getKey() + " = " + pairs.getValue());
		        webResource.header(pairs.getKey().toString(),pairs.getValue().toString());

		}
		
		webResource = webResource.queryParams(queryParams);
		webResource.type(MediaType.APPLICATION_JSON);
		if(requestBody != null) {
			try {
				HttpEntity entity = null;
				if(requestBody instanceof String){
					entity = new StringEntity(requestBody.toString());
				}
				else{
					throw new Exception("Body type is supported");
				}
				webResource.put(String.class,requestBody);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//String s = webResource.put(String.class);
		//String s = webResource.queryParams(queryParams).get(String.class);
		
		JerseyClientRequestExecutor<String> request = new JerseyClientRequestExecutor<String>(this, webResource, new JerseyStringHandler());
		
		try {
			IResponse result = request.execute();
			
			System.out.println("Code:" + result.getStatusCode());
			System.out.println("Value:" + result.getValue());
			
			return result;
			
		} catch (ResponseException e) {
			e.printStackTrace();
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public IResponse delete(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResponse head(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRequestSpecification parameters(
			HashMap<String, List<Object>> parametersMap) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		
		BNJerseyClient client = new BNJerseyClient();
		
		BufferedReader fileReader = new BufferedReader(
				new FileReader("C:\\New folder\\test1.json"));
		
		String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");
	    while( ( line = fileReader.readLine() ) != null ) {
	        stringBuilder.append( line );
	        stringBuilder.append( ls );
	    }
	    String json = stringBuilder.toString();
		System.out.println(stringBuilder.toString());
		
		String url = "http://dsvdevctg02.sjc1700.bnweb.user.bn:8080/catalog/api/internal/products";
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("X-BN-Auth-Organization", "Nook");
		headers.put("X-BN-Auth-Role", "Developer");
		headers.put("X-BN-Auth-User", "Tester");
		headers.put("accept", "application/json");
		
		
		IResponse result = null;//client.get("http://10.1.168.64/RecommendationService/v1.0.0/getRecommendations?");
		result = client.headers(headers).body(json).contentType("application/json").put(url);
		
		
	}

}
