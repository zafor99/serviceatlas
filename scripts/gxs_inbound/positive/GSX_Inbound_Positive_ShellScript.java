package scripts.gxs_inbound.positive;
import resources.scripts.gxs_inbound.positive.GSX_Inbound_Positive_ShellScriptHelper;
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
public class GSX_Inbound_Positive_ShellScript extends GSX_Inbound_Positive_ShellScriptHelper
{
	/**
	 * Script Name   : <b>ShellScripts_GSX_Positive</b>
	 * Generated     : <b>Jun 11, 2013 2:17:48 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/06/11
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		
		callScript("scripts.gxs_inbound.positive.TC01_AdvanceShipNoticeInternal");
		callScript("scripts.gxs_inbound.positive.TC02_SingleShipmentUnit");
		callScript("scripts.gxs_inbound.positive.TC03_MultipleShipmentUnitsOneItemPerShipmentUnit");
		callScript("scripts.gxs_inbound.positive.TC04_MultipleShipmenUnitsMultipleItemsPerShipmentUnit");
		callScript("scripts.gxs_inbound.positive.TC05_MultipleTranscationIDs");
		callScript("scripts.gxs_inbound.positive.TC06_AdvanceShipNoticeExternal");
		callScript("scripts.gxs_inbound.positive.TC07_InvoiceExternal");
		callScript("scripts.gxs_inbound.positive.TC08_InvoiceInternal");
		callScript("scripts.gxs_inbound.positive.TC09_PurchaseOrderExternal");
		callScript("scripts.gxs_inbound.positive.TC10_PurchaseOrderInternal");
		callScript("scripts.gxs_inbound.positive.TC11_PurchaseOrderAcknowledgementExternal");
		callScript("scripts.gxs_inbound.positive.TC12_PurchaseOrderAcknowledgementInternal");
		

	}
}

