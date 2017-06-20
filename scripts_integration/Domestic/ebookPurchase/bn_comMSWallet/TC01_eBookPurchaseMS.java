package scripts_integration.Domestic.ebookPurchase.bn_comMSWallet;
import resources.scripts_integration.Domestic.ebookPurchase.bn_comMSWallet.TC01_eBookPurchaseMSHelper;
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
public class TC01_eBookPurchaseMS extends TC01_eBookPurchaseMSHelper
{
	/**
	 * Script Name   : <b>TC01_NookAppPurchase</b>
	 * Generated     : <b>Oct 4, 2013 11:36:55 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/10/04
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		timerStart("TC01_eBookPurchaseMSwallet");
		String accessToken,ean,orderNumber,purchaseRequisition, poNumber,status,iDoc,customerID,timeStamp,orderStatus = null;
		ean = "9780553905656";//"9780671036904";

		/* QC Step1-10
		 * Submit IP orders with MS Wallet 
		 */

		msWalletIPEnv().readClientInfo("US", "WEB");
		mswalletTool().startApp();
		mswalletTool().enterCredentials("QA06", msWalletIPEnv().getClientType(), msWalletIPEnv().getUserName(), msWalletIPEnv().getPassword());
		mswalletTool().clickRequestCodeButton();
		mswalletTool().clickAccessTokenButton();
		accessToken = mswalletTool().getAccessToken();
		mswalletTool().closeApp();
		if(accessToken.length()>2){
			customerUserAccount().createAccount();
			customerID = customerUserAccount().getCustomerId();
			msWalletService().createInstantPurchase(accessToken, customerID, ean) ;
			framework.HealthCheckSmokeTest.setServiceId(23);
			msWalletService().verifyVertexServiceStatusInInstantPurchase("VertexService");

			framework.HealthCheckSmokeTest.setServiceId(22);
			msWalletService().verifyEDSServiceStatusInInstantPurchase("EDSService");
			orderNumber=msWalletService().getOrderNumber();
			timeStamp = msWalletService().getOrderTimeStamp();


			if(orderNumber.length()>2){
				framework.HealthCheckSmokeTest.setServiceId(20);
				if(activeMQService().verifyPendingMessageCountByRoute("SAPOrderRoute", "saporders")){
					//Step 12 : EDS DB Validation . Doing through Service
					//Validate Order Exist in Digital Locker
					//	digitalLocker(customerID).verifyLockerItem("DigitalLocker");

					//Step 12: Verify OSDB DB Validation
					framework.HealthCheckSmokeTest.setServiceId(10);
					dbService().OrderStatusDB().verifyOrderExistInOrderHeaderTable("OSDB_OrdExtInOrderHeaderTable", orderNumber);

					/*
					 * Step 14
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
					sap().dataBrowserInitialScreenDialog().topLevelToolbar().enterCommand("/nse16");
					sap().dataBrowserInitialScreenDialog().execute("EDIDC");
					sap().dataBrowserTableSelectionScreenDialog().execute(orderNumber);
					//					framework.HealthCheckSmokeTest.setServiceId(20);
					//					activeMQService().verifyPendingMessageCountByRoute("SAPOrderRoute", "saporders");

					//					if(sap().dataBrowserTableEDIDCDialog().waitForExistence(10)){
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
					//sap().automaticCreationOfPODialog().verifyPurchaseOrderCreation("POCreation");

					sap().automaticCreationOfPurchaseDialog().topLevelToolbar().enterCommand("/nva03");

					sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
					sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);



					/* * Step 15*/

					sap().displayBNOrderPhaseOverviewDialog().openDisplayDocHeaderDeatailsDialog();
					sap().displayBNOrdPhaseHeaderDataDialog().salesTab().select();
					framework.HealthCheckSmokeTest.setServiceId(5);
					sap().displayBNOrdPhaseHeaderDataDialog().salesTab().verifySalesData("SalesData");

					/*	step 14
					 *  Verify purchase with MSW*/


					sap().displayBNOrdPhaseHeaderDataDialog().paymentCardsTab().select();
					framework.HealthCheckSmokeTest.setServiceId(5);
					sap().displayBNOrdPhaseHeaderDataDialog().paymentCardsTab().verifyPaymentCardsTable("MSW");


					/*	 * Step 16
					 * TODO: Why need to validate "Verify Correct "YO Microsoft" Partner is populated!"*/

					sap().displayBNOrdPhaseHeaderDataDialog().partnersTab().select();
					framework.HealthCheckSmokeTest.setServiceId(5);
					sap().displayBNOrdPhaseHeaderDataDialog().partnersTab().verifyPartnersTable("PartnersTab");

					sap().displayBNOrdPhaseHeaderDataDialog().openDisplayDocumentFlowDialog();
					poNumber = sap().documentFlowDialog().getPurchaseOrderNumberFromHeaderData();


					/* * Verify Final Doc Flow*/

					//Processing  invoice job
					sap().documentFlowDialog().topLevelToolbar().enterCommand("/nse38");
					sap().aBAPEditorInitialScreenDialog().executeWithVariant("ZROF_CREATE_INCMNG_INVOICE_DP","ZAPPMMINV_QAAP");
					sap().createIncomingInvoiceDialog().createIncomingInvoice(poNumber);
					framework.HealthCheckSmokeTest.setServiceId(5);
					sap().createIncomingInvoiceDialog().verifyInvoiceCreated("InvoicePosted");
					sap().createIncomingInvoiceDialog().topLevelToolbar().enterCommand("/nva03");
					sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
					sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
					sap().displayBNOrderPhaseOverviewDialog().openDisplayDocumentFlowDialog();
					framework.HealthCheckSmokeTest.setServiceId(5);
					sap().documentFlowDialog().verifyDocumentTable("FinalDocFlow");
					sap().documentFlowDialog().topLevelToolbar().enterCommand("/nw45");
					//Close application
					if(!isShellMode()){
						sap().easyAccessDialog().close();
						sap().logOffDialog().clickYesButton();
						sap().closeApplication();
					}
					/*
					 * Step 17 
					 * 
					 */
					if(!isShellMode()){
						crm().startApplication();
						crm().signInDialog().signIn();
						crm().selectABusinessRolePage().clickZBNT1CSRLink();
					}
					crm().identifyAccountPage().accountSearchPanel().search(orderNumber, null, null, null, null, "search account");
					crm().identifyAccountPage().accountConfirmPanel().confirm();
					framework.HealthCheckSmokeTest.setServiceId(5);
					crm().identifyAccountPage().ordersTabPanel().verifyOrdersTable("OrderClosed");
					orderStatus = crm().identifyAccountPage().ordersTabPanel().getOrderStatus(orderNumber);
					//					writeIntegrationOrdersToExcel("TC01_eBookPurchaseBN", timeStamp, orderNumber, ean, customerID,orderStatus);
					crm().topNavToolBar().end();

					if(!isShellMode()){
						crm().logOff();
						crm().closeApplication();
					}

					//}
				}
				else{
					if(activeMQService().getRoutePendingMessageCount("saporders")>0){
						framework.HealthCheckSmokeTest.setServiceId(20);
						HealthCheckSmokeTest.writeResultToDB("ActiveMQ issue", false, "SAPOrders Route of ActiveMQ is not pushing SAP Orders",  "Status Code : 500");
					}
					else{
						framework.HealthCheckSmokeTest.setServiceId(10);
						HealthCheckSmokeTest.writeResultToDB("Order didn't reach SAP", false, "Order didn't reach SAP", null);
					}
					if(!isShellMode()){
						sap().dataBrowserTableSelectionScreenDialog().close();
						sap().logOffDialog().clickYesButton();
						sap().closeApplication();
					}
				}

			}
			else{

				framework.HealthCheckSmokeTest.setServiceId(20);
				logError("Order did not get generated");
				HealthCheckSmokeTest.writeResultToDB("Order Not Generated", false, "Ms Wallet Order Not Generated", null);
				HealthCheckSmokeTest.writeResultToDB("Order Not Generated", false, "Ms Wallet Order Not Generated", null);
			}

		}
		else{
			framework.HealthCheckSmokeTest.setServiceId(20);
			HealthCheckSmokeTest.writeResultToDB("AccessToken", false, "Unable to generate MS Wallet Access Token", null);
			framework.HealthCheckSmokeTest.setServiceId(20);
			HealthCheckSmokeTest.writeResultToDB("ActiveMQ issue", false, "ActiveMQ is not Run", null);
			framework.HealthCheckSmokeTest.setServiceId(10);
			HealthCheckSmokeTest.writeResultToDB("SAP Issue", false, "SAP part of the script did not get executed", null);

			logError("Unable to generate Access Token");
		}
		timerStop("TC01_eBookPurchaseMSwallet");

	}
}

