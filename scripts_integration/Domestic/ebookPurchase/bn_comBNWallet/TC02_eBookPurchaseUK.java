package scripts_integration.Domestic.ebookPurchase.bn_comBNWallet;
import resources.scripts_integration.Domestic.ebookPurchase.bn_comBNWallet.TC02_eBookPurchaseUKHelper;
import utils.BNTimer;

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
public class TC02_eBookPurchaseUK extends TC02_eBookPurchaseUKHelper
{
	/**
	 * Script Name   : <b>TC02_eBookPurchaseUK</b>
	 * Generated     : <b>Oct 4, 2013 11:36:55 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/10/04
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{

		timerStart("TC02_eBookPurchaseUK");
		String ean,orderNumber,purchaseRequisition, poNumber,status,iDoc,orderStatus = null;
		ean = "9780007278923"; ///from Salil 9780007278923,9781406342765
		/* QC Step1,2,3
		 * Submit IP orders 
		 */
		//instantPurchase().submitIPOrder("VI", "4313081835209051", ean);
		instantPurchase().submitUKIPOrder("VI", "4313081835209051",ean);

		orderNumber=instantPurchase().getOrderNumber();


		framework.HealthCheckSmokeTest.setServiceId(21);
		instantPurchase().verifyAcertifyServiceStatusInInstantPurchase("acertifyService");

		framework.HealthCheckSmokeTest.setServiceId(23);
		instantPurchase().verifyVertexServiceStatusInInstantPurchase("VertexService");


		framework.HealthCheckSmokeTest.setServiceId(22);
		instantPurchase().verifyEDSServiceStatusInInstantPurchase("EDSService");

		framework.HealthCheckSmokeTest.setServiceId(3);
		instantPurchase().verifyCCAuthorizationServiceStatusInInstantPurchase("CCAuthorization");

		if(orderNumber.length()>2){
			/*
			 * Validating SAPOurder route in Active MQ
			 */
			framework.HealthCheckSmokeTest.setServiceId(20);
			if(activeMQService().verifyPendingMessageCountByRoute("SAPOrderRoute", "saporders")){
				/*
				 * Step 7
				 * 
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
				sap().dataBrowserTableSelectionScreenDialog().execute(orderNumber);	
				/*			framework.HealthCheckSmokeTest.setServiceId(20);
				activeMQService().verifyPendingMessageCountByRoute("SAPOrderRoute", "saporders");
				 *///			if(sap().dataBrowserTableEDIDCDialog().waitForExistence(10)){
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
				sap().automaticCreationOfPODialog().createPOfromPurchaseRequisition(purchaseRequisition,"DIGITAL_QAEBA");
				//			sap().automaticCreationOfPODialog().verifyPurchaseOrderCreation("POCreation");

				sap().automaticCreationOfPurchaseDialog().topLevelToolbar().enterCommand("/nva03");

				sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
				sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);

				/*
				 * step 8
				 * Verify Sales Data
				 */
				sap().displayBNOrderPhaseOverviewDialog().openDisplayDocHeaderDeatailsDialog();
				sap().displayBNOrdPhaseHeaderDataDialog().salesTab().select();

				framework.HealthCheckSmokeTest.setServiceId(5);
				sap().displayBNOrdPhaseHeaderDataDialog().salesTab().verifySalesData("SalesData");

				/*
				 * Step 9 
				 * 
				 */
				sap().displayBNOrdPhaseHeaderDataDialog().openDisplayDocumentFlowDialog();
				poNumber = sap().documentFlowDialog().getPurchaseOrderNumberFromHeaderData();
				//Processing  invoice job
				sap().documentFlowDialog().topLevelToolbar().enterCommand("/nse38");
				sap().aBAPEditorInitialScreenDialog().executeWithVariant("ZROF_CREATE_INCMNG_INVOICE_DP","ZERMMINV_QAEB");
				sap().createIncomingInvoiceDialog().createIncomingInvoice(poNumber);
				if(sap().createIncomingInvoiceDialog().isInvoiceCreated()){
					framework.HealthCheckSmokeTest.setServiceId(5);
					sap().createIncomingInvoiceDialog().verifyInvoiceCreated("InvoicePosted");
					sap().createIncomingInvoiceDialog().topLevelToolbar().enterCommand("/nva03");	
				}else{
					sap().createIncomingInvoiceDialog().topLevelToolbar().enterCommand("/nva03");
				}

				sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
				sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
				sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();

				framework.HealthCheckSmokeTest.setServiceId(5);
				sap().documentFlowDialog().verifyDocumentTable("FinalDocFlow");
				sap().documentFlowDialog().topLevelToolbar().enterCommand("/nw45");
				//Close application.
				if(!isShellMode()){
					sap().easyAccessDialog().close();
					sap().logOffDialog().clickYesButton();
					sap().closeApplication();
				}
				/*
				 * Step 10 
				 * validate in CRM
				 */
				if(!isShellMode()){
					crm().clearBrowserCookies();
					crm().startApplication();
					crm().signInDialog().signIn();
					crm().selectABusinessRolePage().clickZBNT1CSRLink();
				}
				crm().identifyAccountPage().accountSearchPanel().search(orderNumber, null, null, null, null, "search account");
				if(crm().identifyAccountPage().accountConfirmPanel().isOrderExist()){
					crm().identifyAccountPage().accountConfirmPanel().confirm();
					framework.HealthCheckSmokeTest.setServiceId(5);
					crm().identifyAccountPage().ordersTabPanel().verifyOrdersTable("OrderClosed");
					orderStatus = crm().identifyAccountPage().ordersTabPanel().getOrderStatus(orderNumber);
					crm().topNavToolBar().end();
					//	writeIntegrationOrdersToExcel("TC01_eBookPurchaseBN", timeStamp, orderNumber, ean, customerID,orderStatus);
				}
				else{
					logError("Order Number :" +orderNumber +"  not found in CRM");
				}

				framework.HealthCheckSmokeTest.setServiceId(10);
				dbService().OrderStatusDB().verifyOrderInOrderHeaderTable(orderNumber, "OSDB_OrderHeaderTable");
				if(!isShellMode()){
					crm().logOff();
					crm().closeApplication();
				}

				//Validate Order Exist in Digital Locker
				//	digitalLocker(customerID).verifyLockerItem("DigitalLocker");


				//	}
			}


			else{
				if(activeMQService().getRoutePendingMessageCount("saporders")>0){
					framework.HealthCheckSmokeTest.setServiceId(20);
					HealthCheckSmokeTest.writeResultToDB("ActiveMQ issue", false, "SAPOrders Route of ActiveMQ is not pushing SAP Orders", "Status Code : 500");
					framework.HealthCheckSmokeTest.setServiceId(5);
					HealthCheckSmokeTest.writeResultToDB("SAP Did not Run", false, "SAP Didn't execute due to order failure", "Status Code : 200");

				}
				else{
					framework.HealthCheckSmokeTest.setServiceId(10);
					HealthCheckSmokeTest.writeResultToDB("Order didn't reach SAP", false, "Order didn't reach SAP", "Status Code : 200");
					framework.HealthCheckSmokeTest.setServiceId(5);
					HealthCheckSmokeTest.writeResultToDB("SAP Did not Run", false, "SAP Didn't execute due to order failure", "Status Code : 200");

				}
				if(!isShellMode()){
					sap().dataBrowserTableSelectionScreenDialog().close();
					sap().logOffDialog().clickYesButton();
					sap().closeApplication();
				}
			}

		}
		else{
			framework.HealthCheckSmokeTest.setServiceId(10);
			HealthCheckSmokeTest.writeResultToDB("Order Not Generated", false, "BN Wallet Order Not Generated", "Status Code : 200");

			framework.HealthCheckSmokeTest.setServiceId(20);
			HealthCheckSmokeTest.writeResultToDB("ActiveMQ issue", false, "SAP Route of ActiveMQ is queing up", "SAP Route of ActiveMQ is queing up");

			framework.HealthCheckSmokeTest.setServiceId(5);
			HealthCheckSmokeTest.writeResultToDB("SAP Did not Run", false, "SAP Didn't execute due to order failure", "Status Code : 200");

			logError("Order Number is not generated");
		}

		timerStop("TC02_eBookPurchaseUK");

	}
}

