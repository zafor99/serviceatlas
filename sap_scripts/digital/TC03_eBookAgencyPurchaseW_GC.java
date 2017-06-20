package sap_scripts.digital;
import resources.sap_scripts.digital.TC03_eBookAgencyPurchaseW_GCHelper;
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

/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class TC03_eBookAgencyPurchaseW_GC extends TC03_eBookAgencyPurchaseW_GCHelper
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
		
		
		String ean,orderNumber,timeStamp,status,iDoc,purchaseRequisition,poNumber = null;
		ean = "9780671036904";
		//Submit IP orders 
		instantPurchase().purchaseDigitalItemWith2GC(ean, "2", "8");
		orderNumber=instantPurchase().getOrderNumber();
		timeStamp = instantPurchase().getOrderTimeStamp();
		if(orderNumber.length()>2){
			writeDigitalOrdersForSAPToExcel("TC03_eBookAgencyPurchaseW_GC", timeStamp, orderNumber, ean);
			//Starting  SAP Application
			sap().startApplication();
			sap().logon720Dialog().selectSystemToLogin();
			//sap().userLoginDialog().waitForExistence();
			sap().userLoginDialog().logIn();
			delayFor(5);
			//Process Orders in SAP
			sap().easyAccessDialog().topLevelToolbar().enterCommand("/nse16");
			sap().dataBrowserInitialScreenDialog().execute("EDIDC");
			sap().dataBrowserTableSelectionScreenDialog().execute(orderNumber);
			status = sap().dataBrowserTableEDIDCDialog().getStatus();
			//If order needs to be process
			if(status.contentEquals("64")){
				iDoc = sap().dataBrowserTableEDIDCDialog().getiDocNumber();
				sap().dataBrowserTableEDIDCDialog().topLevelToolbar().enterCommand("/nbd87");
				sap().selectIDocDialog().searchIDoc(iDoc);
				sap().statusMonitorDialog().expandiDocInboundProcessing();
				if(	sap().statusMonitorDialog().isInboundOrdersVisible()){
					sap().statusMonitorDialog().selectInboundORDERSndProcess();
					sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nva03");
					
				} else {
					sap().statusMonitorDialog().topLevelToolbar().enterCommand("/nva03");
				}
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
			sap().automaticCreationOfPODialog().verifyPurchaseOrderCreation("POCreation");
			
			sap().automaticCreationOfPurchaseDialog().topLevelToolbar().enterCommand("/nva03");

			sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
			sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
			
			//Verify ZEAM Status	
			sap().displayBNOrderPhaseOverviewDialog().salesTab().verifyAllItemsTable("ZEAMStatus");
			sap().displayBNOrderPhaseOverviewDialog().openDisplayDocHeaderDeatailsDialog();
			
			//Verify the order is Authorized/
			sap().displayBNOrdPhaseHeaderDataDialog().paymentCardsTab().verifyPaymentCardsTable("Authorized");
			
			//Verify Sales Data
			sap().displayBNOrdPhaseHeaderDataDialog().salesTab().select();
			sap().displayBNOrdPhaseHeaderDataDialog().salesTab().verifySalesData("SalesData");
			
			//Verify Net Price and Tax on conditions Tab
			sap().displayBNOrdPhaseHeaderDataDialog().conditionTab().select();
			sap().displayBNOrdPhaseHeaderDataDialog().conditionTab().verifyNetAndTax("NetPriceAndTax");
			
			//Verify Partners Table in Partners Tab
			sap().displayBNOrdPhaseHeaderDataDialog().partnersTab().select();
			sap().displayBNOrdPhaseHeaderDataDialog().partnersTab().verifyPartnersTable("PartnersTable");
			sap().displayBNOrdPhaseHeaderDataDialog().openDisplayDocumentFlowDialog();
			
			//Verify Order Processed in Doc Flow and get PO number 
			sap().documentFlowDialog().verifyDocumentTable("OrderProcessed");
			poNumber = sap().documentFlowDialog().getPurchaseOrderNumberFromHeaderData();
			
			//Processing  invoice job
			sap().documentFlowDialog().topLevelToolbar().enterCommand("/nse38");
			sap().aBAPEditorInitialScreenDialog().executeWithVariant("ZROF_CREATE_INCMNG_INVOICE_DP","ZERMMINV_QAEB");
			sap().createIncomingInvoiceDialog().createIncomingInvoice(poNumber);
			sap().createIncomingInvoiceDialog().verifyInvoiceCreated("InvoicePosted");
			
			//Verify IR gets created
			sap().createIncomingInvoiceDialog().topLevelToolbar().enterCommand("/nva03");
			sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
			sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
			sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
			sap().documentFlowDialog().verifyDocumentTable("FinalDocFlow");
			sap().documentFlowDialog().selectPurchaseOrderAndDisplayDoc();
			sap().appsDownloadPODialog().purchaseOrderHistoryTab().select();
			sap().appsDownloadPODialog().purchaseOrderHistoryTab().verifyPurchaseHistoryTable("IRGotCreated");
		
			//verify closed status
			crm().startApplication();
			crm().signInDialog().signIn("zsadeque", "Aminai@6", "Login");
			crm().selectABusinessRolePage().clickZBNT1CSRLink();
			crm().identifyAccountPage().accountSearchPanel().search(orderNumber, null, null, null, null, "search account");
			crm().identifyAccountPage().accountConfirmPanel().confirm();
			crm().identifyAccountPage().ordersTabPanel().selectOrderTableRowbyOrderNum(orderNumber);
			crm().identifyAccountPage().ordersTabPanel().verifyOrdersTable("OrderClosed");
			crm().topNavToolBar().end();
			crm().logOff();
			crm().closeApplication();
		
			//Close application
			sap().appsDownloadPODialog().close();
			sap().appsDownloadPODialog().close();
			sap().closeApplication();
		}
		else{
			logError("Order Number is not generated");
		}
		
	
	
		
	}
}

