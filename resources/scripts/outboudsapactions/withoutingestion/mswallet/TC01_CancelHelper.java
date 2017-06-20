// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.scripts.outboudsapactions.withoutingestion.mswallet;
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
 * Script Name   : <b>TC01_Cancel</b><br>
 * Generated     : <b>2014/07/17 2:25:11 PM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 build 7601 Service Pack 1 <br>
 * 
 * @since  July 17, 2014
 * @author zsadeque
 */
public abstract class TC01_CancelHelper extends framework.AtlasScriptbase
{
	/**
	 * Locate and return the verification point BNIncDB_Customer_Closed object in the SUT.
	 */
	protected IFtVerificationPoint BNIncDB_Customer_ClosedVP() 
	{
		return vp("BNIncDB_Customer_Closed");
	}
	protected IFtVerificationPoint BNIncDB_Customer_ClosedVP(TestObject anchor)
	{
		return vp("BNIncDB_Customer_Closed", anchor);
	}
	
	/**
	 * Locate and return the verification point BNIncDB_Customer_Open object in the SUT.
	 */
	protected IFtVerificationPoint BNIncDB_Customer_OpenVP() 
	{
		return vp("BNIncDB_Customer_Open");
	}
	protected IFtVerificationPoint BNIncDB_Customer_OpenVP(TestObject anchor)
	{
		return vp("BNIncDB_Customer_Open", anchor);
	}
	
	/**
	 * Locate and return the verification point BNIncDB_OrderCCNumber_Closed object in the SUT.
	 */
	protected IFtVerificationPoint BNIncDB_OrderCCNumber_ClosedVP() 
	{
		return vp("BNIncDB_OrderCCNumber_Closed");
	}
	protected IFtVerificationPoint BNIncDB_OrderCCNumber_ClosedVP(TestObject anchor)
	{
		return vp("BNIncDB_OrderCCNumber_Closed", anchor);
	}
	
	/**
	 * Locate and return the verification point BNIncDB_OrderCCNumber_Open object in the SUT.
	 */
	protected IFtVerificationPoint BNIncDB_OrderCCNumber_OpenVP() 
	{
		return vp("BNIncDB_OrderCCNumber_Open");
	}
	protected IFtVerificationPoint BNIncDB_OrderCCNumber_OpenVP(TestObject anchor)
	{
		return vp("BNIncDB_OrderCCNumber_Open", anchor);
	}
	
	/**
	 * Locate and return the verification point BNIncDB_OrderDetail_Closed object in the SUT.
	 */
	protected IFtVerificationPoint BNIncDB_OrderDetail_ClosedVP() 
	{
		return vp("BNIncDB_OrderDetail_Closed");
	}
	protected IFtVerificationPoint BNIncDB_OrderDetail_ClosedVP(TestObject anchor)
	{
		return vp("BNIncDB_OrderDetail_Closed", anchor);
	}
	
	/**
	 * Locate and return the verification point BNIncDB_OrderDetail_Open object in the SUT.
	 */
	protected IFtVerificationPoint BNIncDB_OrderDetail_OpenVP() 
	{
		return vp("BNIncDB_OrderDetail_Open");
	}
	protected IFtVerificationPoint BNIncDB_OrderDetail_OpenVP(TestObject anchor)
	{
		return vp("BNIncDB_OrderDetail_Open", anchor);
	}
	
	/**
	 * Locate and return the verification point BNIncDB_OrderHeader_Closed object in the SUT.
	 */
	protected IFtVerificationPoint BNIncDB_OrderHeader_ClosedVP() 
	{
		return vp("BNIncDB_OrderHeader_Closed");
	}
	protected IFtVerificationPoint BNIncDB_OrderHeader_ClosedVP(TestObject anchor)
	{
		return vp("BNIncDB_OrderHeader_Closed", anchor);
	}
	
	/**
	 * Locate and return the verification point BNIncDB_OrderHeader_Open object in the SUT.
	 */
	protected IFtVerificationPoint BNIncDB_OrderHeader_OpenVP() 
	{
		return vp("BNIncDB_OrderHeader_Open");
	}
	protected IFtVerificationPoint BNIncDB_OrderHeader_OpenVP(TestObject anchor)
	{
		return vp("BNIncDB_OrderHeader_Open", anchor);
	}
	
	/**
	 * Locate and return the verification point DWDB_Customer_Closed object in the SUT.
	 */
	protected IFtVerificationPoint DWDB_Customer_ClosedVP() 
	{
		return vp("DWDB_Customer_Closed");
	}
	protected IFtVerificationPoint DWDB_Customer_ClosedVP(TestObject anchor)
	{
		return vp("DWDB_Customer_Closed", anchor);
	}
	
	/**
	 * Locate and return the verification point DWDB_Customer_Open object in the SUT.
	 */
	protected IFtVerificationPoint DWDB_Customer_OpenVP() 
	{
		return vp("DWDB_Customer_Open");
	}
	protected IFtVerificationPoint DWDB_Customer_OpenVP(TestObject anchor)
	{
		return vp("DWDB_Customer_Open", anchor);
	}
	
	/**
	 * Locate and return the verification point DWDB_OrderCCNumber_Closed object in the SUT.
	 */
	protected IFtVerificationPoint DWDB_OrderCCNumber_ClosedVP() 
	{
		return vp("DWDB_OrderCCNumber_Closed");
	}
	protected IFtVerificationPoint DWDB_OrderCCNumber_ClosedVP(TestObject anchor)
	{
		return vp("DWDB_OrderCCNumber_Closed", anchor);
	}
	
	/**
	 * Locate and return the verification point DWDB_OrderCCNumber_Open object in the SUT.
	 */
	protected IFtVerificationPoint DWDB_OrderCCNumber_OpenVP() 
	{
		return vp("DWDB_OrderCCNumber_Open");
	}
	protected IFtVerificationPoint DWDB_OrderCCNumber_OpenVP(TestObject anchor)
	{
		return vp("DWDB_OrderCCNumber_Open", anchor);
	}
	
	/**
	 * Locate and return the verification point DWDB_OrderDetailActivity_Open object in the SUT.
	 */
	protected IFtVerificationPoint DWDB_OrderDetailActivity_OpenVP() 
	{
		return vp("DWDB_OrderDetailActivity_Open");
	}
	protected IFtVerificationPoint DWDB_OrderDetailActivity_OpenVP(TestObject anchor)
	{
		return vp("DWDB_OrderDetailActivity_Open", anchor);
	}
	
	/**
	 * Locate and return the verification point DWDB_OrderDetail_Closed object in the SUT.
	 */
	protected IFtVerificationPoint DWDB_OrderDetail_ClosedVP() 
	{
		return vp("DWDB_OrderDetail_Closed");
	}
	protected IFtVerificationPoint DWDB_OrderDetail_ClosedVP(TestObject anchor)
	{
		return vp("DWDB_OrderDetail_Closed", anchor);
	}
	
	/**
	 * Locate and return the verification point DWDB_OrderDetail_Open object in the SUT.
	 */
	protected IFtVerificationPoint DWDB_OrderDetail_OpenVP() 
	{
		return vp("DWDB_OrderDetail_Open");
	}
	protected IFtVerificationPoint DWDB_OrderDetail_OpenVP(TestObject anchor)
	{
		return vp("DWDB_OrderDetail_Open", anchor);
	}
	
	/**
	 * Locate and return the verification point DWDB_OrderHeader_Closed object in the SUT.
	 */
	protected IFtVerificationPoint DWDB_OrderHeader_ClosedVP() 
	{
		return vp("DWDB_OrderHeader_Closed");
	}
	protected IFtVerificationPoint DWDB_OrderHeader_ClosedVP(TestObject anchor)
	{
		return vp("DWDB_OrderHeader_Closed", anchor);
	}
	
	/**
	 * Locate and return the verification point DWDB_OrderHeader_Open object in the SUT.
	 */
	protected IFtVerificationPoint DWDB_OrderHeader_OpenVP() 
	{
		return vp("DWDB_OrderHeader_Open");
	}
	protected IFtVerificationPoint DWDB_OrderHeader_OpenVP(TestObject anchor)
	{
		return vp("DWDB_OrderHeader_Open", anchor);
	}
	
	/**
	 * Locate and return the verification point IPStatus object in the SUT.
	 */
	protected IFtVerificationPoint IPStatusVP() 
	{
		return vp("IPStatus");
	}
	protected IFtVerificationPoint IPStatusVP(TestObject anchor)
	{
		return vp("IPStatus", anchor);
	}
	
	/**
	 * Locate and return the verification point NoError object in the SUT.
	 */
	protected IFtVerificationPoint NoErrorVP() 
	{
		return vp("NoError");
	}
	protected IFtVerificationPoint NoErrorVP(TestObject anchor)
	{
		return vp("NoError", anchor);
	}
	
	/**
	 * Locate and return the verification point OSDB_Item_Closed object in the SUT.
	 */
	protected IFtVerificationPoint OSDB_Item_ClosedVP() 
	{
		return vp("OSDB_Item_Closed");
	}
	protected IFtVerificationPoint OSDB_Item_ClosedVP(TestObject anchor)
	{
		return vp("OSDB_Item_Closed", anchor);
	}
	
	/**
	 * Locate and return the verification point OSDB_Item_Open object in the SUT.
	 */
	protected IFtVerificationPoint OSDB_Item_OpenVP() 
	{
		return vp("OSDB_Item_Open");
	}
	protected IFtVerificationPoint OSDB_Item_OpenVP(TestObject anchor)
	{
		return vp("OSDB_Item_Open", anchor);
	}
	
	/**
	 * Locate and return the verification point OSDB_OrderHeader_Closed object in the SUT.
	 */
	protected IFtVerificationPoint OSDB_OrderHeader_ClosedVP() 
	{
		return vp("OSDB_OrderHeader_Closed");
	}
	protected IFtVerificationPoint OSDB_OrderHeader_ClosedVP(TestObject anchor)
	{
		return vp("OSDB_OrderHeader_Closed", anchor);
	}
	
	/**
	 * Locate and return the verification point OSDB_OrderHeader_Open object in the SUT.
	 */
	protected IFtVerificationPoint OSDB_OrderHeader_OpenVP() 
	{
		return vp("OSDB_OrderHeader_Open");
	}
	protected IFtVerificationPoint OSDB_OrderHeader_OpenVP(TestObject anchor)
	{
		return vp("OSDB_OrderHeader_Open", anchor);
	}
	
	/**
	 * Locate and return the verification point OSDB_OrderShipment_Closed object in the SUT.
	 */
	protected IFtVerificationPoint OSDB_OrderShipment_ClosedVP() 
	{
		return vp("OSDB_OrderShipment_Closed");
	}
	protected IFtVerificationPoint OSDB_OrderShipment_ClosedVP(TestObject anchor)
	{
		return vp("OSDB_OrderShipment_Closed", anchor);
	}
	
	/**
	 * Locate and return the verification point OSDB_OrderShipment_Open object in the SUT.
	 */
	protected IFtVerificationPoint OSDB_OrderShipment_OpenVP() 
	{
		return vp("OSDB_OrderShipment_Open");
	}
	protected IFtVerificationPoint OSDB_OrderShipment_OpenVP(TestObject anchor)
	{
		return vp("OSDB_OrderShipment_Open", anchor);
	}
	
	/**
	 * Locate and return the verification point SalesRankDB_OrderDetail_Closed object in the SUT.
	 */
	protected IFtVerificationPoint SalesRankDB_OrderDetail_ClosedVP() 
	{
		return vp("SalesRankDB_OrderDetail_Closed");
	}
	protected IFtVerificationPoint SalesRankDB_OrderDetail_ClosedVP(TestObject anchor)
	{
		return vp("SalesRankDB_OrderDetail_Closed", anchor);
	}
	
	/**
	 * Locate and return the verification point SalesRankDB_OrderDetail_Open object in the SUT.
	 */
	protected IFtVerificationPoint SalesRankDB_OrderDetail_OpenVP() 
	{
		return vp("SalesRankDB_OrderDetail_Open");
	}
	protected IFtVerificationPoint SalesRankDB_OrderDetail_OpenVP(TestObject anchor)
	{
		return vp("SalesRankDB_OrderDetail_Open", anchor);
	}
	
	/**
	 * Locate and return the verification point SalesRankDB_OrderHeader_Closed object in the SUT.
	 */
	protected IFtVerificationPoint SalesRankDB_OrderHeader_ClosedVP() 
	{
		return vp("SalesRankDB_OrderHeader_Closed");
	}
	protected IFtVerificationPoint SalesRankDB_OrderHeader_ClosedVP(TestObject anchor)
	{
		return vp("SalesRankDB_OrderHeader_Closed", anchor);
	}
	
	/**
	 * Locate and return the verification point SalesRankDB_OrderHeader_Open object in the SUT.
	 */
	protected IFtVerificationPoint SalesRankDB_OrderHeader_OpenVP() 
	{
		return vp("SalesRankDB_OrderHeader_Open");
	}
	protected IFtVerificationPoint SalesRankDB_OrderHeader_OpenVP(TestObject anchor)
	{
		return vp("SalesRankDB_OrderHeader_Open", anchor);
	}
	
	/**
	 * Locate and return the verification point SubcCanceled object in the SUT.
	 */
	protected IFtVerificationPoint SubcCanceledVP() 
	{
		return vp("SubcCanceled");
	}
	protected IFtVerificationPoint SubcCanceledVP(TestObject anchor)
	{
		return vp("SubcCanceled", anchor);
	}
	
	/**
	 * Locate and return the verification point Z1116_Status object in the SUT.
	 */
	protected IFtVerificationPoint Z1116_StatusVP() 
	{
		return vp("Z1116_Status");
	}
	protected IFtVerificationPoint Z1116_StatusVP(TestObject anchor)
	{
		return vp("Z1116_Status", anchor);
	}
	
	/**
	 * Locate and return the verification point Z112_Status object in the SUT.
	 */
	protected IFtVerificationPoint Z112_StatusVP() 
	{
		return vp("Z112_Status");
	}
	protected IFtVerificationPoint Z112_StatusVP(TestObject anchor)
	{
		return vp("Z112_Status", anchor);
	}
	
	/**
	 * Locate and return the verification point Z116_processed object in the SUT.
	 */
	protected IFtVerificationPoint Z116_processedVP() 
	{
		return vp("Z116_processed");
	}
	protected IFtVerificationPoint Z116_processedVP(TestObject anchor)
	{
		return vp("Z116_processed", anchor);
	}
	
	/**
	 * Locate and return the verification point Z150_processed object in the SUT.
	 */
	protected IFtVerificationPoint Z150_processedVP() 
	{
		return vp("Z150_processed");
	}
	protected IFtVerificationPoint Z150_processedVP(TestObject anchor)
	{
		return vp("Z150_processed", anchor);
	}
	
	

	protected TC01_CancelHelper()
	{
		setScriptName("scripts.outboudsapactions.withoutingestion.mswallet.TC01_Cancel");
	}
	
}

