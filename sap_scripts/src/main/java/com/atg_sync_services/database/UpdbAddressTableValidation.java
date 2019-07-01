package com.atg_sync_services.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import com.atg_sync_services.framework.TestScriptBase;

public class UpdbAddressTableValidation extends DatabaseTableBase{
	

	private OracleUserProfileDB oracleUserProfileDB;
	

	private String customerId;
	private String addressId;
	private String sqlQuery;
	//private ResultSet rs;
	
	public UpdbAddressTableValidation(){
		oracleUserProfileDB = new OracleUserProfileDB();
		connection = oracleUserProfileDB.dbConnection();
	}
	
	public UpdbAddressTableValidation(String customerId){
		oracleUserProfileDB = new OracleUserProfileDB();
		connection = oracleUserProfileDB.dbConnection();
		this.customerId = customerId;
		getAddressTable();
	}
	
	public UpdbAddressTableValidation(String customerId, String addressId){
		oracleUserProfileDB = new OracleUserProfileDB();
		connection = oracleUserProfileDB.dbConnection();
		this.customerId = customerId;
		this.addressId = addressId;
		getAddressTable();
	}
	
	public ValidateTable validateTable(){
		return new ValidateTable();
	}
	
	private void getAddressTable(){
		//ResultSet rs = null;
		try {
			sqlQuery = "SELECT * from userprofile.address where ";
			if(this.customerId!=null){
				sqlQuery = sqlQuery + "customerid ='" + customerId + "'";	
			}		
			if(this.addressId!=null){
				if(customerId==null){
					sqlQuery = sqlQuery + "addressid ='" + addressId + "'";
				}
				else
				{
					sqlQuery = sqlQuery + " and addressid ='" + addressId + "'";
				}
			}	
			rsHashMap().clear();
			resultSetHashMap(sqlQuery);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public String getAddressId(){
		
		String addressId=null;
		try {
			
			addressId = rsHashMap().get("ADDRESSID");
			
			System.out.println(addressId);	
			reportTest().log(LogStatus.PASS, "UPDB Address Table Validate Address ID retrieved: " + addressId);
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addressId;
	}
	
	public class ValidateTable{
		
		public void emptyRows(){
			boolean actual = rsHashMap().isEmpty();
			System.out.println(actual);		
			try {
				Assert.assertEquals(actual, true);
				reportTest().log(LogStatus.PASS, "UPDB Address Table Validate table is empty : " );
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Address Table Validate table is empty : " + true + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
		}

		public ValidateTable addressId(String expected){
			
			String actual = rsHashMap().get("ADDRESSID");
			System.out.println(actual);		
			try {
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Address Table Validate Address ID : " + expected);
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Address Table Validate Address ID Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}

			return this;
		}
		
		public ValidateTable nickName(String expected){
			
			String actual = rsHashMap().get("NICKNAME");
			try {
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Address Table Validate Nick Name : " + expected);
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Address Table Validate NIck Name Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
			
			return this;
		}
		
		public ValidateTable firstName(String expected){
			
			String actual = rsHashMap().get("FIRSTNAME");
			try {
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Address Table Validate Fisrt Name : " + expected);
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Address Table Validate First Name Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}

			return this;
		}
		
		public ValidateTable lastName(String expected){
			String actual = rsHashMap().get("LASTNAME");
			try {
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Address Table Validate Last Name : " + expected);
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Address Table Validate Last Name Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}

			return this;
		}
		
		public ValidateTable addressLine1(String expected){
			String actual = rsHashMap().get("LINE1");
			try {
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Address Table Validate Address1 : " + expected);
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Address Table Validate Address1 Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}

			return this;
		}
		
		public ValidateTable addressLine2(String expected) {

			try {
				String actual = rsHashMap().get("LINE2");
				try {
					Assert.assertEquals(actual, expected);
					reportTest().log(LogStatus.PASS, "UPDB Address Table Validate Address Line 2 : " + expected);
				} catch (AssertionError e) {
					TestScriptBase.testFailed = true;
					reportTest().log(LogStatus.FAIL,"UPDB Address Table Validate Address Line 2 Expected Result : " + expected + " didn't match with Actual Result : " + actual);
					e.printStackTrace();
				}

			} catch (AssertionError e) {
				e.printStackTrace();
			}
			return this;
		}
		
		public ValidateTable city(String expected){
			String actual = rsHashMap().get("CITY");
			try {
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Address Table Validate City : " + expected);
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Address Table Validate City Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}

			return this;
		}
		
		public ValidateTable state(String expected){
			String actual = rsHashMap().get("STATE");
			try {
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Address Table Validate State : " + expected);
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Address Table Validate State Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}

			return this;
		}
		
		public ValidateTable zipCode(String expected){
			String actual = rsHashMap().get( "ZIP");
			try {
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Address Table Validate Zip Code : " + expected);
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Address Table Validate Zip Code Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}

			return this;
		}
		
		public ValidateTable phoneNumber(String expected){
			String actual = rsHashMap().get("PHONE");
			try {
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Address Table Validate Phone Number : " + expected);
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Address Table Validate Phone Number Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}

			return this;
		}
	}
	

	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		UpdbAddressTableValidation addressTbl = new UpdbAddressTableValidation("000xQ2kY20JYO810", "95683827");
		//addressTbl.validateTable("000xQ2kY20JYO810", "95683827").addressId("95683827").nickName("Johnny Test");

		//addressTbl.validateTable("000xQ2kY20JYO810", "95683827").addressId("").nickName("");
		addressTbl.validateTable().firstName("Johnny").lastName("Test").addressLine1("122 Pine Street")
		.city("Deer Park").state("NY");
		addressTbl.validateTable().firstName("Johnny").lastName("Test").addressLine1("122 Pine Street")
		.city("Deer Park").state("NY");
		addressTbl.validateTable().firstName("Johnny").lastName("Test").addressLine1("122 Pine Street")
		.city("Deer Park").state("NY");
		addressTbl.validateTable().firstName("Johnny").lastName("Test").addressLine1("122 Pine Street")
		.city("Deer Park").state("NY");
		addressTbl.validateTable().firstName("Johnny").lastName("Test").addressLine1("122 Pine Street")
		.city("Deer Park").state("NY");
	}
	
	
}
