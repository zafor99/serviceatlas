package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  August 12, 2013
 */
public  class SAPEDIOrderManagementMainMenuDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel =null;

	public SAPEDIOrderManagementMainMenuDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","EDI Order Management Main Menu"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		return dialog;
		
	}
	public SAPGuiToggleTestObject processButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[5]")), true)[0];
		
	}
	public SAPGuiToggleTestObject orderWorkListButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/radS_OR_RADBUTTON")), true)[0];
	}
}
