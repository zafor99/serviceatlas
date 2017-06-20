package scripts.outboudsapactions.withoutingestion.bnwallet;
import resources.scripts.outboudsapactions.withoutingestion.bnwallet.TC10_ROCHANGEBILLADDRESSHelper;
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

import framework.AtlasScriptExecution;

import utils.EnvironmentUtility;
import utils.SpreadSheetUtil;

/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class TC10_ROCHANGEBILLADDRESS extends TC10_ROCHANGEBILLADDRESSHelper
{
	/**
	 * Script Name   : <b>TC01_NookApp</b>
	 * Generated     : <b>Feb 21, 2014 4:07:41 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/02/21
	 * @author zsadeque
	 * @throws ClassNotFoundException 
	 */
	public void testMain(Object[] args) throws ClassNotFoundException 
	{

		String ean,orderNumber,purchaseRequisition, poNumber,status,iDoc,customerID,timeStamp,orderStatus = null;
		ean = "2940000952825";
		instantPurchase().submitIPOrder(EnvironmentUtility.atlas().serverName().substring(7, 17)+"_","VI", "4313081835209051", ean);
		instantPurchase().verifyInstantPurchase("IPStatus");
		orderNumber=instantPurchase().getOrderNumber();
		customerID = instantPurchase().getCustomerID();
		timeStamp = instantPurchase().getOrderTimeStamp();
		
		if(orderNumber.length()>2){
			writeOutboundBNOrdersToExcel("TC10_ROCHANGEBILLADDRESS", timeStamp, orderNumber, ean,customerID);
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
				sap().createBatchJobToProcessiDocDialog().topLevelToolbar().enterCommand("/ncic0");
			}
			else{
				sap().dataBrowserTableEDIDCDialog().topLevelToolbar().enterCommand("/ncic0");
			}
			//orderNumber = "147703220";
			sap().customerInteractionCenterDialog().clickOrderSearchFindButton();
			sap().restrictValueRangeDialog().searchWebOrder(orderNumber);
			sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber,"Web Order Number");
			sap().customerInteractionCenterDialog().goToDisplayChangeOrderDialog();
			sap().bnOrderDataRefreshDialog().selectPaymentCardsTab();
			sap().bnOrderDataRefreshDialog().paymentCardsTab().clickBillToAddress();
			sap().updateBillingAddress().updateBillingAddress("Darren Tang", null, "2124144000", "76 9th ave", "9th fl", null, "New York", "10011", "US", "NY", null, "update");
			
			sap().bnOrderDataRefreshDialog().topLevelToolbar().enterCommand("/nzalo1");
			sap().iDocReportingToolDialog().searchSalesOrder(null, orderNumber, null);
			sap().iDocReportingToolDialog().expandAllSalesOrder();
			sap().iDocReportingToolDialog().expandAllSalesOrder();
			String iDocForZ135 = sap().iDocReportingToolDialog().getIDocForZ135OrderChgBilAdrs();
			System.out.println(iDocForZ135);
			sap().iDocReportingToolDialog().topLevelToolbar().enterCommand("/nbd87");
			sap().selectIDocDialog().searchIDoc(iDocForZ135);
			sap().statusMonitorDialog().expandiDocOutboundProcessing();
			if(	sap().statusMonitorDialog().isOutboundORDRSPVisible()){
				sap().statusMonitorDialog().selectORDRSPndProcess();
				sap().informationDialog().clickOkButton();
				sap().iDocProcessingDialog().verifyProcessedIDOCGrid("Z135_processed");
				sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nzalo1");

			} else {
				sap().statusMonitorDialog().topLevelToolbar().enterCommand("/nzalo1");
			}
			sap().iDocProcessingDialog().close();
			sap().logOffDialog().clickYesButton();
			sap().logon720Dialog().close();
			
			//Data Validation for OSDB
			dbService().OrderStatusDB().verifyOrderInOrderHeaderTable(orderNumber, "OSDB_OrderHeader_Closed");

			//Data Validation for DWDB
			dbService().datawareHouseDB().verifyDetailsInCustomerTable(customerID, "DWDB_Customer_Closed");
			dbService().datawareHouseDB().verifyOrderInOrderHeaderTable(orderNumber, "DWDB_OrderHeader_Closed");
			dbService().datawareHouseDB().verifyOrderInOrderCCNumberTable(orderNumber, "DWDB_OrderCCNumber_Closed");
			
			//Data Validation for BN Inc DB
			dbService().BNIncDB().verifyOrderInOrderHeaderTable(orderNumber, "BNIncDB_OrderHeader_Closed");
			dbService().BNIncDB().verifyDetailsInCustomerTable(customerID, "BNIncDB_Customer_Closed");

			//Data Validation for Sales Rank DB 
			dbService().salesRankDB().verifyOrderInOrderDetailTable(orderNumber, "SalesRankDB_OrderDetail_Closed");
			//Validate no Error in Activity Momonitor for this order
			activityMonitor().verifyActivityMonitorForError(ean, "NoError");
			
		}
		else{
			logError("Order Number is not generated");
		}	

	}
}

