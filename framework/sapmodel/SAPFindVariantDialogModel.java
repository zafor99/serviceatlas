package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  April 04, 2014
 */
public  class SAPFindVariantDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = new SAPApplicationModel();
	public SAPFindVariantDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[1]","Text","Find Variant"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		
		return dialog;
	}
	public SAPGuiTextTestObject variantTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/txtV-LOW")), true)[0];
	}
	public SAPGuiTextTestObject createByTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/txtENAME-LOW")), true)[0];
	}
	public SAPGuiToggleTestObject executeButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[0]"),atChild("Id","/btn[8]")))[0];
	}
}
