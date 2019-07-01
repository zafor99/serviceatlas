package com.atg_sync_services.framework;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import com.atg_sync_services.framework.HttpClientService.ValidateErrorMessage;
import com.atg_sync_services.framework.NestAddressService.ModifyJSONAddressRequest;
import com.atg_sync_services.utils.EnvironmentUtility;
import com.atg_sync_services.utils.JSONUtility;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

/*
 * 
 */
public class NestCustomerService extends HttpClientService{

	private static String m_MercuryUserId = "No ID Created";
	private static String m_Url = EnvironmentUtility.nest().serverName() + "/accounts";
	private static String m_FileName = System.getProperty("user.dir") + "/testData/customer.json";
	private static JSONUtility m_RequestJSONUtil = new JSONUtility();
	private static JSONUtility m_ResponseJSONUtil = null;
	private static String m_ResponseJSONString = null;
	private static Map<String,String> m_Headers = new HashMap<String,String>();
	private static Response m_Response = null;
	private static Integer m_StatusCode = 0;
	private static String m_Status = null;
	private static String m_ResponseText = null;
	private  String m_login = null; //"qaautomation9999@bn.com";
	private  String m_email = null; //"qaautomation9999@bn.com";
	
	public NestCustomerService(){
		load();
	}
	
	public void load(){
		m_RequestJSONUtil.load(m_FileName);
	}
	
	public ModifyJSONCustomerRequest modifyCustomerJSONRequest(){
		return new ModifyJSONCustomerRequest();
	}
	
	
	public void setLogin(String login)
	{
		m_login = login;
	}
	
	public String getLogin(String login)
	{
	    login = m_login;	    
	    return login;
	}
	
	public void setEmail(String email)
	{
		m_email = email;
	}
	
	public String getEmail()
	{
		return m_email;
	}
	
	public void setUserId(String userId)
	{
		m_MercuryUserId = userId;
	}
	
	public String getUserId()
	{
		return m_MercuryUserId;
	}
	
	public class ModifyJSONCustomerRequest{
		
		public ModifyJSONCustomerRequest(){
			
			
		}
		
		public ModifyJSONCustomerRequest lastModified(String lastModified){
			
			m_RequestJSONUtil.modifyNodeValue("//lastModified", lastModified);
			reportTest().log(LogStatus.INFO, "Customer Service Modify Json customer Request last Modified Date : " + lastModified);
			return this;
		}
		
		public ModifyJSONCustomerRequest login(String login){
			
			m_RequestJSONUtil.modifyNodeValue("//login", login);
			reportTest().log(LogStatus.INFO, "Customer Service Modify Json customer Request login : " + login);
			return this;
		}
		
		public ModifyJSONCustomerRequest email(String email){
			
			m_RequestJSONUtil.modifyNodeValue("//email", email);
			reportTest().log(LogStatus.INFO, "Customer Service Modify Json customer Request email : " + email);
			return this;
		}
		
		public ModifyJSONCustomerRequest password(String password){
			
			m_RequestJSONUtil.modifyNodeValue("//password", password);
			reportTest().log(LogStatus.INFO, "Customer Service Modify Json customer Request password : " + password);
			return this;
		}
		
		public ModifyJSONCustomerRequest firstName(String firstName){
			
			m_RequestJSONUtil.modifyNodeValue("//firstName", firstName);
			reportTest().log(LogStatus.INFO, "Customer Service Modify Json customer Request first Name : " + firstName);
			return this;
		}
		
		public ModifyJSONCustomerRequest lastName(String lastName){
			
			m_RequestJSONUtil.modifyNodeValue("//lastName", lastName);
			reportTest().log(LogStatus.INFO, "Customer Service Modify Json customer Request last Name : " + lastName);
			return this;
		}
		
		public ModifyJSONCustomerRequest registrationDate(String registrationDate){
			
			m_RequestJSONUtil.modifyNodeValue("//registrationDate", registrationDate);
			reportTest().log(LogStatus.INFO, "Customer Service Modify Json customer Request registration Date : " + registrationDate);
			return this;
		}
		
		public ModifyJSONCustomerRequest secretQuestionId(String secretQuestionId){
			
			m_RequestJSONUtil.modifyNodeValue("//secretQuestionId", secretQuestionId);
			reportTest().log(LogStatus.INFO, "Customer Service Modify Json customer Request secret Question Id : " + secretQuestionId);
			return this;
		}
		
		public ModifyJSONCustomerRequest secretAnswer(String secretAnswer){
			
			m_RequestJSONUtil.modifyNodeValue("//secretAnswer", secretAnswer);
			reportTest().log(LogStatus.INFO, "Customer Service Modify Json customer Request secret Answer : " + secretAnswer);
			return this;
		}
		
		public ModifyJSONCustomerRequest penName(String penName){
			
			m_RequestJSONUtil.modifyNodeValue("//penName", penName);
			reportTest().log(LogStatus.INFO, "Customer Service Modify Json customer Request pen Name : " + penName);
			return this;
		}
		
		
	}
	
	public void printJson(){
		System.out.println(m_RequestJSONUtil.getJSONString());
	}
	
	public Response create(){
		
		String postUrl = m_Url;
		System.out.println(postUrl);
		reportTest().log(LogStatus.INFO, "Address Service Post URL : " + postUrl);
		boolean success = false;
		String json = m_RequestJSONUtil.getJSONString();
	    System.out.println(json);
	    reportTest().log(LogStatus.INFO, "Address Service Post Body : " + json);
		
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
			m_MercuryUserId = m_Response.path("mercuryId");
			reportTest().log(LogStatus.PASS, "Customer Service Response Returned : " + m_Response.getStatusCode());
			reportTest().log(LogStatus.PASS, "Customer Service Customer created successfully. Mercury Customer Id : " + m_MercuryUserId);
			
			
		}catch(AssertionError e)
		{
			if(m_Response.getStatusCode()>=500 && m_Response.getStatusCode()<600){
				reportTest().log(LogStatus.FATAL, "Customer Service failed. Status Code Returned " + m_Response.getStatusCode());
			}
			
			
		}
		
			
		return m_Response;
		
	}
	
	public Response create(String email){
		
		modifyCustomerJSONRequest().email(email).login(email);
		create();
		
		return m_Response;
	}
	
	public Response lookup(){
		boolean success = false;
		String lookupUrl = m_Url + "/" + m_MercuryUserId;
		reportTest().log(LogStatus.INFO, "Customer Service Get URL : " + lookupUrl);
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
				reportTest().log(LogStatus.FATAL, "Customer Service failed. Status Code Returned " + m_Response.getStatusCode());
			}
			
			
		}
		return m_Response;
	}
	
	public Response update(){
		boolean success = false;
		String updateUrl = m_Url + "/" + m_MercuryUserId;
		reportTest().log(LogStatus.INFO, "Customer Service Update URL : " + updateUrl);
		System.out.println(updateUrl);
		String json = m_RequestJSONUtil.getJSONString();
	    System.out.println(json);
	    
	    reportTest().log(LogStatus.INFO, "Customer Service update Body : " + json);
	    
		//m_Headers.put("Authorization", "apikey 12345");
		//m_Headers.put("Accept", "application/json");
	
		try {
			
			m_Response = httpClient().body(json).contentType("application/json").headers(m_Headers).put(updateUrl);
			System.out.println(m_Response.asString());
			System.out.println(m_Response.getStatusLine());

		}catch(AssertionError e)
		{
			if(m_Response.getStatusCode()>=500 && m_Response.getStatusCode()<600){
				reportTest().log(LogStatus.FATAL, "Customer Service failed. Status Code Returned " + m_Response.getStatusCode());
			}
			
			
		}
		return m_Response;
	}	
	
}
