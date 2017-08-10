package com.atg_sync_services.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.commons.collections.map.HashedMap;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import com.atg_sync_services.framework.TestScriptBase;

public class DatabaseTableBase {
	protected Connection connection;
	protected Statement statement;
	protected ResultSet rs;
	protected SoftAssert soft_Assert = new SoftAssert();
	protected static HashMap<String, String> rsHashMap=null;
	
	protected ExtentTest reportTest(){
		return TestScriptBase.reportTest();
	}
	
	protected HashMap<String,String> rsHashMap(){
		if(rsHashMap==null){
			rsHashMap = new HashMap<String, String>();
		}
		return rsHashMap;
	}
	
	public void rsMoveFirst(){
		try {
			while(!rs.isBeforeFirst()){
				rs.previous();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet getResultSet(String sql){
		
		//Statement statement = null;
		//ResultSet rs = null;
		try
        {
        	 
			statement = connection.prepareStatement(sql);
       		rs = statement.executeQuery(sql);	
       		//statement.close();
        	
        }
        catch (SQLException ex) 
        {
        	System.out.println(ex);
		} 
       
        finally{
        	
        }
		
		return rs;
	}
	
	public void resultSetHashMap(String sqlQuery){
		
		rsHashMap().clear();
			
		try {
			//ResultSet rs = null;
			
			System.out.println("****************Trying to query Address table**************");	
			do {
				rs = getResultSet(sqlQuery);
	
			} while (rs==null);
			System.out.println("SQL Query : " +  sqlQuery);
			//rs.next();
			ResultSetMetaData metaData = rs.getMetaData();
			int rowCount =  rs.getRow();
            rowCount=rowCount+1;
            int columCount = metaData.getColumnCount(); 
            int i=0;
            while(rs.next()){
            	//rs.next();
            	for(int j=1;j<columCount+1;j++){
            		if(metaData.getColumnTypeName(j)=="DATE" && rs.getDate(j)!=null){
            			Format formatter = new SimpleDateFormat("dd-MMM-yy"); 
            			rsHashMap().put(metaData.getColumnLabel(j), formatter.format(rs.getDate(j)));
            		}
            		else
            		{
            			rsHashMap().put(metaData.getColumnLabel(j), rs.getString(j));
            		}
            		
            		System.out.println(metaData.getColumnLabel(j) + " : " + rsHashMap().get(metaData.getColumnLabel(j)));
            	}
            	i++;
            }
            /*for(int i=1;i<rowCount;i++){
            	for(int j=1;j<columCount+1;j++){
            		if(metaData.getColumnTypeName(j)=="DATE" && rs.getDate(j)!=null){
            			Format formatter = new SimpleDateFormat("dd-MMM-yy"); 
            			rsHashMap().put(metaData.getColumnLabel(j), formatter.format(rs.getDate(j)));
            		}
            		else
            		{
            			rsHashMap().put(metaData.getColumnLabel(j), rs.getString(j));
            		}
            		
            		System.out.println(metaData.getColumnLabel(j) + " : " + rsHashMap().get(metaData.getColumnLabel(j)));
            	}
            }*/
			System.out.println("****************Done query Address table**************");	
			rs.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected   String getField(ResultSet rs, String fieldName) {

		String result=null;
		try {
			//rs.next();
			result = rs.getString(fieldName);
			rs.beforeFirst();
			}

			catch (SQLException ex) {
			}
		return result;
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
