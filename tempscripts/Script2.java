package tempscripts;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;

import resources.tempscripts.Script2Helper;
import utils.GiftCardUtil;

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
import com.bn.service.atlas.logistics.api.deliveryrequest.Message;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class Script2 extends Script2Helper
{
	/**
	 * Script Name   : <b>Script2</b>
	 * Generated     : <b>Aug 21, 2013 4:50:35 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/08/21
	 * @author zsadeque
	 * @throws ParserConfigurationException 
	 * @throws TransformerException 
	 * @throws TransformerFactoryConfigurationError 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void testMain(Object[] args) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException, IOException, ClassNotFoundException 
	{
		sap().updateBillingAddress().updateBillingAddress("Darren Tang", null, "2124144000", "76 9th ave", "9th fl", null, "New York", "10011", "US", "NY", null, "update");
		
/*
		crm().startApplication();
		crm().signInDialog().signIn();
		crm().selectABusinessRolePage().clickZBNT1CSRLink();
		crm().identifyAccountPage().accountSearchPanel().search("147683841", null, null, null, null, "search account");
		
		crm().identifyAccountPage().topPanel().clickEnd();
		*/
		//activityMonitor().verifyActivityMonitorForError("2940000228043", "NoError");
/*		sap().bnOrderDataRefreshDialog().itemsTab().select1stItem();
		sap().bnOrderDataRefreshDialog().itemsTab().cancel();
*/		//	sap().iDocReportingToolDialog().searchSalesOrder(null, "147642170", "ZCQ BN Subscription                                      ");
		
/*		// Dialog: Cancel subscription
		comboBox_g_CANCEL_REASON().setValue("88-Cancel eSubscription");
		comboBox_g_CANCEL_REASON().setValue("88-Cancel eSubscription");
		comboBox_g_CANCEL_REASON().setValue("88-Cancel eSubscription");
		comboBox_g_CANCEL_REASON().setValue("88-Cancel eSubscription");
		*/
/*		// Window: BN.com order 147633652 - data refresh at 11:40:47
		window_bnComOrder147633652Data().maximize();
		window_bnComOrder147633652Data().maximize();
		// PageTab: Items
		table_saplzcsdisptctlitem().selectRow(atRow(atIndex(0)));
		table_saplzcsdisptctlitem().selectRow(atRow(atIndex(0)));
		table_saplzcsdisptctlitem().selectRow(atRow(atIndex(0)));
	
	
		*/
		
		
		//sap().deliveryRequestPTNDialog().purchaseOrderHistoryTab().waitForGRGeneration(30);
		/*String poNumber = "1100934253";
		String shipmentHeaderdocumentID = "2136261533374";
		atlas().advanceShipNotice().loadXmlFile("gxs_inbound\\Positive\\AdvanceShipmentNotice.xml");
		atlas().advanceShipNotice().setHeader();
		atlas().advanceShipNotice().setRandomReferenceID();
		String shipmentHeaderdocumentID = generateRandom(13);
		atlas().advanceShipNotice().setShipmentDocumentID(shipmentHeaderdocumentID);
		atlas().advanceShipNotice().setPONumber(poNumber);
		atlas().advanceShipNotice().updateDatesForAdvanceShipNoticeXML();
		atlas().advanceShipNotice().postInternal();
		atlas().advanceShipNotice().verifyResponse("AdvanceShipNotice", "201");
		
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
		atlas().invoiceService().verifyResponse("Innvoice", "201");*/
		//System.out.println(shipmentHeaderdocumentID);
		//sap().documentFlowDialog().verifyDocumentTable("OrderProcessed");
		
/*		crm().startApplication();
		crm().signInDialog().signIn("zsadeque", "Aminai@6", "Login");
		crm().selectABusinessRolePage().clickZBNT1CSRLink();
		crm().logOff();
		crm().closeApplication();
		delayFor(6);
		crm().startApplication();
		crm().signInDialog().signIn("zsadeque", "Aminai@6", "Login");
		crm().selectABusinessRolePage().clickZBNT1CSRLink();
		crm().logOff();
		crm().closeApplication();*/
		
		//String deliveryNumber = sap().documentFlowDialog().getDeliveryNumber();
/*		sap().statusMonitorDialog().expandiDocOutboundProcessing();
		if(	sap().statusMonitorDialog().isOutboundOrdersVisible()){
			sap().statusMonitorDialog().selectORDERSndProcess();
			sap().informationDialog().clickOkButton();
			//start VA03 - Display Sales Order & Verify PO Gets Created
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nva03");
		}*/
		//sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		//String customerId = custUserAcc.getNewCustomerId
		/*crm().startApplication();
		  crm().signInDialog().signIn("zsadeque", "Aminai@6", "Login");
		  crm().selectABusinessRolePage().clickZBNT1CSRLink();
		  crm().identifyAccountPage().accountSearchPanel().search("147097045", null, null, null, null, "search account");
		  crm().identifyAccountPage().accountConfirmPanel().confirm();
		  crm().identifyAccountPage().ordersTabPanel().selectOrderTableRowbyOrderNum("147097045");
		  crm().identifyAccountPage().ordersTabPanel().verifyOrdersTable("orderTable");
		  crm().identifyAccountPage().ordersTabPanel().openSAPOrderNo("147097045");*/
		
		//sap().documentFlowDialog().selectOutbDelDeviceAndDisplayDoc(); 
		//sap().documentFlowDialog().selectPurchaseOrderAndDisplayDoc();
		//sap().statusMonitorDialog().isInboundSHPCONAVisible();
		
		// Window: Status Monitor for ALE Messages
		//tree_sapTableTreeControl1_2().performTest(SAPTableTreeControl1_tree_3VP());
		
		// Window: Status Monitor for ALE Messages
		//tree_sapTableTreeControl1_2().performTest(SAPTableTreeControl1_treeVP());
		
		// Window: Status Monitor for ALE Messages
		//tree_sapTableTreeControl1_2().performTest(SAPTableTreeControl1_tree_2VP());
		
/* *///		crm().identifyAccountPage().accountPanel().accountDetailsPanel().confirm();
		//dbService().pfsDB().verifyStatusInTRXHEADER("amsas", "8102921728","C");
		// Dialog: Initiate Return for Refund
		//sap().appsDownloadPODialog().purchaseOrderHistoryTab().verifyPurchaseHistoryTable("IRGotCreated");
		//instantPurchase().submitIPVideoPurchase("2949000000284");
		//instantPurchase().purchaseDigitalItemWith2GC("9783527644513", "60", "50");
	//	instantPurchase().submitIPOrder("VI", "4313081835209051", "2940000228302");
		//instantPurchase().purchaseDigitalItemWith2GC("9780671036911", "5", "5");
		//instantPurchase().submitIPVideoPurchase("2949000000062");
		
		//	sap().statusMonitorDialog().expandiDocInboundProcessing();
//		sap().displayBNOrdPhaseHeaderDataDialog().paymentCardsTab().verifyAllItemsTable("Approved");
		/*if(	sap().statusMonitorDialog().isInboundOrdersVisible()){
			sap().statusMonitorDialog().selectInboundORDERSndProcess();
			//start VA03 - Display Sales Order & Verify PO Gets Created
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/ncic0");
			
		} else {
			sap().statusMonitorDialog().topLevelToolbar().enterCommand("/ncic0");
		}*/
		
		// Window: PFSTool.exe: PFS Message Test Tool
		// TabbedPage: 1: Ship Confirm
	//	pfsTools().mainDialog().updateWebServiceXML();
		/*pfsTools().mainDialog().updateWebServiceXMLWothSerialNo();
		pfsTools().mainDialog().clickCallWebService();*/
/*		sap().bnOrderDataRefreshDialog().selectSchedulesNPOsTab();
		sap().bnOrderDataRefreshDialog().verifyScheduleSummaryTable("OrderSourced");*/
		//sap().bnOrderDataRefreshDialog().verifyWarehousePOStatusTable("POCreated");
		
//		sap().bnDropshipPODialog().topLevelToolbar().enterCommand("/ncic0");
/*		sap().customerInteractionCenterDialog().clickOrderSearchFindButton();
		sap().restrictValueRangeDialog().searchWebOrder("146891258");
		sap().hitList1EntryDialog().selectPurchaseOrder("146891258");
		sap().customerInteractionCenterDialog().goToiDocReportingToolDialog();*/
		
		//sap().deliveryRequestPTNDialog().selectPurchaseHistoryTab();
		
		//sap().bnDropshipPODialog().verifyConfirmationTable("EmptyConfirmTable");
//		sap().documentFlowDialog().selectPurchaseOrderAndDisplayDoc();
/*		TestObject tos[] = find(atList(atDescendant("Id","/usr"),atChild("Id","/tabsTAXI_TABSTRIP_ITEM"),atChild("Id",xRegex("/tabpT.*03"))),true);
		
		
		if(tos.length>0){
			SAPGuiTabTestObject to = new SAPGuiTabTestObject(tos[0]);
		}*/
	//	sap().displayOrdPhaseItemDataDialog().selectShippingTab();
		
		// Window: Display BN.com Ord Phase II 1147715168: Item Data
		// PageTab: Shipping
		//pageTab_shipping().select();
		
		/*boolean result = false;
		System.out.println(sap().statusMonitorDialog().isInboundOrdersVisible());
		System.out.println("*********************");
		int count = tree_sapTableTreeControl1().getNodeChildrenCountByPath(atPath("RQ2 Client 010->IDoc in inbound processing->IDoc ready to be transferred to application"));
		System.out.println(count);
		String keyNode = tree_sapTableTreeControl1().getNodeKeyByPath(atPath("RQ2 Client 010->IDoc in inbound processing->IDoc ready to be transferred to application"));
		String nodes[] =  tree_sapTableTreeControl1().getSubNodesCol(keyNode);
		System.out.println(nodes.length);
		if(nodes!=null){
			for(int i=0; i<nodes.length;i++){
				String nodesText = tree_sapTableTreeControl1().getNodeTextByKey(nodes[i]);
				if(nodesText.contentEquals("ORDERS")){
					result = true;
					break;
				}
			}
		}
		System.out.println(result);*/
		//System.out.println(tree_sapTableTreeControl1().getNodeKeyByPath(atPath("RQ2 Client 010->IDoc in inbound processing->IDoc ready to be transferred to application->ORDERS")));
		//sap().statusMonitorDialog().isInboundOrdersVisible();
		
		// Window: Status Monitor for ALE Messages
		//String data[] = 
		//TestObject[] children = tree_sapTableTreeControl1().get
		//	tree_sapTableTreeControl1().get
/*		for(int i =0;i<data.length;i++){
			System.out.println("length"+data.length);
			System.out.println(data[i].toString());
			
		}
		
*/		//sap().statusMonitorDialog().isInboundOrdersVisible()
	/*	checkout().USPhysicalPurchase().submitOrder();
		String orderNum = checkout().USPhysicalPurchase().getOrderNumber();*/
	//	sap().documentFlowDialog().selectOutbDelDeviceAndDisplayDoc();
/*		pfsTools().startApplication();
		pfsTools().logIndialog().login();
		pfsTools().mainDialog().searchOrder("146861671");
		
		// Window: PFSTool.exe: PFS Message Test Tool
		lstPOlist().click(RIGHT, atText("1100932214"));
		
		// 
		contextMenuStrip1toolBar().click(atPath("Confirm PO"));*/
	//pfsTools().mainDialog().clickGetOpenPO();

		
		// Window: PFSTool.exe: PFS Message Test Tool
		// TabbedPage: 0: Shop Confirm
	//	chkPoTypeslist().click(atPath("Inflight->Location(CHECKBOX)"));
	//	chkPoTypeslist().click(atPath("Inflight->Location(CHECKBOX)"));
		
		/*sap().outbDelDevice3PLDialog().select1stItem();
		sap().outbDelDevice3PLDialog().clickProcessGoodIssue();
		*/// Window:  Outb Del Device 3PL 8102926903 Display: Overview
		// PageTab: ItemOverview
		//System.out.println(sap().documentFlowDialog().getDeliveryNumber());
/*		
		 * URL's
		 
		
		String BASE_JCHECKOUT_URL = "http://injsvcjapp04:8080";
		String BASE_SUBMIT_ORDER_PATH = "/CheckoutService/submitOrder?";
		String BASE_SET_EMAIL_PATH = "/CheckoutService/setEmail?";
		String BASE_AGGREGATION_PATH = "/CheckoutService/aggregation";
		String BASE_ADD_ITEM_PATH = "/CheckoutService/cartItem/add?";
		
		
		
		
		 * All Variables
		 
		String cartClientId = "41";
		String idType = "customerId";
		String id; 
		String country =  "US";
		String retailer = "";
		String ean  =  "9780894807626";  //"9781400699032"  9781400699032
		String shipmentID = "1";
		String setDefaults = "true";
		String 	url,urlAggregation,urlEmail,urlSubmitOrder, 
			   	urlAddItem,urlSetAccountDefault,setBillingURL, 
			   	setShippingAddressURL, urlSetEmail, 
			   	setShippinDefaultURL,setShiptServiceURL,
			   	urlGetpaymentMethod;
		String xml,xmlFile,xmlSetBilling, xmlSetShiServiceAddress, xmlSetEmail, xmlSetShipping;
		urlSubmitOrder = BASE_JCHECKOUT_URL + BASE_SUBMIT_ORDER_PATH;
		urlEmail = BASE_JCHECKOUT_URL + BASE_SET_EMAIL_PATH; 
		urlAggregation = BASE_JCHECKOUT_URL + BASE_AGGREGATION_PATH;
		url = BASE_JCHECKOUT_URL + BASE_ADD_ITEM_PATH;	
		
		
		 * Add payment
		 
		  
		id = createIntlAccountWithPayment(); 
		*/
	}
}

