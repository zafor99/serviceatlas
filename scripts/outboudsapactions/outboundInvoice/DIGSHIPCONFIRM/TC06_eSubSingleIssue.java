package scripts.outboudsapactions.outboundInvoice.DIGSHIPCONFIRM;
import resources.scripts.outboudsapactions.outboundInvoice.DIGSHIPCONFIRM.TC06_eSubSingleIssueHelper;
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
public class TC06_eSubSingleIssue extends TC06_eSubSingleIssueHelper
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
		ean = "2940000983874";
		instantPurchase().submitIPOrder(EnvironmentUtility.atlas().serverName().substring(7, 17)+"_","VI", "4313081835209051", ean);
		instantPurchase().verifyInstantPurchase("IPStatus");
		orderNumber=instantPurchase().getOrderNumber();
		customerID = instantPurchase().getCustomerID();
		timeStamp = instantPurchase().getOrderTimeStamp();
		delayFor(15);
		if(orderNumber.length()>2){
			writeInvoicedBNOrdersToExcel("TC06_eSubSingleIssue", timeStamp, orderNumber, ean,customerID);
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
			 
/*
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
			dbService().salesRankDB().verifyOrderInOrderHeaderTable(orderNumber, "SalesRankDB_OrderHeader_Open");
			dbService().salesRankDB().verifyOrderInOrderDetailTable(orderNumber, "SalesRankDB_OrderDetail_Open");
*/

			//Validate no Error in Activity Momonitor for this order
			activityMonitor().verifyActivityMonitorForError(ean, "NoError");

			
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
			delayFor(20);
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
			//148720413
		/*	orderNumber = "148720413";
			customerID = "000eUQUWH0JYO810";*/
			//Data Validation for OSDB
			dbService().OrderStatusDB().verifyOrderInOrderHeaderTable(orderNumber, "OSDB_OrderHeader_Invoiced");
			dbService().OrderStatusDB().verifyOrderInOrderItemTable(orderNumber, "OSDB_Item_Invoiced");
			dbService().OrderStatusDB().verifyOrderInOrderShipmentTable(orderNumber, "OSDB_OrderShipment_Invoiced");

			//Data Validation for DWDB
			dbService().datawareHouseDB().verifyOrderInOrderHeaderTable(orderNumber, "DWDB_OrderHeader_Invoiced");
			dbService().datawareHouseDB().verifyOrderInOrderDetailTable(orderNumber, "DWDB_OrderDetail_Invoiced");
			dbService().datawareHouseDB().verifyOrderInOrderDetailActivityTable(orderNumber, "DWDB_OrderDetailActivity_Invoiced");
			dbService().datawareHouseDB().verifyDetailsInCustomerTable(customerID, "DWDB_Customer_Invoiced");
			dbService().datawareHouseDB().verifyOrderInOrderCCNumberTable(orderNumber, "DWDB_OrderCCNumber_Invoiced");

			//Data Validation for BN Inc DB
			dbService().BNIncDB().verifyOrderInOrderHeaderTable(orderNumber, "BNIncDB_OrderHeader_Invoiced");
			dbService().BNIncDB().verifyOrderInOrderDetailTable(orderNumber, "BNIncDB_OrderDetail_Invoiced");
			dbService().BNIncDB().verifyOrderInOrderCCNumberActivityTable(orderNumber, "BNIncDB_OrderCCNumber_Invoiced");
			dbService().BNIncDB().verifyDetailsInCustomerTable(customerID, "BNIncDB_Customer_Invoiced");

			//Data Validation for Sales Rank DB 
			dbService().salesRankDB().verifyOrderInOrderHeaderTable(orderNumber, "SalesRankDB_OrderHeader_Invoiced"+EnvironmentUtility.SalesRankDB);
			dbService().salesRankDB().verifyOrderInOrderDetailTable(orderNumber, "SalesRankDB_OrderDetail_Invoiced"+EnvironmentUtility.SalesRankDB);

			sap().iDocProcessingDialog().close();
			sap().logOffDialog().clickYesButton();
			sap().logon720Dialog().close();
		}
		else{
			logError("Order Number is not generated");
		}	

	}
}

