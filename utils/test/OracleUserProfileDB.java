package com.atg_sync_services.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;

import com.atg_sync_services.utils.EnvironmentUtility;

public class OracleUserProfileDB {
	
	
	@Autowired 
	private EnvironmentUtility envUtil = new EnvironmentUtility();
 
	private OracleDBConnection dbConnection ;
	
	public OracleUserProfileDB() {
		
	}
	
	
	public Connection dbConnection(){
		
		//if(dbConnection==null){
			dbConnection = new OracleDBConnection(envUtil.userProfileDB().serverName(), envUtil.userProfileDB().userName(), envUtil.userProfileDB().passWord());
	//	}
		return dbConnection.getConnection();

	}
	

}
