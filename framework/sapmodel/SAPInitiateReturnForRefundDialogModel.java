package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiComboBoxTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTableControlTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  November 01, 2013
 */
public  class SAPInitiateReturnForRefundDialogModel extends SAPModelBase
{
private SAPApplicationModel appModel = null;
	
	public SAPInitiateReturnForRefundDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[1]","Text",xRegex("Initiate Return for Refund")));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		return dialog;
	}
	public SAPGuiComboBoxTestObject customerReturnReasonCodeComboBox(){
		return (SAPGuiComboBoxTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/cmbG_REASON_RETN")), true)[0];
	}
	public SAPGuiTableControlTestObject paymentCardInfoTable(){
		return (SAPGuiTableControlTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","//tblSAPLZDEVICETCTRL_RETURN")), true)[0];
	}
	public SAPGuiToggleTestObject updateButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[0]"),atChild("Id","/btn[5]")), true)[0];
	}
	public SAPGuiToggleTestObject closeButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[0]"),atChild("Id","/btn[6]")), true)[0];
	}

}
