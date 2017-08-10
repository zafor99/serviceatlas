package com.atg_sync_services.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import com.atg_sync_services.framework.TestScriptBase;

public class UPDBAccountTableValidation extends DatabaseTableBase
{

	private OracleUserProfileDB oracleUserProfileDB;
	public String emailAddress;
	private String sqlQuery;
	
	public UPDBAccountTableValidation(){
	}
	
	public UPDBAccountTableValidation(String emailAddress){
		oracleUserProfileDB = new OracleUserProfileDB();
		
		connection = oracleUserProfileDB.dbConnection();
		this.emailAddress = emailAddress;
		getAccountTable();
		
	}
	
	public String getCustomerNumber(){
		String custNum = null;
		custNum = rsHashMap().get("CUSTOMERNUM");
		reportTest().log(LogStatus.INFO, "UPDB Account Table Customer Number retrieved : " + custNum);
		System.out.println("UPDB Account Table Customer ID retrieved : " + custNum);
		return custNum;
	}
	
	public String getCustomerId(){
		
		String custId = null;
		custId = rsHashMap().get("CUSTOMERID");
		reportTest().log(LogStatus.INFO, "UPDB Account Table Customer ID retrieved : " + custId);
		System.out.println("UPDB Account Table Customer ID retrieved : " + custId);
		
		return custId;
	}
	
	public String getPassword(){
		
		String password = null;
		password = rsHashMap().get("PASSWD");
		reportTest().log(LogStatus.INFO, "UPDB Account Table Password retrieved : " + password);
		System.out.println("UPDB Account Table Password retrieved : " + password);
		
		return password;
	}
	
	public void validatePasswordUpdated(String oldPassword){
		String newPassword = getPassword();
		try {
			Assert.assertNotEquals(oldPassword, newPassword);
			reportTest().log(LogStatus.PASS, "UPDB Account Table Password updated");					
		} catch (AssertionError e) {
			TestScriptBase.testFailed = true;
			reportTest().log(LogStatus.FAIL,"UPDB Account Table Password did not updated");
			e.printStackTrace();
		}
		
	}
	
	public String getDefaultShippingAddressId(){
		
		String addressId = null;
		addressId = rsHashMap().get("DEFAULTSHIPADDRID");
		reportTest().log(LogStatus.INFO, "UPDB Account Table Default Shipping Address ID retrieved : " + addressId);
		System.out.println("UPDB Account Table Default Shipping Address ID retrieved : " + addressId);
		
		return addressId;
	}
	
	public String getDefaultShippingMethodId(){
		
		String methodId = null;
		methodId = rsHashMap().get("DEFAULTSHIPMETHODID");
		reportTest().log(LogStatus.INFO, "UPDB Account Table Default Shipping Method ID retrieved : " + methodId);
		System.out.println(methodId);
		
		return methodId;
	}
	
	public String getDefaultPaymentMethodId(){
		
		String methodId = null;
		methodId = rsHashMap().get("DEFAULTPAYMETHODID");
		reportTest().log(LogStatus.INFO, "UPDB Account Table Default Payment Method ID retrieved : " + methodId);
		System.out.println("UPDB Account Table Default Payment Method ID retrieved : " +  methodId);
		
		return methodId;
	}
	
	public void validateDefaultShippingAddress(Boolean expected){

		String defaultShipping = null;
		defaultShipping = getDefaultShippingAddressId();
		if(expected){
			try {
				Assert.assertNotNull(defaultShipping);
				reportTest().log(LogStatus.PASS, "UPDB Account Table Default Shipping Address not Null");					
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Account Table Default Shipping Address not Null Expected Result : not Null didn't match with Actual Result : Null");
				e.printStackTrace();
			}
		}
		else
		{
			try {
				Assert.assertNull(defaultShipping);
				reportTest().log(LogStatus.PASS, "UPDB Account Table Default Shipping Addres is Null");	
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Account Table Default Shipping Address is Null", "Expected Result : is Null didn't match with Actual Result : not Null");
				e.printStackTrace();
			}
		}
		
	}
	
	public void validateDefaultShippingMethod(Boolean expected){
		
		String defaultShipping = null;
		defaultShipping = getDefaultShippingMethodId();
		if(expected){
			try {
				Assert.assertNotNull(defaultShipping);
				reportTest().log(LogStatus.PASS, "UPDB Account Table Default Shipping Method not Null");					
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Account Table Default Shipping Shipping not Null Expected Result : not Null didn't match with Actual Result : Null");
				e.printStackTrace();
			}
		}
		else
		{
			try {
				Assert.assertNull(defaultShipping);
				reportTest().log(LogStatus.PASS, "UPDB Account Table Default Shipping Method is Null");	
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Account Table Default Shipping Method is Null Expected Result : is Null didn't match with Actual Result : not Null");
				e.printStackTrace();
			}
		}
	}
	
	public void validateDefaultPayMethod(Boolean expected){
		
		String defaultShipping = null;
		
		defaultShipping = getDefaultPaymentMethodId();
		if(expected){
			try {
				Assert.assertNotNull(defaultShipping);
				reportTest().log(LogStatus.PASS, "UPDB Account Table Default Payment Method not Null");					
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Account Table Default Payment Method Expected Result : not Null didn't match with Actual Result : Null");
				e.printStackTrace();
			}
		}
		else
		{
			try {
				Assert.assertNull(defaultShipping);
				reportTest().log(LogStatus.PASS, "UPDB Account Table Default Payment Method is Null");	
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Account Table Default Payment Method Expected Result : is Null didn't match with Actual Result : not Null");
				e.printStackTrace();
			}
		}
	}
	
	public ValidateTable validateTable(){
		return new ValidateTable();
	}
	
	private void getAccountTable(){
		
		try {
			String sql =  "SELECT * from userprofile."+"account"+" where lower(email)='" + this.emailAddress + "'";
			rsHashMap().clear();
			resultSetHashMap(sql);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public class ValidateTable{
		ResultSet rs;
		
		public ValidateTable(){			
			
		}
		
		public ValidateTable customerNumber(String expected){
			String actual = null;
			
			try {
				actual = rsHashMap().get("CUSTOMERNUM");
				Assert.assertEquals(actual, expected);				
				reportTest().log(LogStatus.PASS, "UPDB Account Table Validate Customer Number : " + actual);
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Account Table Validate Customer Number Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
			
			return this;
		}
		
		public ValidateTable emailAddress(String expected){
			String actual = null;
			
			try {
				actual = rsHashMap().get("EMAIL");
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Account Table Validate Email Address : " + expected);
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Account Table Validate Email Address Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
			return this;
		}
		
		public ValidateTable firstName(String expected){
			String actual = null;
			try {
				actual = rsHashMap().get("FIRSTNAME");
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Account Table Validate First Name : " + expected);
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Account Table Validate First Name Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
			return this;
		}
		
		public ValidateTable lastName(String expected){
			String actual = null;
			
			try {
				actual = rsHashMap().get( "LASTNAME");
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Account Table Validate Last Name : " + expected);
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Account Table Validate Last Name Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
			return this;
		}
		
		public ValidateTable defaultPaymentMethodId(String expected){
			String actual = null;
			
			try {
				actual = rsHashMap().get( "DEFAULTPAYMENTMETHODID");
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Account Table Validate Default Payment Method Id : " + expected);
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Account Table Validate Default Payment Method Id Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
			return this;
		}
		
		public ValidateTable hintQuestionId(String expected){
			String actual = null;
			
			try {
				actual = rsHashMap().get("HINTQUESTIONID");
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Account Table Validate Question ID : " + expected);
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Account Table Validate Question ID Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
			return this;
		}
		
		
		public ValidateTable hintAnswer(String expected){
			String actual = null;
			
			try {
				actual = rsHashMap().get("HINTANSWER");
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Account Table Validate Hint Answer : " + expected);
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Account Table Validate Hint Answer Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
			return this;
		}
		
	
	}
	
	
	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		UPDBAccountTableValidation addressTbl = new UPDBAccountTableValidation("qaautomation21231@book.com");
		//addressTbl.validateTable("000xQ2kY20JYO810", "95683827").addressId("95683827").nickName("Johnny Test");

		//addressTbl.validateTable("000xQ2kY20JYO810", "95683827").addressId("").nickName("");
		addressTbl.validateTable().emailAddress("qaautomation54881@book.com").firstName("Johnny").lastName("Test").hintAnswer("neruda");
		
	}
	
}
