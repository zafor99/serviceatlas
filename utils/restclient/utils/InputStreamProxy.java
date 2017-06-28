package com.bn.qa.webservice.restclient.utils;

import java.io.IOException;
import java.io.InputStream;

import com.bn.qa.webservice.restclient.specification.IProgressCallback;


public class InputStreamProxy extends InputStream {
	private static final int GZIP_PROGRESS_INCREMENT = 4096;
	
	private InputStream inputStream;
	private IProgressCallback progressCallback;
	private long total;
	private int read = 0;
	
	public InputStreamProxy(InputStream inputStream, IProgressCallback progressCallback, long total) {
		this.inputStream = inputStream;
		this.progressCallback = progressCallback;
		this.total = total;
	}
	
	@Override
	public int read() throws IOException {
		read++;
		if (read > 0 && (read % GZIP_PROGRESS_INCREMENT == 0 || read == total)) this.progressCallback.onProgress(read, total);
		return this.inputStream.read();
	}	
}