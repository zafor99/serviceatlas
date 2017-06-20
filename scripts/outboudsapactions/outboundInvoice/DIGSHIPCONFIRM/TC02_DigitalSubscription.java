package scripts.outboudsapactions.outboundInvoice.DIGSHIPCONFIRM;
import resources.scripts.outboudsapactions.outboundInvoice.DIGSHIPCONFIRM.TC02_DigitalSubscriptionHelper;
import utils.BNTimer;
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

import framework.HealthCheckSmokeTest;

/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class TC02_DigitalSubscription extends TC02_DigitalSubscriptionHelper
{
	/**
	 * Script Name   : <b>TC01_eSubPurchaseUS</b>
	 * Generated     : <b>Oct 4, 2013 11:36:55 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/10/04
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		timerStart("TC02_DigitalSubscription");
		String ean,orderNumber,purchaseRequisition, poNumber,status,iDoc,customerID,currentDate,contract,timeStamp,releaseNumber = null;
		BNTimer timer = new BNTimer();
		currentDate = timer.getCurrentDate();
		ean = "2940043955296";//"2940043960528";
 
		/* QC Step1,2,3
		 * Submit IP orders 
		 */
		instantPurchase().submitIPOrder("VI", "4313081835209051", ean);
		instantPurchase().verifyVertexServiceStatusInInstantPurchase("VertexService");
		instantPurchase().verifyEDSServiceStatusInInstantPurchase("EDSService");

		instantPurchase().verifyCCAuthorizationServiceStatusInInstantPurchase("CCAuthorization");
		instantPurchase().verifyInstantPurchase("IPResponseVP");
		orderNumber=instantPurchase().getOrderNumber();
		timeStamp = instantPurchase().getOrderTimeStamp();
		customerID = instantPurchase().getCustomerID();
		if(orderNumber.length()>2){
			writeInvoicedBNOrdersToExcel("TC02_DigitalSubscription", timeStamp, orderNumber, ean,customerID);
			/*
			 * Step 4 : Step is not clear...order can go through EAI/Atlas
			 * Step 5 : We should only verify if one row gets inserted in 
			 */
			framework.HealthCheckSmokeTest.setServiceId(10);
			dbService().OrderStatusDB().verifyOrderExistInOrderHeaderTable("OSDB_OrdExtInOrderHeaderTable", orderNumber);
			/*
			 * step 6 : why we need to verify the Timestamp of the order?  and tax details of the order?
			 * 			Auth Type = null
			 * 			Payauth DB takes a long time to get updated
			 */

			/*
			 * Step 7
			 */
			//Starting  SAP Application
			if(!isShellMode()){
				sap().startApplication();
				sap().logon720Dialog().selectSystemToLogin();
				sap().userLoginDialog().logIn();
			}
			
			//Process Orders in SAP
			sap().easyAccessDialog().topLevelToolbar().enterCommand("/nse16");
			sap().dataBrowserInitialScreenDialog().execute("EDIDC");
			sap().dataBrowserInitialScreenDialog().topLevelToolbar().enterCommand("/nse16");
			sap().dataBrowserInitialScreenDialog().execute("EDIDC");
			sap().dataBrowserTableSelectionScreenDialog().execute(orderNumber);
			status = sap().dataBrowserTableEDIDCDialog().getStatus();
			//If order needs to be process
			if(status.contentEquals("64")){
				iDoc = sap().dataBrowserTableEDIDCDialog().getiDocNumber();
				sap().dataBrowserTableEDIDCDialog().topLevelToolbar().enterCommand("/nZRPIDOC");
				sap().createBatchJobToProcessiDocDialog().createBatchtoProcessiDoc(iDoc);
				sap().createBatchJobToProcessiDocDialog().topLevelToolbar().enterCommand("/nva42");
			}
			else{
				sap().dataBrowserTableEDIDCDialog().topLevelToolbar().enterCommand("/nva42");
			}
			sap().changeContractInitialScreenDialog().search(orderNumber);
			sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
			
			
/*			 * changing billing date to current date*/
			 
			
			sap().displayBNOrderPhaseOverviewDialog().openDisplayDocHeaderDeatailsDialog();
			sap().displayBNOrdPhaseHeaderDataDialog().BillingPlanTab().select();
			sap().displayBNOrdPhaseHeaderDataDialog().BillingPlanTab().updateStartDate(currentDate);
			sap().displayBNOrdPhaseHeaderDataDialog().topLevelToolbar().navigateBack();
			sap().displayBNOrderPhaseOverviewDialog().topLevelToolbar().save();
			framework.HealthCheckSmokeTest.setServiceId(5);
			sap().changeContractInitialScreenDialog().verifyStatusBar("SubsSaved");
			sap().changeContractInitialScreenDialog().search(orderNumber);
			sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
			contract= sap().displayBNOrderPhaseOverviewDialog().getBNSubscription();
			
			/* * Release Order Creation*/
			 
			sap().displayBNOrderPhaseOverviewDialog().topLevelToolbar().enterCommand("/nse38");
			sap().aBAPEditorInitialScreenDialog().execute("ZSD_SUBSCRIPTION_IDOC_CREATE");
			sap().createAndProcessSubscriptioniDocsDialog().createSubscriptioniDocs(currentDate,contract);
			sap().createAndProcessSubscriptioniDocsDialog().waitForStatus53();
			
			
			
			/* * Getting purchase Requisition number */
			 
			sap().createAndProcessSubscriptioniDocsDialog().topLevelToolbar().enterCommand("/nva43");
			sap().changeContractInitialScreenDialog().search(orderNumber);
			sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
			sap().displayBNOrderPhaseOverviewDialog().openDisplayDocumentFlowDialog();
			framework.HealthCheckSmokeTest.setServiceId(5);
			sap().documentFlowDialog().verifyBNSubcriptionRel("BNSubcriptionRelGotCreated");
			sap().documentFlowDialog().selectBNSubsRelAndDisplayDoc();
			sap().displayBNOrderPhaseOverviewDialog().clickScheduleLinesForItem();
			sap().displayOrdPhaseItemDataDialog().scheduleLinesTab().select();
			purchaseRequisition = sap().displayOrdPhaseItemDataDialog().scheduleLinesTab().getFirstPurchaseRequisition();
			
			
	/*		 * Step 9
			 *Create PO and verify Doc Flow */
			 
			sap().displayOrdPhaseItemDataDialog().topLevelToolbar().enterCommand("/nME59");
			sap().automaticCreationOfPODialog().createPOfromPurchaseRequisition(purchaseRequisition,"DIGITAL_QASUB");
			//sap().automaticCreationOfPODialog().verifyPurchaseOrderCreation("POCreation");
			
			sap().automaticCreationOfPurchaseDialog().topLevelToolbar().enterCommand("/nva43");
			sap().changeContractInitialScreenDialog().search(orderNumber);
			sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
			sap().displayBNOrderPhaseOverviewDialog().openDisplayDocumentFlowDialog();
			sap().documentFlowDialog().verifyDocumentTable("POCreated");
			poNumber = sap().documentFlowDialog().getPurchaseOrderNumberFromHeaderData();
			
			
	/*		 * Step 8
			 * Verify Sales office & Sales Group*/
			 
			sap().documentFlowDialog().topLevelToolbar().navigateBack();
			sap().displayBNOrderPhaseOverviewDialog().openDisplayDocHeaderDeatailsDialog();
			sap().displayBNOrdPhaseHeaderDataDialog().salesTab().select();
			framework.HealthCheckSmokeTest.setServiceId(5);
			sap().displayBNOrdPhaseHeaderDataDialog().salesTab().verifySalesData("SalesData");
		
			
			
			 
			sap().displayBNOrdPhaseHeaderDataDialog().topLevelToolbar().enterCommand("/nse38");
			sap().aBAPEditorInitialScreenDialog().executeWithVariant("ZROF_CREATE_INCMNG_INVOICE_DP","ZAPPMMINV_QAAP");
			sap().createIncomingInvoiceDialog().createIncomingInvoice(poNumber,"ZSAM");
			sap().createIncomingInvoiceDialog().verifyInvoiceCreated("InvoicePosted");
			
			
			sap().createIncomingInvoiceDialog().topLevelToolbar().enterCommand("/nva43");
			sap().changeContractInitialScreenDialog().search(orderNumber);
			sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
			sap().displayBNOrderPhaseOverviewDialog().openDisplayDocumentFlowDialog();
			sap().documentFlowDialog().waitForAccountingDoc();
			sap().documentFlowDialog().verifyDocumentTable("FinalDocFlow");
			releaseNumber =sap().documentFlowDialog().getBNSubscriptionRELNumberFromHeaderData(); 
			//sap().documentFlowDialog().topLevelToolbar().enterCommand("/nw45");
			sap().documentFlowDialog().topLevelToolbar().enterCommand("/nzalo1");
			
			
			sap().iDocReportingToolDialog().searchSalesOrder(releaseNumber, null, null);
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
			
			
			sap().easyAccessDialog().close();
			sap().logOffDialog().clickYesButton();
			sap().closeApplication();
			
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


			
		//	dbService().edsDB().verifyDeatailsInPeriodicalitemTable(customerID, ean, "EDSLocker");
			activityMonitor().verifyActivityMonitorForError(orderNumber, "ActivityMonitorErrorCount");
			activityMonitor().verifyNeolaneCountInActivityMonitor(orderNumber, "NeolaneEmailEventCount");
			
		}
		else{
			logError("Order Number is not generated");
		}
		
		timerStop("TC02_DigitalSubscription");

	}
}

 