package scripts.atlasProcessingOrders.msWalletInstantPurchase;
import resources.scripts.atlasProcessingOrders.msWalletInstantPurchase.TC02_freeEBookHelper;
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
public class TC02_freeEBook extends TC02_freeEBookHelper
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
		String accessToken,orderNumber,customerID,timeStamp,ean = null;
		ean = "9781451658866";
		if(AtlasScriptExecution.getScriptRun())
		{
			msWalletIPEnv().readClientInfo("US", "WEB");
			accessToken = getAccessTokenFromMSWalletAccountBindingExcel();
			customerID = getcustomerIDFromMSWalletAccountBindingExcel();
			msWalletService().createInstantPurchase(accessToken, customerID, ean) ;
			msWalletService().verifyInstantPurchase("IPStatus");
			orderNumber=msWalletService().getOrderNumber();
			timeStamp = msWalletService().getOrderTimeStamp();
			if(orderNumber.length()>2){
				writeMSWalletOrdersToExcel("TC02_freeEBook", timeStamp, orderNumber, ean,customerID);
			}
			else{
				logError("Order Number is not generated");
			}	
		}

		if (AtlasScriptExecution.getDBValidation()){
			orderNumber = getOrderNumberFromMSWalletOrdersExcel("TC02_freeEBook");
			customerID = getCustomerIDFromMSWalletOrdersExcel("TC02_freeEBook");
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
				dbService().datawareHouseDB().verifyCustomerExist("DwDB_CustomerExtInCustomerTable", customerID);

				//Data Validation for DWDB
				dbService().datawareHouseDB().verifyOrderInOrderHeaderTable(orderNumber, "DWDB_OrderHeaderTable");
				dbService().datawareHouseDB().verifyOrderInOrderDetailTable(orderNumber, "DWDB_OrderDetailTable");
				dbService().datawareHouseDB().verifyOrderInOrderDetailActivityTable(orderNumber, "DWDB_OrderDetailActivityTable");
				dbService().datawareHouseDB().verifyDetailsInCustomerTable(customerID, "DWDB_CustomerTable");

				//Verify Order is inserted into BN Inc DB
				dbService().BNIncDB().verifyOrderExistInOrderHeaderTable("BNIncDB_OrdExtInOrderHeader", orderNumber);
				dbService().BNIncDB().verifyOrderExistInOrderDetailTable("BNIncDB_OrdExtInOrderDetail", orderNumber);
				dbService().BNIncDB().verifyOrderExistInOrderDetailActivityTable("BNIncDB_OrdExtInOrderDetailActivity", orderNumber);
				dbService().BNIncDB().verifyCustomerExist("BNIncDB_CustomerExtInCustomerTable", customerID);

				//Data Validation for BN Inc DB
				dbService().BNIncDB().verifyOrderInOrderHeaderTable(orderNumber, "BNIncDB_OrderHeaderTable");
				dbService().BNIncDB().verifyOrderInOrderDetailTable(orderNumber, "BNIncDB_OrderDetailTable");
				dbService().BNIncDB().verifyDetailsInCustomerTable(customerID, "BNIncDB_CustomerTable");

				//Verify Order is inserted into Sales Rank DB and Source by Atlas User ID
				dbService().salesRankDB().verifyOrderExistInOrderHeaderTable("SalesRankDB_OrderExistInOrderHeader", orderNumber);
				dbService().salesRankDB().verifyOrderExistInOrderDetailTable("SalesRankDB_OrderExistInOrderDetail", orderNumber);
				dbService().salesRankDB().verifyOrderSourceIsAtlas("AtlasSourced", orderNumber);

				//Data Validation for Sales Rank DB 
				dbService().salesRankDB().verifyOrderInOrderHeaderTable(orderNumber, "SalesRankDB_OrderHeaderTable");
				dbService().salesRankDB().verifyOrderInOrderDetailTable(orderNumber, "SalesRankDB_OrderDetailTable");

				//Validate Order Exist in Digital Locker
				digitalLocker(customerID).verifyLockerItem("DigitalLocker",ean);

				//Validate no Error in Activity Momonitor for this order
				activityMonitor().verifyActivityMonitorForError(ean, "NoError");

			}

			else{
				logError("Order Number is not generated");
			}
		}
	}
}
