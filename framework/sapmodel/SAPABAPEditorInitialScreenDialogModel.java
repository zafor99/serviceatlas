package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToolbarTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  October 18, 2013
 */
public  class SAPABAPEditorInitialScreenDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = new SAPApplicationModel();
	public SAPABAPEditorInitialScreenDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos =SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","ABAP Editor: Initial Screen")); 
				//appModel.getSAPSession().find(atDescendant("Id","/wnd[0]","Text","ABAP Editor: Initial Screen"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		
		return dialog;
	}
	public ABAPEditorDialogToolbar aBAPEditorDialogToolbar(){
		return new ABAPEditorDialogToolbar();
	}
	public class ABAPEditorDialogToolbar{
		private SAPGuiToolbarTestObject toolbar = null;
		public ABAPEditorDialogToolbar(){
			toolbar = (SAPGuiToolbarTestObject)dialog().find(atList(atChild("Id","/tbar[1]")))[0];
		}
		public SAPGuiToggleTestObject executeButton(){
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[8]")), true)[0];
		}
		public SAPGuiToggleTestObject withVariantButton(){
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[18]")), true)[0];
		}

	}
	public SAPGuiTextTestObject programTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtRS38M-PROGRAMM")), true)[0];
	}
	public SAPGuiToggleTestObject createButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/btnNEW")), true)[0];
	}
	
}
