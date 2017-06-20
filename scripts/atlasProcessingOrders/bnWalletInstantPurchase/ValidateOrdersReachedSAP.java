package scripts.atlasProcessingOrders.bnWalletInstantPurchase;
import resources.scripts.atlasProcessingOrders.bnWalletInstantPurchase.ValidateOrdersReachedSAPHelper;
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
public class ValidateOrdersReachedSAP extends ValidateOrdersReachedSAPHelper
{
	/**
	 * Script Name   : <b>ValidateOrdersReachedSAP</b>
	 * Generated     : <b>Mar 20, 2014 4:42:54 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/03/20
	 * @author zsadeque
	 */
	private static String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\AtlasOrders.xls";
	private SpreadSheetUtil excelSheet = null;
	
	public SpreadSheetUtil spreadSheet(){
		if(excelSheet == null){
			excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
		}
		return excelSheet;
	}
	
	public void testMain(Object[] args) 
	{
		
		sap().startApplication();
		sap().logon720Dialog().selectSystemToLogin();
		sap().userLoginDialog().logIn();
		
		//Get iDoc for Order numbers
		sap().easyAccessDialog().topLevelToolbar().enterCommand("/nse16");
		sap().dataBrowserInitialScreenDialog().execute("EDIDC");
		sap().writeiDocToExcel(spreadSheet());
		
		//process iDoc
		sap().dataBrowserTableSelectionScreenDialog().topLevelToolbar().enterCommand("/nZRPIDOC");
		sap().writeiDocProcessStatusToExcel(spreadSheet());

	//	sap().createBatchJobToProcessiDocDialog().topLevelToolbar().enterCommand("/nva03");
		
		//sap().writeOrderStatusToExcel(spreadSheet());
		sap().createBatchJobToProcessiDocDialog().close();
		sap().logOffDialog().clickYesButton();
		sap().logon720Dialog().close();
	}
}

