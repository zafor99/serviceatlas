package sap_scripts.digital;
import java.io.IOException;

import resources.sap_scripts.digital.TC07_eNewspaperSubsPurchaseW_CCHelper;
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
public class TC07_eNewspaperSubsPurchaseW_CC extends TC07_eNewspaperSubsPurchaseW_CCHelper
{
	/**
	 * Script Name   : <b>TC01_NookAppPurchase</b>
	 * Generated     : <b>Oct 4, 2013 11:36:55 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/10/04
	 * @author zsadeque
	 * @throws IOException 
	 */
	public void testMain(Object[] args) throws IOException 
	{
		
		
		String orderNumber,timeStamp,hr,min = null;
		
		//Submit IP orders 
		instantPurchase().submitIPOrder("VI", "4313081835209051", "2940000228302");
	
	/*	orderNumber=instantPurchase().getOrderNumber();
		timeStamp = instantPurchase().getOrderTimeStamp();
		hr = timeStamp.substring(11, 13);
		min = timeStamp.substring(14, 16);
		
		//Starting  SAP Application
		sap().startApplication();
		sap().logon720Dialog().selectSystemToLogin();
		//sap().userLoginDialog().waitForExistence();
		sap().userLoginDialog().logIn();
		delayFor(5);
		//Process iDoc in BD87 
		sap().easyAccessDialog().topLevelToolbar().enterCommand("/nbd87");
		String newHr = (Integer.parseInt(hr)+ 1) + "" ;
		if(newHr.length() == 1){
			newHr = "0"+newHr;
		}
		delayFor(30);
		sap().selectIDocDialog().searchIDoc(hr +":"+min+":00",newHr+":"+min+":00", null, null, hr +":"+min+":00", newHr+":"+min+":00",null);
		sap().statusMonitorDialog().expandiDocInboundProcessing();
		if(	sap().statusMonitorDialog().isInboundOrdersVisible()){
			sap().statusMonitorDialog().selectInboundORDERSndProcess();
			//start cic0 - Display Sales Order & Verify PO Gets Created
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nva43");
			
		} else {
			sap().statusMonitorDialog().topLevelToolbar().enterCommand("/nva43");
		}

		
		sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
		
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
		sap().customerInteractionCenterDialog().clickOrderSearchFindButton();
		sap().restrictValueRangeDialog().searchWebOrder(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber,"Web Order Number");
		sap().customerInteractionCenterDialog().goToDispSAPSalesOrderDialog();
		
		//Verify ZNAP Status	
		sap().displayBNOrderPhaseOverviewDialog().salesTab().verifyAllItemsTable("ZNAPStatus");
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
		sap().documentFlowDialog().verifyDocumentTableFfromHeaderData("OrderProcessed");
		
		//Process the job
		sap().documentFlowDialog().topLevelToolbar().enterCommand("/nsm37");
		sap().simpleJobSelectionDialog().executeJob("ZROF_REQ2PO_JOB_MANAGER_DIG", "*", "Sched", "Execute");
		sap().jobOverviewDialog().topLevelMenuBar().selectMenuItem(atPath("Job->Repeat scheduling"));
		sap().startTimeDialog().clickImmediate();
		sap().startTimeDialog().save();
		delayFor(10);

		//Get purchase order
		sap().jobOverviewDialog().topLevelToolbar().enterCommand("/ncic0");
		sap().customerInteractionCenterDialog().clickOrderSearchFindButton();
		sap().restrictValueRangeDialog().searchWebOrder(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber,"Web Order Number");
		sap().customerInteractionCenterDialog().goToDispSAPSalesOrderDialog();
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
		sap().createIncomingInvoiceDialog().topLevelToolbar().enterCommand("/ncic0");
		sap().customerInteractionCenterDialog().clickOrderSearchFindButton();
		sap().restrictValueRangeDialog().searchWebOrder(orderNumber);
		sap().hitList1EntryDialog().verifyEntryTable("OrderClosed");
		sap().hitList1EntryDialog().clickOkButton();
		
		//Verify Docflow
		sap().customerInteracti
		onCenterDialog().goToDispSAPSalesOrderDialog();
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		sap().documentFlowDialog().verifyDocumentTable("FinalDocFlow");
		sap().documentFlowDialog().selectPurchaseOrderAndDisplayDoc();
		sap().appsDownloadPODialog().purchaseOrderHistoryTab().select();
		sap().appsDownloadPODialog().purchaseOrderHistoryTab().verifyPurchaseHistoryTable("IRGotCreated");
		
		sap().appsDownloadPODialog().close();
		sap().closeApplication();
	
*/		
	}
}

