package scripts_customerservice;
import resources.scripts_customerservice.TC02_eBookPurchaseHelper;
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
public class TC02_eBookPurchase extends TC02_eBookPurchaseHelper
{
	/**
	 * Script Name   : <b>TC02_eBookPurchase</b>
	 * Generated     : <b>Oct 16, 2014 10:49:58 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * eBook Orders
	 * @since  2014/10/16
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		String spreadSheetPath = "C:\\MyDevelopment\\Workspaces\\RFTWorkspace\\Atlas-QA-RFT\\testdata\\CustomerService\\orders.xls";
		SpreadSheetUtil excelSheet = null;
		excelSheet = new SpreadSheetUtil(spreadSheetPath, 0);
		String orderNumber,poNumber,shopConfirmIDOC = null;
		for(int i=1;i<101;i++)
		{
			instantPurchase().submitIPOrder("MC", "5454545454545454", "2940000952825");
			orderNumber=instantPurchase().getOrderNumber();

			
			excelSheet.readRow(i);
			//Write the Data
			excelSheet.setCellStringValue("eBook Orders", orderNumber);
		}
	}
}

