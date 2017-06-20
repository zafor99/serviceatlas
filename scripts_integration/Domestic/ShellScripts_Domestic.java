package scripts_integration.Domestic;
import resources.scripts_integration.Domestic.ShellScripts_DomesticHelper;
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
public class ShellScripts_Domestic extends ShellScripts_DomesticHelper
{
	/**
	 * Script Name   : <b>ShellScripts_Domestic</b>
	 * Generated     : <b>May 21, 2014 11:14:08 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/05/21
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		timerStart("ShellScripts_Domestic");

		try {
			callScript("scripts_integration.Domestic.ebookPurchase.bn_comBNWallet.TC01_eBookPurchaseBN");
			callScript("scripts_integration.Domestic.ebookPurchase.bn_comMSWallet.TC01_eBookPurchaseMS");
			callScript("scripts_integration.Domestic.eSubscriptionPurchase.eSubscriptionBNWallet.TC01_eSubPurchaseUS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		timerStop("ShellScripts_Domestic");		

	}
}

