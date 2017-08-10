package com.atg_sync_services.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;




/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;*/
import oracle.jdbc.OracleConnection;

public class OracleDBConnection {
	private static String m_URL = "jdbc:oracle:thin:@//injpaymntdb01.barnesandnoble.com:1521/injpaymntdb";
	private static String m_userName = "qa_user";
	private static String m_password = "qa_user";
	
	private static Connection connection = null; 
	private static Statement statement = null;
	private static boolean isConnected = false;
	
	public OracleDBConnection() throws ClassNotFoundException{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(m_URL,m_userName,m_password);
			isConnected = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public OracleDBConnection(String URL, String userName, String password) {
		try {
			setURL("jdbc:oracle:thin:@"+URL);
			setUserName(userName);
			setPassword(password);
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(m_URL,m_userName,m_password);
			isConnected = true;	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Connection getConnection(){
		return connection;
	}
	public boolean isConnected(){
		return isConnected;
	}
	public  void setURL(String URL){
		m_URL = URL;
		
	}
	
	public   void setUserName(String userName){
		m_userName = userName;
	}
	
	public   void setPassword(String password){
		m_password = password;
	}
	
	public  void connect(){
		try {
			
			if(!isConnected){
				connection = DriverManager.getConnection(m_URL,m_userName,m_password);
				statement = connection.createStatement();
				isConnected = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public  void close(){
		if(isConnected){
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	


}
