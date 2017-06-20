package scripts.atlasProcessingOrders.bnWalletInstantPurchase;
import resources.scripts.atlasProcessingOrders.bnWalletInstantPurchase.ShellScriptsHelper;
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
	 * Generated     : <b>Mar 24, 2014 2:50:03 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2014/03/24
	 * @author zsadeque
	 */
	public void testMain(Object[] args) 
	{
		timerStart("bnWalletInstantPurchase");
		/*
		 * Pre-Req to execute the scripts
		 */
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.ExcelDataReset");
		
		/*
		 * Scripts
		 */
		AtlasScriptExecution.executeWitoutDBValidation();
		
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC01_eBookAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC02_ebookKids");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC03_ebookPreOrder");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC04_eBookPubIt");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC06_eMagazineSingleIssueAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC07_eMagazineSIP");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC08_eMagazineSubscriptionAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC09_SingleIssue_ENewspaper");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC10_eNewspaperSubLibreAggregator");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC11_eNewspaperSubscriptionAgency");
		/*
		 * Text book is ignored as per Jane
		 */
		//callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC15_eTextbookRental");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC16_freeEBook");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC17_eBookNonAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC18_NookApp");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC19_NookVideo");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC20_NookVideoRental");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC21_eMagazineSubscriptionAnnual");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC25_SparkNotes");

		
		/*
		 * DB Validation
		 */
		AtlasScriptExecution.executeDBValidationOnly();
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC01_eBookAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC02_ebookKids");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC03_ebookPreOrder");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC04_eBookPubIt");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC06_eMagazineSingleIssueAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC07_eMagazineSIP");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC08_eMagazineSubscriptionAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC09_SingleIssue_ENewspaper");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC10_eNewspaperSubLibreAggregator");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC11_eNewspaperSubscriptionAgency");
//		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC14_eTextbookPurchase");
//		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC15_eTextbookRental");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC16_freeEBook");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC17_eBookNonAgency");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC18_NookApp");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC19_NookVideo");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC20_NookVideoRental");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC21_eMagazineSubscriptionAnnual");
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.TC25_SparkNotes");


		
		/*
		 * Validation in SAP
		 */
		callScript("scripts.atlasProcessingOrders.bnWalletInstantPurchase.ValidateOrdersReachedSAP");
		timerStop("bnWalletInstantPurchase");
	}
}

