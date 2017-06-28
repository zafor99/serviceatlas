package com.bn.qa.webservice.restclient.handler;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import com.bn.qa.webservice.exceptions.ResponseException;
import com.bn.qa.webservice.restclient.specification.IHandler;
import com.bn.qa.webservice.restclient.specification.IProgressCallback;
import com.bn.qa.webservice.restclient.utils.InputStreamProxy;

public class AssetHandler implements IHandler<Asset> {
	IProgressCallback progressCallback;
	OutputStream out;
	
	public AssetHandler(IProgressCallback progressCallback) { this.progressCallback = progressCallback; }
	
	public AssetHandler(OutputStream out, IProgressCallback progressCallback) { 
		this.out = out;
		this.progressCallback = progressCallback; 
	}
	
	public Asset handleResponse(HttpResponse response) throws ResponseException {
		HttpEntity entity = response.getEntity();
		InputStream stream = null;
		Asset asset = null;
		try {				
			stream = entity.getContent();
			BufferedInputStream input = null;
			asset = new Asset();
			
			// Create stream, handling gzip
			boolean gzip = false;
			if (response.containsHeader("Content-Encoding")) {
				Header encodingHeader = response.getFirstHeader("Content-Encoding");
				if (encodingHeader.getValue().contains("gzip")) gzip = true;
			}				
			if (gzip) input = new BufferedInputStream(new GZIPInputStream(new InputStreamProxy(stream, progressCallback, entity.getContentLength())));
			else input = new BufferedInputStream(stream);
			
			// Handle content range, etc.
			if (response.containsHeader("Content-Range")) {
				Header rangeHeader = response.getFirstHeader("Content-Range");
				String[] parts = rangeHeader.getValue().split("[\\s-/]");
				if (parts.length != 4) throw new ResponseException("Invalid Content-Range header: " + rangeHeader.getValue());
				asset.setTotalSize(Long.parseLong(parts[3]));
				asset.setEndByte(Long.parseLong(parts[2]));
				asset.setStartByte(Long.parseLong(parts[1]));
			} else {
				asset.setTotalSize(entity.getContentLength());
				asset.setStartByte(0);
				asset.setEndByte(asset.getTotalSize() - 1);
			}
			
			// Handle file name
			asset.setFileName("");
			if (response.containsHeader("Content-Disposition")) {
				Header dispositionHeader = response.getFirstHeader("Content-Disposition");
				String[] parts = dispositionHeader.getValue().split("=");
				if (parts.length == 2) asset.setFileName(parts[1].substring(1, parts[1].length() - 1));
			}
			
			// Extract the data as byte array and assign on asset
			int chunkLength = (int) (asset.getEndByte() - asset.getStartByte() + 1);

			if (out == null) {
				byte[] data = new byte[chunkLength];
				int read = 0, total = 0;
				boolean success = response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 206;
				while ((total < data.length) && (read = input.read(data, total, data.length - total)) != -1) {
					total += read;
					if (success && progressCallback != null) progressCallback.onProgress(total, chunkLength);
				}
				asset.setData(data);
			} else {
				int total = 0;
				boolean success = response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 206;
				byte buf[] = new byte[8 * 1024];
				
				while ((total < chunkLength)) {
					int read = input.read(buf);
					
					if (read == -1)
						break;
					else if (read == 0) {			// No bytes on the stream.  TODO: We also should have timeout logic on the stream.
						try {
							Thread.sleep(10); 
						} catch (InterruptedException e) { }
					} else {
						
						out.write(buf, 0, read);
						
						total += read;
						if (success && progressCallback != null) progressCallback.onProgress(total, chunkLength);
					}
				}
				// only call asset.setData if an error occurred so that it only ever contains 
				// the XML error response, and not the actual byte data of the epub
				if (!success) { 
					asset.setData(buf);
				}
			}
		} catch (IOException e) {
			throw new ResponseException(e);
		} finally {
			try {
				if (stream != null) stream.close();
				if (entity != null) entity.consumeContent();
			} catch (Exception e) {
				// TODO we should never get here, but should we log it just in case?
			}
		}
		return asset;
	}

	@Override
	public Asset handleResponse(Object response) throws ResponseException {
		// TODO Auto-generated method stub
		return null;
	}
}