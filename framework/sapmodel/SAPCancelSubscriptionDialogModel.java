package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiComboBoxTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  July 10, 2014
 */
public  class SAPCancelSubscriptionDialogModel extends SAPModelBase
{
private SAPApplicationModel appModel = new SAPApplicationModel();
	
	public SAPCancelSubscriptionDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[1]","Text","Cancel subscription"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		
		return dialog;
	}
	public SAPGuiComboBoxTestObject cancelReasonComboBox(){
		 return (SAPGuiComboBoxTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/cmbG_CANCEL_REASON")), true)[0];
	}
	public SAPGuiToggleTestObject updateButton(){
		 return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[0]"),atChild("Id","/btn[5]")), true)[0];
	}
	public SAPGuiToggleTestObject cancelButton(){
		 return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[0]"),atChild("Id","/btn[6]")), true)[0];
	}
}
