package scripts.outboudsapactions.withoutingestion.bnwallet;
import resources.scripts.outboudsapactions.withoutingestion.bnwallet.ShellScriptsHelper;
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
public class ShellScripts extends ShellScriptsHelper
{
	/**
	 * Script Name   : <b>ShellScripts</b>
	 * Generated     : <b>Jul 15, 2014 11:18:05 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/15
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		callScript("scripts.outboudsapactions.withoutingestion.bnwallet.ExcelDataReset");
		
		callScript("scripts.outboudsapactions.withoutingestion.bnwallet.TC03_Cancel");
		callScript("scripts.outboudsapactions.withoutingestion.bnwallet.TC05_Declined");
		callScript("scripts.outboudsapactions.withoutingestion.bnwallet.TC06_Fail");
		callScript("scripts.outboudsapactions.withoutingestion.bnwallet.TC08_04_OpenAndClose");
		callScript("scripts.outboudsapactions.withoutingestion.bnwallet.TC09_Pending");
		//callScript("scripts.outboudsapactions.withoutingestion.bnwallet.TC01_ABREFUND");
/*		callScript("scripts.outboudsapactions.withoutingestion.bnwallet.TC10_ROCHANGEBILLADDRESS");
		callScript("scripts.outboudsapactions.withoutingestion.bnwallet.TC11_ROUPDATEPAYMENTMETHOD");*/


	}
}

