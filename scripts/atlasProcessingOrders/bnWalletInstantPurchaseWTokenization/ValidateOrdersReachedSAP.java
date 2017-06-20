package scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization;
import resources.scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.ValidateOrdersReachedSAPHelper;
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
	private static String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\BNOrdersWTokenization.xls";
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
		//Process iDoc in BD87 
		sap().easyAccessDialog().topLevelToolbar().enterCommand("/nbd87");
		sap().selectIDocDialog().searchIDoc(null, "24:00:00", null, null, null,"24:00:00" );
		sap().statusMonitorDialog().expandiDocInboundProcessing();
		if(	sap().statusMonitorDialog().isInboundOrdersVisible()){
			sap().statusMonitorDialog().selectInboundORDERSndProcess();
			sap().iDocProcessingDialog().topLevelToolbar().enterCommand("/nva03");
			
		} else {
			sap().statusMonitorDialog().topLevelToolbar().enterCommand("/nva03");
		}
		sap().writeOrderStatusToExcel(spreadSheet());
		sap().displaySalesOrderInitailScreenDialog().close();
		sap().logOffDialog().clickYesButton();
		sap().logon720Dialog().close();
	}
}

