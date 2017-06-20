package sap_scripts.digital;
import resources.sap_scripts.digital.TC04_kidsEbooksPurchaseW_2GCnCCHelper;
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
public class TC04_kidsEbooksPurchaseW_2GCnCC extends TC04_kidsEbooksPurchaseW_2GCnCCHelper
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
		
		String orderNumber,timeStamp,hr,min = null;
		
		//instantPurchase().purchaseDigitalItemWith2GC("2940000883259", "3", "3");
		instantPurchase().purchaseDigitalItemWith2GC("9780984930708", ".3", ".3");
		orderNumber=instantPurchase().getOrderNumber();
		timeStamp = instantPurchase().getOrderTimeStamp();
		hr = timeStamp.substring(11, 13);
		min = timeStamp.substring(14, 16);
		
		//Starting  SAP Application
		sap().startApplication();
		sap().logon720Dialog().selectSystemToLogin();
		//sap().userLoginDialog().waitForExistence();
		sap().userLoginDialog().logIn();
		
		//Process iDoc in BD87 
		sap().easyAccessDialog().topLevelToolbar().enterCommand("/nbd87");
		String newHr = (Integer.parseInt(hr)+ 1) + "" ;
		if(newHr.length() == 1){
			newHr = "0"+newHr;
		}
		delayFor(10);
		sap().selectIDocDialog().searchIDoc(hr +":"+min+":00",newHr+":"+min+":00", null, null, hr +":"+min+":00", newHr+":"+min+":00",null);
		sap().statusMonitorDialog().expandiDocInboundProcessing();
		if(	sap().statusMonitorDialog().isInboundOrdersVisible()){
			sap().statusMonitorDialog().selectInboundORDERSndProcess();
			//start cic0 - Display Sales Order & Verify PO Gets Created
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nva03");
			
		} else {
			sap().statusMonitorDialog().topLevelToolbar().enterCommand("/nva03");
		}
		sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
		
		
		//Verify ZDE Status	
		sap().displayBNOrderPhaseOverviewDialog().salesTab().verifyAllItemsTable("ZDEStatus");
		sap().displayBNOrderPhaseOverviewDialog().openDisplayDocHeaderDeatailsDialog();
		
		//Verify the order is Authorized
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
		
		//Process the job
		sap().documentFlowDialog().topLevelToolbar().enterCommand("/nsm37");
		sap().simpleJobSelectionDialog().executeJob("ZROF_REQ2PO_JOB_MANAGER_DIG", "*", "Sched", "Execute");
		sap().jobOverviewDialog().topLevelMenuBar().selectMenuItem(atPath("Job->Repeat scheduling"));
		sap().startTimeDialog().clickImmediate();
		sap().startTimeDialog().save();
		delayFor(10);

		//Get purchase order
		sap().jobOverviewDialog().topLevelToolbar().enterCommand("/nva03");
		sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
		sap().displayBNOrderPhaseOverviewDialog().openDisplayDocHeaderDeatailsDialog();
		sap().displayBNOrdPhaseHeaderDataDialog().openDisplayDocumentFlowDialog();
		String poNumber;
		poNumber = sap().documentFlowDialog().getPurchaseOrderNumberFromHeaderData();

		//Need to process another program
		sap().documentFlowDialog().topLevelToolbar().enterCommand("/nse38");
		sap().aBAPEditorInitialScreenDialog().executeWithVariant("ZROF_CREATE_INCMNG_INVOICE_DP","ZAPP_MM_INV");
		sap().createIncomingInvoiceDialog().createIncomingInvoice(poNumber);
		delayFor(30);
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
		
		//Verify Docflow
		sap().createIncomingInvoiceDialog().topLevelToolbar().enterCommand("/nva03");
		sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		sap().documentFlowDialog().verifyDocumentTable("FinalDocFlow");
		delayFor(30);
		sap().documentFlowDialog().selectPurchaseOrderAndDisplayDoc();
		sap().appsDownloadPODialog().purchaseOrderHistoryTab().select();
		sap().appsDownloadPODialog().purchaseOrderHistoryTab().verifyPurchaseHistoryTable("IRGotCreated");
		
		sap().appsDownloadPODialog().close();
		sap().closeApplication();
	
	
	}
}

