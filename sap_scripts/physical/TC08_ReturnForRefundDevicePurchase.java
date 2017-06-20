package sap_scripts.physical;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import resources.sap_scripts.physical.TC08_ReturnForRefundDevicePurchaseHelper;
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
public class TC08_ReturnForRefundDevicePurchase extends TC08_ReturnForRefundDevicePurchaseHelper
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
	
		String orderNumber = "147084345";
		//Starting  SAP Application		
		sap().startApplication();
		sap().logon720Dialog().selectSystemToLogin();
		sap().userLoginDialog().waitForExistence();;
		sap().userLoginDialog().logIn();
		delayFor(10);
		
		//Open the order for Device Search
		sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/ncic0");
		sap().customerInteractionCenterDialog().selectDeviceSearchTab();
		sap().customerInteractionCenterDialog().clickDeviceSearchFindButton();
		sap().restrictValueRangeDialog().deviceSearchTab().searchDeviceWebOrder(orderNumber);
		
		sap().deviceInfoSerialNumberDialog().verifyDeviceHistoryTable("DeviceHistoryOrder");
		//Update with Return Reason Code
		sap().initiateReturnForRefundDialog().selectCustomerReturnReasonCode("R004:Broken Screen (OOB)");
		sap().initiateReturnForRefundDialog().verifyCustomerReturnReasonCode("BrokenScreen");
		sap().initiateReturnForRefundDialog().update();
		
		//Verify Return Orders
		sap().deviceInfoSerialNumberDialog().verifyDeviceHistoryTable("ReturnOrders");
		
		//Open the Orders and verify open Return status
		sap().deviceInfoSerialNumberDialog().topLevelToolbar().enterCommand("/nva03");
		sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		sap().documentFlowDialog().verifyDocumentTable("OpenDeviceReturns");
		
		
		//TODO: Need more info
		
		
		
		
	}
	
}

