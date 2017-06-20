package scripts.gxs_inbound.positive;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import resources.scripts.gxs_inbound.positive.TC07_InvoiceExternalHelper;
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
public class TC07_InvoiceExternal extends TC07_InvoiceExternalHelper
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
		atlas().invoiceService().loadXmlFile("gxs_inbound\\Positive\\TC07_InvoiceExternal.xml");
		atlas().invoiceService().setHeader();
		atlas().invoiceService().setHeader("Authorization", "Basic Z3hzOkNyNzZ3ZQ==");
		atlas().invoiceService().setRandomReferenceID();
		
		
		//TODO: Need to change 
		//Need to enter the same PO number
		//InvoiceHeader\PurchaseOrderReference\DocumentID\ID
		//ENter the same again
		//InvoiceLine\PurchaseOrderReference\DocumentID\ID
		
		//AlternateDocumentID  needs to be changed also
		//This is the same document id from ASN Call
		//InvoiceHeader\AlternateDocumentID \\
		atlas().invoiceService().postExternal();
		atlas().invoiceService().verifyResponse("TC07_InvoiceExternal", "201");
		
		//TODO: Need to Complete verification for SAP
	}
	
}

