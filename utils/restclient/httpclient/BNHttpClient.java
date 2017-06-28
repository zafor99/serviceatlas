package com.bn.qa.webservice.restclient.httpclient;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

import org.apache.http.HttpVersion;

import org.apache.http.client.CookieStore;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import com.bn.qa.webservice.exceptions.ConnectionException;
import com.bn.qa.webservice.exceptions.ResponseException;
import com.bn.qa.webservice.restclient.ClientFactory;
import com.bn.qa.webservice.restclient.handler.StringHandler;
import com.bn.qa.webservice.restclient.specification.IRequestSpecification;
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.bn.qa.webservice.restclient.utils.Util;



public class BNHttpClient extends HttpClientRequest  {

	private static final int CONCURRENCY = 10;
	private static final int CONNECTION_TIMEOUT = 30000;
	private static final int SOCKET_TIMEOUT = 60000;
	private static BNHttpClient instance;
	
	public static final String BN_API_VERSION_MAJOR = "1";
	public static final String BN_API_VERSION_MINOR = "3";
	public static String BN_PLATFORM_ID = "";
	public static final String BN_APP_TYPE = "eReader";
	public static final String BN_USER_AGENT = String.format("BN Client API Java/%s.%s.0.0 (%s;%s;%s;%s)", BN_API_VERSION_MAJOR, BN_API_VERSION_MINOR, BN_APP_TYPE, "Generic", "4", BN_PLATFORM_ID);
	public static final String BN_REFERRER_NAME = "bnereader.barnesandnoble.com";
	public static final String BN_ANDROID_SYNC_FILE_NAME = "syncfile-%s.xml";
	public static final String BN_ANDROID_SYNC_FILE_PATH = BN_ANDROID_SYNC_FILE_NAME;
	
	DefaultHttpClient client;
	
	
	public BNHttpClient(){
		
		HttpParams params = new BasicHttpParams();
		ConnPerRoute connPerRoute = new ConnPerRouteBean(CONCURRENCY);
		ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);
		ConnManagerParams.setMaxTotalConnections(params, CONCURRENCY);
		HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT);
		
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		
		//SSL Socket Authentication added for secure connection string FA 7/11/2012
		HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
		SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
		socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
		//FA 7/11/2012
		
		schemeRegistry.register(new Scheme("https", socketFactory, 443));

		
		ClientConnectionManager connectionManager = new ThreadSafeClientConnManager(params, schemeRegistry);
		this.client = new DefaultHttpClient(connectionManager, params);
		
		/*
		// Enable gzip acceptance
		this.client.addRequestInterceptor(new HttpRequestInterceptor() {
			public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
				if (!request.containsHeader("Accept-Encoding")) request.addHeader("Accept-Encoding", "gzip");
			}
		});
		*/
		
		
		// Do not automatically follow redirects
		this.client.getParams().setBooleanParameter(ClientPNames.HANDLE_REDIRECTS, false);
	}
	
	
	public static BNHttpClient getInstance() {
		if (instance == null) instance = new BNHttpClient();
		return instance;
	}
	
	
	@Override
	public IResponse get(String url) {
		
		System.out.println("URL: " + url);
		
		boolean isFullyQualifiedUri = isFullyQualified(url);
	      
	    url = extractRequestParamsIfNeeded(url);
	    	

		HttpGet get = null;
		try {
			get = new HttpGet(Util.queryString(url, parameters));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		
	/*	
	 	HttpGet get = new HttpGet(url);
	 	HttpParams params = new BasicHttpParams();
		if (parameters != null && parameters.keySet().size() > 0) {
			for (String key : parameters.keySet()) {
				if (parameters.get(key) != null) {
					params.setParameter(key, URLEncoder.encode(parameters.get(key)));
				}
			}
		}
		get.setParams(params);
	*/
		
	/*
		HttpParams params = new BasicHttpParams();
		if (parameters != null && parameters.keySet().size() > 0) {
			for (String key : parameters.keySet()) {
				if (parameters.get(key) != null) {
					params.setParameter(key, URLEncoder.encode(parameters.get(key)));
				}
			}
		}
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUseExpectContinue(params, false);
        HttpParams defaultParameters = null;
        defaultParameters = params;
        
        HttpGet get = new HttpGet(url);
        get.setParams(defaultParameters);
    */
		//set content type
		//get.setHeader("Content-type", this.contentType + "; charset=ISO-8859-1");
		//get.setHeader("Content-type", this.contentType);
		//set headers
		//System.out.println("************new jar*************");
		Map mp = this.headers;
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        System.out.println(pairs.getKey() + " = " + pairs.getValue());
		        get.setHeader(pairs.getKey().toString(),pairs.getValue().toString());
		        
		}

		HttpClientRequestExecutor<String> request = new HttpClientRequestExecutor<String>(this, get, new StringHandler());
		
		try {
			IResponse result = request.execute();
			
			System.out.println("Code:" + result.getStatusCode());
			
			//convert to UTF-8 added 6/11/2012
			byte[] utf8bytes = result.getValue().toString().getBytes();
			Charset utf8charset = Charset.forName("UTF-8");
			String newValue = new String (utf8bytes, utf8charset);

			//System.out.println("Value:" + result.getValue());
			System.out.println("Value:" + newValue);
			
			clear();
			
			return result;
			
		} catch (ResponseException e) {
			e.printStackTrace();
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public IResponse post(String url) {
		
		System.out.println("URL: " + url);
		
		HttpPost post = new HttpPost(url);

		
		
		 if(!parameters.isEmpty() && requestBody != null) {
		        throw new IllegalStateException("You can either send parameters OR body content in the POST, not both!");
		 }
		 
		try {
			
			if(!parameters.isEmpty()){
				HttpParams params = new BasicHttpParams();
				if (parameters != null && parameters.keySet().size() > 0) {
					
					
					/*for (String key : parameters.keySet()) {
						if (parameters.get(key) != null) {
							params.setParameter(key, URLEncoder.encode(parameters.get(key)));
						}
					}*/
					
					for (String key : parameters.keySet()) {
						if (parameters.get(key) != null) {
							
							List<Object> list = parameters.get(key);
							for(Object o : list){
								params.setParameter(key,o);
							}
							//if (count < parameters.keySet().size()) fullUrl.append("&");
						}
					}
					
					
					
				}
		        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		        HttpProtocolParams.setUseExpectContinue(params, false);
		        HttpParams defaultParameters = null;
		        defaultParameters = params;
		        post.setParams(defaultParameters);
			}
			
			
			if(requestBody != null) {
				HttpEntity entity = null;
				if(requestBody instanceof String){
					entity = new StringEntity(requestBody.toString());
				}
				else{
					throw new Exception("Body type is supported");
				}
				post.setEntity(entity);
			}
			
			post.setHeader("Content-type", contentType);

			//Set Header for Post
			//New code added 01/25/2012 by fahmed
			Map mp = this.headers;
			Iterator it = mp.entrySet().iterator();
			while (it.hasNext()) {
			        Map.Entry pairs = (Map.Entry)it.next();
			        System.out.println(pairs.getKey() + " = " + pairs.getValue());
			        post.setHeader(pairs.getKey().toString(),pairs.getValue().toString());
			        
			}

			HttpClientRequestExecutor<String> request = new HttpClientRequestExecutor<String>(this, post, new StringHandler());
		
			IResponse result = request.execute();
			
			System.out.println("Code:" + result.getStatusCode());
			
			//convert to UTF-8
			System.out.println("Value:" + result.getValue());
			
			clear();
			
			return result;
			
		} catch (ResponseException e) {
			e.printStackTrace();
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return null;
	}
	
	@Override
	public IResponse put(String path) {
		
		System.out.println("URL: " + path);
		
		boolean isFullyQualifiedUri = isFullyQualified(path);
	      
		path = extractRequestParamsIfNeeded(path);
	    	

		HttpPut put = null;
		try {
			put = new HttpPut(Util.queryString(path, parameters));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		
	/*	
	 	HttpGet get = new HttpGet(url);
	 	HttpParams params = new BasicHttpParams();
		if (parameters != null && parameters.keySet().size() > 0) {
			for (String key : parameters.keySet()) {
				if (parameters.get(key) != null) {
					params.setParameter(key, URLEncoder.encode(parameters.get(key)));
				}
			}
		}
		get.setParams(params);
	*/
		
	/*
		HttpParams params = new BasicHttpParams();
		if (parameters != null && parameters.keySet().size() > 0) {
			for (String key : parameters.keySet()) {
				if (parameters.get(key) != null) {
					params.setParameter(key, URLEncoder.encode(parameters.get(key)));
				}
			}
		}
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUseExpectContinue(params, false);
        HttpParams defaultParameters = null;
        defaultParameters = params;
        
        HttpGet get = new HttpGet(url);
        get.setParams(defaultParameters);
    */
		//set content type
		//get.setHeader("Content-type", this.contentType + "; charset=ISO-8859-1");
		//get.setHeader("Content-type", this.contentType);
		//set headers
		//System.out.println("************new jar*************");
		Map mp = this.headers;
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        System.out.println(pairs.getKey() + " = " + pairs.getValue());
		        put.setHeader(pairs.getKey().toString(),pairs.getValue().toString());
		        
		}
		
		if(requestBody != null) {
			try {
				HttpEntity entity = null;
				if(requestBody instanceof String){
					entity = new StringEntity(requestBody.toString());
				}
				else{
					throw new Exception("Body type is supported");
				}
				put.setEntity(entity);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		put.setHeader("Content-Type", "application/json");
		HttpClientRequestExecutor<String> request = new HttpClientRequestExecutor<String>(this, put, new StringHandler());
		
		try {
			IResponse result = request.execute();
			
			System.out.println("Code:" + result.getStatusCode());
			System.out.println("Value:" + result.getValue());
			//convert to UTF-8 added 6/11/2012
			byte[] utf8bytes = result.getValue().toString().getBytes();
			Charset utf8charset = Charset.forName("UTF-8");
			String newValue = new String (utf8bytes, utf8charset);

			//System.out.println("Value:" + result.getValue());
			System.out.println("Value:" + newValue);
			
			clear();
			
			return result;
			
		} catch (ResponseException e) {
			e.printStackTrace();
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
		
		return null;
		/*System.out.println("URL: " + path);
		
		HttpPut put = new HttpPut(path);

		
		
		 if(!parameters.isEmpty() && requestBody != null) {
		        throw new IllegalStateException("You can either send parameters OR body content in the POST, not both!");
		 }
		 
		try {
			
			if(!parameters.isEmpty()){
				HttpParams params = new BasicHttpParams();
				if (parameters != null && parameters.keySet().size() > 0) {
					
					
					for (String key : parameters.keySet()) {
						if (parameters.get(key) != null) {
							params.setParameter(key, URLEncoder.encode(parameters.get(key)));
						}
					}
					
					for (String key : parameters.keySet()) {
						if (parameters.get(key) != null) {
							
							List<Object> list = parameters.get(key);
							for(Object o : list){
								params.setParameter(key,o);
							}
							//if (count < parameters.keySet().size()) fullUrl.append("&");
						}
					}
					
					
					
				}
		        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		        HttpProtocolParams.setUseExpectContinue(params, false);
		        HttpParams defaultParameters = null;
		        defaultParameters = params;
		        put.setParams(defaultParameters);
			}
			
			
			if(requestBody != null) {
				HttpEntity entity = null;
				if(requestBody instanceof String){
					entity = new StringEntity(requestBody.toString());
				}
				else{
					throw new Exception("Body type is supported");
				}
				put.setEntity(entity);
			}
			
			put.setHeader("Content-type", contentType);

			//Set Header for Post
			//New code added 01/25/2012 by fahmed
			Map mp = this.headers;
			Iterator it = mp.entrySet().iterator();
			while (it.hasNext()) {
			        Map.Entry pairs = (Map.Entry)it.next();
			        System.out.println(pairs.getKey() + " = " + pairs.getValue());
			        put.setHeader(pairs.getKey().toString(),pairs.getValue().toString());
			        
			}

			HttpClientRequestExecutor<String> request = new HttpClientRequestExecutor<String>(this, put, new StringHandler());
		
			IResponse result = request.execute();
			
			System.out.println("Code:" + result.getStatusCode());
			
			//convert to UTF-8
			System.out.println("Value:" + result.getValue());
			
			clear();
			
			return result;
			
		} catch (ResponseException e) {
			e.printStackTrace();
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return null;*/
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
	
	
	
	// Get a cookie from the cookie store
	public String getCookie(String key) {
		String value = null;
		CookieStore cookieStore = this.client.getCookieStore();
		List<Cookie> cookies = cookieStore.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equalsIgnoreCase(key)) {
				value = cookie.getValue(); 
				break;
			}
		}
		return value;
	}
	
	// Set a cookie
	public void setCookie(String key, String value, String domain, String path, Date expiryDate) {
		CookieStore cookieStore = this.client.getCookieStore();
		BasicClientCookie cookie = new BasicClientCookie(key, value);
		if (domain != null) cookie.setDomain(domain);
		if (path != null) cookie.setPath(path);
		if (expiryDate != null) cookie.setExpiryDate(expiryDate);
		cookieStore.addCookie(cookie);
	}
	
	// Clear cookies
	public void clearCookies() {
		this.client.getCookieStore().clear();
	}
	
	
	private void clear()
	{
		parameters.clear();
		cookies.clear();
		headers.clear();
	}
	
/*	public String get(String address,Map<String, String> parameters) throws Exception
	{
		String query = null;
		String request = null;
		StringBuilder sb = new StringBuilder();
		
		query = Utils.buildWebQuery(parameters);
		request = address + query;
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet =  new HttpGet(request);
		HttpResponse  response = httpClient.execute(httpGet);
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("StatusCode: " + statusCode);
		if(statusCode == HttpStatus.SC_OK){
			HttpEntity entity = response.getEntity();
			if (entity != null) {
			    InputStream instream = entity.getContent();
			    BufferedReader br = new BufferedReader(new InputStreamReader(instream));
				String lineRead;
				while ((lineRead = br.readLine()) != null) {
					//System.out.println(lineRead);
					sb.append(lineRead);
				}
				br.close();
			}
		}

		return sb.toString();
	}*/
	
	

	/*
	public IResponse<String> get(String url, Map<String, String> parameters) throws ConnectionException {
		HttpGet get = new HttpGet(RestUtil.queryString(url, parameters));
		HttpClientRequestExecutor<String> request = new HttpClientRequestExecutor<String>(this, get, new StringHandler());
		return request.execute();
	}
	
	public IResponse<String> post(String url, Map<String, String> parameters) throws ConnectionException {
		HttpPost post = new HttpPost(url);
		post.setEntity(encodedFormEntity(parameters));
		HttpClientRequestExecutor<String> request = new HttpClientRequestExecutor<String>(this, post, new StringHandler());
		return request.execute();
	}
	
	public IResponse<String> syncML(String url, String message) throws ConnectionException {
		HttpPost post = new HttpPost(url);
		
		// SyncML specific Http headers
		post.setHeader("Content-Type", "application/vnd.syncml+xml");
		post.setHeader("Connection", "Keep-Alive");
		
		post.setEntity(new ByteArrayEntity(message.getBytes()));
		HttpClientRequestExecutor<String> request = new HttpClientRequestExecutor<String>(this, post, new StringHandler());
		return request.execute();
	}
	
	public IResponse<File> syncMLFile(String url, String message) throws ConnectionException {
		HttpPost post = new HttpPost(url);
		
		// SyncML specific Http headers
		post.setHeader("Content-Type", "application/vnd.syncml+xml");
		post.setHeader("Connection", "Keep-Alive");
		
		post.setEntity(new ByteArrayEntity(message.getBytes()));
		HttpClientRequestExecutor<File> request = new HttpClientRequestExecutor<File>(this, post, new FileHandler());
		return request.execute();
	}
	
	public IResponse<Asset> download(String url, Map<String, String> parameters, IProgressCallback callback) throws ConnectionException {
		return download(url, parameters, -1, -1, callback);
	}
	
	public IResponse<Asset> download(String url, Map<String, String> parameters, long startByte, long endByte, IProgressCallback callback) throws ConnectionException {
		HttpGet get = new HttpGet(RestUtil.queryString(url, parameters));
		if (endByte >= 0 & startByte >= 0 && startByte <= endByte) get.addHeader("range", "bytes=" + startByte + "-" + endByte);
		return new HttpClientRequestExecutor<Asset>(this, get, new AssetHandler(callback)).execute();
	}
	
	public IResponse<Asset> download(String url, Map<String, String> parameters, long startByte, long endByte, OutputStream out, IProgressCallback callback) throws ConnectionException {
		HttpGet get = new HttpGet(RestUtil.queryString(url, parameters));
		if (endByte >= 0 & startByte >= 0 && startByte <= endByte) get.addHeader("range", "bytes=" + startByte + "-" + endByte);
		return new HttpClientRequestExecutor<Asset>(this, get, new AssetHandler(out, callback)).execute();
	}*/
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		List<Object> list = new ArrayList<Object>();
		BNHttpClient client = new BNHttpClient();
		//IRequestSpecification client = (IRequestSpecification) ClientFactory.getDefaultProxy();
		String requestXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request customerid=\""+ "000oLfrO20AO8og0" + "\" offset=\"0\" limit=\"" + "10" + "\" displayStatus=\"ALL\" sortType=\"TITLE_ASC\"/>";
		
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
	    System.out.println(json);
	    
	    Charset utf8charset = Charset.forName("UTF-8");
		Charset iso88591charset = Charset.forName("ISO-8859-1");
		
		byte[] iso88591bytes = json.toString().getBytes();
		//byte[] utf8bytes = json.toString().getBytes();
		
		//String temp = new String(utf8bytes,utf8charset);
		
		//System.out.println(temp);
		

		String temp2 = new String ( iso88591bytes, iso88591charset);
		System.out.println(temp2);
		
		
		System.out.println(stringBuilder.toString());
		String getUrl = "http://dsvdevctg02.sjc1700.bnweb.user.bn:8080/catalog/api/internal/products/invalidate";
		
		
		String url = "http://dsvdevctg02.sjc1700.bnweb.user.bn:8080/catalog/api/internal/products";
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("X-BN-Auth-Organization", "Nook");
		headers.put("X-BN-Auth-Role", "Developer");
		headers.put("X-BN-Auth-User", "Tester");
		headers.put("accept", "application/json");
		
		//IResponse getResult = client.headers(headers).contentType("application/json").get(url);
		
		IResponse result = null;//client.get("http://10.1.168.64/RecommendationService/v1.0.0/getRecommendations?");
		result = client.headers(headers).body(temp2).contentType("application/json").put(url);
		
		System.out.println(result.getStatusCode());
		
		
		
		
		System.out.println(result.getStatusCode());
		System.out.println(result.getValue());
		
		/*String xml = client.get("http://10.1.168.64/RecommendationService/v1.0.0/getRecommendations?", parameters);
		System.out.println(xml);
		
		XPathReader reader = new XPathReader(xml);
		
		String expression = "//recommendedProducts/product/EAN";
		NodeList nodeValue = (NodeList) reader.read(expression, XPathConstants.NODESET);
		System.out.println(nodeValue.getLength());*/
		//Assert.assertEquals("9780307449627", nodeValue);
		
	}


	/*@Override
	public IRequestSpecification parameters(Map<String, String> parametersMap) {
		// TODO Auto-generated method stub
		return null;
	}*/





}