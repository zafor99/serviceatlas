package scripts.outboudsapactions.outboundInvoice.DIGSHIPCONFIRM;
import resources.scripts.outboudsapactions.outboundInvoice.DIGSHIPCONFIRM.ShellScriptsHelper;
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
	 * Generated     : <b>Mar 20, 2015 2:35:01 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2015/03/20
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
	//	callScript("scripts.outboudsapactions.outboundInvoice.DIGSHIPCONFIRM.ExcelDataReset");
		
		//need to rerun this script
	//	callScript("scripts.outboudsapactions.outboundInvoice.DIGSHIPCONFIRM.TC02_DigitalSubscription");
		callScript("scripts.outboudsapactions.outboundInvoice.DIGSHIPCONFIRM.TC03_eBook");

		callScript("scripts.outboudsapactions.outboundInvoice.DIGSHIPCONFIRM.TC06_eSubSingleIssue");
		callScript("scripts.outboudsapactions.outboundInvoice.DIGSHIPCONFIRM.TC07_eBookAgencyModel");
	//	callScript("scripts.outboudsapactions.outboundInvoice.DIGSHIPCONFIRM.TC05_videoRental");

	}
}

