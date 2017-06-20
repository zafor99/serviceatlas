package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiCtrlGridViewTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  October 01, 2013
 */
public  class SAPBNEmailSystemDialogModel extends SAPModelBase
{
private SAPApplicationModel appModel = null;
	
	public SAPBNEmailSystemDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","BN.COM Email System"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		return dialog;
	}
	public SAPGuiCtrlGridViewTestObject emailHistoryGrid(){
		return (SAPGuiCtrlGridViewTestObject)RationalTestScript.find(atList(atDescendant("Id","/cntlCLFCONTAINER/shellcont/shell")), true)[0];		
	}
	public SAPGuiToggleTestObject retrieveEmailHistoryButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[5]")), true)[0];
	}
}
