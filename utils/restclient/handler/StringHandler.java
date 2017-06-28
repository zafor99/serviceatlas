package com.bn.qa.webservice.restclient.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import com.bn.qa.webservice.exceptions.ResponseException;
import com.bn.qa.webservice.restclient.specification.IHandler;


public class StringHandler implements IHandler<String> {
	public String handleResponse(Object httpresponse) throws ResponseException {
		
		HttpResponse response = (HttpResponse) httpresponse;
		
		HttpEntity entity = response.getEntity();
		InputStream stream = null;
		String body = null;
		try {
			stream = entity.getContent();
			
			boolean gzip = false;
			if (response.containsHeader("Content-Encoding")) {
				Header encodingHeader = response.getFirstHeader("Content-Encoding");
				if (encodingHeader.getValue().contains("gzip")) gzip = true;
			}
			
			// Input reader
			BufferedReader input = null;
			if (gzip) input = new BufferedReader(new InputStreamReader(new GZIPInputStream(stream)));
			else input = new BufferedReader(new InputStreamReader(stream));
			
			StringBuilder buffer = new StringBuilder();
			String line = null;
			while ((line = input.readLine()) != null) buffer.append(line + "\n");
			body = buffer.toString();
		} catch (IOException e) {
			throw new ResponseException(e);
		} finally {
			try {
				if (entity != null) entity.consumeContent();
				if (stream != null) stream.close();
			} catch (Exception e) {
				// TODO we should never get here, but log in case?
				e.printStackTrace();
			}
		}
		// TODO remove logging
		//LoggerFactory.getDefaultInstance().information(TAG, "RESPONSE: " + body);
		return body; 
	}
}