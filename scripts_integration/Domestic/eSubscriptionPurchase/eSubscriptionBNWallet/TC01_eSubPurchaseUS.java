package scripts_integration.Domestic.eSubscriptionPurchase.eSubscriptionBNWallet;
import resources.scripts_integration.Domestic.eSubscriptionPurchase.eSubscriptionBNWallet.TC01_eSubPurchaseUSHelper;
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
public class TC01_eSubPurchaseUS extends TC01_eSubPurchaseUSHelper
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
		timerStart("TC01_eSubPurchaseUS");
		String ean,orderNumber,purchaseRequisition, poNumber,status,iDoc,customerID,currentDate,contract = null;
		BNTimer timer = new BNTimer();
		currentDate = timer.getCurrentDate();
		ean = "2940043960528";
 
		/* QC Step1,2,3
		 * Submit IP orders 
		 */
		instantPurchase().submitIPOrder("VI", "4313081835209051", ean);
		
		framework.HealthCheckSmokeTest.setServiceId(23);
		instantPurchase().verifyVertexServiceStatusInInstantPurchase("VertexService");
		

		framework.HealthCheckSmokeTest.setServiceId(22);
		instantPurchase().verifyEDSServiceStatusInInstantPurchase("EDSService");

		framework.HealthCheckSmokeTest.setServiceId(3);
		instantPurchase().verifyCCAuthorizationServiceStatusInInstantPurchase("CCAuthorization");
		/*framework.HealthCheckSmokeTest.setServiceId(10);
		instantPurchase().verifyInstantPurchase("IPResponseVP");
		*/orderNumber=instantPurchase().getOrderNumber();
		customerID = instantPurchase().getCustomerID();
		if(orderNumber.length()>2){
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
			framework.HealthCheckSmokeTest.setServiceId(5);
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
			framework.HealthCheckSmokeTest.setServiceId(5);
			sap().createIncomingInvoiceDialog().verifyInvoiceCreated("InvoicePosted");
			
			
			sap().createIncomingInvoiceDialog().topLevelToolbar().enterCommand("/nva43");
			sap().changeContractInitialScreenDialog().search(orderNumber);
			sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
			sap().displayBNOrderPhaseOverviewDialog().openDisplayDocumentFlowDialog();
			sap().documentFlowDialog().waitForAccountingDoc();
			framework.HealthCheckSmokeTest.setServiceId(5);
			sap().documentFlowDialog().verifyDocumentTable("FinalDocFlow");
			sap().documentFlowDialog().topLevelToolbar().enterCommand("/nw45");
			
			if(!isShellMode()){
				sap().easyAccessDialog().close();
				sap().logOffDialog().clickYesButton();
				sap().closeApplication();
			}
			
/*			 * Step 10
			 * Validate in CRM or Cico
			 */
			
			if(!isShellMode()){
				crm().clearBrowserCookies();
				crm().startApplication();
				crm().signInDialog().signIn();
				crm().selectABusinessRolePage().clickZBNT1CSRLink();
			}
			crm().identifyAccountPage().accountSearchPanel().search(orderNumber, null, null, null, null, "search account");
			crm().identifyAccountPage().accountConfirmPanel().confirm();
			framework.HealthCheckSmokeTest.setServiceId(5);
			crm().identifyAccountPage().ordersTabPanel().verifyOrdersTable("OrderOpen");
			crm().topNavToolBar().end();

			if(!isShellMode()){
				crm().logOff();
				crm().closeApplication();
			}
			
		/*	 * step 8
			 */
			
		//	dbService().edsDB().verifyDeatailsInPeriodicalitemTable(customerID, ean, "EDSLocker");
			
			
		}
		else{
			framework.HealthCheckSmokeTest.setServiceId(10);
			HealthCheckSmokeTest.writeResultToDB("Order Not Generated", false, "BN Subscription Order Not Generated", "200");
			HealthCheckSmokeTest.writeResultToDB("ActiveMQ issue", false, "ActiveMQ is not Run", null);
			framework.HealthCheckSmokeTest.setServiceId(10);
			HealthCheckSmokeTest.writeResultToDB("SAP Issue", false, "SAP part of the script did not get executed", null);

			logError("Order Number is not generated");
		}
		
		timerStop("TC01_eSubPurchaseUS");

	}
}

 