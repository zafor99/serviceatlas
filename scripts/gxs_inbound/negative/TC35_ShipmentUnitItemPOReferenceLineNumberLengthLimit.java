package scripts.gxs_inbound.negative;
import resources.scripts.gxs_inbound.negative.TC35_ShipmentUnitItemPOReferenceLineNumberLengthLimitHelper;
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
public class TC35_ShipmentUnitItemPOReferenceLineNumberLengthLimit extends TC35_ShipmentUnitItemPOReferenceLineNumberLengthLimitHelper
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
		
		atlas().advanceShipNotice().loadXmlFile("gxs_inbound\\TC35_ShipmentUnitItemPOReferenceLineNumberLengthLimit.xml");
		atlas().advanceShipNotice().setHeader();
		atlas().advanceShipNotice().postInternal();
		atlas().advanceShipNotice().verifyResponse("TC35_01", "422");
		atlas().advanceShipNotice().verifyResponseBody("TC35_02", "Object failed validation assertions.\n - [/DataArea/Shipment[*]/ShipmentUnit[*]/ShipmentUnitItem[*]/PurchaseOrderReference[0]/LineNumber/value] '12334666' can be of at most length '6'");
	}
}


