package com.atg_sync_services.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.soap.AddressingFeature.Responses;

import org.testng.Assert;

import com.atg_sync_services.utils.EnvironmentUtility;
import com.atg_sync_services.utils.JSONUtility;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import com.atg_sync_services.framework.TestScriptBase;


/*
 * everything you need addess service call
 * credit card service 
 */
public class NestAddressService extends HttpClientService{

	private static String m_Url = EnvironmentUtility.nest().serverName() + "/accounts/";
	private static String m_FileName = System.getProperty("user.dir") + "/testData/addressUS.json";
	private static JSONUtility m_RequestJSONUtil = new JSONUtility();
	private static JSONUtility m_ResponseJSONUtil = null;
	private static String m_MercuryUserId = "000pBSPU80O9xfo0";
	private static String m_MercuryAddId = null;
	private static String m_ResponseJSONString = null;
	private static Map<String,String> m_Headers = new HashMap<String,String>();
	private static Response m_Response = null;
	private static Integer m_StatusCode = 0;
	private static String m_Status = null;
	private static String m_ResponseText = null;
	private static ResponseService responseService = null;
	
	public   NestAddressService(){
		m_RequestJSONUtil.load(m_FileName);
	}
	
	public ModifyJSONAddressRequest modifyJSONAddressRequest(){
		return new ModifyJSONAddressRequest();
	}
	
	
	
	public void load(){
		m_RequestJSONUtil.load(m_FileName);
	}
	
	public  void setMercuryCustomerId(String user){
		
		m_MercuryUserId = user;
	}
	
	public String getMercuryCustomerId(){
		
		return m_MercuryUserId;
	}
	
	public void setMercuryAddressId(String addressId){
		m_MercuryAddId = addressId;
	}
	
	public String getMercuryAddressId(){
		
		return m_MercuryAddId;
	}
	
	public String getModifiedUpdatedTime()
	{
		String time;
		time = m_RequestJSONUtil.getNodeValue("//lastModified");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat tf = new SimpleDateFormat("HH:mm:ss");
		
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.MONTH, -1);
		System.out.println(df.format(cal.getTime())+ "T" + tf.format(cal.getTime()) + ".000+0000");
		time = df.format(cal.getTime())+ "T" + tf.format(cal.getTime()) + ".000+0000";
		return time;
	}
	
	public String getPlusOneHourModifiedDateTime()
	{
		String time;
		time = m_RequestJSONUtil.getNodeValue("//lastModified");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat tf = new SimpleDateFormat("HH:mm:ss");
		
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.MONTH, -1);
		System.out.println(df.format(cal.getTime())+ "T" + tf.format(cal.getTime()) + ".000+0000");
		time = df.format(cal.getTime())+ "T" + tf.format(cal.getTime()) + ".000+0000";
		
		
		return time;
	}
	
	public class ModifyJSONAddressRequest{
		
		public ModifyJSONAddressRequest(){
			
		}
		
		public ModifyJSONAddressRequest firstName(String firstName){
			m_RequestJSONUtil.modifyNodeValue("//firstName", firstName);
			reportTest().log(LogStatus.INFO, "Address Service Modify Json Address Request First Name : " + firstName);
			return this;
		}
		
		public ModifyJSONAddressRequest lastName(String lastName){
			m_RequestJSONUtil.modifyNodeValue("//lastName", lastName);
			reportTest().log(LogStatus.INFO, "Address Service Modify Json Address Request last Name : " + lastName);
			return this;
		}	
		
		public ModifyJSONAddressRequest addressNickName(String nickName){			
			m_RequestJSONUtil.modifyNodeValue("//addressNickname", nickName);
			reportTest().log(LogStatus.INFO, "Address Service Modify Json Address Request Address Nick name : " + nickName);
			return this;
		}
		
		public ModifyJSONAddressRequest lastModified(String lastModified){
			m_RequestJSONUtil.modifyNodeValue("//lastModified", lastModified);
			reportTest().log(LogStatus.INFO, "Address Service Modify Json Address Request Last modified date : " + lastModified);
			return this;
		}
		
		public ModifyJSONAddressRequest addressLine1(String address1){
			m_RequestJSONUtil.modifyNodeValue("//address1", address1);
			reportTest().log(LogStatus.INFO, "Address Service Modify Json Address Request Address Line1 : " + address1);
			return this;
		}		
		
		public ModifyJSONAddressRequest addressLine2(String address2){
			m_RequestJSONUtil.modifyNodeValue("//address2", address2);
			reportTest().log(LogStatus.INFO, "Address Service Modify Json Address Request Address Line 2 : " + address2);
			return this;
		}
		
		public ModifyJSONAddressRequest city(String city){
			m_RequestJSONUtil.modifyNodeValue("//city", city);
			reportTest().log(LogStatus.INFO, "Address Service Modify Json Address Request City : " + city);
			return this;
		}
		
		public ModifyJSONAddressRequest state(String state){
			m_RequestJSONUtil.modifyNodeValue("//state", state);
			reportTest().log(LogStatus.INFO, "Address Service Modify Json Address Request State : " + state);
			return this;
		}
		
		public ModifyJSONAddressRequest country(String country){
			m_RequestJSONUtil.modifyNodeValue("//country", country);
			reportTest().log(LogStatus.INFO, "Address Service Modify Json Address Request Country: " + country);
			return this;
		}
		
	}
	
	
	
	public Response create(){
		
		String postUrl = m_Url + m_MercuryUserId + "/addresses";
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
			m_MercuryAddId = m_Response.path("mercuryId");
			reportTest().log(LogStatus.INFO, "Address Service Customer Id : " + getMercuryCustomerId());
			reportTest().log(LogStatus.PASS, "Address Service Response Returned : " + m_Response.getStatusCode());
			reportTest().log(LogStatus.PASS, "Address Service Address created successfully. Mercury Address Id : " + getMercuryAddressId());
			
			
		}catch(AssertionError e)
		{
			if(m_Response.getStatusCode()>=500 && m_Response.getStatusCode()<600){
				reportTest().log(LogStatus.FATAL, "Address Service failed. Status Code Returned " + m_Response.getStatusCode());
			}
			
			
		}
		
			
		return m_Response;
		
	}
	
	public Response delete(){
		boolean success = false;
		String deleteUrl = m_Url + m_MercuryUserId + "/addresses/" + m_MercuryAddId;
		reportTest().log(LogStatus.INFO, "Address Service Post URL : " + deleteUrl);
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
				reportTest().log(LogStatus.FATAL, "Address Service failed. Status Code Returned " + m_Response.getStatusCode());
			}
			
			
		}
		m_ResponseText = responseText;
		return m_Response;
	}
	
	public Response lookup(){
		boolean success = false;
		String lookupUrl = m_Url + m_MercuryUserId + "/addresses/" + m_MercuryAddId;
		reportTest().log(LogStatus.INFO, "Address Service Get URL : " + lookupUrl);
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
				reportTest().log(LogStatus.FATAL, "Address Service failed. Status Code Returned " + m_Response.getStatusCode());
			}
			
			
		}
		return m_Response;
	}
	
	public Response update(){
		boolean success = false;
		String updateUrl = m_Url + getMercuryCustomerId() + "/addresses/" + m_MercuryAddId;
		reportTest().log(LogStatus.INFO, "Address Service Update URL : " + updateUrl);
		System.out.println(updateUrl);
		String json = m_RequestJSONUtil.getJSONString();
	    System.out.println(json);
	    
	    reportTest().log(LogStatus.INFO, "Address Service update Body : " + json);
	    
		m_Headers.put("Authorization", "apikey 12345");
		m_Headers.put("Accept", "application/json");
	
		try {
			
			m_Response = httpClient().body(json).contentType("application/json").headers(m_Headers).put(updateUrl);
			System.out.println(m_Response.asString());
			System.out.println(m_Response.getStatusLine());

		}catch(AssertionError e)
		{
			if(m_Response.getStatusCode()>=500 && m_Response.getStatusCode()<600){
				reportTest().log(LogStatus.FATAL, "Address Service failed. Status Code Returned " + m_Response.getStatusCode());
			}
			
			
		}
		return m_Response;
	}	
	
/*	public class ValidateErrorMessage{
		
		public ValidateErrorMessage(){
			
		}
		
		public ValidateErrorMessage code(String expectedErrorCode){
			JSONUtility jsonUtil = new JSONUtility(m_Response.asString());
			String actualErrorCode =null;
			try{
				actualErrorCode = jsonUtil.getNodeValue("//errors/[1]/code");
				Assert.assertEquals(expectedErrorCode, actualErrorCode);
				reportTest().log(LogStatus.PASS, "Address Service validate Error Code : " + actualErrorCode);
			}catch(AssertionError e)
			{
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL, "Address Service validate Error Code  " + expectedErrorCode + "didn't match with actaul " + actualErrorCode);
				
			}
			return this;
		}
		
		public ValidateErrorMessage description(String expectedErrorDesc){
			JSONUtility jsonUtil = new JSONUtility(m_Response.asString());
			String actualErrorDesc =null;
			try{
				actualErrorDesc = jsonUtil.getNodeValue("//errors/[1]/description");
				Assert.assertEquals(expectedErrorDesc, actualErrorDesc);
				reportTest().log(LogStatus.PASS, "Address Service validate Error Description : " + actualErrorDesc);
			}catch(AssertionError e)
			{
				//TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.WARNING, "Address Service validate Error Description : " + expectedErrorDesc + "didn't match with actaul " + actualErrorDesc);
				
			}
			return this;
		}
	}*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
