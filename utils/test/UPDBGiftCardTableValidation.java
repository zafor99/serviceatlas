package com.atg_sync_services.database;

import java.sql.SQLException;

import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import com.atg_sync_services.framework.TestScriptBase;
import com.atg_sync_services.database.UPDBCardTableValidation.ValidateTable;

public class UPDBGiftCardTableValidation extends DatabaseTableBase {
	
	private OracleUserProfileDB oracleUserProfileDB;
	
	private String customerId;
	private String cardId;
	private String sqlQuery;
	
	public UPDBGiftCardTableValidation(String customerId){
		this.customerId = customerId;
		oracleUserProfileDB = new OracleUserProfileDB();
		connection = oracleUserProfileDB.dbConnection();
	}
	
	public String getCardId(){

		String cardId = null;
		cardId = rsHashMap.get("CARDID");
		reportTest().log(LogStatus.INFO, "UPDB Gift Card Table Card Id retrieved : " + cardId);
		return cardId;
	}
	
	public String getCardBalance(){

		String balance = null;
		balance = rsHashMap.get("BALANCE");
		reportTest().log(LogStatus.INFO, "UPDB Gift Card Table Card Balance retrieved : " + balance);
		return balance;
	}	
	
	private void getCardTable(){
		
		try {
			sqlQuery = "SELECT * from userprofile.giftcard where ";
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

		
		public ValidateTable customerId(String expected){
			String actual = rsHashMap.get("CUSTOMERID");
			try {
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Gift Card Table Validate Customer Id");
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Gift Card Table Validate Customer Id Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
			return this;
		}
		
		public ValidateTable balance(String expected){
			String actual = rsHashMap.get("BALANCE");
			try {
				Assert.assertEquals(actual, expected);
				reportTest().log(LogStatus.PASS, "UPDB Gift Card Table Validate Address Id");
			} catch (AssertionError e) {
				TestScriptBase.testFailed = true;
				reportTest().log(LogStatus.FAIL,"UPDB Gift Card Table Validate AddressId Expected Result : " + expected + " didn't match with Actual Result : " + actual);
				e.printStackTrace();
			}
			return this;
		}
		
		
		
	}

}
