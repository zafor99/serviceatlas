package sap_scripts.physical;
import resources.sap_scripts.physical.TC07_CDPurchaseWCCUsingIntAirmailHelper;
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
 * @author mquraishi
 */
public class TC07_CDPurchaseWCCUsingIntAirmail extends TC07_CDPurchaseWCCUsingIntAirmailHelper
{
	/**
	 * Script Name   : <b>TC07_CDPurchaseWCCUsingIntAirmail</b>
	 * Generated     : <b>Oct 7, 2013 2:38:59 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/10/07
	 * @author mquraishi
	 * Description: Purchase CD with Credit Card from BN.com using Int'l Airmail
	 */ 
	public void testMain(Object[] args) 
	{
		String timeStamp = null;
		timeStamp = "";
		checkout().USPhysicalPurchase("C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\checkout_US_IRM.xml").submitOrder(true,"IRM","696998529328");
		String orderNumber = checkout().USPhysicalPurchase().getOrderNumber();
		timeStamp = checkout().USPhysicalPurchase().getOrderTimeStamp().toString();
		System.out.println("timeStamp :" + timeStamp);
		String hr = timeStamp.substring(11, 13);
		String min = timeStamp.substring(14, 16);
		
		//Starting  SAP Application
		sap().startApplication();
		sap().logon720Dialog().selectSystemToLogin();
		sap().userLoginDialog().waitForExistence();
		sap().userLoginDialog().logIn();
		delayFor(10);
		
		//Process iDoc in BD87 
		sap().easyAccessDialog().topLevelToolbar().enterCommand("/nbd87");
		String newHr = (Integer.parseInt(hr)+ 1) + "" ;
		if(newHr.length() == 1){
			newHr = "0"+newHr;
		}
		delayFor(10);
		sap().selectIDocDialog().searchIDoc(hr +":"+min+":00",newHr+":"+min+":00", null, null, hr +":"+min+":00", newHr+":"+min+":00","64");
		sap().statusMonitorDialog().expandiDocInboundProcessing();
		if(	sap().statusMonitorDialog().isInboundOrdersVisible()){
			sap().statusMonitorDialog().selectInboundORDERSndProcess();
			
			//start VA03 - Display Sales Order & Verify PO Gets Created
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nva03");
			
		} else {
			sap().statusMonitorDialog().topLevelToolbar().enterCommand("/nva03");
		}
			
		sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		sap().documentFlowDialog().verifyDocumentTable("POCreated");
		sap().documentFlowDialog().selectPurchaseOrderAndDisplayDoc();
		sap().bnDropshipPODialog().confirmationsTab().verifyConfirmationTable("EmptyConfirmTable");
		
		//Start PFS Tool and Confirm PO
		pfsTools().startApplication();
		pfsTools().logIndialog().login();
		pfsTools().mainDialog().searchOrder(orderNumber,"Shop Confirm");
		pfsTools().mainDialog().verifyPOList("POList_01");
		pfsTools().mainDialog().comfirmPO();
		pfsTools().mainDialog().verifyPOList("empty");
		
		//Confirm AB Status
		sap().bnDropshipPODialog().toolbar().clickOtherPurchaseOrder();
		sap().bnDropshipPODialog().toolbar().clickOtherDocument();
		sap().bnDropshipPODialog().confirmationsTab().verifyConfirmationTable("CC_Status_AB");
		
		//Ship confirm in PFS Tool
		pfsTools().mainDialog().selectTab("Ship Confirm");
		pfsTools().mainDialog().searchOrder(orderNumber,"Ship Confirm");
		pfsTools().mainDialog().verifyPOList("POList_02");
		pfsTools().mainDialog().shipPO();
		pfsTools().mainDialog().verifyPOList("empty");
		pfsTools().closeApplication();

		//Process Ideoc in BD87
		sap().bnDropshipPODialog().topLevelToolbar().enterCommand("/nbd87");
		delayFor(10);
		sap().selectIDocDialog().searchIDoc(hr +":"+min+":00",newHr+":"+min+":00", null, null, hr +":"+min+":00", newHr+":"+min+":00","64");
		sap().statusMonitorDialog().expandiDocInboundProcessing();
		sap().statusMonitorDialog().selectDESADVAndProcess();
		sap().iDocProcessingDialog().verifyProcessedIDOCGrid("Status_53");
		
		//Goto VA03 - Display Sales Order 
		sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nva03");
		sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		sap().documentFlowDialog().verifyDocumentTable("OutbdDeliveryCreated");
		sap().documentFlowDialog().selectPurchaseOrderAndDisplayDoc();
		sap().bnDropshipPODialog().confirmationsTab().verifyConfirmationTable("CC_Status_LA_AB");
		sap().bnDropshipPODialog().topLevelToolbar().navigateBack();
		sap().documentFlowDialog().topLevelToolbar().navigateBack();
		sap().displayDeviceOrderOverViewDialog().topLevelMenuBar().selectMenuItem(atPath("Sales document->Change"));

		//Getting Delivery Number
		sap().changeSalesOrderInitailScreenDialog().search(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
		sap().informationDialog().clickOkButton();
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		String deliveryNumber = sap().documentFlowDialog().getDeliveryNumber();
		
		//Update Deliver Number
		sap().documentFlowDialog().topLevelToolbar().enterCommand("/nvf01");
		sap().createBillingDocumentDialog().enterDocumentNumber(deliveryNumber);
		sap().createBillingDocumentDialog().topLevelToolbar().save();
		sap().createBillingDocumentDialog().verifyStatusBar("DocumentSaved");
		sap().createBillingDocumentDialog().topLevelToolbar().enterCommand("/nva03");
		
		//Verify Billing And Accounting Doc created
		sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		sap().documentFlowDialog().verifyDocumentTable("BilledAndAccountingDocCreated");

		//Process the job
		sap().documentFlowDialog().topLevelToolbar().enterCommand("/nsm37");
		sap().simpleJobSelectionDialog().executeJob("ZSPC_ORDER_CLOSE_CLEAR_FASTLANE", "*", "Sched", "Execute");
		sap().jobOverviewDialog().topLevelMenuBar().selectMenuItem(atPath("Job->Repeat scheduling"));
		sap().startTimeDialog().clickImmediate();
		sap().startTimeDialog().save();
		delayFor(10);
		
		//verify closed status
		sap().jobOverviewDialog().topLevelToolbar().enterCommand("/ncic0");
		sap().customerInteractionCenterDialog().clickOrderSearchFindButton();
		sap().restrictValueRangeDialog().searchWebOrder(orderNumber);
		sap().hitList1EntryDialog().verifyEntryTable("OrderClosed");
		sap().hitList1EntryDialog().clickOkButton();
		
		//Verify Standard Shipping(/va03)
		sap().customerInteractionCenterDialog().goToDispSAPSalesOrderDialog();
		sap().displayDeviceOrderOverViewDialog().openFirstArticle();
		sap().displayOrdPhaseItemDataDialog().selectShippingTab();
		sap().displayOrdPhaseItemDataDialog().verifySpecProcessing("IrmShipping");
		sap().displayOrdPhaseItemDataDialog().close();
		sap().closeApplication();
		
	
	}
}

