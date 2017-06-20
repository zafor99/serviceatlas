package tempscripts;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import resources.tempscripts.TC01_PhysicalPurchaseHelper;
import utils.BNTimer;
import utils.XMLUtility;

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
import com.bn.qa.webservice.restclient.specification.IResponse;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;

/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class TC01_PhysicalPurchase extends TC01_PhysicalPurchaseHelper
{
	/**
	 * Script Name   : <b>TC01_BlankReferenceID</b>
	 * Generated     : <b>May 29, 2013 11:36:55 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/05/29
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		String timeStamp = null;
		timeStamp = "";
		//Submit order Through Caliber
		checkout().USPhysicalPurchase().submitOrder();
		String orderNumber = checkout().USPhysicalPurchase().getOrderNumber();
		timeStamp = checkout().USPhysicalPurchase().getOrderTimeStamp().toString();
		System.out.println("timeStamp :" + timeStamp);
		String hr = timeStamp.substring(11, 13);
		String min = timeStamp.substring(14, 16);
		System.out.println("HR :" + hr);
		System.out.println("Min :" + min);
		
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
		sap().bnDropshipPODialog().verifyConfirmationTable("EmptyConfirmTable");
		
		//Verify PFS Database
		//verify the status is open and PONUM is there
		
		//Start PFS Tool and Confirm PO
		pfsTools().startApplication();
		pfsTools().logIndialog().login();
		pfsTools().mainDialog().searchOrder(orderNumber,"Shop Confirm");
		pfsTools().mainDialog().verifyPOList("POList_01");
		pfsTools().mainDialog().comfirmPO();
		pfsTools().mainDialog().verifyPOList("empty");
		
		//Confirm AB Status
		sap().bnDropshipPODialog().clickOtherPurchaseOrder();
		sap().bnDropshipPODialog().clickOtherDocument();
		sap().bnDropshipPODialog().verifyConfirmationTable("CC_Status_AB");
		
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
		sap().bnDropshipPODialog().verifyConfirmationTable("CC_Status_LA_AB");
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
		
		//Verify Standard Shipping
		sap().customerInteractionCenterDialog().goToDispSAPSalesOrderDialog();
		sap().displayDeviceOrderOverViewDialog().openFirstArticle();
		sap().displayOrdPhaseItemDataDialog().selectShippingTab();
		sap().displayOrdPhaseItemDataDialog().verifySpecProcessing("StdShipping");
		
		
	}
	
}

