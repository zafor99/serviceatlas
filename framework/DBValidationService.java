package framework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.internal.seleniumemulation.GetExpression;

import com.bn.qa.xobject.script.XObjectTestScript;
import com.bn.qa.xobject.vp.XManualVerificationPoint;
import com.bn.qa.xobject.vp.XTestDataVerificationPoint;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.VpUtil;

import utils.BNTimer;
import utils.MySQLDBConnection;
import utils.OracleDBConnection;
import utils.SQLServerDBConnection;
import framework.AtlasScriptbase;
/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  November 13, 2013
 */
public  class DBValidationService extends AtlasScriptbase
{
	//private static Statement statement = null;
	//private static Connection connection = null; 
	private static Logger logger = Logger.getLogger(DBValidationService.class);
	public DBValidationService(){
		
	}
	public PFS_DB pfsDB(){
		return new PFS_DB();
	}
	public OracleDwDB datawareHouseDB(){
		return new OracleDwDB();
	}
	public OracleEDSDB edsDB(){
		return new OracleEDSDB();
	}
	public  OracleOrderStatusDB OrderStatusDB(){
		return new OracleOrderStatusDB();
	}
	public OraclePayAuthDB payAuthDB(){
		return new OraclePayAuthDB();
	}
	public SQLServerBNInc BNIncDB(){
		return new SQLServerBNInc();
	}
	public MYSQLServerSalesRank salesRankDB(){
		return new MYSQLServerSalesRank();
	}
	public SQLServerBooksDB booksDB(){
		return new SQLServerBooksDB();
	}
	public class OraclePayAuthDB{
		private OracleDBConnection dbConnect = null;
		/*
		 * Connecting to Oracle Dataware HouseDB
		 */
		public OraclePayAuthDB(){
			try {
				dbConnect = new OracleDBConnection("jdbc:oracle:thin:@//injpaymntdb01.barnesandnoble.com:1521/injpaymntdb", "qa_user", "qa_user");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		public OracleDBConnection getDbConnect(){
			return dbConnect;
		}
		public boolean verifyOrderExistInActivityLogTable(String vpName,String orderNumber){
			boolean result = false;
			result = verifyOrderExist(vpName, orderNumber, "ACTIVITY_LOG");
			 return result;
		 }
		public boolean verifyOrderInActivityLogTable(String orderNumber,String vpName){
			
			
			boolean result = false;
			result = verifyOrderDetailsInTable(orderNumber, "PAYAUTH.ACTIVITY_LOG", vpName);
			return result;
		}
		public boolean verifyOrderDetailsInTable(String orderNumber,String tableName,String vpName){
			boolean result = false;
			boolean connected = false;

			ITestData testData = null;
			String query ="SELECT * from "+tableName+" where order_number='" + orderNumber + "'";

			System.out.println(query);
			ResultSet rs = dbConnect.getResultSet(query);

			connected = isDBConnected("PayAuthDB",query);
			if(connected){
				try
				{
					rs.next();
					ResultSetMetaData metaData = rs.getMetaData();
					int rowCount =  rs.getRow();
					rowCount=rowCount+1;
					int columCount = metaData.getColumnCount(); 
					String[][] tableData = new String[rowCount][columCount];

					for(int i=0;i<rowCount;i++){
						for(int j=1;j<columCount+1;j++){
							if(i==0){
								tableData[i][j-1] = metaData.getColumnLabel(j); 
							}
							else{
								tableData[i][j-1] = rs.getString(j);	
							}
						}
					}
					testData = VpUtil.getTestData(tableData);
					XTestDataVerificationPoint vp = new XTestDataVerificationPoint(vpName, getExecutingScript(),testData);
					result = vp.performTest();
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}

			}
			else
			{
				logError("PayAuthDB Database data not retrieved");
			}
			return result;

		}
		public boolean verifyOrderExist(String vpName,String orderNumber,String tableName){
			boolean result = false;
			boolean connected = false;
			String query = "Select count(*) from PAYAUTH."+tableName +" where order_number ='" + orderNumber + "'";
			System.out.println(query);
			ResultSet rs = dbConnect.getResultSet(query);
			connected = isDBConnected("DwDB",query);
			if(connected){
				try
				{
					if(rs!=null){
						rs.next();
						int count = Integer.parseInt(rs.getString(1));
						//int count = rs.getRow();
						System.out.println("no of item "+count);
						result = getExecutingScript().vpManual(vpName, 1, count).performTest();
					}
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}
			}
			else
			{
				logError("PayAuthDB Database data not retrieved");
			}
			
			return result;

		}
	}
	
	public boolean isDBConnected(String dbName,String query){
		logger.info("isDBConnected("+dbName+"),("+ query+")");
		SQLServerDBConnection sqlServerDBConnect = null;
		SQLServerDBConnection mySQLDBConnect= null;
		//MySQL
		//mySQLDBConnection mySQLDBConnect= null;
		OracleDBConnection oracleDBConnect = null;
		boolean result = false;
		ResultSet rs = null;
		String newQuery = null;
		int getTime = 0;

		newQuery = query.substring(query.toLowerCase().indexOf("from"));
		newQuery = "Select COUNT(*) " + newQuery;
		System.out.println(newQuery);
		
		int currentRow = 0;
		
		BNTimer timer = new BNTimer();
		timer.start();
		do{
			
			if(dbName.contentEquals("BooksDB")){
				SQLServerBooksDB tempDB= new SQLServerBooksDB();
				sqlServerDBConnect = tempDB.getDbConnect();
				rs = sqlServerDBConnect.getResultSet(newQuery);
			}
			else if(dbName.contentEquals("SalesRankDB")){
				//TODO
				MYSQLServerSalesRank tempDB;
					tempDB = new MYSQLServerSalesRank();
					mySQLDBConnect = tempDB.getDbConnect();
				
				
				rs = mySQLDBConnect.getResultSet(newQuery);
			}
			else if(dbName.contentEquals("BNIncDB")){
				SQLServerBNInc tempDB= new SQLServerBNInc();
				sqlServerDBConnect = tempDB.getDbConnect();
				rs = sqlServerDBConnect.getResultSet(newQuery);
			}
			else if(dbName.contentEquals("DwDB")){
				OracleDwDB tempDB= new OracleDwDB();
				oracleDBConnect = tempDB.getDbConnect();
				rs = oracleDBConnect.getResultSet(newQuery);
			}
			else if(dbName.contentEquals("OrderStatusDB")){
				OracleOrderStatusDB tempDB= new OracleOrderStatusDB();
				oracleDBConnect = tempDB.getDbConnect();
				rs = oracleDBConnect.getResultSet(newQuery);
			}
			else if(dbName.contentEquals("PayAuthDB")){
				OraclePayAuthDB tempDB= new OraclePayAuthDB();
				oracleDBConnect = tempDB.getDbConnect();
				rs = oracleDBConnect.getResultSet(newQuery);
			}
			else if(dbName.contentEquals("EDSDB")){
				OracleEDSDB tempDB= new OracleEDSDB();
				oracleDBConnect = tempDB.getDbConnect();
				rs = oracleDBConnect.getResultSet(newQuery);
			}

			try {
				rs.next();
				currentRow = rs.getInt(1);
				if(currentRow>0){
					result = true;
					timer.stop();
					break;
				}
				System.out.print("Current row: " + currentRow+" ...");
				getTime = timer.getElapsedTime();
				if(getTime>= 20){
					logInfo("Database get recordset timed out at " + getTime );
					timer.stop();
					break;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}while (currentRow<1);
		System.out.println("Database connected in elapsed time : " + getTime);
		return result;
	}
	public class SQLServerBooksDB{
		private SQLServerDBConnection dbConnect = null;
		public SQLServerBooksDB(){
			try {
				dbConnect = new SQLServerDBConnection("jdbc:sqlserver://10.1.161.88;database=web;integratedSecurity=true", "mquraishi", "Nopassword15");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		public SQLServerDBConnection getDbConnect(){
			return dbConnect;
		}
		private int getRandomNumber(int max){
			Random rnd = new Random();
			int random = rnd.nextInt(max);
			return random;
		}
		
		public String getRandomEANForProduct(String amount, String productType, String orderType){
			logger.info("getRandomEANForProduct("+amount+"),("+ productType+"),("+orderType+")");
			String ean = null;
			String[] amountlist = null;
			amountlist = amount.split("-");
			ResultSet rs = null;
			int index = 1;
			int count = getResultSetCount_SQLServerBooksDB(amount,productType, orderType);
			// Product Type BK for book and ER for eBooks
			// Order Type 9 is for preorder books and 7 for current stocks
			String sql = "select top " + count + " vd.ean, vd.bnprice " +
			             "from title t " +
			             "join volatiledata vd on t.ean = vd.ean " +
			             "where " + 
			             "t.productcode ='" + productType + "'" +
			             "and vd.deliverycode in ('" + orderType + "') " +
			             "and vd.webFlag='Y' " +
			             "and vd.productDisplayFlag='Y' " +
			             "and vd.bnprice > " + amountlist[0] +
			             "and vd.bnprice < " + amountlist[1] +
			             "and t.deliverycode in ('" + orderType + "') " +
			             "and t.webFlag='Y' " +
			             "and t.productDisplayFlag='Y'" +
			             "and t.ean like '978%' " +
			             "order by bnprice asc ";
			System.out.println(sql);
			int random = getRandomNumber(count);
			System.out.println("Random Number generated : " + random);
			
			rs = dbConnect.getResultSet(sql);
			try {
				
				while(rs.next()){
					if(index == random){
						ean = rs.getString("ean");
						System.out.println("EAN Retrieved : " + ean);
					}
					index++;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("EAN Captured : " + ean);
			return ean;
			
		}
		private int getResultSetCount_SQLServerBooksDB(String amount, String productType, String orderType) {
			logger.info("getResultSetCount_SQLServerBooksDB("+amount+"),("+ productType+"),("+orderType+")");
			int count;
			String[] amountlist = null;
			amountlist = amount.split("-");
			int currentRow = 0;
			
			// Product Type BK for book and ER for eBooks
			// Order Type 9 is for preorder books and 7 for current stocks
			
			String sql = "select COUNT(vd.ean) as 'rowcount' " +
			"from title t " +
			"join volatiledata vd on t.ean = vd.ean " +
			"where " + 
			"t.productcode ='" + productType + "' " +
			"and vd.deliverycode in ('" + orderType + "') " +
			"and vd.webFlag='Y' " +
			"and vd.productDisplayFlag='Y' " +
			"and vd.bnprice > " + amountlist[0] +
			"and vd.bnprice < " + amountlist[1] +
			"and t.deliverycode in ('" + orderType + "') " +
			"and t.webFlag='Y' " +
			"and t.productDisplayFlag='Y'" +
			"and t.ean like '978%' ";
			System.out.println(sql);
			ResultSet rs = dbConnect.getResultSet(sql);

			try {
				rs.next();
				currentRow = rs.getInt("rowcount");
				System.out.println("Resultset row count : " + currentRow);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return currentRow;
		}
		
	}
	public class MYSQLServerSalesRank{
		//private MySQLDBConnection dbConnect = null;
		private SQLServerDBConnection dbConnect = null;
		public MYSQLServerSalesRank(){
			//Regular QA DB
			//dbConnect = new SQLServerDBConnection("jdbc:sqlserver://INYINTRADB01;database=Sales;integratedSecurity=true", "EAIUser", "ea1");
			//PE DB
//				dbConnect = new SQLServerDBConnection("jdbc:sqlserver://injpdmdbs01;database=Sales;integratedSecurity=true", "atlas_user", "Atlas_user1");
//Connection update on 047/27/2015
				
//			dbConnect = new MySQLDBConnection("jdbc:mysql://tqamkpsd01.bnqa.bn-dev.com/sales?noAccessToProcedureBodies=true", "atlas_user", "atlas_user");
			
			try{
				dbConnect = new SQLServerDBConnection("jdbc:sqlserver://10.1.161.88;database=Sales;integratedSecurity=true", "atlas_user", "Atlas_user1");
//				dbConnect = new MySQLDBConnection("jdbc:mysql://tqamkpsd01.bnqa.bn-dev.com:3306", "atlas_user", "atlas_user");
				System.out.println(dbConnect.isConnected());
			}
			catch (Exception e) {//(Exception e) {
				e.printStackTrace();
			}
		}
		//For using with MySQL
/*		public MySQLDBConnection getDbConnect(){
			return dbConnect;
		}
*/		public SQLServerDBConnection getDbConnect(){
			return dbConnect;
		}

		public boolean verifyOrderExist(String vpName,String orderNumber,String tableName){
			logger.info("verifyOrderExist("+vpName+"),("+ orderNumber+"),("+tableName+")");
			boolean result = false;
			boolean connected = false;
			String query = "Select count(*) from dbo."+tableName+" where orderNumber ='" + orderNumber + "'";
			//MySQL
			//String query = "Select count(*) from sales."+tableName+" where orderNumber ='" + orderNumber + "'";
			System.out.println(query);
			getExecutingScript().delayFor(5);
			connected = isDBConnected("SalesRankDB",query);
			if(connected){
				ResultSet rs = dbConnect.getResultSet(query);
				try
				{
					if(rs!=null){
						rs.next();
						int count = Integer.parseInt(rs.getString(1));
						//int count = rs.getRow();
						System.out.println("no of item "+count);
						result = getExecutingScript().vpManual(vpName, 1, count).performTest();
					}
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}
			}
			else
			{
				logError("SalesRankDB Database data not retrieved");
			}

			return result;
		}
		public boolean verifyOrderDetailsInTable(String orderNumber,String tableName,String vpName){
			logger.info("verifyOrderDetailsInTable("+orderNumber+"),("+ tableName+"),("+vpName+")");
			boolean result = false;
			boolean connected = false;

			ITestData testData = null;
			//MySQL
			//String query ="SELECT * from sales."+tableName+" where orderNumber='" + orderNumber + "'";
			String query ="SELECT * from dbo."+tableName+" where orderNumber='" + orderNumber + "'";

			System.out.println(query);
			ResultSet rs = dbConnect.getResultSet(query);

			connected = isDBConnected("SalesRankDB",query);
			if(connected){
				try
				{
					rs.next();
					ResultSetMetaData metaData = rs.getMetaData();
					int rowCount =  rs.getRow();
					rowCount=rowCount+1;
					int columCount = metaData.getColumnCount(); 
					String[][] tableData = new String[rowCount][columCount];

					for(int i=0;i<rowCount;i++){
						for(int j=1;j<columCount+1;j++){
							if(i==0){
								tableData[i][j-1] = metaData.getColumnLabel(j); 
							}
							else{
								tableData[i][j-1] = rs.getString(j);	
							}
						}
					}
					testData = VpUtil.getTestData(tableData);
					XTestDataVerificationPoint vp = new XTestDataVerificationPoint(vpName, getExecutingScript(),testData);
					result = vp.performTest();
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}

			}
			else
			{
				logError("SalesRankDB Database data not retrieved");
			}
			return result;

		}
		public boolean verifyOrderInOrderHeaderTable(String orderNumber,String vpName){
			logger.info("verifyOrderInOrderHeaderTable("+orderNumber+"),("+vpName+")");
			boolean result = false;
			result = verifyOrderDetailsInTable(orderNumber, "OrderHeader", vpName);
			//MySQL
			//result = verifyOrderDetailsInTable(orderNumber, "digitalHeader", vpName);
			return result;
		}
		public boolean verifyOrderInOrderDetailTable(String orderNumber,String vpName){
			logger.info("verifyOrderInOrderDetailTable("+orderNumber+"),("+vpName+")");
			boolean result = false;
			result = verifyOrderDetailsInTable(orderNumber, "OrderDetail", vpName);
			//MySQL
//			result = verifyOrderDetailsInTable(orderNumber, "digitalDetail", vpName);
			return result;
		}
		public boolean verifyOrderExistInOrderHeaderTable(String vpName,String orderNumber){
			logger.info("verifyOrderExistInOrderHeaderTable("+orderNumber+"),("+vpName+")");
			 boolean result = false;
			 result = verifyOrderExist(vpName, orderNumber, "OrderHeader");
			 //result = verifyOrderExist(vpName, orderNumber, "digitalHeader");
			 return result;
		 }
		public boolean verifyOrderExistInOrderDetailTable(String vpName,String orderNumber){
			logger.info("verifyOrderExistInOrderDetailTable("+orderNumber+"),("+vpName+")");
			 boolean result = false;
			 result = verifyOrderExist(vpName, orderNumber, "OrderDetail");
			 //MySQL
			// result = verifyOrderExist(vpName, orderNumber, "digitalDetail");
			 return result;
		 }
		public boolean verifyOrderSourceIsAtlas(String vpName,String orderNumber) throws ClassNotFoundException 
		{
			logger.info("verifyOrderSourceIsAtlas("+orderNumber+"),("+vpName+")");
			String query = "Select UpdUserId from dbo.OrderHeader where orderNumber ='" + orderNumber + "'";
			//mySQL
			//String query = "Select UpdUserId from sales.digitalHeader where orderNumber ='" + orderNumber + "'";
			System.out.println(query);
			ResultSet rs1 = dbConnect.getResultSet(query);
			boolean result = false;

			try
			{
				if(rs1!=null){
					rs1.next();
					String source = rs1.getString("UpdUserId");					
					result = getExecutingScript().vpManual(vpName, "Atlas_user",source).performTest();
					//MYSQL
					//result = getExecutingScript().vpManual(vpName, "atlas_user@10.5.162.",source).performTest();
				}
			}
			catch (Exception ex) 
			{
				logException(ex);
			}
			finally
			{
				try 
				{ 
					rs1.close();
					dbConnect.close();
				} 
				catch(SQLException ex)
				{
					logException(ex);
				}
			}
			return result;
		}
	}
	public class SQLServerBNInc{
		private SQLServerDBConnection dbConnect = null;
		public SQLServerBNInc(){
			try {
//				dbConnect = new SQLServerDBConnection("jdbc:sqlserver://DWCMEDDB01;integratedSecurity=false", "EAIUser", "ea1");
				dbConnect = new SQLServerDBConnection("jdbc:sqlserver://DWCMEDDB01;integratedSecurity=false", "EAIUser", "ea1");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		public SQLServerDBConnection getDbConnect(){
			return dbConnect;
		}
		
		public boolean verifyOrderDetailsInTable(String orderNumber,String tableName,String vpName){
			logger.info("verifyOrderDetailsInTable("+orderNumber+"),("+ tableName+"),("+vpName+")");
			boolean result = false;
			boolean connected = false;

			ITestData testData = null;
			String query ="SELECT * from dbo."+tableName+" where order_number='" + orderNumber + "'";

			System.out.println(query);
			ResultSet rs = dbConnect.getResultSet(query);

			connected = isDBConnected("BNIncDB",query);
			if(connected){
				try
				{
					rs.next();
					ResultSetMetaData metaData = rs.getMetaData();
					int rowCount =  rs.getRow();
					rowCount=rowCount+1;
					int columCount = metaData.getColumnCount(); 
					String[][] tableData = new String[rowCount][columCount];

					for(int i=0;i<rowCount;i++){
						for(int j=1;j<columCount+1;j++){
							if(i==0){
								tableData[i][j-1] = metaData.getColumnLabel(j); 
							}
							else{
								tableData[i][j-1] = rs.getString(j);	
							}
						}
					}
					testData = VpUtil.getTestData(tableData);
					XTestDataVerificationPoint vp = new XTestDataVerificationPoint(vpName, getExecutingScript(),testData);
					result = vp.performTest();
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}

			}
			else
			{
				logError("BNIncDB Database data not retrieved");
			}
			return result;

		}
		public boolean verifyOrderInOrderHeaderTable(String orderNumber,String vpName){
			logger.info("verifyOrderInOrderHeaderTable("+orderNumber+"),("+vpName+")");
			boolean result = false;
			result = verifyOrderDetailsInTable(orderNumber, "stage_Order_Header", vpName);
			return result;
		}
		public boolean verifyOrderInOrderDetailTable(String orderNumber,String vpName){
			logger.info("verifyOrderInOrderDetailTable("+orderNumber+"),("+vpName+")");
			boolean result = false;
			result = verifyOrderDetailsInTable(orderNumber, "stage_Order_detail", vpName);
			return result;
		}
		public boolean verifyOrderInOrderDetailActivityTable(String orderNumber,String vpName){
			logger.info("verifyOrderInOrderDetailActivityTable("+orderNumber+"),("+vpName+")");
			boolean result = false;
			result = verifyOrderDetailsInTable(orderNumber, "stage_Order_detail_activity", vpName);
			return result;
		}
		public boolean verifyOrderInOrderCCNumberActivityTable(String orderNumber,String vpName){
			logger.info("verifyOrderInOrderCCNumberActivityTable("+orderNumber+"),("+vpName+")");
			boolean result = false;
			result = verifyOrderDetailsInTable(orderNumber, "stage_ORDER_CCNUMBER", vpName);
			return result;
		}
		public boolean verifyOrderExistInOrderDetailTable(String vpName,String orderNumber){
			logger.info("verifyOrderExistInOrderDetailTable("+orderNumber+"),("+vpName+")");
			boolean result = false;
			result = verifyOrderExist(vpName, orderNumber, "stage_Order_detail");
			 return result;
		 }

		public boolean verifyOrderExistInOrderHeaderTable(String vpName,String orderNumber){
			logger.info("verifyOrderExistInOrderHeaderTable("+orderNumber+"),("+vpName+")");
			boolean result = false;
			result = verifyOrderExist(vpName, orderNumber, "stage_Order_Header");
			 return result;
		 }
		public boolean verifyOrderExistInOrderDetailActivityTable(String vpName,String orderNumber){
			logger.info("verifyOrderExistInOrderDetailActivityTable("+orderNumber+"),("+vpName+")");
			boolean result = false;
			result = verifyOrderExist(vpName, orderNumber, "stage_Order_detail_activity");
			 return result;
		 }
		public boolean verifyOrderExistInOrderCCNumberTable(String vpName,String orderNumber){
			logger.info("verifyOrderExistInOrderCCNumberTable("+orderNumber+"),("+vpName+")");
			boolean result = false;
			result = verifyOrderExist(vpName, orderNumber, "stage_ORDER_CCNUMBER");
			 return result;
		 }
		public boolean verifyDetailsInCustomerTable(String customerID,String vpName){
			logger.info("verifyDetailsInCustomerTable("+customerID+"),("+vpName+")");
			boolean result = false;
			boolean connected = false;

			ITestData testData = null;
			String query ="SELECT * from dbo.stage_customer where customer_id='" + customerID + "'";

			System.out.println(query);
			ResultSet rs = dbConnect.getResultSet(query);

			connected = isDBConnected("BNIncDB",query);
			if(connected){
				try
				{
					rs.next();
					ResultSetMetaData metaData = rs.getMetaData();
					int rowCount =  rs.getRow();
					rowCount=rowCount+1;
					int columCount = metaData.getColumnCount(); 
					String[][] tableData = new String[rowCount][columCount];

					for(int i=0;i<rowCount;i++){
						for(int j=1;j<columCount+1;j++){
							if(i==0){
								tableData[i][j-1] = metaData.getColumnLabel(j); 
							}
							else{
								tableData[i][j-1] = rs.getString(j);	
							}
						}
					}
					testData = VpUtil.getTestData(tableData);
					XTestDataVerificationPoint vp = new XTestDataVerificationPoint(vpName, getExecutingScript(),testData);
					result = vp.performTest();
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}

			}
			else
			{
				logError("BNIncDB Database data not retrieved");
			}
			return result;
		}
		public boolean verifyCustomerExist(String vpName,String customerID){
			logger.info("verifyCustomerExist("+vpName+"),("+customerID+")");
			boolean result = false;
			boolean connected = false;
			String query = "Select count(*) from dbo.stage_customer where customer_id ='" + customerID + "'";
			System.out.println(query);
			ResultSet rs = dbConnect.getResultSet(query);
			connected = isDBConnected("BNIncDB",query);
			if(connected){
				try
				{
					if(rs!=null){
						rs.next();
						int count = Integer.parseInt(rs.getString(1));
						//int count = rs.getRow();
						System.out.println("no of item "+count);
						result = getExecutingScript().vpManual(vpName, 1, count).performTest();
					}
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}
			}
			else
			{
				logError("BNIncDB Database data not retrieved");
			}
			
			return result;

		}
		public boolean verifyOrderExist(String vpName,String orderNumber,String tableName){
			logger.info("verifyOrderExist("+vpName+"),("+ orderNumber+"),("+tableName+")");
			boolean result = false;
			boolean connected = false;
			String query = "Select count(*) from dbo."+tableName+" where order_number ='" + orderNumber + "'";
			System.out.println(query);
			ResultSet rs = dbConnect.getResultSet(query);
			connected = isDBConnected("BNIncDB",query);
			if(connected){
				try
				{
					if(rs!=null){
						rs.next();
						//int count = rs.getRow();
						int count = Integer.parseInt(rs.getString(1));
						System.out.println("no of item "+count);
						result = getExecutingScript().vpManual(vpName, 1, count).performTest();
					}
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}
			}
			else
			{
				logError("BNIncDB Database data not retrieved");
			}

			return result;
		}
		 public boolean verifyOrderExist(String vpName,String orderNumber){
			 boolean result = false;
			 String query = "Select count(*) from dbo.STAGE_ORDER_HEADER where order_number ='" + orderNumber + "'";
			 System.out.println(query);
			 ResultSet rs = dbConnect.getResultSet(query);
			 try
				{
					if(rs!=null){
						rs.next();
						//int count = Integer.parseInt(rs.getString("COUNT(*)"));
						int count = rs.getRow();
						System.out.println("no of item "+count);
						result = getExecutingScript().vpManual(vpName, 1, count).performTest();
					}
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}
			 return result;
		 }
	}
	public class OracleDwDB{
		private OracleDBConnection dbConnect = null;
		/*
		 * Connecting to Oracle Dataware HouseDB
		 */
		public OracleDwDB(){
			try {
//				dbConnect = new OracleDBConnection("jdbc:oracle:thin:@//injpaymntdb01.barnesandnoble.com:1521/injpaymntdb", "qa_user", "qa_user");
				dbConnect = new OracleDBConnection("jdbc:oracle:thin:@//inydwdb01:1521/EAI_XA_DW.barnesandnoble.com", "qa_user", "qa_user");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		public OracleDBConnection getDbConnect(){
			return dbConnect;
		}
		public boolean verifyOrderExistInOrderHeaderTable(String vpName,String orderNumber){
			boolean result = false;
			result = verifyOrderExist(vpName, orderNumber, "stage_order_header");
			 return result;
		 }
		
		public boolean verifyOrderInOrderHeaderTable(String orderNumber,String vpName){
	
	
			boolean result = false;
			result = verifyOrderDetailsInTable(orderNumber, "bndw.stage_order_header", vpName);
			return result;
		}
		public boolean verifyOrderExistInOrderDetailTable(String vpName,String orderNumber){
			boolean result = false;
			result = verifyOrderExist(vpName, orderNumber, "stage_Order_detail");
			 return result;
		 }
		public boolean verifyOrderInOrderDetailTable(String orderNumber,String vpName){
			
			boolean result = false;
			result = verifyOrderDetailsInTable(orderNumber, "bndw.stage_Order_detail", vpName);
			return result;
		}

		public boolean verifyOrderExistInOrderDetailActivityTable(String vpName,String orderNumber){
			boolean result = false;
			result = verifyOrderExist(vpName, orderNumber, "stage_Order_detail_activity");
			 return result;
		 }
		public boolean verifyOrderInOrderDetailActivityTable(String orderNumber,String vpName){
			
			boolean result = false;
			result = verifyOrderDetailsInTable(orderNumber, "bndw.stage_Order_detail_activity", vpName);
			return result;
		}

		public boolean verifyOrderExistInOrderCCNumberTable(String vpName,String orderNumber){
			boolean result = false;
			result = verifyOrderExist(vpName, orderNumber, "stage_ORDER_CCNUMBER");
			 return result;
		 }
		public boolean verifyOrderDetailsInTable(String orderNumber,String tableName,String vpName){
			boolean result = false;
			boolean connected = false;

			ITestData testData = null;
			String query ="SELECT * from "+tableName+" where order_number='" + orderNumber + "'";

			System.out.println(query);
			ResultSet rs = dbConnect.getResultSet(query);

			connected = isDBConnected("DwDB",query);
			if(connected){
				try
				{
					rs.next();
					ResultSetMetaData metaData = rs.getMetaData();
					int rowCount =  rs.getRow();
					rowCount=rowCount+1;
					int columCount = metaData.getColumnCount(); 
					String[][] tableData = new String[rowCount][columCount];

					for(int i=0;i<rowCount;i++){
						for(int j=1;j<columCount+1;j++){
							if(i==0){
								tableData[i][j-1] = metaData.getColumnLabel(j); 
							}
							else{
								tableData[i][j-1] = rs.getString(j);	
							}
						}
					}
					testData = VpUtil.getTestData(tableData);
					XTestDataVerificationPoint vp = new XTestDataVerificationPoint(vpName, getExecutingScript(),testData);
					result = vp.performTest();
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}

			}
			else
			{
				logError("DwDB Database data not retrieved");
			}
			return result;

		}
		public boolean verifyOrderInOrderCCNumberTable(String orderNumber,String vpName){

			boolean result = false;
			result = verifyOrderDetailsInTable(orderNumber, "bndw.stage_ORDER_CCNUMBER", vpName);
			return result;
		}
		public boolean verifyOrderExist(String vpName,String orderNumber,String tableName){
			boolean result = false;
			boolean connected = false;
			String query = "Select count(*) from bndw."+tableName +" where order_number ='" + orderNumber + "'";
			System.out.println(query);
			ResultSet rs = dbConnect.getResultSet(query);
			connected = isDBConnected("DwDB",query);
			if(connected){
				try
				{
					if(rs!=null){
						rs.next();
						int count = Integer.parseInt(rs.getString(1));
						//int count = rs.getRow();
						System.out.println("no of item "+count);
						result = getExecutingScript().vpManual(vpName, 1, count).performTest();
					}
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}
			}
			else
			{
				logError("DwDB Database data not retrieved");
			}
			
			return result;

		}
		
		public boolean verifyOrderExist(String vpName,String orderNumber){
			boolean result = false;
			String query = "Select count(*) from bndw.stage_order_header where order_number ='" + orderNumber + "'";
			System.out.println(query);
			ResultSet rs = dbConnect.getResultSet(query);
			try
			{
				if(rs!=null){
					rs.next();
					//int count = Integer.parseInt(rs.getString("COUNT(*)"));
					int count = rs.getRow();
					System.out.println("no of item "+count);
					result = getExecutingScript().vpManual(vpName, 1, count).performTest();
				}
			}
			catch (Exception ex) 
			{
				logException(ex);
			}
			finally
			{
				try 
				{ 
					rs.close();
					dbConnect.close();
				} 
				catch(SQLException ex)
				{
					logException(ex);
				}

			}
			return result;

		}
		public boolean verifyCustomerExist(String vpName,String customerID){
			boolean result = false;
			boolean connected = false;
			String query = "Select count(*) from bndw.stage_customer where customer_id ='" + customerID + "'";
			System.out.println(query);
			ResultSet rs = dbConnect.getResultSet(query);
			connected = isDBConnected("DwDB",query);
			if(connected){
				try
				{
					if(rs!=null){
						rs.next();
						//int count = rs.getRow();
						int count = Integer.parseInt(rs.getString(1));
						System.out.println("no of item "+count);
						result = getExecutingScript().vpManual(vpName, 1, count).performTest();
					}
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}
			}
			else
			{
				logError("DwDB Database data not retrieved");
			}
			
			return result;

		}
		public boolean verifyDetailsInCustomerTable(String customerID,String vpName){
			
			boolean result = false;
			boolean connected = false;
			
			ITestData testData = null;
			String query ="SELECT * from bndw.stage_customer where customer_id='" + customerID + "'";

			System.out.println(query);
			ResultSet rs = dbConnect.getResultSet(query);
			
			connected = isDBConnected("DwDB",query);
			if(connected){
				try
				{
					rs.next();
					ResultSetMetaData metaData = rs.getMetaData();
					int rowCount =  rs.getRow();
					rowCount=rowCount+1;
					int columCount = metaData.getColumnCount(); 
					String[][] tableData = new String[rowCount][columCount];
					
					for(int i=0;i<rowCount;i++){
						for(int j=1;j<columCount+1;j++){
							if(i==0){
								tableData[i][j-1] = metaData.getColumnLabel(j); 
							}
							else{
								tableData[i][j-1] = rs.getString(j);	
							}
						}
					}
					testData = VpUtil.getTestData(tableData);
					XTestDataVerificationPoint vp = new XTestDataVerificationPoint(vpName, getExecutingScript(),testData);
					result = vp.performTest();
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}

			}
			else
			{
				logError("DwDB Database data not retrieved");
			}
			return result;
		}

	}
	
	public class OracleEDSDB{
		private OracleDBConnection dbConnect = null;
		public OracleEDSDB(){
			try {
				//dbConnect = new OracleDBConnection("jdbc:oracle:thin:@//injedsdb01.barnesandnoble.com:1521/IEDSDB", "EDS_DATAREADER", "EDS_DATAREADER");
				dbConnect = new OracleDBConnection("jdbc:oracle:thin:@//injqa2rac1n01.barnesandnoble.com/dcloud4", "EDS_DATAREADER", "EDS_DATAREADER");
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		public OracleDBConnection getDbConnect(){
			return dbConnect;
		}
		public boolean verifyDeatailsInPeriodicalitemTable(String customerID,String ean,String vpName){
			
			boolean result = false;
			result = verifyDetailsInTable("customerid", customerID, "ean", ean, "periodicalitem", vpName);
		
			return result;
		}
		public boolean verifyDetailsInTable(String field1Name,String fields1Value,String field2Name,String fields2Value,String tableName,String vpName){
			boolean result = false;
			boolean connected = false;

			ITestData testData = null;
			String query ="SELECT * from eds."+tableName+" where "+field1Name+" = '" + fields1Value + "' and "+field2Name+" = '"+fields2Value+"'";

			System.out.println(query);
			ResultSet rs = dbConnect.getResultSet(query);

			connected = isDBConnected("EDSDB",query);
			if(connected){
				try
				{
					rs.next();
					ResultSetMetaData metaData = rs.getMetaData();
					int rowCount =  rs.getRow();
					rowCount=rowCount+1;
					int columCount = metaData.getColumnCount(); 
					String[][] tableData = new String[rowCount][columCount];

					for(int i=0;i<rowCount;i++){
						for(int j=1;j<columCount+1;j++){
							if(i==0){
								tableData[i][j-1] = metaData.getColumnLabel(j); 
							}
							else{
								tableData[i][j-1] = rs.getString(j);	
							}
						}
					}
					testData = VpUtil.getTestData(tableData);
					XTestDataVerificationPoint vp = new XTestDataVerificationPoint(vpName, getExecutingScript(),testData);
					result = vp.performTest();
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}

			}
			else
			{
				logError("EDSDB Database data not retrieved");
			}
			return result;

		}
	}
	public class OracleOrderStatusDB{
		private OracleDBConnection dbConnect = null;
		/*
		 * Connecting to Oracle OrderStatusDB
		 */
		
		public OracleOrderStatusDB(){
			try {
				
				dbConnect = new OracleDBConnection("jdbc:oracle:thin:@//10.1.161.108:1521/osrodb.barnesandnoble.com", "qa_user", "qa_user");
//				dbConnect = new OracleDBConnection("jdbc:oracle:thin:@//10.1.161.108:1521/OSRODB.barnesandnoble.com", "qa_user", "qa_user");
//				dbConnect = new OracleDBConnection("jdbc:oracle:thin:@//10.1.161.34:1521/OSMDB-QA.barnesandnoble.com", "qa_user", "qa_user");
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		public OracleDBConnection getDbConnect(){
			return dbConnect;
		}
		public boolean verifyOrderDetailsInTable(String orderNumber,String tableName,String vpName){
			boolean result = false;
			boolean connected = false;

			ITestData testData = null;
			String query ="SELECT * from orderstatus."+tableName+" where order_number='" + orderNumber + "'";

			System.out.println(query);
			ResultSet rs = dbConnect.getResultSet(query);

			connected = isDBConnected("OrderStatusDB",query);
			if(connected){
				try
				{
					rs.next();
					ResultSetMetaData metaData = rs.getMetaData();
					int rowCount =  rs.getRow();
					rowCount=rowCount+1;
					int columCount = metaData.getColumnCount(); 
					String[][] tableData = new String[rowCount][columCount];

					for(int i=0;i<rowCount;i++){
						for(int j=1;j<columCount+1;j++){
							if(i==0){
								tableData[i][j-1] = metaData.getColumnLabel(j); 
							}
							else{
								tableData[i][j-1] = rs.getString(j);	
							}
						}
					}
					testData = VpUtil.getTestData(tableData);
					XTestDataVerificationPoint vp = new XTestDataVerificationPoint(vpName, getExecutingScript(),testData);
					result = vp.performTest();
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}

			}
			else
			{
				logError("OrderStatusDB Database data not retrieved");
			}
			return result;

		}
		public boolean verifyOrderInOrderHeaderTable(String orderNumber,String vpName){
		
			boolean result = false;
			result = verifyOrderDetailsInTable(orderNumber, "order_header", vpName);
			AtlasScriptbase.writeResultToExternalSources(vpName, null, result);
			return result;
		}
		public boolean verifyOrderExist(String vpName,String orderNumber,String tableName){
			boolean result = false;
			boolean connected = false;
			String query = "Select count(*) from orderstatus."+tableName+" where order_number ='" + orderNumber + "'";
			System.out.println(query);
			connected = isDBConnected("OrderStatusDB",query);
			if(connected){
				if(!dbConnect.isConnected()){
					dbConnect.connect();
				}
				ResultSet rs = dbConnect.getResultSet(query);
				try
				{
					if(rs!=null){
						rs.next();
						int count = Integer.parseInt(rs.getString(1));
						System.out.println("No of row found for order :"+ orderNumber+" in Table :"+tableName + " is "+count);
						//count = Integer.parseInt(rs.getString(0));
						result = getExecutingScript().vpManual(vpName, 1, count).performTest();
					}
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}
			}
			else
			{
				logError("OrderStatusDB Database data not retrieved");
			}
			return result;

		}
		
		public String getEANFromOrderItemByOrderNumber(String orderNumber){
			
			String query ="select *from ORDERSTATUS.order_item where order_number='" + orderNumber + "'";
			ResultSet rs = dbConnect.getResultSet(query);
			String orderNo = "";
			boolean connected = false;
			BNTimer timer = new BNTimer();			
			connected = isDBConnected("OrderStatusDB",query);
			if(connected){
				timer.start();
				try
				{
					do {
						rs.next();
						orderNo = rs.getString("EAN");
						System.out.println(orderNo);
					} while (connected);
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}

			}
			else
			{
				logError("OrderStatusDB Database data not retrieved");
			}
			return orderNo;
			
		}
		
		public String getOrderNumberByCustomerEmail(String emailAddress){
			
			//emailAddress = "deviceauto25139@book.com";
			String query ="SELECT order_number from orderstatus.order_header where customer_email='" + emailAddress + "'";
			ResultSet rs = dbConnect.getResultSet(query);
			String orderNo = "";
			boolean connected = false;

			connected = isDBConnected("OrderStatusDB",query);
			BNTimer timer = new BNTimer();
			if(connected){
				try
				{
					timer.start();
					do {
						rs.next();						
						System.out.println("Current row : " + rs.getRow());
						if(rs.getRow()<1){
							rs = dbConnect.getResultSet(query);
						}
					} while (rs.getRow()<1 && timer.getElapsedTime()<10);
					
					orderNo = rs.getString("order_number");
					System.out.println(orderNo);
				}
				catch (Exception ex) 
				{
					logException(ex);
				}
				finally
				{
					try 
					{ 
						rs.close();
						dbConnect.close();
					} 
					catch(SQLException ex)
					{
						logException(ex);
					}

				}

			}
			else
			{
				logError("OrderStatusDB Database data not retrieved");
			}
			return orderNo;
			
		}

		 public boolean verifyOrderExistInOrderPaymentTable(String vpName,String orderNumber){
				boolean result = false;
				 result = verifyOrderExist(vpName, orderNumber, "Order_payment");
				 return result;
			 }
		 
		 public boolean verifyOrderExistInOrderItemTable(String vpName,String orderNumber){
			 boolean result = false;
			 result = verifyOrderExist(vpName, orderNumber, "Order_item");
			 return result;

		 }
		 /*
		  * Validate OrderDetails in Item Table in OSDB
		  */
		 
		 public boolean verifyOrderInOrderItemTable(String orderNumber,String vpName){
			 
			 boolean result = false;
			 result = verifyOrderDetailsInTable(orderNumber, "Order_item", vpName);
			
			 return result;
		 }
		 
		 
		 public boolean verifyOrderExistInOrderHeaderTable(String vpName,String orderNumber){
			
			boolean result = false;
			result = verifyOrderExist(vpName, orderNumber, "order_header");
			AtlasScriptbase.writeResultToExternalSources(vpName, null, result);
			 return result;
		 }
		 public boolean verifyOrderExistInOrderShipmentTableTable(String vpName,String orderNumber){
			 boolean result = false;
			 result = verifyOrderExist(vpName, orderNumber, "order_shipment");
			 return result;
		 }
		 
		 /*
		  * Validate OrderDetails in order_shipment Table in OSDB
		  */
		 
		 public boolean verifyOrderInOrderShipmentTable(String orderNumber,String vpName){
			 boolean result = false;
			 result =verifyOrderDetailsInTable(orderNumber, "order_shipment", vpName);
			 
			 return result;
		 }
	}
	public class PFS_DB {
		private OracleDBConnection dbConnect = null;
		
		/*
		 * Connecting to Oracle DBinjpfsdb01
		 */
		public PFS_DB(){
			try {
				dbConnect = new OracleDBConnection("jdbc:oracle:thin:@//injpfsdb01:1521/PFSNK.barnesandnoble.com", "PFS_UPDATE", "PFS_UPDATE");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		/*
		 * Verify the Status is trxheader Table based on Delivery Number
		 * @param vpName : Name of the verification Point
		 * @param deliveryNumber
		 * @param expectedStatus
		 */
		public boolean verifyStatusInTRXHEADER(String vpName,String deliveryNumber,String expectedStatus) throws ClassNotFoundException 
		{
			getExecutingScript().delayFor(5);
			String query = "Select status from pfsnk.trxheader where trxnum ='" + deliveryNumber + "'";
			ResultSet rs = dbConnect.getResultSet(query);
			boolean result = false;
			try
			{
				if(rs!=null){
					rs.next();
					String status = rs.getString("STATUS");					
					result = getExecutingScript().vpManual(vpName, status, expectedStatus).performTest();
				}
			}
			catch (Exception ex) 
			{
				logException(ex);
			}
			finally
			{
				try 
				{ 
					rs.close();
					dbConnect.close();
				} 
				catch(SQLException ex)
				{
					logException(ex);
				}

			}
			return result;
		}

	}

}
