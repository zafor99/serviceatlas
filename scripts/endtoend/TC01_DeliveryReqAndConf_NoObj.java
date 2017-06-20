package scripts.endtoend;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import resources.scripts.endtoend.TC01_DeliveryReqAndConf_NoObjHelper;
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
public class TC01_DeliveryReqAndConf_NoObj extends TC01_DeliveryReqAndConf_NoObjHelper
{
	/**
	 * Script Name   : <b>TC01_DeliveryReqAndConf_NoObj</b>
	 * Generated     : <b>May 23, 2013 2:25:59 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/05/23
	 * @author zsadeque
	 * @throws ClassNotFoundException 
	 */
	public void testMain(Object[] args) throws ClassNotFoundException 
	{
		String orderNum,deliveryNumber = null;		
		String timeStamp = null;
	
		timeStamp = "";
		 //* Placing Order Via Caliber
		checkout().GBPhysicalPurchase().submitOrder();
		orderNum = checkout().GBPhysicalPurchase().getOrderNumber();
		timeStamp = checkout().GBPhysicalPurchase().getOrderTimeStamp().toString();
		System.out.println("timeStamp :" + timeStamp);
		
		String hr = timeStamp.substring(11, 13);
		String min = timeStamp.substring(14, 16);

		System.out.println("HR :" + hr);
		System.out.println("Min :" + min);

		
		// Window: saplogon.exe: SAP Logon 730
		sap().startApplication();
		sap().logon720Dialog().selectSystemToLogin();
		sap().userLoginDialog().waitForExistence();
		sap().userLoginDialog().logIn();
		sap().easyAccessDialog().openDisplaySalesOrderInitialScreenDialog();
		sap().displaySalesOrderInitailScreenDialog().search(orderNum);
		
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNum);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		sap().documentFlowDialog().verifyDocumentTable("OpenOrder");
		
		// Create Outbound Delivery with Order Reference
		sap().documentFlowDialog().topLevelToolbar().navigateBack();
		sap().displayDeviceOrderOverViewDialog().deliver();
		sap().outbDelDevice3PLDialog().topLevelToolbar().save();
		sap().createOutboundDeliveryDialog().verifyDeviceDeliveryStatus("StatusBar_01VP");
	    deliveryNumber = sap().createOutboundDeliveryDialog().getDeliveryNumber();
		System.out.println("Delivery No :"+deliveryNumber);
		sap().createOutboundDeliveryDialog().topLevelToolbar().navigateBack();
		sap().easyAccessDialog().openDisplaySalesOrderInitialScreenDialog();
		sap().displaySalesOrderInitailScreenDialog().search(orderNum);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNum);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		sap().documentFlowDialog().verifyDocumentTable("DeliveryCreated");
		
		
		///process iDOc for DESADV
		
		//Placing request via Atlas
		atlas().deliveryConf().setDeliveryNumber(deliveryNumber);
		atlas().deliveryConf().post();
		atlas().deliveryConf().verifyResponse("AtlasResponse", "201");
		
		delayFor(10);
		// Process Delivery confirmation on SAP
		sap().documentFlowDialog().topLevelToolbar().enterCommand("/nbd87");
			
		// Window: Select IDocs
		String newHr = (Integer.parseInt(hr)+ 1) + "" ;
		if(newHr.length() == 1){
			newHr = "0"+newHr;
		}
		sap().selectIDocDialog().searchIDoc(hr +":"+min+":00",newHr+":"+min+":00", null, null, hr +":"+min+":00", newHr+":"+min+":00","64");
		sap().statusMonitorDialog().expandiDocInboundProcessing();
		

		if(sap().statusMonitorDialog().isInboundSHPCONAVisible()){
			sap().statusMonitorDialog().selectSHPCONAndProcess();
//			sap().iDocProcessingDialog().verifyProcessedIDOCGrid("gridview");
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nva03");

		}
		else{
			sap().statusMonitorDialog().topLevelToolbar().enterCommand("/nva03");			
		}
	//	orderNum = "147509130";
	//	deliveryNumber = "8102941283";
		
		sap().displaySalesOrderInitailScreenDialog().search(orderNum);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNum);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		sap().documentFlowDialog().verifyDocumentTable("DeliveryConfirmation");
		
		//Create Billing Document
		sap().documentFlowDialog().topLevelToolbar().enterCommand("/nvf01");
		sap().createBillingDocumentDialog().topLevelToolbar().save();
		sap().createBillingDocumentDialog().verifyBillingDocStatus("StatusBar_02VP");
		sap().createBillingDocumentDialog().topLevelToolbar().enterCommand("/nVA03");
		sap().displaySalesOrderInitailScreenDialog().search(orderNum);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNum);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		sap().documentFlowDialog().verifyDocumentTable("DeliveryConfirmation_01");
//		dbService().pfsDB().verifyStatusInTRXHEADER("CloseStatus", deliveryNumber,"C");
		sap().documentFlowDialog().close();
		sap().logOffDialog().clickYesButton();
		sap().closeApplication();
	}
}

