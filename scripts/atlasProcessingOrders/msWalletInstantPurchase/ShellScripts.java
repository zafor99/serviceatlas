package scripts.atlasProcessingOrders.msWalletInstantPurchase;
import resources.scripts.atlasProcessingOrders.msWalletInstantPurchase.ShellScriptsHelper;
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
	 * Generated     : <b>Mar 27, 2014 3:13:33 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/03/27
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		timerStart("msWalletInstantPurchase");
		/*
		 * Pre-Req to execute the scripts
		 */
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.ExcelDataReset");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.LinkAccount");
		
		/*
		 *
		 * Scripts
		 */
		 
		 
		AtlasScriptExecution.executeWitoutDBValidation();
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC01_eBookAgency");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC02_freeEBook");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC03_eBookPubIt");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC04_eMagazineSingleIssue");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC05_eBookNonAgency");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC06_ebookPreOrder");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC07_eMagazineSubscriptionAgency");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC08_ebookKids");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC09_ebookComics");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC10_eSubscriptionNonAgency");
		
		/*
		 * DB Validation
		 */
		AtlasScriptExecution.executeDBValidationOnly();
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC01_eBookAgency");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC02_freeEBook");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC03_eBookPubIt");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC04_eMagazineSingleIssue");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC05_eBookNonAgency");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC06_ebookPreOrder");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC07_eMagazineSubscriptionAgency");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC08_ebookKids");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC09_ebookComics");
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.TC10_eSubscriptionNonAgency");
		 //Validation in SAP
		 
		callScript("scripts.atlasProcessingOrders.msWalletInstantPurchase.ValidateOrdersReachedSAP");
		timerStop("msWalletInstantPurchase");
	}
}

