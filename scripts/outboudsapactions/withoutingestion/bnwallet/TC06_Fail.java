package scripts.outboudsapactions.withoutingestion.bnwallet;
import resources.scripts.outboudsapactions.withoutingestion.bnwallet.TC06_FailHelper;
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
public class TC06_Fail extends TC06_FailHelper
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

		String ean,orderNumber,purchaseRequisition, poNumber,status,iDoc,customerID,timeStamp,orderStatus = null;
		ean = "9780553905656";
		instantPurchase().submitIPOrder(EnvironmentUtility.atlas().serverName().substring(7, 17)+"_","VI", "4754040013589524", ean);
		//instantPurchase().submitIPOrder(EnvironmentUtility.atlas().serverName().substring(7, 19)+"_","VI", "4387751111111020", ean);
		instantPurchase().verifyInstantPurchase("IPStatus");
		orderNumber=instantPurchase().getOrderNumber();
		customerID = instantPurchase().getCustomerID();
		timeStamp = instantPurchase().getOrderTimeStamp();
		if(orderNumber.length()>2){
			writeOutboundBNOrdersToExcel("TC06_Fail", timeStamp, orderNumber, ean,customerID);
		/*
		 * starting SAP and login
		 */
		sap().startApplication();
		sap().logon720Dialog().selectSystemToLogin();
		sap().userLoginDialog().logIn();


		delayFor(30);
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

		/*
		 * 		DB Validation
		 */

		//Data Validation for OSDB
		dbService().OrderStatusDB().verifyOrderInOrderHeaderTable(orderNumber, "OSDB_OrderHeader_New");
		dbService().OrderStatusDB().verifyOrderInOrderItemTable(orderNumber, "OSDB_Item_New");
		dbService().OrderStatusDB().verifyOrderInOrderShipmentTable(orderNumber, "OSDB_OrderShipment_New");

		//Data Validation for DWDB
		dbService().datawareHouseDB().verifyOrderInOrderHeaderTable(orderNumber, "DWDB_OrderHeader_New");
		dbService().datawareHouseDB().verifyOrderInOrderDetailTable(orderNumber, "DWDB_OrderDetail_New");
		dbService().datawareHouseDB().verifyOrderInOrderDetailActivityTable(orderNumber, "DWDB_OrderDetailActivity_New");
		dbService().datawareHouseDB().verifyDetailsInCustomerTable(customerID, "DWDB_Customer_New");
		dbService().datawareHouseDB().verifyOrderInOrderCCNumberTable(orderNumber, "DWDB_OrderCCNumber_New");

		//Data Validation for BN Inc DB
		dbService().BNIncDB().verifyOrderInOrderHeaderTable(orderNumber, "BNIncDB_OrderHeader_New");
		dbService().BNIncDB().verifyOrderInOrderDetailTable(orderNumber, "BNIncDB_OrderDetail_New");
		dbService().BNIncDB().verifyOrderInOrderCCNumberActivityTable(orderNumber, "BNIncDB_OrderCCNumber_New");
		dbService().BNIncDB().verifyDetailsInCustomerTable(customerID, "BNIncDB_Customer_New");

		//Data Validation for Sales Rank DB 
		dbService().salesRankDB().verifyOrderInOrderHeaderTable(orderNumber, "SalesRankDB_OrderHeader_New"+EnvironmentUtility.SalesRankDB);
		dbService().salesRankDB().verifyOrderInOrderDetailTable(orderNumber, "SalesRankDB_OrderDetail_New"+EnvironmentUtility.SalesRankDB);


		//Validate no Error in Activity Momonitor for this order
		activityMonitor().verifyActivityMonitorForError(ean, "NoError");

		sap().iDocReportingToolDialog().searchSalesOrder(null, orderNumber, null);
		sap().iDocReportingToolDialog().expandAllSalesOrder();
		sap().iDocReportingToolDialog().expandAllSalesOrder();
		String iDocForZ105 = sap().iDocReportingToolDialog().getIDocForZ105OrderFailed();
		//sap().iDocReportingToolDialog().verifyIDocReportingToolTable("Z105_Status");
		System.out.println(iDocForZ105);
		sap().iDocReportingToolDialog().topLevelToolbar().enterCommand("/nbd87");
		sap().selectIDocDialog().searchIDoc(iDocForZ105);
		sap().statusMonitorDialog().expandiDocOutboundProcessing();
		if(	sap().statusMonitorDialog().isOutboundORDRSPVisible()){
			sap().statusMonitorDialog().selectORDRSPndProcess();
			sap().informationDialog().clickOkButton();
			sap().iDocProcessingDialog().verifyProcessedIDOCGrid("Z116_processed");
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/ncic0");

		} else {
			sap().statusMonitorDialog().topLevelToolbar().enterCommand("/ncic0");
		}

		
		//Data Validation for OSDB
		dbService().OrderStatusDB().verifyOrderInOrderHeaderTable(orderNumber, "OSDB_OrderHeader_Failed");
		dbService().OrderStatusDB().verifyOrderInOrderItemTable(orderNumber, "OSDB_Item_Failed");
		dbService().OrderStatusDB().verifyOrderInOrderShipmentTable(orderNumber, "OSDB_OrderShipment_Failed");

		//Data Validation for DWDB
		dbService().datawareHouseDB().verifyOrderInOrderHeaderTable(orderNumber, "DWDB_OrderHeader_Failed");
		dbService().datawareHouseDB().verifyOrderInOrderDetailTable(orderNumber, "DWDB_OrderDetail_Failed");
		dbService().datawareHouseDB().verifyOrderInOrderDetailActivityTable(orderNumber, "DWDB_OrderDetailActivity_Failed");
		dbService().datawareHouseDB().verifyDetailsInCustomerTable(customerID, "DWDB_Customer_Failed");
		dbService().datawareHouseDB().verifyOrderInOrderCCNumberTable(orderNumber, "DWDB_OrderCCNumber_Failed");

		//Data Validation for BN Inc DB
		dbService().BNIncDB().verifyOrderInOrderHeaderTable(orderNumber, "BNIncDB_OrderHeader_Failed");
		dbService().BNIncDB().verifyOrderInOrderDetailTable(orderNumber, "BNIncDB_OrderDetail_Failed");
		dbService().BNIncDB().verifyOrderInOrderCCNumberActivityTable(orderNumber, "BNIncDB_OrderCCNumber_Failed");
		dbService().BNIncDB().verifyDetailsInCustomerTable(customerID, "BNIncDB_Customer_Failed");

		//Data Validation for Sales Rank DB 
		dbService().salesRankDB().verifyOrderInOrderHeaderTable(orderNumber, "SalesRankDB_OrderHeader_Failed"+EnvironmentUtility.SalesRankDB);
		dbService().salesRankDB().verifyOrderInOrderDetailTable(orderNumber, "SalesRankDB_OrderDetail_Failed"+EnvironmentUtility.SalesRankDB);
		
		sap().customerInteractionCenterDialog().close();
		sap().logOffDialog().clickYesButton();
		sap().logon720Dialog().close();
		}
		else{
			logError("Order Number is not generated");
		}	

	}
}

