package scripts;
import resources.scripts.AtlasShellScriptHelper;
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
public class AtlasShellScript extends AtlasShellScriptHelper
{
	/**
	 * Script Name   : <b>AtlasShellScript</b>
	 * Generated     : <b>Jul 24, 2014 11:50:59 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/24
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		callScript("scripts.atlasProcessingOrders.ShellScripts");
		callScript("scripts.endtoend.TC01_DeliveryReqAndConf_NoObj");
		callScript("scripts.gxs_inbound.negative.GSX_Inbound_Negative_ShellScript");
		callScript("scripts.gxs_inbound.positive.TC13_EndToEnd");
		callScript("scripts.outboudsapactions.ShellScripts");

	}
}

