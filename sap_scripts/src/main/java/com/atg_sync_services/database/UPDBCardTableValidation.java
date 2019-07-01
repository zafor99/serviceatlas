package com.atg_sync_services.database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import com.atg_sync_services.framework.TestScriptBase;

public class UPDBCardTableValidation extends DatabaseTableBase {
	private OracleUserProfileDB oracleUserProfileDB;
	
	private String customerId;
	private String cardId;
	private String sqlQuery;
	
	public UPDBCardTableValidation(String customerId, String cardId){
		oracleUserProfileDB = new OracleUserProfileDB();
		connection = oracleUserProfileDB.dbConnection();
		this.customerId = customerId;
		this.cardId = cardId;	
		getCardTable();
	}
	
	public String getAddressId(){

		String addressId =null;
		addressId = rsHashMap.get("ADDRESSID");
		reportTest().log(LogStatus.INFO, "UPDB Card Table Address Id retrieved : " + addressId);
		return addressId;
	}
	
	public String getCardId(){

		String cardId = null;
		cardId = rsHashMap.get("CARDID");
		reportTest().log(LogStatus.INFO, "UPDB Card Table Card Id retrieved : " + cardId);
		return cardId;
	}
	
	public void validateCardExists(String cardNo){
		
	}
	
	public ValidateTable validateTable(){
		return new ValidateTable();
	}
	
	private void getCardTable(){
		
		try {
			sqlQuery = "SELECT * from userprofile.card where ";
			if(this.customerId!=null){
				sqlQuery = sqlQuery + "customerid ='" + customerId + "'";	
			}		
			if(this.cardId!=null){
				if(customerId==null){
					sqlQuery = sqlQuery + "cardid ='" + cardId + "'";
				}
				else
				{
					sqlQuery = sqlQuery + " and cardid ='" + cardId + "'";
				}
			}	
			System.out.println("SQL Query : " + sqlQuery);
			rsHashMap().clear();
			resultSetHashMap(sqlQuery);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
/*		try {
			String sql = "SELECT * from userprofile."+"card"+" where customerid='" + customerId + "' and cardid ='" + cardId + "'";
			rsHashMap().clear();
			resultSetHashMap(sql);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}
	
	public class ValidateTable{
		
		public ValidateTable(){
		}
		
		public void emptyRows(){
			boolean actual = rsHashMap().isEmpty();
			System.out.println(actual);		
			try {
				Assert.assertEquals(actual, true);
				reportTest().log(LogStatus.PASS, "UPDB Credit Card Table Validate table is empty : " );
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Credit Card Table Validate table is empty : " + true + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
		}
		public ValidateTable cardType(String expected){
			
			String actual = rsHashMap.get("PAYTYPE");

			try {
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Card Table Validate Pay Type");
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Card Table Validate Card Type Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
			return this;
		}
		
		public ValidateTable accountNumber(String expected){	
			String actual = rsHashMap.get("ACCOUNTNUMBER");

			try {
				Assert.assertEquals(actual.contains(expected), true);
				reportTest().log(LogStatus.PASS, "UPDB Card Table Validate Account Number");
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Card Table Validate Account Number Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
			return this;
		}
		
		public ValidateTable expirationDate(String expected){
						
			String actual =  rsHashMap.get("EXPIRATIONDATE");
			try {
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Card Table Validate Expiration Date");
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Card Table Validate Expiration Date Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
			return this;
		}
		
		public ValidateTable customerId(String expected){
			String actual = rsHashMap.get("CUSTOMERID");
			try {
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Card Table Validate Customer Id");
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Card Table Validate Customer Id Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
			return this;
		}
		
		public ValidateTable addressId(String expected){
			String actual = rsHashMap.get("ADDRESSID");
			try {
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Card Table Validate Address Id");
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Card Table Validate AddressId Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
			return this;
		}
		
		
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		UPDBCardTableValidation cardTbl = new UPDBCardTableValidation("300O6S1Z75JYO810","82693176");
		
		//cardTbl.getCardTable();

	cardTbl.validateTable().accountNumber("545454").cardType("MC").expirationDate("31-Jan-18");
	//	addressTbl.validateTable("000xQ2kY20JYO810", "95683827").addressId("").nickName("");

	}
	


}
