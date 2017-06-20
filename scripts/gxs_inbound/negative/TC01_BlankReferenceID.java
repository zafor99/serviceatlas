package scripts.gxs_inbound.negative;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import resources.scripts.gxs_inbound.negative.TC01_BlankReferenceIDHelper;
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
public class TC01_BlankReferenceID extends TC01_BlankReferenceIDHelper
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
		atlas().advanceShipNotice().setReferenceID("");
		atlas().advanceShipNotice().setHeader();
		atlas().advanceShipNotice().postInternal();
		atlas().advanceShipNotice().verifyResponse("TC01_BlankReferenceID_01", "422");
		atlas().advanceShipNotice().verifyResponseBody("TC01_BlankReferenceID_02", "Object failed validation assertions.\n - [/ApplicationArea/Sender/ReferenceID/value] '' must be of at least length '1'");
	}
}

