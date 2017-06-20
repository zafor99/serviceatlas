package scripts.sap;
import resources.scripts.sap.TC01_eBookInstantPurchaseHelper;
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
public class TC01_eBookInstantPurchase extends TC01_eBookInstantPurchaseHelper
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
		ipOrdersXLS().readRow(1);
		orderNumber = ipOrdersXLS().getCellStringValue(1);
		
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
		sap().documentFlowDialog().verifyDocumentTable("OrderDetails");
	}
}

