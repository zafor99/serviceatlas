package com.atg_sync_services.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLServerDBConnection {

	private static String m_URL = "jdbc:oracle:thin:@//injpaymntdb01.barnesandnoble.com:1521/injpaymntdb";
	private static String m_userName = "qa_user";
	private static String m_password = "qa_user";
	
	private static Connection connection = null; 
	private static Statement statement = null;
	private static boolean isConnected = false;
	
	public SQLServerDBConnection(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(m_URL,m_userName,m_password);
			isConnected = true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public SQLServerDBConnection(String URL, String userName, String password) throws ClassNotFoundException{
		try {
			setURL(URL);
			setUserName(userName);
			setPassword(password);
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(m_URL,m_userName,m_password);
			if(connection!=null){
				isConnected = true;
				 System.out.println("DataBase Connection Successful!");
			}
			else{
				 System.out.println("Unable to connect to DataBase");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	public boolean isConnected(){
		return isConnected;
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
	public ResultSet getResultSet(String sql){
			
			ResultSet rs = null;
			try
	        {
				statement = connection.createStatement();
	        	rs = statement.executeQuery(sql);
	        	
	        }
	        catch (SQLException ex) 
	        {
	        	System.out.println(ex);
			} 
	       
	        finally{
	        	
	        }
			
			return rs;
		}
	
	public int getColumnCount(ResultSet rs){
		int colCount=0;
		ResultSetMetaData metaData = null;
		try {
			metaData = rs.getMetaData();
			colCount = metaData.getColumnCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return colCount;
	}
	
	public String[] getColumnHeader(ResultSet rs){
		String[] data = null;
		int index = 0;
		
		ResultSetMetaData metaData = null;
		try {
			metaData = rs.getMetaData();
			data = new String[metaData.getColumnCount()];
			for(int j=1;j<=metaData.getColumnCount();j++){
				data[index] = metaData.getColumnLabel(j);
				index++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}		
	
	public int getRowCount(ResultSet rs){
		int rowCount = 0;
		int index = 0;
		
		try {
			rs.beforeFirst();
			while(rs.next()){
				index++;
			}
			rs.beforeFirst();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return index;
	}
	
	public String[][] getRecordsetData(ResultSet rs){
		String[][] data = null;
		
		int colCount = getColumnCount(rs);
		int rowCount = getRowCount(rs);
		int index = 0;
		
		try {
			
			if(rowCount ==0){
				data = new String[2][colCount];
				data[index] = getColumnHeader(rs);
				index++;
				
				for(int i=0; i<1; i++){
					rs.next();
					for(int j=0; j<colCount;j++){
						data[index][j] = " ";
					}
					index++;
				}
				
			}else
			{
				data = new String[rowCount+1][colCount];
				data[index] = getColumnHeader(rs);
				index++;
				rs.beforeFirst();
				
				for(int i=0; i<rowCount; i++){
					rs.next();
					for(int j=0; j<colCount;j++){
						data[i+1][j] = rs.getString(j+1);
					}
					index++;
				}				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
}
