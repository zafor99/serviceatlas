package sap_scripts.physical;
import resources.sap_scripts.physical.PhysicalPurchaseShellHelper;
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
public class PhysicalPurchaseShell extends PhysicalPurchaseShellHelper
{
	/**
	 * Script Name   : <b>PhysicalPurchaseShell</b>
	 * Generated     : <b>Sep 30, 2013 11:33:42 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2013/09/30
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		timerStart("sap");
		callScript("sap_scripts.physical.TC01_BookPurchaseWValidCC");
		callScript("sap_scripts.physical.TC02_BookPurchaseWExpressShipping");
		callScript("sap_scripts.physical.TC03_DVDPurchaseWStdShipping");
		callScript("sap_scripts.physical.TC04_DVDPurchaseWExpShipping");
		callScript("sap_scripts.physical.TC05_DevicePurchaseWExpShipping");
		//callScript("sap_scripts.physical.TC06_PurchaseNewMembership");
		timerStop("sap");
	}
}

