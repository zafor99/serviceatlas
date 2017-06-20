package scripts.gxs_inbound.negative;
import resources.scripts.gxs_inbound.negative.TC32_BlankShipmentUnitItemPOReferenceDocumentIDHelper;
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
public class TC32_BlankShipmentUnitItemPOReferenceDocumentID extends TC32_BlankShipmentUnitItemPOReferenceDocumentIDHelper
{
	/**
	 * Script Name   : <b>TC02_BatchNumberReferenceIDDuplicate</b>
	 * Generated     : <b>May 31, 2013 4:54:35 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/05/31
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		
		atlas().advanceShipNotice().loadXmlFile("gxs_inbound\\TC32_BlankShipmentUnitItemPOReferenceDocumentID.xml");
		atlas().advanceShipNotice().setHeader();
		atlas().advanceShipNotice().postInternal();
		atlas().advanceShipNotice().verifyResponse("TC32_01", "422");
		atlas().advanceShipNotice().verifyResponseBody("TC32_02", "Object failed validation assertions.\n - [/DataArea/Shipment[*]/ShipmentUnit[*]/ShipmentUnitItem[*]/PurchaseOrderReference[0]/DocumentID/ID/value] '' must be of at least length '1'");
		//atlas().advanceShipNotice().verifyResponseBody("AtlasResponse_02", "PurchaseOrderReference/DocumentID/ID cannot be empty or exceeds max length 10");
	}
}


