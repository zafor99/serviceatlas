package scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization;
import resources.scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.ShellScriptsHelper;
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
	 * Generated     : <b>Apr 11, 2014 10:16:50 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/04/11
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		timerStart("bnWalletInstantPurchaseWTokenization");
		/*
		 * Pre-Req to execute the scripts
		 */
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.ExcelDataReset");
		/*
		 * Scripts
		 */
		AtlasScriptExecution.executeWitoutDBValidation();
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC01_eBookAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC02_ebookKids");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC03_ebookPreOrder");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC04_eBookPubIt");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC06_eMagazineSingleIssueAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC07_eMagazineSIP");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC08_eMagazineSubscriptionAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC09_SingleIssue_ENewspaper");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC10_eNewspaperSubLibreAggregator");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC11_eNewspaperSubscriptionAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC14_eTextbookPurchase");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC15_eTextbookRental");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC16_freeEBook");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC17_eBookNonAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC18_NookApp");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC19_NookVideo");

		
		/*
		 * DB Validation
		 */
		AtlasScriptExecution.executeDBValidationOnly();
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC01_eBookAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC02_ebookKids");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC03_ebookPreOrder");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC04_eBookPubIt");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC06_eMagazineSingleIssueAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC07_eMagazineSIP");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC08_eMagazineSubscriptionAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC09_SingleIssue_ENewspaper");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC10_eNewspaperSubLibreAggregator");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC11_eNewspaperSubscriptionAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC14_eTextbookPurchase");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC15_eTextbookRental");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC16_freeEBook");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC17_eBookNonAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC18_NookApp");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC19_NookVideo");
		
		/*
		 *Validate Order Reached in SAP 
		 */
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.ValidateOrdersReachedSAP");

		timerStop("bnWalletInstantPurchaseWTokenization");
		
	}
}

