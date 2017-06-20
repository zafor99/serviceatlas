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
 * @author fahmed1
 * @since  April 30, 2013
 */
public class OracleDBConnection {

	private static String m_URL = "jdbc:oracle:thin:@//injpaymntdb01.barnesandnoble.com:1521/injpaymntdb";
	private static String m_userName = "qa_user";
	private static String m_password = "qa_user";
	
	private static Connection connection = null; 
	private static Statement statement = null;
	private static boolean isConnected = false;
	
	public OracleDBConnection() throws ClassNotFoundException{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(m_URL,m_userName,m_password);
			isConnected = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public OracleDBConnection(String URL, String userName, String password) throws ClassNotFoundException{
		try {
			setURL(URL);
			setUserName(userName);
			setPassword(password);
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(m_URL,m_userName,m_password);
			isConnected = true;	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
	
	public ResultSet getResultSet(String sql){
		
		ResultSet rs = null;
		try
        {
        	 
			statement = connection.prepareStatement(sql);
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
