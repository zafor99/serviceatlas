package tempscripts;
import resources.tempscripts.AlchemyHelper;
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

import framework.AtlasScriptbase;
import framework.alchemymodel.Device;
/**
 * Description   : Functional Test Script
 * @author fahmed1
 */
public class Alchemy extends AlchemyHelper
{
	/**
	 * Script Name   : <b>Alchemy</b>
	 * Generated     : <b>Sep 9, 2014 2:37:07 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/09/09
	 * @author fahmed1
	 * @throws ClassNotFoundException 
	 */
	public void testMain(Object[] args) throws ClassNotFoundException 
	{
		String purchaseRequisition, poNumber,status,iDoc,orderStatus = null;
		Device.setDeviceGalaxyTab10Inch();
		Device.launchApp();
		String emailAddress = "deviceauto" + generateRandom(5) + "@book.com";

		//String emailAddress = "deviceauto31023@book.com";
		customerUserAccount().generateUserAccount(emailAddress);
		System.out.println("Acc : " + emailAddress);		
		System.out.println(alchemy().getClient().getDevicesInformation());

		alchemy().explorePage().clickSignInButton();
		alchemy().singInPage().signIn(emailAddress, "qatester");

		alchemy().homePage().gotoShopPage();
		alchemy().shopPage().buyBook();

		String customerID = customerUserAccount().getCustomerId();
		//String customerID = "000zyDaVe9JYO810";
		System.out.println("Customer ID : " + customerID);
		String orderNumber = dbService().OrderStatusDB().getOrderNumberByCustomerEmail(emailAddress);
		System.out.println("Order Number : " + orderNumber);
		String ean =  dbService().OrderStatusDB().getEANFromOrderItemByOrderNumber(orderNumber);
		System.out.println("EAN : " + ean);

		//alchemy().homePage().signOut();

		//SAP Validation
		sap().startApplication();
		sap().logon720Dialog().selectSystemToLogin();
		sap().userLoginDialog().logIn();

		//Process Orders in SAP
		sap().easyAccessDialog().topLevelToolbar().enterCommand("/nse16");
		sap().dataBrowserInitialScreenDialog().execute("EDIDC");
		sap().dataBrowserTableSelectionScreenDialog().execute(orderNumber);
		status = sap().dataBrowserTableEDIDCDialog().getStatus();
		
		
		//If order needs to be process
		if(status.contentEquals("64")){
			iDoc = sap().dataBrowserTableEDIDCDialog().getiDocNumber();
			sap().dataBrowserTableEDIDCDialog().topLevelToolbar().enterCommand("/nZRPIDOC");
			sap().createBatchJobToProcessiDocDialog().createBatchtoProcessiDoc(iDoc);
			sap().createBatchJobToProcessiDocDialog().topLevelToolbar().enterCommand("/nva03");
		}
		else{
			sap().dataBrowserTableEDIDCDialog().topLevelToolbar().enterCommand("/nva03");
		}
		sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);

	
		//Get purchase Requisition 
		sap().displayBNOrderPhaseOverviewDialog().clickScheduleLinesForItem();
		purchaseRequisition = sap().displayOrdPhaseItemDataDialog().scheduleLinesTab().getFirstPurchaseRequisition();

		//Create PO
		sap().displayOrdPhaseItemDataDialog().topLevelToolbar().enterCommand("/nME59");
		sap().automaticCreationOfPODialog().createPOfromPurchaseRequisition(purchaseRequisition,"DIGITAL_QAEBA");
		sap().automaticCreationOfPurchaseDialog().topLevelToolbar().enterCommand("/nva03");
		sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);

		/*
		 * step 8
		 * Verify Sales Data
		 */
		sap().displayBNOrderPhaseOverviewDialog().openDisplayDocHeaderDeatailsDialog();
		sap().displayBNOrdPhaseHeaderDataDialog().salesTab().select();
		framework.HealthCheckSmokeTest.setServiceId(5);
		sap().displayBNOrdPhaseHeaderDataDialog().salesTab().verifySalesData("SalesData");

		/*
		 * Step 9 
		 * 
		 */
		sap().displayBNOrdPhaseHeaderDataDialog().openDisplayDocumentFlowDialog();
		poNumber = sap().documentFlowDialog().getPurchaseOrderNumberFromHeaderData();
		//Processing  invoice job
		sap().documentFlowDialog().topLevelToolbar().enterCommand("/nse38");
		sap().aBAPEditorInitialScreenDialog().executeWithVariant("ZROF_CREATE_INCMNG_INVOICE_DP","ZERMMINV_QAEB");
		sap().createIncomingInvoiceDialog().createIncomingInvoice(poNumber);
		if(sap().createIncomingInvoiceDialog().isInvoiceCreated()){
			framework.HealthCheckSmokeTest.setServiceId(5);
			sap().createIncomingInvoiceDialog().verifyInvoiceCreated("InvoicePosted");
			sap().createIncomingInvoiceDialog().topLevelToolbar().enterCommand("/nva03");	
		}else{
			sap().createIncomingInvoiceDialog().topLevelToolbar().enterCommand("/nva03");
		}

		sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
		sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);
		sap().displayDeviceOrderOverViewDialog().openDisplayDocumentFlowDialog();

		sap().documentFlowDialog().verifyDocumentTable("FinalDocFlow");
		sap().documentFlowDialog().topLevelToolbar().enterCommand("/nw45");
		//Close application.

		sap().easyAccessDialog().close();
		sap().logOffDialog().clickYesButton();
		sap().closeApplication();
		/*
		 * Step 10 
		 * validate in CRM
		 */
		crm().clearBrowserCookies();
		crm().startApplication();
		crm().signInDialog().signIn();
		crm().selectABusinessRolePage().clickZBNT1CSRLink();

		crm().identifyAccountPage().accountSearchPanel().search(orderNumber, null, null, null, null, "search account");
		if(crm().identifyAccountPage().accountConfirmPanel().isOrderExist()){
			crm().identifyAccountPage().accountConfirmPanel().confirm();
			framework.HealthCheckSmokeTest.setServiceId(5);
			crm().identifyAccountPage().ordersTabPanel().verifyOrdersTable("OrderClosed");
			crm().topNavToolBar().end();
			
			crm().logOff();
			crm().closeApplication();
		}
		else{
			logError("Order Number :" +orderNumber +"  not found in CRM");
		}

		//Data Validation for OSDB
		dbService().OrderStatusDB().verifyOrderInOrderHeaderTable(orderNumber, "OSDB_OrderHeaderTable");
		dbService().OrderStatusDB().verifyOrderInOrderItemTable(orderNumber, "OSDB_ItemTable");
		dbService().OrderStatusDB().verifyOrderInOrderShipmentTable(orderNumber, "OSDB_OrderShipmentTable");

		//Data Validation for DWDB
		dbService().datawareHouseDB().verifyOrderInOrderHeaderTable(orderNumber, "DWDB_OrderHeaderTable");
		dbService().datawareHouseDB().verifyOrderInOrderDetailTable(orderNumber, "DWDB_OrderDetailTable");
		dbService().datawareHouseDB().verifyOrderInOrderDetailActivityTable(orderNumber, "DWDB_OrderDetailActivityTable");
		dbService().datawareHouseDB().verifyDetailsInCustomerTable(customerID, "DWDB_CustomerTable");
		dbService().datawareHouseDB().verifyOrderInOrderCCNumberTable(orderNumber, "DWDB_OrderCCNumberTable");

		//Data Validation for BN Inc DB
		dbService().BNIncDB().verifyOrderInOrderHeaderTable(orderNumber, "BNIncDB_OrderHeaderTable");
		dbService().BNIncDB().verifyOrderInOrderDetailTable(orderNumber, "BNIncDB_OrderDetailTable");
		dbService().BNIncDB().verifyOrderInOrderCCNumberActivityTable(orderNumber, "BNIncDB_OrderCCNumberTable");
		dbService().BNIncDB().verifyDetailsInCustomerTable(customerID, "BNIncDB_CustomerTable");

		//Data Validation for Sales Rank DB 
		dbService().salesRankDB().verifyOrderInOrderHeaderTable(orderNumber, "SalesRankDB_OrderHeaderTable");
		dbService().salesRankDB().verifyOrderInOrderDetailTable(orderNumber, "SalesRankDB_OrderDetailTable");

		//Validate Order Exist in Digital Locker
		digitalLocker(customerID).verifyLockerItem("DigitalLocker");

	}
}

