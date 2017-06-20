package sap_scripts.physical;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import resources.sap_scripts.physical.TC05_DevicePurchaseWExpShippingHelper;
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
public class TC05_DevicePurchaseWExpShipping extends TC05_DevicePurchaseWExpShippingHelper
{
	/**
	 * Script Name   : <b>TC01_BlankReferenceID</b>
	 * Generated     : <b>May 29, 2013 11:36:55 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/05/29
	 * @author zsadeque
	 * @throws TransformerException 
	 * @throws TransformerFactoryConfigurationError 
	 * @throws ParserConfigurationException 
	 */
	public void testMain(Object[] args) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException 
	{
		timerStart("TC05_DevicePurchaseWExpShipping");
		String timeStamp = null;
		timeStamp = "";
		 String xmlFile = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\checkout_USwWarranty.xml";
		//Submit order Through Caliber
		checkout().USPhysicalPurchase(xmlFile).submitOrder(true,"EDO",null);
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
		sap().userLoginDialog().waitForExistence();;
		sap().userLoginDialog().logIn();
		delayFor(10);
		
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
			//start VA03 - Display Sales Order & Verify PO Gets Created
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/ncic0");
			
		} else {
			sap().statusMonitorDialog().topLevelToolbar().enterCommand("/ncic0");
		}
		sap().customerInteractionCenterDialog().clickOrderSearchFindButton();
		sap().restrictValueRangeDialog().searchWebOrder(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber,"Web Order Number");
		sap().customerInteractionCenterDialog().goToDisplayChangeOrderDialog();

		//Verify Order is sourced and PO got created
		sap().bnOrderDataRefreshDialog().selectSchedulesNPOsTab();
		sap().bnOrderDataRefreshDialog().schedulesNPOsTab().verifyScheduleSummaryTable("OrderSourced");
		sap().bnOrderDataRefreshDialog().schedulesNPOsTab().verifyWarehousePOStatusTable("POCreated");
		sap().bnOrderDataRefreshDialog().topLevelToolbar().navigateBack();
		
		//process iDeoc
		sap().customerInteractionCenterDialog().topLevelToolbar().enterCommand("/nbd87");
		sap().selectIDocDialog().searchIDoc(hr +":"+min+":00",newHr+":"+min+":00", null, null, hr +":"+min+":00", newHr+":"+min+":00",null);
		sap().statusMonitorDialog().expandiDocOutboundProcessing();
		if(	sap().statusMonitorDialog().isOutboundORDRSPVisible()){
			sap().statusMonitorDialog().selectORDRSPndProcess();
			//start VA03 - Display Sales Order & Verify PO Gets Created
			sap().informationDialog().clickOkButton();
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/ncic0");
			
		} else {
			sap().statusMonitorDialog().topLevelToolbar().enterCommand("/ncic0");
		}
		
		//Start PFS Tool and Confirm PO
		pfsTools().startApplication();
		pfsTools().logIndialog().login();
		pfsTools().mainDialog().searchOrder(orderNumber,"Shop Confirm");
		pfsTools().mainDialog().verifyPOList("POList_01");
		pfsTools().mainDialog().comfirmPO();
		pfsTools().mainDialog().verifyPOList("empty");

		//Verify Order is acknowledged
		sap().customerInteractionCenterDialog().clickOrderSearchFindButton();
		sap().restrictValueRangeDialog().searchWebOrder(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber,"Web Order Number");
		sap().customerInteractionCenterDialog().goToDisplayChangeOrderDialog();
		//Verify Order is acknowledged
		sap().bnOrderDataRefreshDialog().selectSchedulesNPOsTab();
		sap().bnOrderDataRefreshDialog().schedulesNPOsTab().verifyWarehousePOStatusTable("OrderAcknowledged");
		sap().bnOrderDataRefreshDialog().topLevelToolbar().navigateBack();

		//Ship confirm in PFS Tool
		pfsTools().mainDialog().selectTab("Ship Confirm");
		pfsTools().mainDialog().searchOrder(orderNumber,"Ship Confirm");
		pfsTools().mainDialog().verifyPOList("POList_02");
		pfsTools().mainDialog().checkXMLOnly();
		pfsTools().mainDialog().shipPO();
		pfsTools().mainDialog().verifyPOList("empty");
		pfsTools().mainDialog().updateWebServiceXMLWithSerialNo();
		pfsTools().mainDialog().clickCallWebService();
		delayFor(3);
		pfsTools().mainDialog().verifyMsgBox("Completed");
		pfsTools().closeApplication();

		//process iDeoc
		sap().customerInteractionCenterDialog().topLevelToolbar().enterCommand("/nbd87");
		delayFor(10);
		sap().selectIDocDialog().searchIDoc(hr +":"+min+":00",newHr+":"+min+":00", null, null, hr +":"+min+":00", newHr+":"+min+":00",null);
		sap().statusMonitorDialog().expandiDocInboundProcessing();
		if(	sap().statusMonitorDialog().isInboundDesadvVisible()){
			sap().statusMonitorDialog().selectDESADVAndProcess();
			//start VA03 - Display Sales Order & Verify PO Gets Created
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/ncic0");
			
		} else {
			sap().statusMonitorDialog().topLevelToolbar().enterCommand("/ncic0");
		}
		//Open the order
		sap().customerInteractionCenterDialog().clickOrderSearchFindButton();
		sap().restrictValueRangeDialog().searchWebOrder(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber,"Web Order Number");
		sap().customerInteractionCenterDialog().goToDisplayChangeOrderDialog();

		//Verify Order is sourced and Inbound Delivery got created
		sap().bnOrderDataRefreshDialog().selectSchedulesNPOsTab();
		sap().bnOrderDataRefreshDialog().schedulesNPOsTab().verifyWarehousePOStatusTable("OrderAcknNInboundDel");
		sap().bnOrderDataRefreshDialog().selectItemsTab();
		sap().bnOrderDataRefreshDialog().itemsTab().verisySummaryTable("ShipConfirm");
		
		//Process Ideoc in BD87
		sap().bnOrderDataRefreshDialog().topLevelToolbar().enterCommand("/nbd87");
		sap().selectIDocDialog().searchIDoc(hr +":"+min+":00",newHr+":"+min+":00", null, null, hr +":"+min+":00", newHr+":"+min+":00",null);
		sap().statusMonitorDialog().expandiDocInboundProcessing();
		sap().statusMonitorDialog().selectZWARRANTY_UPDATEndProcess();

		//start VA03 - Display Sales Order & Verify PO Gets Created
		delayFor(20);
		sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nsm37");
		//Process the FastLane job
		sap().simpleJobSelectionDialog().executeJob("ZSPC_ORDER_CLOSE_CLEAR_FASTLANE", "*", "Sched", "Execute");
		sap().jobOverviewDialog().topLevelMenuBar().selectMenuItem(atPath("Job->Repeat scheduling"));
		sap().startTimeDialog().clickImmediate();
		sap().startTimeDialog().save();
		delayFor(30);

		//Verify Device History Order
		sap().jobOverviewDialog().topLevelToolbar().enterCommand("/ncic0");
		sap().customerInteractionCenterDialog().selectDeviceSearchTab();
		sap().customerInteractionCenterDialog().clickDeviceSearchFindButton();
		sap().restrictValueRangeDialog().deviceSearchTab().searchDeviceWebOrder(orderNumber);
		sap().deviceInfoSerialNumberDialog().verifyDeviceHistoryTable("DeviceHistoryOrder");
		sap().deviceInfoSerialNumberDialog().verifyStatusBar("NoError");
		
		//Verify order is closed
		sap().deviceInfoSerialNumberDialog().topLevelToolbar().enterCommand("/ncic0");
		sap().customerInteractionCenterDialog().clickOrderSearchFindButton();
		sap().restrictValueRangeDialog().searchWebOrder(orderNumber);
		sap().hitList1EntryDialog().verifyEntryTable("Closed");
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber,"Web Order Number");
		
		//Verify Docflow
		sap().customerInteractionCenterDialog().goToDispSAPSalesOrderDialog();
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		sap().documentFlowDialog().verifyDocumentTable("FinalDocFlow");
		sap().documentFlowDialog().topLevelToolbar().enterCommand("/ncic0");
		sap().customerInteractionCenterDialog().clickOrderSearchFindButton();
		sap().restrictValueRangeDialog().searchWebOrder(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber,"Web Order Number");
		
		//Verify email is sent
		sap().customerInteractionCenterDialog().goToDisplayChangeOrderDialog();
		sap().bnOrderDataRefreshDialog().clickEmail();
		sap().bnEmailSystemDialog().verifyEmailhistoryTable("OrderEmails");
		
		//Closing Application
		sap().bnEmailSystemDialog().close();
		sap().closeApplication();
		timerStop("TC05_DevicePurchaseWExpShipping");
		
	}
	
}

