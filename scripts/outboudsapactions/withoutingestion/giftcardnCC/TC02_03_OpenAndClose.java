package scripts.outboudsapactions.withoutingestion.giftcardnCC;
import resources.scripts.outboudsapactions.withoutingestion.giftcardnCC.TC02_03_OpenAndCloseHelper;
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
public class TC02_03_OpenAndClose extends TC02_03_OpenAndCloseHelper
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
		instantPurchase().purchaseDigitalItemWith2GC(ean, "1", "1");
		//instantPurchase().submitIPOrder(EnvironmentUtility.atlas().serverName().substring(7, 19)+"_","VI", "4313081835209051", ean);
		instantPurchase().verifyInstantPurchase("IPStatus");
		orderNumber=instantPurchase().getOrderNumber();
		customerID = instantPurchase().getCustomerID();
		timeStamp = instantPurchase().getOrderTimeStamp();
		if(orderNumber.length()>2){
			writeOutboundGCOrdersToExcel("TC02_03_OpenAndClose", timeStamp, orderNumber, ean,customerID);

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
				sap().createBatchJobToProcessiDocDialog().topLevelToolbar().enterCommand("/nva03");
			}
			else{
				sap().dataBrowserTableEDIDCDialog().topLevelToolbar().enterCommand("/nva03");
			}
			sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
			sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);

			//Get purchase Requisition 
			sap().displayBNOrderPhaseOverviewDialog().clickScheduleLinesForItem();
			purchaseRequisition = sap().displayOrdPhaseItemDataDialog().scheduleLinesTab().getFirstPurchaseRequisition();

			//Create PO
			sap().displayOrdPhaseItemDataDialog().topLevelToolbar().enterCommand("/nME59");
			sap().automaticCreationOfPODialog().createPOfromPurchaseRequisition(purchaseRequisition,"DIGITAL_QAAPP");
			sap().automaticCreationOfPODialog().verifyPurchaseOrderCreation("POCreation");
			sap().automaticCreationOfPurchaseDialog().topLevelToolbar().enterCommand("/nzalo1");


			sap().iDocReportingToolDialog().searchSalesOrder(null, orderNumber, null);
			sap().iDocReportingToolDialog().expandAllSalesOrder();
			sap().iDocReportingToolDialog().expandAllSalesOrder();
			String iDocForZ100 = sap().iDocReportingToolDialog().getIDocForZ100OrderAuthorized();
			System.out.println(iDocForZ100);
			sap().iDocReportingToolDialog().topLevelToolbar().enterCommand("/nbd87");
			sap().selectIDocDialog().searchIDoc(iDocForZ100);
			sap().statusMonitorDialog().expandiDocOutboundProcessing();
			if(	sap().statusMonitorDialog().isOutboundORDRSPVisible()){
				sap().statusMonitorDialog().selectORDRSPndProcess();
				sap().informationDialog().clickOkButton();
				sap().iDocProcessingDialog().verifyProcessedIDOCGrid("Z100_processed");
				sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nzalo1");

			} else {
				sap().statusMonitorDialog().topLevelToolbar().enterCommand("/nzalo1");
			}

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
			//activityMonitor().verifyActivityMonitorForError(ean, "NoError");

			/*
			 * 	
			 */
			sap().iDocReportingToolDialog().searchSalesOrder(null, orderNumber, null);
			sap().iDocReportingToolDialog().expandAllSalesOrder();
			sap().iDocReportingToolDialog().expandAllSalesOrder();

			String iDocForZ150 = sap().iDocReportingToolDialog().getIDocForZ150ShipmentClosed();
			String statusForZ150 = sap().iDocReportingToolDialog().getStatusForZ150ShipmentClosed();

			System.out.println("iDoc Number for Z150 : "+ iDocForZ150 + " and status : "+statusForZ150);

			if(statusForZ150.contains("30")){
				sap().iDocReportingToolDialog().topLevelToolbar().enterCommand("/nbd87");
				sap().selectIDocDialog().searchIDoc(iDocForZ150);
				sap().statusMonitorDialog().expandiDocOutboundProcessing();
				sap().statusMonitorDialog().selectOutboundINVOICndProcess();
				sap().informationDialog().clickOkButton();
				sap().iDocProcessingDialog().verifyProcessedIDOCGrid("Z150_processed");
				sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nzalo1");


			}
			sap().iDocReportingToolDialog().searchSalesOrder(null, orderNumber, null);
			sap().iDocReportingToolDialog().expandAllSalesOrder();
			String iDocForZ108 = 	sap().iDocReportingToolDialog().getIDocForZ108OrderClosed();
			System.out.println(iDocForZ108);

			sap().iDocReportingToolDialog().topLevelToolbar().enterCommand("/nbd87");
			sap().selectIDocDialog().searchIDoc(iDocForZ108);
			sap().statusMonitorDialog().expandiDocOutboundProcessing();
			if(	sap().statusMonitorDialog().isOutboundORDRSPVisible()){
				sap().statusMonitorDialog().selectORDRSPndProcess();
				sap().informationDialog().clickOkButton();
				sap().iDocProcessingDialog().verifyProcessedIDOCGrid("Z108_processed");
				sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nzalo1");

			} else {
				sap().statusMonitorDialog().topLevelToolbar().enterCommand("/nzalo1");
			}
			//Data Validation for OSDB
			dbService().OrderStatusDB().verifyOrderInOrderHeaderTable(orderNumber, "OSDB_OrderHeader_Closed");
			dbService().OrderStatusDB().verifyOrderInOrderItemTable(orderNumber, "OSDB_Item_Closed");
			dbService().OrderStatusDB().verifyOrderInOrderShipmentTable(orderNumber, "OSDB_OrderShipment_Closed");

			//Data Validation for DWDB
			dbService().datawareHouseDB().verifyOrderInOrderHeaderTable(orderNumber, "DWDB_OrderHeader_Closed");
			dbService().datawareHouseDB().verifyOrderInOrderDetailTable(orderNumber, "DWDB_OrderDetail_Closed");
			dbService().datawareHouseDB().verifyOrderInOrderDetailActivityTable(orderNumber, "DWDB_OrderDetailActivity_Closed");
			dbService().datawareHouseDB().verifyDetailsInCustomerTable(customerID, "DWDB_Customer_Closed");
			dbService().datawareHouseDB().verifyOrderInOrderCCNumberTable(orderNumber, "DWDB_OrderCCNumber_Closed");

			//Data Validation for BN Inc DB
			dbService().BNIncDB().verifyOrderInOrderHeaderTable(orderNumber, "BNIncDB_OrderHeader_Closed");
			dbService().BNIncDB().verifyOrderInOrderDetailTable(orderNumber, "BNIncDB_OrderDetail_Closed");
			dbService().BNIncDB().verifyOrderInOrderCCNumberActivityTable(orderNumber, "BNIncDB_OrderCCNumber_Closed");
			dbService().BNIncDB().verifyDetailsInCustomerTable(customerID, "BNIncDB_Customer_Closed");

			//Data Validation for Sales Rank DB 
			dbService().salesRankDB().verifyOrderInOrderHeaderTable(orderNumber, "SalesRankDB_OrderHeader_Closed"+EnvironmentUtility.SalesRankDB);
			dbService().salesRankDB().verifyOrderInOrderDetailTable(orderNumber, "SalesRankDB_OrderDetail_Closed"+EnvironmentUtility.SalesRankDB);

			sap().iDocProcessingDialog().close();
			sap().logOffDialog().clickYesButton();
			sap().logon720Dialog().close();
		}
		else{
			logError("Order Number is not generated");
		}	

	}
}

