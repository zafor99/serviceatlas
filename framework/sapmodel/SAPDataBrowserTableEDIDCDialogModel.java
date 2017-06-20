package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  April 08, 2014
 */
public  class SAPDataBrowserTableEDIDCDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = new SAPApplicationModel();
	public SAPDataBrowserTableEDIDCDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","Data Browser: Table EDIDC Select Entries          1"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		
		return dialog;
	}
	public SAPGuiTextTestObject statusTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[33,5]")), true)[0];
	}
	public SAPGuiTextTestObject iDocTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[9,5]")), true)[0];
	}
}
