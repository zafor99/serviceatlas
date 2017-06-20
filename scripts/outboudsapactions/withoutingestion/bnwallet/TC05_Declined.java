package scripts.outboudsapactions.withoutingestion.bnwallet;
import resources.scripts.outboudsapactions.withoutingestion.bnwallet.TC05_DeclinedHelper;
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
public class TC05_Declined extends TC05_DeclinedHelper
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
		instantPurchase().submitIPOrder(EnvironmentUtility.atlas().serverName().substring(7, 17)+"_","VI", "4387751111111020", ean);
		//instantPurchase().submitIPOrder("VI", "4387751111111020", ean,true);//declined card
		//instantPurchase().submitIPOrder("VI", "4313081835209051", ean,true);
		instantPurchase().verifyInstantPurchase("IPStatus");
		orderNumber=instantPurchase().getOrderNumber();
		customerID = instantPurchase().getCustomerID();
		timeStamp = instantPurchase().getOrderTimeStamp();
		
		if(orderNumber.length()>2){
			writeOutboundBNOrdersToExcel("TC05_Declined", timeStamp, orderNumber, ean,customerID);
			

			
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
		String iDocForZ104 = sap().iDocReportingToolDialog().getIDocForZ104OrderDeclined();
		sap().iDocReportingToolDialog().verifyIDocReportingToolTable("Z104_Status");
		System.out.println(iDocForZ104);
		sap().iDocReportingToolDialog().topLevelToolbar().enterCommand("/nbd87");
		sap().selectIDocDialog().searchIDoc(iDocForZ104);
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
		dbService().OrderStatusDB().verifyOrderInOrderHeaderTable(orderNumber, "OSDB_OrderHeader_Declined");
		dbService().OrderStatusDB().verifyOrderInOrderItemTable(orderNumber, "OSDB_Item_Declined");
		dbService().OrderStatusDB().verifyOrderInOrderShipmentTable(orderNumber, "OSDB_OrderShipment_Declined");

		//Data Validation for DWDB
		dbService().datawareHouseDB().verifyOrderInOrderHeaderTable(orderNumber, "DWDB_OrderHeader_Declined");
		dbService().datawareHouseDB().verifyOrderInOrderDetailTable(orderNumber, "DWDB_OrderDetail_Declined");
		dbService().datawareHouseDB().verifyOrderInOrderDetailActivityTable(orderNumber, "DWDB_OrderDetailActivity_Declined");
		dbService().datawareHouseDB().verifyDetailsInCustomerTable(customerID, "DWDB_Customer_Declined");
		dbService().datawareHouseDB().verifyOrderInOrderCCNumberTable(orderNumber, "DWDB_OrderCCNumber_Declined");

		//Data Validation for BN Inc DB
		dbService().BNIncDB().verifyOrderInOrderHeaderTable(orderNumber, "BNIncDB_OrderHeader_Declined");
		dbService().BNIncDB().verifyOrderInOrderDetailTable(orderNumber, "BNIncDB_OrderDetail_Declined");
		dbService().BNIncDB().verifyOrderInOrderCCNumberActivityTable(orderNumber, "BNIncDB_OrderCCNumber_Declined");
		dbService().BNIncDB().verifyDetailsInCustomerTable(customerID, "BNIncDB_Customer_Declined");

		//Data Validation for Sales Rank DB 
		dbService().salesRankDB().verifyOrderInOrderHeaderTable(orderNumber, "SalesRankDB_OrderHeader_Declined"+EnvironmentUtility.SalesRankDB);
		dbService().salesRankDB().verifyOrderInOrderDetailTable(orderNumber, "SalesRankDB_OrderDetail_Declined"+EnvironmentUtility.SalesRankDB);
		
		sap().customerInteractionCenterDialog().close();
		sap().logOffDialog().clickYesButton();
		sap().logon720Dialog().close();
		}
		else{
			logError("Order Number is not generated");
		}	

	}
}

