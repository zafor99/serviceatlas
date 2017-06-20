package scripts.sap;
import resources.scripts.sap.TC03_DevicePurchaseHelper;
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
public class TC03_DevicePurchase extends TC03_DevicePurchaseHelper
{
	/**
	 * Script Name   : <b>TC01_eBookInstantPurchase</b>
	 * Generated     : <b>Jun 28, 2013 3:33:34 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/06/28
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		String orderNumber = null;
		String timeStamp = null;
		ipOrdersXLS().readRow(3);
		orderNumber = ipOrdersXLS().getCellStringValue(1);
		timeStamp = ipOrdersXLS().getCellStringValue(2);
		System.out.println("Order TimeStamp : " + timeStamp);
		String hr = timeStamp.substring(11, 13);
		String min = timeStamp.substring(14, 16);
		System.out.println("HR :" + hr);
		System.out.println("Min :" + min);
		
		//Start the application
		sap().startApplication();
		sap().logon720Dialog().selectSystemToLogin();
		sap().userLoginDialog().waitForExistence();
		
		//Login to SAP
		sap().userLoginDialog().logIn();
		
		//Search for the order
		sap().easyAccessDialog().openDisplaySalesOrderInitialScreenDialog();
		sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		//Verify open order
		sap().documentFlowDialog().verifyDocumentTable("OpenOrder");
		
		// Create Outbound Delivery with Order Reference
		sap().documentFlowDialog().topLevelToolbar().navigateBack();
		sap().displayDeviceOrderOverViewDialog().deliver();
		sap().outbDelDevice3PLDialog().topLevelToolbar().save();
		sap().createOutboundDeliveryDialog().verifyDeviceDeliveryStatus("StatusBar_01VP");
		String deliveryNumber = sap().createOutboundDeliveryDialog().getDeliveryNumber();
		sap().createOutboundDeliveryDialog().topLevelToolbar().navigateBack();
		sap().easyAccessDialog().openDisplaySalesOrderInitialScreenDialog();
		sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		sap().documentFlowDialog().verifyDocumentTable("DeliveryCreated");
		
		//Placing request via Atlas
		atlas().deliveryConf().setDeliveryNumber(deliveryNumber);
//		atlas().deliveryConf().setRandomBatchNumber();
		atlas().deliveryConf().post();
		atlas().deliveryConf().verifyResponse("AtlasResponse", "201");
		
		// Process Delivery confirmation on SAP
		
		sap().documentFlowDialog().topLevelToolbar().enterCommand("/nbd87");
			
		// Window: Select IDocs
		String newHr = (Integer.parseInt(hr)+ 1) + "" ;
		if(newHr.length() == 1){
			newHr = "0"+newHr;
		}
		sap().selectIDocDialog().searchIDoc(hr +":"+min+":00",newHr+":"+min+":00", null, null, hr +":"+min+":00", newHr+":"+min+":00","64");
		sap().statusMonitorDialog().expandiDocInboundProcessing();
		sap().statusMonitorDialog().selectSHPCONAndProcess();
		sap().iDocProcessingDialog().verifyProcessedIDOCGrid("gridview");
		sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nva03");
		sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		sap().documentFlowDialog().verifyDocumentTable("DeliveryConfirmation");
		
		//Create Billing Document
		sap().documentFlowDialog().topLevelToolbar().enterCommand("/nvf01");
		sap().createBillingDocumentDialog().topLevelToolbar().save();
		sap().createBillingDocumentDialog().verifyBillingDocStatus("StatusBar_02VP");
		sap().createBillingDocumentDialog().topLevelToolbar().enterCommand("/nVA03");
		sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		sap().documentFlowDialog().verifyDocumentTable("DeliveryConfirmation_01");
		
		sap().documentFlowDialog().topLevelToolbar().enterCommand("/ncic0");
		
		/*///////
		
		
		
		//start the application
		sap().startApplication();
		sap().logon720Dialog().selectSystemToLogin();
		sap().userLoginDialog().waitForExistence();
		
		//Login to SAP
		sap().userLoginDialog().logIn();
		
		//Open customer Interaction Center(CICO)
		sap().easyAccessDialog().openCustomerInteractionCenterDialog();
		//Search for order
		sap().customerInteractionCenterDialog().clickOrderSearchFindButton();
		sap().restrictValueRangeDialog().searchWebOrder(orderNumber);
		sap().hitList1EntryDialog().verifyEntryTable("OrderStatus");
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber,"Web Order Number");
		sap().customerInteractionCenterDialog().goToDispSAPSalesOrderDialog();
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();
		sap().documentFlowDialog().verifyDocumentTable("OrderDetails");*/
	}
}

