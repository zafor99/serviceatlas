package com.atg_sync_services.framework;

import org.springframework.test.web.servlet.result.HandlerResultMatchers;
import org.testng.Assert;

import com.atg_sync_services.utils.JSONUtility;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import static io.restassured.path.json.JsonPath.*;
import io.restassured.response.Response;
import com.atg_sync_services.framework.TestScriptBase;

public class ResponseService {
	
	private static Response m_Response = null;
	private static Validate validate = null;
	public ResponseService(){
		
	}

	public Validate validate(Response response){

			return new Validate(response);

	}
	
	public class Validate {
		
		
		private ExtentTest reportTest(){
			return TestScriptBase.reportTest();
		}
		
		public Validate(Response response){
			m_Response = response;
		}
		
		public Validate statusCode(int expectedCode){
			
			
			try{
				int actualCode = m_Response.statusCode();
				Assert.assertEquals(expectedCode, actualCode);
				reportTest().log(LogStatus.PASS, "Response Service validate Status Code : " + m_Response.getStatusCode());
			}catch(AssertionError e)
			{
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL, "Expected Service validate Status Code " + expectedCode + " didn't match with actaul " + m_Response.getStatusCode());
				
			}
			return this;
		}
		
		public Validate statusLine(String expectedStatusline){
			
			
			try{
				String actualStatusLine = m_Response.getStatusLine();
				boolean contains = actualStatusLine.contains(expectedStatusline);
				Assert.assertEquals(true, contains);
				reportTest().log(LogStatus.PASS, "Response Service validate Status Line : " + expectedStatusline);
			}catch(AssertionError e)
			{
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL, "Expected Service validate Status Line  " + expectedStatusline + " didn't match with actaul " + m_Response.getStatusLine());
				
			}
			return this;
		}
		
		public Validate path(String path, String expectedValue){
			JSONUtility jsonUtil = new JSONUtility(m_Response.asString());
			String actualValue =null;
			try{
				//actualValue = jsonUtil.getNodeValue(path);
				if(m_Response!=null){
					actualValue = m_Response.path(path).toString();
				}
				actualValue = m_Response.path(path).toString();
				Assert.assertEquals(expectedValue, actualValue);
				reportTest().log(LogStatus.PASS, "Response Service validate Response value by path : " + path + " Value : " + actualValue);
			}catch(AssertionError e)
			{
				reportTest().log(LogStatus.FAIL, "Expected Service validate Response value by path : " + path + " Value : "  + expectedValue + "didn't match with actaul " + actualValue);
				
			}
			return this;
		}
		
	}

}
