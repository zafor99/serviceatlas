package scripts.outboudsapactions.withoutingestion.giftcardnCC;
import resources.scripts.outboudsapactions.withoutingestion.giftcardnCC.ShellScriptsHelper;
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
	 * Generated     : <b>Jul 21, 2014 10:51:58 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/07/21
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		callScript("scripts.outboudsapactions.withoutingestion.giftcardnCC.ExcelDataReset");
		callScript("scripts.outboudsapactions.withoutingestion.giftcardnCC.TC01_Cancel");
		callScript("scripts.outboudsapactions.withoutingestion.giftcardnCC.TC02_03_OpenAndClose");

	}
}

