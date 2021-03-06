// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.scripts_integration.Domestic.eSubscriptionPurchase.eSubscriptionBNWallet;
import framework.AtlasScriptbase;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.vp.IFtVerificationPoint;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Script Name   : <b>TC01_eSubPurchaseUS</b><br>
 * Generated     : <b>2014/05/16 12:36:32 PM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 build 7601 Service Pack 1 <br>
 * 
 * @since  May 16, 2014
 * @author zsadeque
 */
public abstract class TC01_eSubPurchaseUSHelper extends framework.AtlasScriptbase
{
	/**
	 * Locate and return the verification point BNSubcriptionRelCreated object in the SUT.
	 */
	protected IFtVerificationPoint BNSubcriptionRelCreatedVP() 
	{
		return vp("BNSubcriptionRelCreated");
	}
	protected IFtVerificationPoint BNSubcriptionRelCreatedVP(TestObject anchor)
	{
		return vp("BNSubcriptionRelCreated", anchor);
	}
	
	/**
	 * Locate and return the verification point InvoicePosted object in the SUT.
	 */
	protected IFtVerificationPoint InvoicePostedVP() 
	{
		return vp("InvoicePosted");
	}
	protected IFtVerificationPoint InvoicePostedVP(TestObject anchor)
	{
		return vp("InvoicePosted", anchor);
	}
	
	/**
	 * Locate and return the verification point IPResponseVP object in the SUT.
	 */
	protected IFtVerificationPoint IPResponseVPVP() 
	{
		return vp("IPResponseVP");
	}
	protected IFtVerificationPoint IPResponseVPVP(TestObject anchor)
	{
		return vp("IPResponseVP", anchor);
	}
	
	/**
	 * Locate and return the verification point NetPriceAndTax object in the SUT.
	 */
	protected IFtVerificationPoint NetPriceAndTaxVP() 
	{
		return vp("NetPriceAndTax");
	}
	protected IFtVerificationPoint NetPriceAndTaxVP(TestObject anchor)
	{
		return vp("NetPriceAndTax", anchor);
	}
	
	/**
	 * Locate and return the verification point OrderProcessed object in the SUT.
	 */
	protected IFtVerificationPoint OrderProcessedVP() 
	{
		return vp("OrderProcessed");
	}
	protected IFtVerificationPoint OrderProcessedVP(TestObject anchor)
	{
		return vp("OrderProcessed", anchor);
	}
	
	/**
	 * Locate and return the verification point PartnersTable object in the SUT.
	 */
	protected IFtVerificationPoint PartnersTableVP() 
	{
		return vp("PartnersTable");
	}
	protected IFtVerificationPoint PartnersTableVP(TestObject anchor)
	{
		return vp("PartnersTable", anchor);
	}
	
	/**
	 * Locate and return the verification point POCreated object in the SUT.
	 */
	protected IFtVerificationPoint POCreatedVP() 
	{
		return vp("POCreated");
	}
	protected IFtVerificationPoint POCreatedVP(TestObject anchor)
	{
		return vp("POCreated", anchor);
	}
	
	/**
	 * Locate and return the verification point POCreation object in the SUT.
	 */
	protected IFtVerificationPoint POCreationVP() 
	{
		return vp("POCreation");
	}
	protected IFtVerificationPoint POCreationVP(TestObject anchor)
	{
		return vp("POCreation", anchor);
	}
	
	/**
	 * Locate and return the verification point SubsSaved object in the SUT.
	 */
	protected IFtVerificationPoint SubsSavedVP() 
	{
		return vp("SubsSaved");
	}
	protected IFtVerificationPoint SubsSavedVP(TestObject anchor)
	{
		return vp("SubsSaved", anchor);
	}
	
	

	protected TC01_eSubPurchaseUSHelper()
	{
		setScriptName("scripts_integration.Domestic.eSubscriptionPurchase.eSubscriptionBNWallet.TC01_eSubPurchaseUS");
	}
	
}

