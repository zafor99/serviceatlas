package scripts_customerservice;
import resources.scripts_customerservice.TC01_PhysicalOrdersClosedHelper;
import utils.SpreadSheetUtil;

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
public class TC01_PhysicalOrdersClosed extends TC01_PhysicalOrdersClosedHelper
{
	/**
	 * Script Name   : <b>TC01_PhysicalOrdersClosed</b>
	 * Generated     : <b>Oct 15, 2014 11:24:35 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/10/15
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		/*		public void writeBNOrdersToExcel(String tcName,String dateNTime,String orderNumber, String ean,String customerID){
			logger.info("writeBNOrdersToExcel("+tcName+"),("+dateNTime+"),("+orderNumber+"),("+ean+")");
			String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\AtlasOrders.xls";
			writeOrdersToExcel(spreadSheetPath, tcName, dateNTime, orderNumber, ean,customerID);
		}
		 */		
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\CustomerService\\orders.xls";
		SpreadSheetUtil excelSheet = null;
		excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
		String orderNumber,poNumber,shopConfirmIDOC = null;
		for(int i=1;i<101;i++)
		{
			checkout().USPhysicalPurchase().submitOrder(true,"STD","9780439796644");
			orderNumber =  checkout().USPhysicalPurchase().getOrderNumber();//"625586024";

			
			excelSheet.readRow(i);
			//Write the Data
			excelSheet.setCellStringValue("Physical Orders", orderNumber);
		}



		//starting SAP
		sap().startApplication();
		sap().logon720Dialog().selectSystemToLogin();
		sap().userLoginDialog().logIn();

		//Process Orders in SAP
		sap().easyAccessDialog().openDisplaySalesOrderInitialScreenDialog();
		for(int i=1;i<9;i++){
			excelSheet.readRow(i);
			orderNumber=excelSheet.getCellStringValue("Physical Orders");
			
			sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
			sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);

			//get PO Number
			sap().displayBNOrderPhaseOverviewDialog().openDisplayDocumentFlowDialog();
			poNumber = sap().documentFlowDialog().getPurchaseOrderNumberFromHeaderData();
			excelSheet.setCellStringValue("PONumber", poNumber);

			//Update Shop Confirm iDoc with PO Number
			sap().documentFlowDialog().topLevelToolbar().enterCommand("/nwe19");
			sap().callingiDocTestToolDialog().clickOkButton();
			sap().testTooliDocProcessingDialog().searchExistingiDoc("664616822");
			sap().testTooliDocProcessingDialog().expandE1PORDCH();
			sap().testTooliDocProcessingDialog().openE1BPMEPOHEADER();
			sap().changeDataRecordDialog().changeData(poNumber, null, null, "ok");
			sap().testTooliDocProcessingDialog().clickStandardInbound();
			sap().testInboundIDocUsPartProfDialog().clickOkButton();

			//Getting Shop Confirm iDOc
			shopConfirmIDOC = sap().informationDialog().getiDocFromTextLabel();
			sap().informationDialog().clickOkButton();

			//Process iDpc in BD87
			sap().testTooliDocProcessingDialog().topLevelToolbar().enterCommand("/nbd87");
			sap().selectIDocDialog().searchIDoc(shopConfirmIDOC);
			sap().statusMonitorDialog().expandiDocInboundProcessing();
			if(sap().statusMonitorDialog().isPORDCHVisible()){
				sap().statusMonitorDialog().selectPORDCHAndProcess();
				sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nwe19");

			}
			else{
				sap().statusMonitorDialog().topLevelToolbar().enterCommand("/nwe19");	
			}


			// Processing Ship Confirm
			
			sap().callingiDocTestToolDialog().clickOkButton();
			sap().testTooliDocProcessingDialog().searchExistingiDoc("664642887");
			sap().testTooliDocProcessingDialog().expandE1EDL20();
			sap().testTooliDocProcessingDialog().expandE1EDL24();
			sap().testTooliDocProcessingDialog().openE1EDL41();
			sap().changeDataRecordDialog().ChangeBSTNR(poNumber, "ok");
			sap().testTooliDocProcessingDialog().clickStandardInbound();
			sap().testInboundIDocUsPartProfDialog().clickOkButton();
			sap().informationDialog().clickOkButton();

			sap().testTooliDocProcessingDialog().topLevelToolbar().enterCommand("/nva03");
		}
	}
}