package com.bn.qa.webservice.restclient.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class Util {

	// Construct a query string
	//@SuppressWarnings("deprecation")
	public static String queryString(String url, Map<String, String> parameters) throws UnsupportedEncodingException {
		StringBuilder fullUrl = new StringBuilder(url);
		if (parameters != null && parameters.keySet().size() > 0) {
			if (!url.contains("?")) fullUrl.append("?");	// Only append ? if absent
			int count = 0;
			for (String key : parameters.keySet()) {
				if (parameters.get(key) != null) {
					fullUrl.append(URLEncoder.encode(key,"UTF-8"));					
					fullUrl.append("=");
					//fullUrl.append(URLEncoder.encode(parameters.get(key),"ISO8859-1"));
					fullUrl.append(URLEncoder.encode(parameters.get(key),"UTF-8"));
					count++;
					if (count < parameters.keySet().size()) fullUrl.append("&");
				}
			}
		}
		System.out.println(fullUrl.toString());
		return fullUrl.toString();
	}
	
	//@SuppressWarnings("deprecation")
	public static String queryString(String url, HashMap<String, List<Object>> parameters) throws UnsupportedEncodingException {
		StringBuilder fullUrl = new StringBuilder(url);
		if (parameters != null && parameters.keySet().size() > 0) {
			if (!url.contains("?")) fullUrl.append("?");	// Only append ? if absent
			int count = 0;
			for (String key : parameters.keySet()) {
				if (parameters.get(key) != null) {
					
					List<Object> list = parameters.get(key);
					for(Object o : list){
						fullUrl.append(URLEncoder.encode(key,"UTF-8"));
						//fullUrl.append(URLEncoder.encode(key,"ISO8859-1"));
						fullUrl.append("=");
						//fullUrl.append(URLEncoder.encode(o.toString(),"ISO8859-1"));
						fullUrl.append(URLEncoder.encode(o.toString(),"UTF-8"));
						fullUrl.append("&");
						count++;
					}
					//if (count < parameters.keySet().size()) fullUrl.append("&");
				}
			}
			fullUrl.deleteCharAt(fullUrl.lastIndexOf("&"));
		}
		
		
		
		System.out.println(fullUrl.toString());
		return fullUrl.toString();
	}
	
	
	// Construct an encoded form entity
	public static HttpEntity encodedFormEntity(Map<String, String> parameters) {
		HttpEntity entity = null;
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		if (parameters != null) {
			for (String key : parameters.keySet()) {
				//LoggerFactory.getDefaultInstance().information(TAG, "POST: " + key + "=" + parameters.get(key));
				pairs.add(new BasicNameValuePair(key, parameters.get(key)));
			}
			try {
				// Note - we should never get an encoding exception
				//entity = new UrlEncodedFormEntity(pairs, HTTP.ISO_8859_1);
				entity = new UrlEncodedFormEntity(pairs, HTTP.UTF_8);
			} catch (UnsupportedEncodingException e) {}
		}
		return entity;
	}
	
	// Utility (for debugging)
	@SuppressWarnings("unused")
	public static void logHttpMessage(String title, HttpMessage message) {
		//Logger logger = LoggerFactory.getDefaultInstance();
		//if (message instanceof HttpResponse) logger.information(TAG, title + " CODE " + ((HttpResponse)message).getStatusLine().getStatusCode());
		//if (message instanceof HttpUriRequest) logger.information(TAG, title + " - " + ((HttpUriRequest)message).getURI().toString()); 
		//for (Header header : message.getAllHeaders()) logger.information(TAG, title + " - " + header.getName() + ": " + header.getValue());
		
		if (message instanceof HttpResponse) System.out.println(title + " CODE " + ((HttpResponse)message).getStatusLine().getStatusCode());
		if (message instanceof HttpUriRequest) System.out.println(title + " - " + ((HttpUriRequest)message).getURI().toString()); 
		for (Header header : message.getAllHeaders()) System.out.println( title + " - " + header.getName() + ": " + header.getValue());
		
	}
	
	public static String buildWebQuery(Map<String, String> parameters) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String key = URLEncoder.encode(entry.getKey(), "UTF-8");
            //String key = URLEncoder.encode(entry.getKey(), "ISO8859-1");
            //String value = URLEncoder.encode(entry.getValue(), "ISO8859-1");
            String value = URLEncoder.encode(entry.getValue(), "UTF-8");
            sb.append(key).append("=").append(value).append("&");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }
	
	public static String buildWebQuery(String... parameters) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (String entry : parameters) {
            //String value = URLEncoder.encode(entry, "UTF-8");
            String value = URLEncoder.encode(entry, "ISO8859-1");
            sb.append(value).append("&");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }
	
	public static String readFile(String filename) throws IOException {
		
		 if(new File(filename).exists()){	
		
		   String lineSep = System.getProperty("line.separator");
		   BufferedReader br = new BufferedReader(new FileReader(filename));
		   String nextLine = "";
		   StringBuffer sb = new StringBuffer();
		   while ((nextLine = br.readLine()) != null) {
		     sb.append(nextLine);
		     //
		     // note:
		     //   BufferedReader strips the EOL character
		     //   so we add a new one!
		     //
		     sb.append(lineSep);
		   }
		   return sb.toString();
		 }
		 else
		 {
			 throw new FileNotFoundException(filename);
		 }
	}
}
