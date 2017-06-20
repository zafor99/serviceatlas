package scripts.outboudsapactions.withoutingestion.bnwallet;
import resources.scripts.outboudsapactions.withoutingestion.bnwallet.TC01_ABREFUNDHelper;
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
public class TC01_ABREFUND extends TC01_ABREFUNDHelper
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

		String ean,orderNumber,purchaseRequisition, poNumber,status,iDoc,customerID,timeStamp,orderStatus,refundSAPOrderNumber = null;
		/*
		 * Dependency
		 *Get order number from excel 
		 */
		orderNumber = getOutboundBNOrderNumber("TC08_04_OpenAndClose");
		System.out.println(orderNumber);
		if(orderNumber.length()>2){
			
			/*
			 * Processing Refund
			 */
			crm().startApplication();
			crm().signInDialog().signIn();
			crm().selectABusinessRolePage().clickZBNT1CSRLink();
			crm().identifyAccountPage().accountSearchPanel().search(orderNumber, null, null, null, null, "search account");
			
			
			crm().identifyAccountPage().accountConfirmPanel().confirm();
			crm().identifyAccountPage().ordersTabPanel().selectOrderTableRowbyOrderNum(orderNumber);
			crm().identifyAccountPage().ordersTabPanel().verifyOrdersTable("orderTable");
			crm().identifyAccountPage().ordersTabPanel().openSAPOrderNo(orderNumber);
			crm().orderManagementOrderPage().topHeaderPanel().next();
			crm().orderManagementServReqPage().orderDetailsPanel().selectItemByItemNum("000001");
			crm().orderManagementServReqPage().createServiceRequestPanel().clickNew();
			crm().selectSalesServiceTransactionPage().selectFirstServiceRequest();
			crm().orderManagementServReqPage().serviceRequestOverviewPanel().updateCallReason("Downloadable Products", "NOOKBooks", "DRM Policy", null);
			crm().orderManagementServReqPage().createServiceRequestPanel().save();
			crm().orderManagementServReqPage().topHeaderPanel().clickNotificationLink();
			crm().orderManagementServReqPage().topHeaderPanel().verifyNotification("CustServreqCreated");
			crm().orderManagementServReqPage().topHeaderPanel().next();
			crm().orderManagementOptionsPage().availableOrderChangePanel().refund();
			crm().orderManagementServReqPage().topHeaderPanel().next();
			crm().orderManagementIssueRefundPage().issueRefundPanel().selectReasonCodeComboBox("Defective/Damaged)");
			crm().orderManagementIssueRefundPage().issueRefundPanel().updaterefundQty();
			
			crm().orderManagementIssueRefundSummaryPage().selectPaymentType("VISN");
			crm().orderManagementIssueRefundSummaryPage().update();
			crm().orderManagementServReqPage().topHeaderPanel().clickNotificationLink();
			crm().orderManagementServReqPage().topHeaderPanel().verifyNotification("refundCreated");
			String refundID=crm().orderManagementServReqPage().topHeaderPanel().getRefundID();
			System.out.println("refundID" + refundID);
			crm().orderManagementServReqPage().topHeaderPanel().next();
			crm().orderManagementSummaryPage().verifySummaryTable("Summary");
			crm().orderManagementSummaryPage().topPanel().clickEnd();

			crm().identifyAccountPage().accountSearchPanel().search(orderNumber, null, null, null, null, "search account");
			crm().identifyAccountPage().accountConfirmPanel().confirm();
			refundSAPOrderNumber = crm().identifyAccountPage().ordersTabPanel().getSAPOrderNumberByOrderStatus(orderNumber, "Order Pending - P");
			System.out.println("refundSAPOrderNumber : "+refundSAPOrderNumber);
			crm().identifyAccountPage().topPanel().clickEnd();
			crm().logOff();
			crm().closeApplication();
			
			
			 /** End of Processing Refund*/
			 
			
			
			 /** DB Validation*/
			 
			//Data Validation for OSDB
			dbService().OrderStatusDB().verifyOrderInOrderHeaderTable(orderNumber, "OSDB_OrderHeader_Closed");
			dbService().OrderStatusDB().verifyOrderInOrderItemTable(orderNumber, "OSDB_Item_Closed");
			dbService().OrderStatusDB().verifyOrderInOrderShipmentTable(orderNumber, "OSDB_OrderShipment_Closed");

			//Data Validation for DWDB
			dbService().datawareHouseDB().verifyOrderInOrderHeaderTable(orderNumber, "DWDB_OrderHeader_Closed");
			dbService().datawareHouseDB().verifyOrderInOrderDetailTable(orderNumber, "DWDB_OrderDetail_Closed");
			dbService().datawareHouseDB().verifyOrderInOrderDetailActivityTable(orderNumber, "DWDB_OrderDetailActivity_Closed");
			//dbService().datawareHouseDB().verifyDetailsInCustomerTable(customerID, "DWDB_Customer_Closed");
			dbService().datawareHouseDB().verifyOrderInOrderCCNumberTable(orderNumber, "DWDB_OrderCCNumber_Closed");

			//Data Validation for BN Inc DB
			dbService().BNIncDB().verifyOrderInOrderHeaderTable(orderNumber, "BNIncDB_OrderHeader_Closed");
			dbService().BNIncDB().verifyOrderInOrderDetailTable(orderNumber, "BNIncDB_OrderDetail_Closed");
			dbService().BNIncDB().verifyOrderInOrderCCNumberActivityTable(orderNumber, "BNIncDB_OrderCCNumber_Closed");
			//dbService().BNIncDB().verifyDetailsInCustomerTable(customerID, "BNIncDB_Customer_Closed");

			//Data Validation for Sales Rank DB 
			dbService().salesRankDB().verifyOrderInOrderHeaderTable(orderNumber, "SalesRankDB_OrderHeader_Closed"+EnvironmentUtility.SalesRankDB);
			dbService().salesRankDB().verifyOrderInOrderDetailTable(orderNumber, "SalesRankDB_OrderDetail_Closed"+EnvironmentUtility.SalesRankDB);
			

			delayFor(20);
			
		//String refundID = "610086315";
			
			/* * starting SAP and login*/
			 
			sap().startApplication();
			sap().logon720Dialog().selectSystemToLogin();
			sap().userLoginDialog().logIn();
			
			
			 /** Search the item in zalo1 transaction*/
			 
			sap().easyAccessDialog().topLevelToolbar().enterCommand("/nzalo1");
			sap().iDocReportingToolDialog().searchSalesOrder(refundID, null, "ZBRE");
			sap().iDocReportingToolDialog().expandAllSalesOrder();
			sap().iDocReportingToolDialog().expandAllSalesOrder();
			String iDocForZ200 = sap().iDocReportingToolDialog().getIDocForZ200Refund();
			System.out.println(iDocForZ200);
			
			
			 /** Process pending iDoc in BD87*/
			 
			sap().iDocReportingToolDialog().topLevelToolbar().enterCommand("/nbd87");
			sap().selectIDocDialog().searchIDoc(iDocForZ200);
			sap().statusMonitorDialog().expandiDocOutboundProcessing();
			if(	sap().statusMonitorDialog().isOutboundORDRSPVisible()){
				sap().statusMonitorDialog().selectORDRSPndProcess();
				sap().informationDialog().clickOkButton();
				sap().iDocProcessingDialog().verifyProcessedIDOCGrid("Z200_processed");
				sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/ncic0");

			} else {
				sap().statusMonitorDialog().topLevelToolbar().enterCommand("/ncic0");
			}
			
			sap().customerInteractionCenterDialog().close();
			sap().logOffDialog().clickYesButton();
			sap().logon720Dialog().close();
			
			
			 /** DB Validation*/
			 
			//Data Validation for OSDB
			dbService().OrderStatusDB().verifyOrderInOrderHeaderTable(orderNumber, "OSDB_OrderHeader_Refund");
			dbService().OrderStatusDB().verifyOrderInOrderItemTable(orderNumber, "OSDB_Item_Refund");
			dbService().OrderStatusDB().verifyOrderInOrderShipmentTable(orderNumber, "OSDB_OrderShipment_Refund");

			//Data Validation for DWDB
			dbService().datawareHouseDB().verifyOrderInOrderHeaderTable(orderNumber, "DWDB_OrderHeader_Refund");
			dbService().datawareHouseDB().verifyOrderInOrderDetailTable(orderNumber, "DWDB_OrderDetail_Refund");
			dbService().datawareHouseDB().verifyOrderInOrderDetailActivityTable(orderNumber, "DWDB_OrderDetailActivity_Refund");
			dbService().datawareHouseDB().verifyOrderInOrderCCNumberTable(orderNumber, "DWDB_OrderCCNumber_Refund");

			//Data Validation for BN Inc DB
			dbService().BNIncDB().verifyOrderInOrderHeaderTable(orderNumber, "BNIncDB_OrderHeader_Refund");
			dbService().BNIncDB().verifyOrderInOrderDetailTable(orderNumber, "BNIncDB_OrderDetail_Refund");
			dbService().BNIncDB().verifyOrderInOrderCCNumberActivityTable(orderNumber, "BNIncDB_OrderCCNumber_Refund");

			//Data Validation for Sales Rank DB 
			dbService().salesRankDB().verifyOrderInOrderHeaderTable(orderNumber, "SalesRankDB_OrderHeader_Refund"+EnvironmentUtility.SalesRankDB);
			dbService().salesRankDB().verifyOrderInOrderDetailTable(orderNumber, "SalesRankDB_OrderDetail_Refund"+EnvironmentUtility.SalesRankDB);
			//Writing the Refund id
			writeOutboundBNOrdersToExcel("TC01_ABREFUND", null, refundID+"(RefundID)",null,null);

		}
		else{
			logError("Order Number is not generated");
		}	

	}
}

