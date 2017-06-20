package scripts.atlasProcessingOrders.physicalpurchase;
import resources.scripts.atlasProcessingOrders.physicalpurchase.ShellScriptsHelper;
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

import framework.AtlasScriptExecution;
/**
 * Description   : Functional Test Script
 * @author zsadeque
 */
public class ShellScripts extends ShellScriptsHelper
{
	/**
	 * Script Name   : <b>ShellScripts</b>
	 * Generated     : <b>Apr 11, 2014 10:40:03 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/04/11
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		timerStart("physicalpurchase");
		
		/*
		 * Pre-Req to execute the scripts
		 */
		callScript("scripts.atlasProcessingOrders.physicalpurchase.ExcelDataReset");

		/*
		 * Scripts
		 */
		AtlasScriptExecution.executeWitoutDBValidation();
		callScript("scripts.atlasProcessingOrders.physicalpurchase.TC01_NetworkDealerItemPurchase");
		callScript("scripts.atlasProcessingOrders.physicalpurchase.TC02_BookPurchase");
		callScript("scripts.atlasProcessingOrders.physicalpurchase.TC03_NewMembershipPurchase");
		callScript("scripts.atlasProcessingOrders.physicalpurchase.TC04_UsedItemPurchase");
		callScript("scripts.atlasProcessingOrders.physicalpurchase.TC05_PurchaseWShippingPromotion");
		
		/*
		 * DB Validation
		 */
		AtlasScriptExecution.executeDBValidationOnly();
		callScript("scripts.atlasProcessingOrders.physicalpurchase.TC01_NetworkDealerItemPurchase");
		callScript("scripts.atlasProcessingOrders.physicalpurchase.TC02_BookPurchase");
		callScript("scripts.atlasProcessingOrders.physicalpurchase.TC03_NewMembershipPurchase");
		callScript("scripts.atlasProcessingOrders.physicalpurchase.TC04_UsedItemPurchase");
		callScript("scripts.atlasProcessingOrders.physicalpurchase.TC05_PurchaseWShippingPromotion");
		/*
		 * Validate Order Reached in SAP
		 */
		
		callScript("scripts.atlasProcessingOrders.physicalpurchase.ValidateOrdersReachedSAP");
		timerStop("physicalpurchase");

	}
}

