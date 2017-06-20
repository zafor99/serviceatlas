// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization;
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
 * Script Name   : <b>TC15_eTextbookRental</b><br>
 * Generated     : <b>2014/06/04 10:19:58 AM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 build 7601 Service Pack 1 <br>
 * 
 * @since  June 04, 2014
 * @author zsadeque
 */
public abstract class TC15_eTextbookRentalHelper extends framework.AtlasScriptbase
{
	/**
	 * Locate and return the verification point BNIncDB_CustomerTable object in the SUT.
	 */
	protected IFtVerificationPoint BNIncDB_CustomerTableVP() 
	{
		return vp("BNIncDB_CustomerTable");
	}
	protected IFtVerificationPoint BNIncDB_CustomerTableVP(TestObject anchor)
	{
		return vp("BNIncDB_CustomerTable", anchor);
	}
	
	/**
	 * Locate and return the verification point BNIncDB_OrderCCNumberTable object in the SUT.
	 */
	protected IFtVerificationPoint BNIncDB_OrderCCNumberTableVP() 
	{
		return vp("BNIncDB_OrderCCNumberTable");
	}
	protected IFtVerificationPoint BNIncDB_OrderCCNumberTableVP(TestObject anchor)
	{
		return vp("BNIncDB_OrderCCNumberTable", anchor);
	}
	
	/**
	 * Locate and return the verification point BNIncDB_OrderDetailActivityTable object in the SUT.
	 */
	protected IFtVerificationPoint BNIncDB_OrderDetailActivityTableVP() 
	{
		return vp("BNIncDB_OrderDetailActivityTable");
	}
	protected IFtVerificationPoint BNIncDB_OrderDetailActivityTableVP(TestObject anchor)
	{
		return vp("BNIncDB_OrderDetailActivityTable", anchor);
	}
	
	/**
	 * Locate and return the verification point BNIncDB_OrderDetailTable object in the SUT.
	 */
	protected IFtVerificationPoint BNIncDB_OrderDetailTableVP() 
	{
		return vp("BNIncDB_OrderDetailTable");
	}
	protected IFtVerificationPoint BNIncDB_OrderDetailTableVP(TestObject anchor)
	{
		return vp("BNIncDB_OrderDetailTable", anchor);
	}
	
	/**
	 * Locate and return the verification point BNIncDB_OrderHeaderTable object in the SUT.
	 */
	protected IFtVerificationPoint BNIncDB_OrderHeaderTableVP() 
	{
		return vp("BNIncDB_OrderHeaderTable");
	}
	protected IFtVerificationPoint BNIncDB_OrderHeaderTableVP(TestObject anchor)
	{
		return vp("BNIncDB_OrderHeaderTable", anchor);
	}
	
	/**
	 * Locate and return the verification point DWDB_CustomerTable object in the SUT.
	 */
	protected IFtVerificationPoint DWDB_CustomerTableVP() 
	{
		return vp("DWDB_CustomerTable");
	}
	protected IFtVerificationPoint DWDB_CustomerTableVP(TestObject anchor)
	{
		return vp("DWDB_CustomerTable", anchor);
	}
	
	/**
	 * Locate and return the verification point DWDB_OrderCCNumberTable object in the SUT.
	 */
	protected IFtVerificationPoint DWDB_OrderCCNumberTableVP() 
	{
		return vp("DWDB_OrderCCNumberTable");
	}
	protected IFtVerificationPoint DWDB_OrderCCNumberTableVP(TestObject anchor)
	{
		return vp("DWDB_OrderCCNumberTable", anchor);
	}
	
	/**
	 * Locate and return the verification point DWDB_OrderDetailActivityTable object in the SUT.
	 */
	protected IFtVerificationPoint DWDB_OrderDetailActivityTableVP() 
	{
		return vp("DWDB_OrderDetailActivityTable");
	}
	protected IFtVerificationPoint DWDB_OrderDetailActivityTableVP(TestObject anchor)
	{
		return vp("DWDB_OrderDetailActivityTable", anchor);
	}
	
	/**
	 * Locate and return the verification point DWDB_OrderDetailTable object in the SUT.
	 */
	protected IFtVerificationPoint DWDB_OrderDetailTableVP() 
	{
		return vp("DWDB_OrderDetailTable");
	}
	protected IFtVerificationPoint DWDB_OrderDetailTableVP(TestObject anchor)
	{
		return vp("DWDB_OrderDetailTable", anchor);
	}
	
	/**
	 * Locate and return the verification point DWDB_OrderHeaderTable object in the SUT.
	 */
	protected IFtVerificationPoint DWDB_OrderHeaderTableVP() 
	{
		return vp("DWDB_OrderHeaderTable");
	}
	protected IFtVerificationPoint DWDB_OrderHeaderTableVP(TestObject anchor)
	{
		return vp("DWDB_OrderHeaderTable", anchor);
	}
	
	/**
	 * Locate and return the verification point OrderDetails object in the SUT.
	 */
	protected IFtVerificationPoint OrderDetailsVP() 
	{
		return vp("OrderDetails");
	}
	protected IFtVerificationPoint OrderDetailsVP(TestObject anchor)
	{
		return vp("OrderDetails", anchor);
	}
	
	/**
	 * Locate and return the verification point OrderDetailsInSAP object in the SUT.
	 */
	protected IFtVerificationPoint OrderDetailsInSAPVP() 
	{
		return vp("OrderDetailsInSAP");
	}
	protected IFtVerificationPoint OrderDetailsInSAPVP(TestObject anchor)
	{
		return vp("OrderDetailsInSAP", anchor);
	}
	
	/**
	 * Locate and return the verification point OSDB_ItemTable object in the SUT.
	 */
	protected IFtVerificationPoint OSDB_ItemTableVP() 
	{
		return vp("OSDB_ItemTable");
	}
	protected IFtVerificationPoint OSDB_ItemTableVP(TestObject anchor)
	{
		return vp("OSDB_ItemTable", anchor);
	}
	
	/**
	 * Locate and return the verification point OSDB_OrderHeaderTable object in the SUT.
	 */
	protected IFtVerificationPoint OSDB_OrderHeaderTableVP() 
	{
		return vp("OSDB_OrderHeaderTable");
	}
	protected IFtVerificationPoint OSDB_OrderHeaderTableVP(TestObject anchor)
	{
		return vp("OSDB_OrderHeaderTable", anchor);
	}
	
	/**
	 * Locate and return the verification point OSDB_OrderShipmentTable object in the SUT.
	 */
	protected IFtVerificationPoint OSDB_OrderShipmentTableVP() 
	{
		return vp("OSDB_OrderShipmentTable");
	}
	protected IFtVerificationPoint OSDB_OrderShipmentTableVP(TestObject anchor)
	{
		return vp("OSDB_OrderShipmentTable", anchor);
	}
	
	/**
	 * Locate and return the verification point SalesRankDB_OrderDetailTable_old object in the SUT.
	 */
	protected IFtVerificationPoint SalesRankDB_OrderDetailTable_oldVP() 
	{
		return vp("SalesRankDB_OrderDetailTable_old");
	}
	protected IFtVerificationPoint SalesRankDB_OrderDetailTable_oldVP(TestObject anchor)
	{
		return vp("SalesRankDB_OrderDetailTable_old", anchor);
	}
	
	/**
	 * Locate and return the verification point SalesRankDB_OrderHeaderTable_old object in the SUT.
	 */
	protected IFtVerificationPoint SalesRankDB_OrderHeaderTable_oldVP() 
	{
		return vp("SalesRankDB_OrderHeaderTable_old");
	}
	protected IFtVerificationPoint SalesRankDB_OrderHeaderTable_oldVP(TestObject anchor)
	{
		return vp("SalesRankDB_OrderHeaderTable_old", anchor);
	}
	
	

	protected TC15_eTextbookRentalHelper()
	{
		setScriptName("scripts.atlasProcessingOrders.bnWalletInstantPurchaseWTokenization.TC15_eTextbookRental");
	}
	
}

