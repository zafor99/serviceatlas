package scripts.atlasProcessingOrders.bnWalletInstantPurchase;
import resources.scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC03_ebookPreOrderHelper;
import utils.EnvironmentUtility;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;

import framework.AtlasScriptExecution;

/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class TC03_ebookPreOrder extends TC03_ebookPreOrderHelper
{
	/**
	 * Script Name   : <b>TC01_NookApp</b>
	 * Generated     : <b>Feb 21, 2014 4:07:41 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/02/21
	 * @author zsadeque
	 * @throws ClassNotFoundException 
	 */
	public void testMain(Object[] args) throws ClassNotFoundException 
	{
		String orderNumber,ean,customerID,timeStamp= null;

		logInfo("TC03_ebookPreOrder");
		if(AtlasScriptExecution.getScriptRun())
		{
			//get Pre-Order EAN
			 //ean = dbService().booksDB().getRandomEANForProduct("0.00-9.99", "ER", "9");
			 ean = "9780761187066";
			//Instant Purchase
			instantPurchase().submitIPOrder("VI", "4313081835209051", ean);
			instantPurchase().verifyInstantPurchase("IPStatus");
			customerID = instantPurchase().getCustomerID();
			orderNumber=instantPurchase().getOrderNumber();
			timeStamp = instantPurchase().getOrderTimeStamp();
			if(orderNumber.length()>2){
				writeBNOrdersToExcel("TC03_ebookPreOrder", timeStamp, orderNumber, ean,customerID);
			}
			else{
				logError("Order Number is not generated");
			}
		}
		if (AtlasScriptExecution.getDBValidation()){
			orderNumber = getOrderNumberFromBNOrdersExcel("TC03_ebookPreOrder");
			customerID = getCustomerIDFromBNOrdersExcel("TC03_ebookPreOrder");
			ean = getEANFromBNOrdersExcel("TC03_ebookPreOrder");
			System.out.println("orderNumber "+  orderNumber);
			System.out.println("customer ID "+  customerID);
			if(orderNumber.length()>2){
				//Verify Order is inserted into OSDB
				dbService().OrderStatusDB().verifyOrderExistInOrderHeaderTable("OSDB_OrdExtInOrderHeaderTable", orderNumber);
				dbService().OrderStatusDB().verifyOrderExistInOrderItemTable("OSDB_OrdExtInOrderItemTable", orderNumber);
				dbService().OrderStatusDB().verifyOrderExistInOrderShipmentTableTable("OSDB_OrdExtInOrderShipmentTableTable", orderNumber);

				//Data Validation for OSDB
				dbService().OrderStatusDB().verifyOrderInOrderHeaderTable(orderNumber, "OSDB_OrderHeaderTable");
				dbService().OrderStatusDB().verifyOrderInOrderItemTable(orderNumber, "OSDB_ItemTable");
				dbService().OrderStatusDB().verifyOrderInOrderShipmentTable(orderNumber, "OSDB_OrderShipmentTable");

				//Verify Order is inserted into DWDB
				dbService().datawareHouseDB().verifyOrderExistInOrderHeaderTable("DwDB_OrdExtInOrderHeader", orderNumber);
				dbService().datawareHouseDB().verifyOrderExistInOrderDetailTable("DwDB_OrdExtInOrderDetail", orderNumber);
				dbService().datawareHouseDB().verifyOrderExistInOrderDetailActivityTable("DwDB_OrdExtInOrderDetailActivity", orderNumber);
				dbService().datawareHouseDB().verifyCustomerExist("DwDB_CustomerExtInCustomerTable", customerID);
				dbService().datawareHouseDB().verifyOrderExistInOrderCCNumberTable("DwDB_CustomerExtInOrderCCNumber", orderNumber);

				//Data Validation for DWDB
				dbService().datawareHouseDB().verifyOrderInOrderHeaderTable(orderNumber, "DWDB_OrderHeaderTable");
				dbService().datawareHouseDB().verifyOrderInOrderDetailTable(orderNumber, "DWDB_OrderDetailTable");
				dbService().datawareHouseDB().verifyOrderInOrderDetailActivityTable(orderNumber, "DWDB_OrderDetailActivityTable");
				dbService().datawareHouseDB().verifyDetailsInCustomerTable(customerID, "DWDB_CustomerTable");
				dbService().datawareHouseDB().verifyOrderInOrderCCNumberTable(orderNumber, "DWDB_OrderCCNumberTable");

				//Verify Order is inserted into BN Inc DB
				dbService().BNIncDB().verifyOrderExistInOrderHeaderTable("BNIncDB_OrdExtInOrderHeader", orderNumber);
				dbService().BNIncDB().verifyOrderExistInOrderDetailTable("BNIncDB_OrdExtInOrderDetail", orderNumber);
				dbService().BNIncDB().verifyOrderExistInOrderDetailActivityTable("BNIncDB_OrdExtInOrderDetailActivity", orderNumber);
				dbService().BNIncDB().verifyCustomerExist("BNIncDB_CustomerExtInCustomerTable", customerID);
				dbService().BNIncDB().verifyOrderExistInOrderCCNumberTable("BNIncDB_CustomerExtInOrderCCNumber", orderNumber);

				//Data Validation for BN Inc DB
				dbService().BNIncDB().verifyOrderInOrderHeaderTable(orderNumber, "BNIncDB_OrderHeaderTable");
				dbService().BNIncDB().verifyOrderInOrderDetailTable(orderNumber, "BNIncDB_OrderDetailTable");
				dbService().BNIncDB().verifyOrderInOrderDetailActivityTable(orderNumber, "BNIncDB_OrderDetailActivityTable");
				dbService().BNIncDB().verifyOrderInOrderCCNumberActivityTable(orderNumber, "BNIncDB_OrderCCNumberTable");
				dbService().BNIncDB().verifyDetailsInCustomerTable(customerID, "BNIncDB_CustomerTable");

				//Verify Order is inserted into Sales Rank DB and Source by Atlas User ID
				dbService().salesRankDB().verifyOrderExistInOrderHeaderTable("SalesRankDB_OrderExistInOrderHeader"+EnvironmentUtility.SalesRankDB, orderNumber);
				dbService().salesRankDB().verifyOrderExistInOrderDetailTable("SalesRankDB_OrderExistInOrderDetail"+EnvironmentUtility.SalesRankDB, orderNumber);
				dbService().salesRankDB().verifyOrderSourceIsAtlas("AtlasSourced"+EnvironmentUtility.SalesRankDB, orderNumber);

				//Data Validation for Sales Rank DB 
				dbService().salesRankDB().verifyOrderInOrderHeaderTable(orderNumber, "SalesRankDB_OrderHeaderTable"+EnvironmentUtility.SalesRankDB);
				dbService().salesRankDB().verifyOrderInOrderDetailTable(orderNumber, "SalesRankDB_OrderDetailTable"+EnvironmentUtility.SalesRankDB);

				//Validate Order Exist in Digital Locker
				digitalLocker(customerID).verifyLockerItem("EmptyLocker");

				//Validate no Error in Activity Momonitor for this order
				activityMonitor().verifyActivityMonitorForError(ean, "NoError");
			}
			else{
				logError("Order Number is not generated");
			}



		}


	}
}

