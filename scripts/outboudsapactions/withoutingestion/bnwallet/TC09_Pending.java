package scripts.outboudsapactions.withoutingestion.bnwallet;
import resources.scripts.outboudsapactions.withoutingestion.bnwallet.TC09_PendingHelper;
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

import utils.EnvironmentUtility;
import utils.SpreadSheetUtil;

/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class TC09_Pending extends TC09_PendingHelper
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

		String ean,orderNumber,status,iDoc,customerID,timeStamp = null;
		ean = "2940149112722";
		instantPurchase().submitIPOrder(EnvironmentUtility.atlas().serverName().substring(7, 17)+"_","VI", "4313081835209051", ean);
		instantPurchase().verifyInstantPurchase("IPStatus");
		orderNumber=instantPurchase().getOrderNumber();
		customerID = instantPurchase().getCustomerID();
		timeStamp = instantPurchase().getOrderTimeStamp();
		if(orderNumber.length()>2){
			writeOutboundBNOrdersToExcel("TC09_Pending", timeStamp, orderNumber, ean,customerID);


			/*
			 * 		DB Validation
			 */

			//Data Validation for OSDB
			dbService().OrderStatusDB().verifyOrderInOrderHeaderTable(orderNumber, "OSDB_OrderHeader_Open");
			dbService().OrderStatusDB().verifyOrderInOrderItemTable(orderNumber, "OSDB_Item_Open");
			dbService().OrderStatusDB().verifyOrderInOrderShipmentTable(orderNumber, "OSDB_OrderShipment_Open");

			//Data Validation for DWDB
			dbService().datawareHouseDB().verifyOrderInOrderHeaderTable(orderNumber, "DWDB_OrderHeader_Open");
			dbService().datawareHouseDB().verifyOrderInOrderDetailTable(orderNumber, "DWDB_OrderDetail_Open");
			dbService().datawareHouseDB().verifyOrderInOrderDetailActivityTable(orderNumber, "DWDB_OrderDetailActivity_Open");
			dbService().datawareHouseDB().verifyDetailsInCustomerTable(customerID, "DWDB_Customer_Open");
			dbService().datawareHouseDB().verifyOrderInOrderCCNumberTable(orderNumber, "DWDB_OrderCCNumber_Open");

			//Data Validation for BN Inc DB
			dbService().BNIncDB().verifyOrderInOrderHeaderTable(orderNumber, "BNIncDB_OrderHeader_Open");
			dbService().BNIncDB().verifyOrderInOrderDetailTable(orderNumber, "BNIncDB_OrderDetail_Open");
			dbService().BNIncDB().verifyOrderInOrderCCNumberActivityTable(orderNumber, "BNIncDB_OrderCCNumber_Open");
			dbService().BNIncDB().verifyDetailsInCustomerTable(customerID, "BNIncDB_Customer_Open");

			//Data Validation for Sales Rank DB 
			dbService().salesRankDB().verifyOrderInOrderHeaderTable(orderNumber, "SalesRankDB_OrderHeader_Open"+EnvironmentUtility.SalesRankDB);
			dbService().salesRankDB().verifyOrderInOrderDetailTable(orderNumber, "SalesRankDB_OrderDetail_Open"+EnvironmentUtility.SalesRankDB);


			//Validate no Error in Activity Momonitor for this order
			activityMonitor().verifyActivityMonitorForError(ean, "NoError");


			/*
			 * starting SAP and login
			 */
			sap().startApplication();
			sap().logon720Dialog().selectSystemToLogin();
			sap().userLoginDialog().logIn();



			//Process Orders in SAP
			sap().easyAccessDialog().topLevelToolbar().enterCommand("/nse16");
			sap().dataBrowserInitialScreenDialog().execute("EDIDC");
			sap().dataBrowserTableSelectionScreenDialog().execute(orderNumber);
			status = sap().dataBrowserTableEDIDCDialog().getStatus();
			//If order needs to be process
			if(status.contentEquals("64")){
				iDoc = sap().dataBrowserTableEDIDCDialog().getiDocNumber();
				sap().dataBrowserTableEDIDCDialog().topLevelToolbar().enterCommand("/nZRPIDOC");
				sap().createBatchJobToProcessiDocDialog().createBatchtoProcessiDoc(iDoc);
				sap().createBatchJobToProcessiDocDialog().topLevelToolbar().enterCommand("/nzalo1");
			}
			else{
				sap().dataBrowserTableEDIDCDialog().topLevelToolbar().enterCommand("/nzalo1");
			}
			/*
			 * Search the item in zalo1 transaction
			 */
			sap().iDocReportingToolDialog().searchSalesOrder(null, orderNumber, "ZBN2");
			sap().iDocReportingToolDialog().expandAllSalesOrder();
			sap().iDocReportingToolDialog().expandAllSalesOrder();
			String iDocForZ102 = sap().iDocReportingToolDialog().getIDocForZ102OrderPending();
			System.out.println(iDocForZ102);
			//		sap().iDocReportingToolDialog().verifyIDocReportingToolTable("Z112_Status");
			sap().iDocReportingToolDialog().topLevelToolbar().enterCommand("/nbd87");
			sap().selectIDocDialog().searchIDoc(iDocForZ102);
			sap().statusMonitorDialog().expandiDocOutboundProcessing();
			if(	sap().statusMonitorDialog().isOutboundORDRSPVisible()){
				sap().statusMonitorDialog().selectORDRSPndProcess();
				sap().informationDialog().clickOkButton();
				sap().iDocProcessingDialog().verifyProcessedIDOCGrid("Z102_processed");
				sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nzalo1");

			} else {
				sap().statusMonitorDialog().topLevelToolbar().enterCommand("/nzalo1");
			}


			//Data Validation for OSDB
			dbService().OrderStatusDB().verifyOrderInOrderHeaderTable(orderNumber, "OSDB_OrderHeader_Pending");
			dbService().OrderStatusDB().verifyOrderInOrderItemTable(orderNumber, "OSDB_Item_Pending");
			dbService().OrderStatusDB().verifyOrderInOrderShipmentTable(orderNumber, "OSDB_OrderShipment_Pending");

			//Data Validation for DWDB
			dbService().datawareHouseDB().verifyOrderInOrderHeaderTable(orderNumber, "DWDB_OrderHeader_Pending");
			dbService().datawareHouseDB().verifyOrderInOrderDetailTable(orderNumber, "DWDB_OrderDetail_Pending");
			dbService().datawareHouseDB().verifyOrderInOrderDetailActivityTable(orderNumber, "DWDB_OrderDetailActivity_Pending");
			dbService().datawareHouseDB().verifyDetailsInCustomerTable(customerID, "DWDB_Customer_Pending");
			dbService().datawareHouseDB().verifyOrderInOrderCCNumberTable(orderNumber, "DWDB_OrderCCNumber_Pending");

			//Data Validation for BN Inc DB
			dbService().BNIncDB().verifyOrderInOrderHeaderTable(orderNumber, "BNIncDB_OrderHeader_Pending");
			dbService().BNIncDB().verifyOrderInOrderDetailTable(orderNumber, "BNIncDB_OrderDetail_Pending");
			dbService().BNIncDB().verifyOrderInOrderCCNumberActivityTable(orderNumber, "BNIncDB_OrderCCNumber_Pending");
			dbService().BNIncDB().verifyDetailsInCustomerTable(customerID, "BNIncDB_Customer_Pending");

			//Data Validation for Sales Rank DB 
			dbService().salesRankDB().verifyOrderInOrderHeaderTable(orderNumber, "SalesRankDB_OrderHeader_Pending"+EnvironmentUtility.SalesRankDB);
			dbService().salesRankDB().verifyOrderInOrderDetailTable(orderNumber, "SalesRankDB_OrderDetail_Pending"+EnvironmentUtility.SalesRankDB);

			sap().iDocReportingToolDialog().close();
			sap().logOffDialog().clickYesButton();
			sap().logon720Dialog().close();
		}
		else{
			logError("Order Number is not generated");
		}	

	}
}

