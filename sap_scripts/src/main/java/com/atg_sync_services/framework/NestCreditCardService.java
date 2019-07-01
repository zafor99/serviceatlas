package com.atg_sync_services.framework;

import java.io.BufferedReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import com.atg_sync_services.framework.NestAddressService.ModifyJSONAddressRequest;
import com.atg_sync_services.utils.EnvironmentUtility;
import com.atg_sync_services.utils.JSONUtility;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class NestCreditCardService extends HttpClientService{


	private static String m_MercuryCCId = "No ID Created";
	private String m_CID = "000sK5PU20O9xfo0";
	private static String m_FileName =  System.getProperty("user.dir") + "/testdata/nestservicesatgtocaliber/creditcard/creditcard.json";
	
	private static JSONUtility m_RequestJSONUtil = new JSONUtility();
	private static JSONUtility m_ResponseJSONUtil = null;
	private static String m_ResponseJSONString = null;
	private static Map<String,String> m_Headers = new HashMap<String,String>();
	private static Response m_Response = null;
	private static Integer m_StatusCode = 0;
	private static String m_Status = null;
	private static String m_ResponseText = null;
	private static String m_CCToken = null; //"qaautomation9999@bn.com";
	private static String m_DisplayNo = null; //"qaautomation9999@bn.com";
	private static String m_CCType = null;
	private static String m_CCExpMO = null;
	private static String m_CCExpY = null;
	private static String m_LastMod = null;
	private static String m_AddressID = null;
	
	public NestCreditCardService(){
		//m_RequestJSONUtil.load(m_FileName);
	}
	
	public void load(){
		m_RequestJSONUtil.load(m_FileName);
	}
	
	public ModifyJSONRequest modifyJSONRequest(){
		return new ModifyJSONRequest();
	}
	
	public String get_mUrl(){
		return EnvironmentUtility.nest().serverName()+ "/accounts/" + m_CID + "/credit-cards";
	}
	
	public void setCustomerId(String customerID)
	{
		m_CID = customerID; 
	}
	
	public String getMercuryCustomerId()
	{
		return m_CID; 
	}
	
	public String getMercuryCCId()
	{
		return m_MercuryCCId;
	}
	
	public void setMercuryCCId(String mercuryCCId)
	{
		 m_MercuryCCId = mercuryCCId;
	}
	
	public class ModifyJSONRequest{
		
		public ModifyJSONRequest(){
			
		}
		
		public ModifyJSONRequest creditCardId(String cid){
			m_RequestJSONUtil.modifyNodeValue("//cid", cid);
			reportTest().log(LogStatus.INFO, "Address Service Modify Json Address Request cid : " + cid);
			return this;
		}
		
		public ModifyJSONRequest creditCardToken(String ccToken){
			m_RequestJSONUtil.modifyNodeValue("//creditCardToken", ccToken);
			reportTest().log(LogStatus.INFO, "Address Service Modify Json Address Request cc Token : " + ccToken);
			return this;
		}
		
		public ModifyJSONRequest displayNumber(String displayNo){
			m_RequestJSONUtil.modifyNodeValue("//displayNumber", displayNo);
			reportTest().log(LogStatus.INFO, "Address Service Modify Json Address Request display Number : " + displayNo);
			return this;
		}
		
		public ModifyJSONRequest creditcardType(String ccType){
			m_RequestJSONUtil.modifyNodeValue("//creditCardType", ccType);
			reportTest().log(LogStatus.INFO, "Address Service Modify Json Address Request cc Type : " + ccType);
			return this;
		}
		
		public ModifyJSONRequest creditCardExpMonth(String ExpMO){
			m_RequestJSONUtil.modifyNodeValue("//creditCardExpMo", ExpMO);
			reportTest().log(LogStatus.INFO, "Address Service Modify Json Address Request Exp Month : " + ExpMO);
			return this;
		}
		
		public ModifyJSONRequest creditCardExpYear(String ExpYr){
			m_RequestJSONUtil.modifyNodeValue("//creditCardExpYr", ExpYr);
			reportTest().log(LogStatus.INFO, "Address Service Modify Json Address Request Exp Year : " + ExpYr);
			return this;
		}
		
		public ModifyJSONRequest lastModified(String lastModified){
			m_RequestJSONUtil.modifyNodeValue("//lastModified", lastModified);
			reportTest().log(LogStatus.INFO, "Address Service Modify Json Address Request Exp Month : " + lastModified);
			return this;
		}

	}
	
	public Response create(){
		
		String postUrl = get_mUrl();
		System.out.println(postUrl);
		reportTest().log(LogStatus.INFO, "Credit card Service Post URL : " + postUrl);
		boolean success = false;
		String json = m_RequestJSONUtil.getJSONString();
	    System.out.println(json);
	    reportTest().log(LogStatus.INFO, "Credit card Service post body : " + json);
		
		try
		{
			
			m_Response = httpClient().body(json).contentType("application/json").headers(m_Headers).post(postUrl);
			System.out.println(m_Response.asString());
			m_ResponseJSONUtil = new JSONUtility(m_Response.asString());
			System.out.println("Status Line : " + m_Response.getStatusLine());
			if(m_Response.getStatusCode()>=200 && m_Response.getStatusCode()<300){
				success = true;
			}
			
			Assert.assertEquals(success, true);
			setMercuryCCId(m_Response.path("mercuryId"));
			reportTest().log(LogStatus.INFO, "Credit Card Customer Id : " + getMercuryCustomerId());
			reportTest().log(LogStatus.INFO, "Credit Card CC Id : " + getMercuryCCId());
			reportTest().log(LogStatus.PASS, "Address Service Response Returned : " + m_Response.getStatusCode());

			
			
		}catch(AssertionError e)
		{
			if(m_Response.getStatusCode()>=500 && m_Response.getStatusCode()<600){
				reportTest().log(LogStatus.FATAL, "Credit Card Service failed. Status Code Returned " + m_Response.getStatusCode());
			}
			
			
		}
		
			
		return m_Response;
		
	}
	
	public Response delete(){
		boolean success = false;
		String deleteUrl = get_mUrl() + "/" + m_MercuryCCId;
		reportTest().log(LogStatus.INFO, "Credit card Service Delete URL : " + deleteUrl);
		System.out.println(deleteUrl);
		BufferedReader buff = null;
		m_Headers.put("Authorization", "apikey 12345");
		m_Headers.put("Accept", "application/json");
	
		String line, responseText = "";
		URI uri;
		try {
			
			m_Response = httpClient().contentType("application/json; charset=utf8").headers(m_Headers).delete(deleteUrl);
			System.out.println(m_Response.asString());
			System.out.println(m_Response.getStatusLine());

		}catch(AssertionError e)
		{
			if(m_Response.getStatusCode()>=500 && m_Response.getStatusCode()<600){
				reportTest().log(LogStatus.FATAL, "Credit Card failed. Status Code Returned " + m_Response.getStatusCode());
			}
			
			
		}
		m_ResponseText = responseText;
		return m_Response;
	}
	
	public Response lookup(){
		boolean success = false;
		String lookupUrl = get_mUrl();
		reportTest().log(LogStatus.INFO, "Credit card Service Lookup URL : " + lookupUrl);
		System.out.println(lookupUrl);

		m_Headers.put("Authorization", "apikey 12345");
		m_Headers.put("Accept", "application/json");
	
		try {
			
			m_Response = httpClient().contentType("application/json; charset=utf8").headers(m_Headers).get(lookupUrl);
			System.out.println(m_Response.asString());
			System.out.println(m_Response.getStatusLine());

		}catch(AssertionError e)
		{
			if(m_Response.getStatusCode()>=500 && m_Response.getStatusCode()<600){
				reportTest().log(LogStatus.FATAL, "Credit card Service failed. Status Code Returned " + m_Response.getStatusCode());
			}
			
			
		}
		return m_Response;
	}
	
	public Response lookupWithCCId(){
		boolean success = false;
		String lookupUrl = get_mUrl() + "/" + m_MercuryCCId;
		reportTest().log(LogStatus.INFO, "Credit card Service Lookup URL : " + lookupUrl);
		System.out.println(lookupUrl);

		m_Headers.put("Authorization", "apikey 12345");
		m_Headers.put("Accept", "application/json");
	
		try {
			
			m_Response = httpClient().contentType("application/json; charset=utf8").headers(m_Headers).get(lookupUrl);
			System.out.println(m_Response.asString());
			System.out.println(m_Response.getStatusLine());

		}catch(AssertionError e)
		{
			if(m_Response.getStatusCode()>=500 && m_Response.getStatusCode()<600){
				reportTest().log(LogStatus.FATAL, "Credit card Service failed. Status Code Returned " + m_Response.getStatusCode());
			}
			
			
		}
		return m_Response;
	}
}
