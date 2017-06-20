package scripts.outboudsapactions.withoutingestion.mswallet;
import resources.scripts.outboudsapactions.withoutingestion.mswallet.TC01_CancelHelper;
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
public class TC01_Cancel extends TC01_CancelHelper
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

		String accessToken,ean,orderNumber,purchaseRequisition, poNumber,status,iDoc,customerID,timeStamp,orderStatus,filePath = null;
		filePath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\outboundSAPActions\\mswallet\\MSWalletAccountBinding.xls";
		ean = "2940000228043";

		msWalletIPEnv().readClientInfo("US", "WEB");
		accessToken = getAccessTokenFromMSWalletAccountBindingExcel(filePath);
		customerID = getcustomerIDFromMSWalletAccountBindingExcel(filePath);

		msWalletService().createInstantPurchase(accessToken, customerID, ean) ;
		msWalletService().verifyInstantPurchase("IPStatus");
		orderNumber=msWalletService().getOrderNumber();
		timeStamp = msWalletService().getOrderTimeStamp();
		if(orderNumber.length()>2){
			writeOutboundMSWalletOrdersToExcel("TC01_Cancel", timeStamp, orderNumber, ean,customerID);
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
			sap().iDocReportingToolDialog().searchSalesOrder(null, orderNumber, "ZCQ");
			sap().iDocReportingToolDialog().expandAllSalesOrder();
			sap().iDocReportingToolDialog().expandAllSalesOrder();
			String iDocForZ112 = sap().iDocReportingToolDialog().getIDocForZ112OrderAuthorized();
			System.out.println(iDocForZ112);
			//		sap().iDocReportingToolDialog().verifyIDocReportingToolTable("Z112_Status");
			sap().iDocReportingToolDialog().topLevelToolbar().enterCommand("/nbd87");
			sap().selectIDocDialog().searchIDoc(iDocForZ112);
			sap().statusMonitorDialog().expandiDocOutboundProcessing();
			if(	sap().statusMonitorDialog().isOutboundORDRSPVisible()){
				sap().statusMonitorDialog().selectORDRSPndProcess();
				sap().informationDialog().clickOkButton();
				sap().iDocProcessingDialog().verifyProcessedIDOCGrid("Z116_processed");
				sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/ncic0");

			} else {
				sap().statusMonitorDialog().topLevelToolbar().enterCommand("/ncic0");
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

			//	dbService().datawareHouseDB().verifyOrderInOrderCCNumberTable(orderNumber, "DWDB_OrderCCNumber_Open");

			//Data Validation for BN Inc DB
			dbService().BNIncDB().verifyOrderInOrderHeaderTable(orderNumber, "BNIncDB_OrderHeader_Open");
			dbService().BNIncDB().verifyOrderInOrderDetailTable(orderNumber, "BNIncDB_OrderDetail_Open");
			//	dbService().BNIncDB().verifyOrderInOrderCCNumberActivityTable(orderNumber, "BNIncDB_OrderCCNumber_Open");
			dbService().BNIncDB().verifyDetailsInCustomerTable(customerID, "BNIncDB_Customer_Open");

			//Data Validation for Sales Rank DB 
			dbService().salesRankDB().verifyOrderInOrderHeaderTable(orderNumber, "SalesRankDB_OrderHeader_Open");
			dbService().salesRankDB().verifyOrderInOrderDetailTable(orderNumber, "SalesRankDB_OrderDetail_Open");


			//Validate no Error in Activity Momonitor for this order
			//		activityMonitor().verifyActivityMonitorForError(ean, "NoError");





			sap().customerInteractionCenterDialog().clickOrderSearchFindButton();
			sap().restrictValueRangeDialog().searchWebOrder(orderNumber);
			//	orderNumber = "147642184";
			sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber,"Web Order Number");
			sap().customerInteractionCenterDialog().goToDisplayChangeOrderDialog();
			sap().bnOrderDataRefreshDialog().selectItemsTab();
			sap().bnOrderDataRefreshDialog().itemsTab().select1stItem();
			sap().bnOrderDataRefreshDialog().itemsTab().cancel();
			sap().cancelSubscriptionDialog().selectCancelReason("88-Cancel eSubscription");
			sap().cancelSubscriptionDialog().update();
			sap().bnOrderDataRefreshDialog().verifyStatusBar("SubcCanceled");

			sap().bnOrderDataRefreshDialog().topLevelToolbar().enterCommand("/nzalo1");



			sap().iDocReportingToolDialog().searchSalesOrder(null, orderNumber, "ZCQ");
			sap().iDocReportingToolDialog().expandAllSalesOrder();
			sap().iDocReportingToolDialog().expandAllSalesOrder();

			String iDocForZ116 = sap().iDocReportingToolDialog().getIDocForZ116OrderAuthorized();
			String statusForZ116 = sap().iDocReportingToolDialog().getStatusForZ116OrderAuthorized();
			//		sap().iDocReportingToolDialog().verifyIDocReportingToolTable("Z1116_Status");
			System.out.println("iDoc Number for Z150 : "+ iDocForZ116 + " and status : "+statusForZ116);

			if(statusForZ116.contains("30")){
				sap().iDocReportingToolDialog().topLevelToolbar().enterCommand("/nbd87");
				sap().selectIDocDialog().searchIDoc(iDocForZ116);
				sap().statusMonitorDialog().expandiDocOutboundProcessing();
				sap().statusMonitorDialog().selectORDRSPndProcess();
				sap().informationDialog().clickOkButton();
				sap().iDocProcessingDialog().verifyProcessedIDOCGrid("Z150_processed");

			}

			//Data Validation for OSDB
			dbService().OrderStatusDB().verifyOrderInOrderHeaderTable(orderNumber, "OSDB_OrderHeader_Closed");
			dbService().OrderStatusDB().verifyOrderInOrderItemTable(orderNumber, "OSDB_Item_Closed");
			dbService().OrderStatusDB().verifyOrderInOrderShipmentTable(orderNumber, "OSDB_OrderShipment_Closed");

			//Data Validation for DWDB
			dbService().datawareHouseDB().verifyOrderInOrderHeaderTable(orderNumber, "DWDB_OrderHeader_Closed");
			dbService().datawareHouseDB().verifyOrderInOrderDetailTable(orderNumber, "DWDB_OrderDetail_Closed");
			dbService().datawareHouseDB().verifyOrderInOrderDetailActivityTable(orderNumber, "DWDB_OrderDetail_Closed");
			//
			//Data Validation for BN Inc DB
			dbService().BNIncDB().verifyOrderInOrderHeaderTable(orderNumber, "BNIncDB_OrderHeader_Closed");
			dbService().BNIncDB().verifyOrderInOrderDetailTable(orderNumber, "BNIncDB_OrderDetail_Closed");
			//	dbService().BNIncDB().verifyOrderInOrderCCNumberActivityTable(orderNumber, "BNIncDB_OrderCCNumber_Closed");
			dbService().BNIncDB().verifyDetailsInCustomerTable(customerID, "BNIncDB_Customer_Closed");

			//Data Validation for Sales Rank DB 
			dbService().salesRankDB().verifyOrderInOrderHeaderTable(orderNumber, "SalesRankDB_OrderHeader_Closed");
			dbService().salesRankDB().verifyOrderInOrderDetailTable(orderNumber, "SalesRankDB_OrderDetail_Closed");

			sap().iDocProcessingDialog().close();
			sap().logOffDialog().clickYesButton();
			sap().logon720Dialog().close();
		}
		else{
			logError("Order Number is not generated");
		}	
	}
}

