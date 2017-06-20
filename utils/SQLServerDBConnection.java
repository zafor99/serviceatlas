package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  February 25, 2014
 */
public  class SQLServerDBConnection 
{
	private static String m_URL = "jdbc:oracle:thin:@//injpaymntdb01.barnesandnoble.com:1521/injpaymntdb";
	private static String m_userName = "qa_user";
	private static String m_password = "qa_user";
	
	private static Connection connection = null; 
	private static Statement statement = null;
	private static boolean isConnected = false;
	
	public SQLServerDBConnection()throws ClassNotFoundException{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(m_URL,m_userName,m_password);
			isConnected = true;
		} catch (SQLException e) {
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
	        	RationalTestScript.logException(ex);
			} 
	       
	        finally{
	        	
	        }
			
			return rs;
		}
	
}
