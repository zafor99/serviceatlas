package com.bn.qa.webservice.restclient.specification;

import java.util.Date;

public interface IProxy extends IResponse{	
	//public IResponse<String> get(String url, Map<String, String> parameters) throws ConnectionException;	
	//public IResponse<String> post(String url, Map<String, String> parameters) throws ConnectionException;	
	//public IResponse<String> syncML(String url, String message) throws ConnectionException;
	//public IResponse<File> syncMLFile(String url, String message) throws ConnectionException;
	
	//public IResponse<Asset> download(String url, Map<String, String> parameters, IProgressCallback callback) throws ConnectionException;
	//public IResponse<Asset> download(String url, Map<String, String> parameters, long startByte, long endByte, IProgressCallback callback) throws ConnectionException;
	//public IResponse<Asset> download(String url, Map<String, String> parameters, long startByte, long endByte, OutputStream out, IProgressCallback callback) throws ConnectionException;
	
	public String getCookie(String key);
	public void setCookie(String key, String value, String domain, String path, Date expiryDate);
	public void clearCookies();
}