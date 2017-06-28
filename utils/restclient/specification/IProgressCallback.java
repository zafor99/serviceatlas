package com.bn.qa.webservice.restclient.specification;

public interface IProgressCallback {
	public void onProgress(long bytesLoaded, long bytesTotal);
}

