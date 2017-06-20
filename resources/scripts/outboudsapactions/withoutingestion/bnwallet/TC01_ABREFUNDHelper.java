// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.scripts.outboudsapactions.withoutingestion.bnwallet;
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
 * Script Name   : <b>TC01_ABREFUND</b><br>
 * Generated     : <b>2014/08/01 11:35:25 AM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 build 7601 Service Pack 1 <br>
 * 
 * @since  August 01, 2014
 * @author zsadeque
 */
public abstract class TC01_ABREFUNDHelper extends framework.AtlasScriptbase
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
	 * Locate and return the verification point CustServreqCreated object in the SUT.
	 */
	protected IFtVerificationPoint CustServreqCreatedVP() 
	{
		return vp("CustServreqCreated");
	}
	protected IFtVerificationPoint CustServreqCreatedVP(TestObject anchor)
	{
		return vp("CustServreqCreated", anchor);
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
	 * Locate and return the verification point DWDB_OrderDetailActivity_Closed object in the SUT.
	 */
	protected IFtVerificationPoint DWDB_OrderDetailActivity_ClosedVP() 
	{
		return vp("DWDB_OrderDetailActivity_Closed");
	}
	protected IFtVerificationPoint DWDB_OrderDetailActivity_ClosedVP(TestObject anchor)
	{
		return vp("DWDB_OrderDetailActivity_Closed", anchor);
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
	 * Locate and return the verification point orderTable object in the SUT.
	 */
	protected IFtVerificationPoint orderTableVP() 
	{
		return vp("orderTable");
	}
	protected IFtVerificationPoint orderTableVP(TestObject anchor)
	{
		return vp("orderTable", anchor);
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
	 * Locate and return the verification point refundCreated object in the SUT.
	 */
	protected IFtVerificationPoint refundCreatedVP() 
	{
		return vp("refundCreated");
	}
	protected IFtVerificationPoint refundCreatedVP(TestObject anchor)
	{
		return vp("refundCreated", anchor);
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
	 * Locate and return the verification point Summary object in the SUT.
	 */
	protected IFtVerificationPoint SummaryVP() 
	{
		return vp("Summary");
	}
	protected IFtVerificationPoint SummaryVP(TestObject anchor)
	{
		return vp("Summary", anchor);
	}
	
	

	protected TC01_ABREFUNDHelper()
	{
		setScriptName("scripts.outboudsapactions.withoutingestion.bnwallet.TC01_ABREFUND");
	}
	
}

