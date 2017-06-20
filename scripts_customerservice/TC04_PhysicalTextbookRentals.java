package scripts_customerservice;
import resources.scripts_customerservice.TC04_PhysicalTextbookRentalsHelper;
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
public class TC04_PhysicalTextbookRentals extends TC04_PhysicalTextbookRentalsHelper
{
	/**
	 * Script Name   : <b>TC04_PhysicalTextbookRentals</b>
	 * Generated     : <b>Oct 16, 2014 11:10:04 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/10/16
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\CustomerService\\orders.xls";
		String xmlFileRentalCheckout_US = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\physicalRentalCheckOut.xml";
		SpreadSheetUtil excelSheet = null;
		excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
		String orderNumber,poNumber,shopConfirmIDOC,ean = null;
		ean = "2580071410178";
	/*	for(int i=1;i<201;i++)
		{
			checkout().USPhysicalPurchase(xmlFileRentalCheckout_US).submitOrder(true,null,null,"MC","5454545454545454");
			orderNumber =  checkout().USPhysicalPurchase().getOrderNumber();//"625586024";

			
			excelSheet.readRow(i);
			//Write the Data
			excelSheet.setCellStringValue("Physical Textbook Rentals", orderNumber);
		}*/
		for(int i=51;i<101;i++){
			excelSheet.readRow(i);
			orderNumber=excelSheet.getCellStringValue("Physical Textbook Rentals");
			poNumber=excelSheet.getCellStringValue("Physical Textbook Rentals PONumber");
			logInfo("Physical Textbook Rentals order no " + orderNumber+ " is processed");
/*			sap().displaySalesOrderInitailScreenDialog().search(orderNumber);
			sap().hitList1EntryDialog().selectPurchaseOrder(orderNumber);

			//get PO Number
			sap().displayBNOrderPhaseOverviewDialog().openDisplayDocumentFlowDialog();
			poNumber = sap().documentFlowDialog().getPurchaseOrderNumberFromHeaderData();
			excelSheet.setCellStringValue("Physical Textbook Rentals PONumber", poNumber);
*/
			//Update Shop Confirm iDoc with PO Number
			//sap().documentFlowDialog().topLevelToolbar().enterCommand("/nwe19");
			sap().callingiDocTestToolDialog().clickOkButton();
			sap().testTooliDocProcessingDialog().searchExistingiDoc("669984182");
			sap().testTooliDocProcessingDialog().expandE1PORDCH();
			sap().testTooliDocProcessingDialog().openE1BPMEPOHEADER();
			sap().changeDataRecordDialog().changeData(poNumber, null, null, "ok");
			sap().testTooliDocProcessingDialog().clickStandardInbound();
			sap().testInboundIDocUsPartProfDialog().clickOkButton();

			//Getting Shop Confirm iDOc
			shopConfirmIDOC = sap().informationDialog().getiDocFromTextLabel();
			sap().informationDialog().clickOkButton();

			//Process iDpc in BD87
			sap().testTooliDocProcessingDialog().topLevelToolbar().enterCommand("/nwe19");
/*			sap().selectIDocDialog().searchIDoc(shopConfirmIDOC);
			sap().statusMonitorDialog().expandiDocInboundProcessing();
			sap().statusMonitorDialog().selectPORDCHAndProcess();
*/

			/*// Processing Ship Confirm
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nwe19");
			sap().callingiDocTestToolDialog().clickOkButton();
			sap().testTooliDocProcessingDialog().searchExistingiDoc("664642887");
			sap().testTooliDocProcessingDialog().expandE1EDL20();
			sap().testTooliDocProcessingDialog().expandE1EDL24();
			sap().testTooliDocProcessingDialog().openE1EDL41();
			sap().changeDataRecordDialog().ChangeBSTNR(poNumber, "ok");
			sap().testTooliDocProcessingDialog().clickStandardInbound();
			sap().testInboundIDocUsPartProfDialog().clickOkButton();
			sap().informationDialog().clickOkButton();
*/
//			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nva03");
		}
	}
}