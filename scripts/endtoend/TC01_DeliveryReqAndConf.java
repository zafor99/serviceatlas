package scripts.endtoend;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import resources.scripts.endtoend.TC01_DeliveryReqAndConfHelper;
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

import framework.CaliberService;
/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class TC01_DeliveryReqAndConf extends TC01_DeliveryReqAndConfHelper
{
	/**
	 * Script Name   : <b>TC01_DeliveryReqAndConf</b>
	 * Generated     : <b>May 20, 2013 11:43:08 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/05/20
	 * @author zsadeque
	 * @throws InterruptedException 
	 */
	public void testMain(Object[] args) throws InterruptedException 
	{
		
		/*
		 * Obsolete script 07/24/2014 . We no longer use obj
		 */

		
	/*	String orderNum = null;		
		String timeStamp = null;
		
	//	orderNum = "146423895";
		timeStamp = "";
		
		 //* Placing Order Via Caliber
		 
		checkout().GBPhysicalPurchase().submitOrder();
		orderNum = checkout().GBPhysicalPurchase().getOrderNumber();
		timeStamp = checkout().GBPhysicalPurchase().getOrderTimeStamp().toString();
		System.out.println("timeStamp :" + timeStamp);
		int hr = Integer.parseInt(timeStamp.substring(11, 13));
		int min = Integer.parseInt(timeStamp.substring(14, 16));
		System.out.println("HR :" + hr);
		System.out.println("Min :" + min);
		
	
		
		//  Starting SAP and processing the Order
		//  And generating Delivery Number
		 

		sap().startApplication();
		sap().logon720Dialog().selectSystemToLogin("New RQ2");
		sap().userLoginDialog().waitForExistence();
		
		// Window: SAP
		text_username().setText("zsadeque");
		text_password().setText("Aminai@3");
		text_password().setFocus();
		window_sap().sendVKey(SAPTopLevelTestObject.VKEY_ENTER);
		
		
	//	sap().userLoginDialog().logIn("zsadeque", "Aminai@3");
		sap().easyAccessDialog().openDisplaySalesOrderInitialScreenDialog();
		delayFor(180);
		sap().displaySalesOrderInitailScreenDialog().search(orderNum);
	
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNum);
		
		// Window: Document Flow
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		//TODO:Remove
		//button_DocumentFlow().click();
		sap().documentFlowDialog().verifyDocumentTable("OpenOrder");

		
		
		// Create Outbound Delivery with Order Reference
		
		sap().documentFlowDialog().topLevelToolbarModel().navigateBack();
		// TODO: Remove
//		window_documentFlow().maximize();
	//	button_back().press();
		sap().displayDeviceOrderOverViewDialog().deliver();
		//TDO: Remove
		//menuBar_mbar().selectMenuItem(atPath("Sales document->Deliver"));
		sap().displayDeviceOrderOverViewDialog().Save();
		//TODO: Remove
		//button_save().press();
		sap().displayDeviceOrderOverViewDialog().verifyDeviceDeliveryStatus("StatusBar_01VP");
		//TODO:Remove
		//		statusBar_01().performTest(StatusBar_01VP());
		
		sap().documentFlowDialog().topLevelToolbarModel().navigateBack();
		//TODO: Remove
		//button_back().press();
		
		sap().easyAccessDialog().openDisplaySalesOrderInitialScreenDialog();
		sap().displaySalesOrderInitailScreenDialog().search(orderNum);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNum);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		//TODO : Remove
//		button_DocumentFlow().click();
		
		// Window: Document Flow
		sap().documentFlowDialog().verifyDocumentTable("DeliveryCreated");
		
		
		String deliveryNumber = label_Doc_02().getProperty("Text").toString();
		deliveryNumber = deliveryNumber.split(" ")[5];

		
		
		// * Placing request via Atlas
		 
		String fileName = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\atlasDeliveryConfirmation.xml";
		XMLUtility xmlUtil = new XMLUtility(fileName);
		
		System.out.println("Current Time:" + new Date().getTime());
		xmlUtil.setNodeAttributeByXPath("DeliveryConfirmationBatch", "BatchNumber", "QA-TEAT2_"+ new Date().getTime());

		xmlUtil.setNodeValueByXPath("//DeliveryNumber", deliveryNumber);
		xmlUtil.setNodeValueByXPath("//LineItem/ItemData/SerialNo", new Date().getTime()+"");
		System.out.println("Current Time 02:" + new Date().getTime());
		
		String url = "https://qa-api.barnesandnoble.com/Logistics/services/rs/WarehouseShippingAdvice/?client_id=cstCIwT9TAyMHw9uuvy3Wg";
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("Authorization", "Basic Y2V2YTpjV0ZqWlhaaA==");
			
		IResponse response = null;
		response = caliber().content("application/xml").headers(headers).body(xmlUtil.getXMLString()).post(url);
		System.out.println(response.getStatusCode());
		vpManual("AtlasResponse", response.getStatusCode(), "201");
		
		
		// * Process Delivery confirmation on SAP
		 
		button_back().press();
		button_back().press();
		button_back().press();
		
		
		// Window: SAP Easy Access  SAP Retail
		comboBox_okcd().setText("/nbd87");
		window_sapEasyAccessSAPRetail().sendVKey(SAPTopLevelTestObject.VKEY_ENTER);
		
		// Window: Select IDocs
		text_sX_CRETILOW().setText(hr +":"+min+":00");
		text_sX_CRETIHIGH().setText(hr+1 +":"+min+":00");
		text_sX_UPDTILOW().setText(hr +":"+min+":00");
		text_sX_UPDTIHIGH().setText(hr+1 +":"+min+":00");
		text_sX_STATULOW().setText("64");
		text_sX_STATULOW().setFocus();
		text_sX_STATULOW().setCaretPosition(2);
		button_execute().press();
		
		
		// Window: Status Monitor for ALE Messages
		
		tree_sapTableTreeControl1().expandNode(atPath("RQ2 Client 010->IDoc in inbound processing->IDoc ready to be transferred to application"));
		
		tree_sapTableTreeControl1().selectItem(
                                        atPath("RQ2 Client 010->IDoc in inbound processing->IDoc ready to be transferred to application->SHPCON"), 
                                        "Column1");
		
		button_process().press();
	
		
		// Window: IDoc processing
		grid_sapguiGridViewCtrl1().performTest(SAPGUIGridViewCtrl1_listVP());
		
		comboBox_okcd().setText("/nva03");
		window_sapEasyAccessSAPRetail().sendVKey(SAPTopLevelTestObject.VKEY_ENTER);
		

		sap().displaySalesOrderInitailScreenDialog().search(orderNum);
		
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNum);
		
		button_DocumentFlow().click();
		
		sap().documentFlowDialog().verifyDocumentTable("DeliveryConfirmation");
		
		*/
	}
}

