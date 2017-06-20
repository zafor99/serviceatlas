package scripts.gxs_inbound.positive;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import resources.scripts.gxs_inbound.positive.TC10_PurchaseOrderInternalHelper;
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
public class TC10_PurchaseOrderInternal extends TC10_PurchaseOrderInternalHelper
{
	/**
	 * Script Name   : <b>TC01_BlankReferenceID</b>
	 * Generated     : <b>May 29, 2013 11:36:55 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/05/29
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		atlas().puchaseOrderService().loadXmlFile("gxs_inbound\\Positive\\TC10_PurchaseOrderInternal.xml");
		atlas().puchaseOrderService().setHeader();
		atlas().puchaseOrderService().setHeader("Authorization", "Basic Z3hzOkNyNzZ3ZQ==");
		atlas().puchaseOrderService().setRandomReferenceID();
		atlas().puchaseOrderService().postInternal();
		atlas().puchaseOrderService().verifyResponse("TC10_PurchaseOrderInternal", "201");
		
		//TODO: Need to Complete verification for SAP
	}
	
}

