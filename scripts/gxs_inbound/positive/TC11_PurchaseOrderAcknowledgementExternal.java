package scripts.gxs_inbound.positive;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import resources.scripts.gxs_inbound.positive.TC11_PurchaseOrderAcknowledgementExternalHelper;
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
public class TC11_PurchaseOrderAcknowledgementExternal extends TC11_PurchaseOrderAcknowledgementExternalHelper
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
		atlas().purchaseOrderAcknowledgementService().loadXmlFile("gxs_inbound\\Positive\\TC11_PurchaseOrderAcknowledgementExternal.xml");
		atlas().purchaseOrderAcknowledgementService().setHeader();
		atlas().purchaseOrderAcknowledgementService().setHeader("Authorization", "Basic Z3hzOkNyNzZ3ZQ==");
		atlas().purchaseOrderAcknowledgementService().setRandomReferenceID();
		atlas().purchaseOrderAcknowledgementService().postExternal();
		atlas().purchaseOrderAcknowledgementService().verifyResponse("TC11_PurchaseOrderAcknowledgementExternal", "201");
		
		//TODO: Need to Complete verification for SAP
	}
	
}

