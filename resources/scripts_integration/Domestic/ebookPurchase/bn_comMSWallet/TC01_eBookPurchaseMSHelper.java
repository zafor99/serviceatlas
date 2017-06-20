// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.scripts_integration.Domestic.ebookPurchase.bn_comMSWallet;
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
 * Script Name   : <b>TC01_eBookPurchaseMS</b><br>
 * Generated     : <b>2014/06/10 2:56:15 PM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 build 7601 Service Pack 1 <br>
 * 
 * @since  June 10, 2014
 * @author zsadeque
 */
public abstract class TC01_eBookPurchaseMSHelper extends framework.AtlasScriptbase
{
	/**
	 * Locate and return the verification point DigitalLocker object in the SUT.
	 */
	protected IFtVerificationPoint DigitalLockerVP() 
	{
		return vp("DigitalLocker");
	}
	protected IFtVerificationPoint DigitalLockerVP(TestObject anchor)
	{
		return vp("DigitalLocker", anchor);
	}
	
	/**
	 * Locate and return the verification point FinalDocFlow object in the SUT.
	 */
	protected IFtVerificationPoint FinalDocFlowVP() 
	{
		return vp("FinalDocFlow");
	}
	protected IFtVerificationPoint FinalDocFlowVP(TestObject anchor)
	{
		return vp("FinalDocFlow", anchor);
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
	 * Locate and return the verification point MSW object in the SUT.
	 */
	protected IFtVerificationPoint MSWVP() 
	{
		return vp("MSW");
	}
	protected IFtVerificationPoint MSWVP(TestObject anchor)
	{
		return vp("MSW", anchor);
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
	 * Locate and return the verification point OrderClosed object in the SUT.
	 */
	protected IFtVerificationPoint OrderClosedVP() 
	{
		return vp("OrderClosed");
	}
	protected IFtVerificationPoint OrderClosedVP(TestObject anchor)
	{
		return vp("OrderClosed", anchor);
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
	 * Locate and return the verification point PartnersTab object in the SUT.
	 */
	protected IFtVerificationPoint PartnersTabVP() 
	{
		return vp("PartnersTab");
	}
	protected IFtVerificationPoint PartnersTabVP(TestObject anchor)
	{
		return vp("PartnersTab", anchor);
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
	 * Locate and return the verification point PayAuthDB_ActivityLog object in the SUT.
	 */
	protected IFtVerificationPoint PayAuthDB_ActivityLogVP() 
	{
		return vp("PayAuthDB_ActivityLog");
	}
	protected IFtVerificationPoint PayAuthDB_ActivityLogVP(TestObject anchor)
	{
		return vp("PayAuthDB_ActivityLog", anchor);
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
	 * Locate and return the verification point SalesData object in the SUT.
	 */
	protected IFtVerificationPoint SalesDataVP() 
	{
		return vp("SalesData");
	}
	protected IFtVerificationPoint SalesDataVP(TestObject anchor)
	{
		return vp("SalesData", anchor);
	}
	
	

	protected TC01_eBookPurchaseMSHelper()
	{
		setScriptName("scripts_integration.Domestic.ebookPurchase.bn_comMSWallet.TC01_eBookPurchaseMS");
	}
	
}

