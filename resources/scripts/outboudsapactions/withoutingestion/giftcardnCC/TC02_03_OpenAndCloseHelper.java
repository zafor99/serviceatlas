// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.scripts.outboudsapactions.withoutingestion.giftcardnCC;
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
 * Script Name   : <b>TC02_03_OpenAndClose</b><br>
 * Generated     : <b>2014/07/21 10:54:35 AM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 build 7601 Service Pack 1 <br>
 * 
 * @since  July 21, 2014
 * @author zsadeque
 */
public abstract class TC02_03_OpenAndCloseHelper extends framework.AtlasScriptbase
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
	 * Locate and return the verification point Z100_processed object in the SUT.
	 */
	protected IFtVerificationPoint Z100_processedVP() 
	{
		return vp("Z100_processed");
	}
	protected IFtVerificationPoint Z100_processedVP(TestObject anchor)
	{
		return vp("Z100_processed", anchor);
	}
	
	/**
	 * Locate and return the verification point Z108_processed object in the SUT.
	 */
	protected IFtVerificationPoint Z108_processedVP() 
	{
		return vp("Z108_processed");
	}
	protected IFtVerificationPoint Z108_processedVP(TestObject anchor)
	{
		return vp("Z108_processed", anchor);
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
	
	

	protected TC02_03_OpenAndCloseHelper()
	{
		setScriptName("scripts.outboudsapactions.withoutingestion.giftcardnCC.TC02_03_OpenAndClose");
	}
	
}

