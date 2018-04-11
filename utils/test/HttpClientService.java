package com.atg_sync_services.framework;


import com.atg_sync_services.utils.EnvironmentUtility;
import com.atg_sync_services.utils.JSONUtility;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;



public abstract class HttpClientService {

	
	public EnvironmentUtility envUtil = new EnvironmentUtility();
	private static ResponseService response = null;
	
	/*
	 * our main http client
	 * RestAssured CLass
	 */
	public RequestSpecification httpClient(){

		return RestAssured.given();
	}
	
/*	public IRequestSpecification httpClientInstance(){

		return getNewInstance();
	}*/
	
	public ResponseService response(){
		
		
		return new ResponseService();
	}
	
	public ValidateErrorMessage validateErrorMessage(Response response){
		return new ValidateErrorMessage(response);
	}
	/*
	 */
	protected ExtentTest reportTest(){
		return TestScriptBase.reportTest();
	}
	
	public class ValidateErrorMessage{
		
		private Response m_Response = null;
		public ValidateErrorMessage(Response response){
			this.m_Response = response;
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
			boolean found = false;
			try{
				actualErrorDesc = jsonUtil.getNodeValue("//errors/[1]/description");
				if(actualErrorDesc.trim().contains(expectedErrorDesc.trim())){
					found = true;
				}
				Assert.assertEquals(found, true);
				reportTest().log(LogStatus.PASS, "Address Service validate Error Description : " + actualErrorDesc);
			}catch(AssertionError e)
			{
				//TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.WARNING, "Address Service validate Error Description : " + expectedErrorDesc + " didn't match with actaul " + actualErrorDesc);
				
			}
			return this;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String workingDir = System.getProperty("user.dir");
		   System.out.println("Current working directory : " + workingDir);

	}

}
