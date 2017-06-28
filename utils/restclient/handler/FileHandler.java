package com.bn.qa.webservice.restclient.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import com.bn.qa.webservice.exceptions.ResponseException;
import com.bn.qa.webservice.restclient.httpclient.BNHttpClient;
import com.bn.qa.webservice.restclient.specification.IHandler;


public class FileHandler implements IHandler<File> {
	public File handleResponse(HttpResponse response) throws ResponseException {
		HttpEntity entity = response.getEntity();
		InputStream stream = null;
		String body = null;
		File file = new File(String.format(BNHttpClient.BN_ANDROID_SYNC_FILE_PATH, System.currentTimeMillis()));
		FileWriter fw = null;
		try {
			file.createNewFile();
			fw = new FileWriter(file);
			stream = entity.getContent();
			boolean gzip = false;
			if (response.containsHeader("Content-Encoding")) {
				Header encodingHeader = response.getFirstHeader("Content-Encoding");
				if (encodingHeader.getValue().contains("gzip")) {
					gzip = true;
				}
			}
			
			// Input reader
			BufferedReader input = null;
			if (gzip) {
				input = new BufferedReader(new InputStreamReader(new GZIPInputStream(stream)));
			}
			else {
				input = new BufferedReader(new InputStreamReader(stream));
			}
			
			//StringBuilder buffer = new StringBuilder();
			String line = null;
			while ((line = input.readLine()) != null) {
				//buffer.append(line + "\n");
				fw.write(line + "\n");
			}
			//body = buffer.toString();
		} catch (IOException e) {
			throw new ResponseException(e);
		} finally {
			try {
				if (entity != null) entity.consumeContent();
				if (stream != null) stream.close();
				if (fw != null) fw.close();
			} catch (Exception e) {
				// TODO we should never get here, but log in case?
			}
		}
		// TODO remove logging
		//LoggerFactory.getDefaultInstance().information(TAG, "RESPONSE: " + body);
		return file;
		//return body; 
	}

	@Override
	public File handleResponse(Object response) throws ResponseException {
		// TODO Auto-generated method stub
		return null;
	}
}