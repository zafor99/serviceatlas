package scripts.gxs_inbound.positive;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import resources.scripts.gxs_inbound.positive.TC13_EndToEndHelper;
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
public class TC13_EndToEnd extends TC13_EndToEndHelper
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
		BNTimer timer = new BNTimer();
		String currentDate = null;
		String currentTime = null;
		
		String documentID = "88"+ generateRandom(4);
		currentDate = timer.getCurrentDate().toString();
		currentTime = timer.getCurrentTime();
		System.out.println(currentDate);
		System.out.println("currentTime  :"+currentTime);
		
		System.out.println("documentID :" + documentID);
		//Placed B2B Purchase Order Via Atlas
		atlas().puchaseOrderService().loadXmlFile("gxs_inbound\\Positive\\TC10_PurchaseOrderInternal.xml");
		atlas().puchaseOrderService().setHeader();
		atlas().puchaseOrderService().setHeader("Authorization", "Basic Z3hzOkNyNzZ3ZQ==");
		atlas().puchaseOrderService().setRandomReferenceID();
		atlas().puchaseOrderService().setDocumentID(documentID);
		atlas().puchaseOrderService().updateDatesForPurchaseOrderXML();
		atlas().puchaseOrderService().postInternal();
		atlas().puchaseOrderService().verifyResponse("TC10_PurchaseOrderInternal", "201");
		
		//Start SAP Application
		sap().startApplication();
		sap().logon720Dialog().selectSystemToLogin();
		sap().userLoginDialog().waitForExistence();
		sap().userLoginDialog().logIn();
		delayFor(10);
		
		//Open the order in ZEDI dialog
		sap().easyAccessDialog().topLevelToolbar().enterCommand("/nzedi");
		sap().ediOrderManagementMainMenuDialog().selectOrderWorkList();
		sap().ediOrderManagementMainMenuDialog().process();
		sap().ediOrderProcessingDialog().searchOrder(currentDate,documentID);
		sap().ediOrderProcessingDialog().verifyOrderDetailsGrid("OrderDetails");

		//Release the order
		sap().ediOrderProcessingDialog().openDollarDollar(documentID);
		sap().releaseCustomerExpectedPriceDialog().verifyDocumentTable("DocumentDetails");
		sap().releaseCustomerExpectedPriceDialog().checkDocument();
		sap().releaseCustomerExpectedPriceDialog().release();
		sap().releaseCustomerExpectedPriceDialog().topLevelToolbar().save();
		sap().releaseCustomerExpectedPriceDialog().verifyReleaseDocumentTable("Released");
		sap().releaseCustomerExpectedPriceDialog().topLevelToolbar().navigateBack();
		sap().ediOrderProcessingDialog().verifyOrderDetailsGrid("GreenStatus");
		sap().ediOrderProcessingDialog().selectRowByCustomerPO(documentID);
		sap().ediOrderProcessingDialog().clickFollowOnFunctions();
		sap().ediOrderProcessingDialog().checkSLByCustomerPO(documentID); 
		sap().ediOrderProcessingDialog().clickUnderReview();
		delayFor(5);
		//Verify Review Planner status
		sap().ediOrderProcessingDialog().verifyOrderDetailsGrid("ReviewPlanner");
		sap().ediOrderProcessingDialog().checkSLByCustomerPO(documentID);
		sap().ediOrderProcessingDialog().clickSourceofSupply();
		sap().assignSourceo0fSupplyDialog().selectEAN();
		sap().assignSourceo0fSupplyDialog().clickAssignAutomatically();
		sap().assignSourceofSupplyDialog().verifySourceOverviewTable("SourceOverviewTable");
//		sap().assignSourceofSupplyDialog().selectInfobyInfoID("4600000020");
		//sap().assignSourceofSupplyDialog().selectInfobyInfoID("4600000092");//RQ2 Data
		sap().assignSourceofSupplyDialog().selectInfobyInfoID("4600000121"); //old vendro 4600000110
		sap().assignSourceofSupplyDialog().verifyInfoRecLabel("VendorAssigned");
		sap().assignSourceofSupplyDialog().topLevelToolbar().save();
		sap().assignSourceofSupplyDialog().verifyStatusBar("PurchaseChanged");
		sap().assignSourceofSupplyDialog().topLevelToolbar().navigateBack();
		sap().ediOrderProcessingDialog().verifyOrderDetailsGrid("RequisitionCreated");
		sap().ediOrderProcessingDialog().checkSLByCustomerPO(documentID);
		sap().ediOrderProcessingDialog().clickCreatePO();
		
		delayFor(60);
		sap().automaticCreationOfPurchaseDialog().verifyPOCreationGrid("PODetails");
		sap().automaticCreationOfPurchaseDialog().topLevelToolbar().navigateBack();
		sap().ediOrderProcessingDialog().verifyOrderDetailsGrid("Updatedto3PL");
		
		String poNumber = sap().ediOrderProcessingDialog().getPONumber(documentID);
		sap().ediOrderProcessingDialog().openPONumber(documentID);
		sap().deliveryRequestPTNDialog().confirmationsTab().verifyConfirmationTable("BlankConf");
		
		//process the iDoc		
		sap().deliveryRequestPTNDialog().topLevelToolbar().enterCommand("/nbd87");
		String hr = currentTime.substring(0, 2);
		String min = currentTime.substring(3, 5);
		System.out.println("HR :" + hr);
		System.out.println("Min :" + min);
		// Window: Select IDocs
		String newHr = (Integer.parseInt(hr)+ 1) + "" ;
		if(newHr.length() == 1){
			newHr = "0"+newHr;
		}
		delayFor(20);
		sap().selectIDocDialog().searchIDoc(hr +":"+min+":00",newHr+":"+min+":00", null, null, hr +":"+min+":00", newHr+":"+min+":00","30");
		sap().statusMonitorDialog().expandiDocOutboundProcessing();

		if(sap().statusMonitorDialog().isOutboundOrdersVisible()){
			sap().statusMonitorDialog().selectORDERSndProcess();
			sap().statusMonitorDialog().clickOK();
			sap().iDocProcessingDialog().verifyProcessedIDOCGrid("New03Status");	
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nzedi");
		}else{
			sap().statusMonitorDialog().topLevelToolbar().enterCommand("/nzedi");
		}
		
		//End of Processing
		delayFor(20);
		//sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nzedi");
		sap().ediOrderManagementMainMenuDialog().selectOrderWorkList();
		sap().ediOrderManagementMainMenuDialog().process();
		sap().ediOrderProcessingDialog().searchOrder(currentDate,documentID);
		///According Sarath - "Matt told him that PO does not need to be approved
		sap().ediOrderProcessingDialog().verifyOrderDetailsGrid("ApprovedPO"); 
	
		//String poNumber = ":1100937693";
		//Atlas Advance Shipment Notice
		atlas().advanceShipNotice().loadXmlFile("gxs_inbound\\Positive\\AdvanceShipmentNotice.xml");
		atlas().advanceShipNotice().setHeader();
		atlas().advanceShipNotice().setRandomReferenceID();
		String shipmentHeaderdocumentID = generateRandom(13);
		System.out.println("shipmentHeaderdocumentID :"+ shipmentHeaderdocumentID);
		atlas().advanceShipNotice().setShipmentDocumentID(shipmentHeaderdocumentID);
		atlas().advanceShipNotice().setPONumber(poNumber);
		atlas().advanceShipNotice().updateDatesForAdvanceShipNoticeXML();
		atlas().advanceShipNotice().postInternal();
		atlas().advanceShipNotice().verifyResponse("AdvanceShipNotice", "201");

		//process the iDoc		
		sap().ediOrderProcessingDialog().topLevelToolbar().enterCommand("/nbd87");
		System.out.println("HR :" + hr);
		System.out.println("Min :" + min);
		// Window: Select IDocs
		delayFor(20);
		sap().selectIDocDialog().searchIDoc(hr +":"+min+":00",newHr+":"+min+":00", null, null, hr +":"+min+":00", newHr+":"+min+":00","64");
		sap().statusMonitorDialog().expandiDocInboundProcessing();
		if(sap().statusMonitorDialog().isInboundDesadvVisible()){
			sap().statusMonitorDialog().selectDESADVAndProcess();
			sap().iDocProcessingDialog().verifyProcessedIDOCGrid("NEW53Status");
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nzedi");
		}else{
			sap().statusMonitorDialog().topLevelToolbar().enterCommand("/nzedi");
		}
		//End of Processing
		delayFor(30);
		//Verify AB And LA created
		sap().ediOrderManagementMainMenuDialog().selectOrderWorkList();
		sap().ediOrderManagementMainMenuDialog().process();
		sap().ediOrderProcessingDialog().searchOrder(currentDate,documentID);
		sap().ediOrderProcessingDialog().verifyOrderDetailsGrid("POACK");
		sap().ediOrderProcessingDialog().openPONumber(documentID);
		sap().deliveryRequestPTNDialog().confirmationsTab().verifyConfirmationTable("AB_LA_Created");
		sap().deliveryRequestPTNDialog().topLevelToolbar().navigateBack();
		
		sap().ediOrderProcessingDialog().openCustomerPO(documentID);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		
		sap().documentFlowDialog().verifyDocumentTable("OutbDelOpenStatus");
		sap().documentFlowDialog().selectOutbDelDeviceAndDisplayDoc(); //selecting the 1st cel
		
		sap().outbDelDevice3PLDialog().clickDisplayChange();
		sap().outbDelDevice3PLDialog().select1stItem();
		sap().outbDelDevice3PLDialog().clickProcessGoodIssue();
		sap().documentFlowDialog().verifyStatusBar("OutbDelSaved");
		sap().documentFlowDialog().selectDeviceOrderWhSaleAndDisplayDoc();
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		
		sap().documentFlowDialog().verifyDocumentTable("ConfOfService");
		sap().documentFlowDialog().selectDeviceOrderWhSaleAndDisplayDoc();
		sap().displayDeviceOrderOverViewDialog().topLevelMenuBar().selectMenuItem(atPath("Sales document->Change"));
		sap().informationDialog().clickOkButton();
		sap().displayDeviceOrderOverViewDialog().selectBillingBlock(" ");
		sap().displayDeviceOrderOverViewDialog().topLevelToolbar().save();
		sap().documentFlowDialog().verifyStatusBar("deviceOrderWhSaleSaved");
		
		//new code 1/22/2014
/*		sap().documentFlowDialog().selectPurchaseOrderAndDisplayDoc();
		sap().documentFlowDialog().topLevelToolbar().navigateBack();*/
		
		delayFor(500);
			
		//Atlas Invoice Service 
		atlas().invoiceService().loadXmlFile("gxs_inbound\\Positive\\Invoice.xml");
		atlas().invoiceService().setHeader();
		atlas().invoiceService().setRandomReferenceID();
		atlas().invoiceService().setRandomInvoiceHeaderDocumentID();
		atlas().invoiceService().setRandomInvoiceHeaderAlternateDocumentID();
		atlas().invoiceService().setInvoiceHeaderDocumentReferenceDocumentID(shipmentHeaderdocumentID);
		atlas().invoiceService().setInvoiceHeaderPONumber(poNumber);
		atlas().invoiceService().setInvoiceLinePONumber(poNumber);
		atlas().invoiceService().updateDatesForInvoiceXML();
		atlas().invoiceService().postInternal();
		atlas().invoiceService().verifyResponse("Innvoice", "201");

		delayFor(30);
		//Processing Invoice		
		sap().documentFlowDialog().topLevelToolbar().enterCommand("/nbd87");
		sap().selectIDocDialog().searchIDoc(hr +":"+min+":00",newHr+":"+min+":00", null, null, hr +":"+min+":00", newHr+":"+min+":00","64");
		sap().statusMonitorDialog().expandiDocInboundProcessing();
		if(sap().statusMonitorDialog().isInboundInvoiceVisible()){
			sap().statusMonitorDialog().selectINVOICndProcess();
			sap().iDocProcessingDialog().verifyProcessedIDOCGrid("NEW53Status");
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nzedi");
		}else{
			sap().statusMonitorDialog().topLevelToolbar().enterCommand("/nzedi");
		}
		delayFor(30);
		sap().ediOrderManagementMainMenuDialog().selectOrderWorkList();
		sap().ediOrderManagementMainMenuDialog().process();
		sap().ediOrderProcessingDialog().searchOrder(currentDate,documentID);
		sap().ediOrderProcessingDialog().openCustomerPO(documentID);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		sap().documentFlowDialog().verifyDocumentTable("DeviceDelNAccDocCreated");
		sap().documentFlowDialog().selectPurchaseOrderAndDisplayDoc();
		sap().deliveryRequestPTNDialog().purchaseOrderHistoryTab().select();
		sap().deliveryRequestPTNDialog().purchaseOrderHistoryTab().verifyPurchaseHistoryTable("IRGenerated");
		sap().deliveryRequestPTNDialog().topLevelToolbar().navigateBack();
		sap().documentFlowDialog().verifyDocumentTable("AccountDocCreated");
		
		sap().documentFlowDialog().close();
		sap().closeApplication();
	
		
		
		
	}
	
}

