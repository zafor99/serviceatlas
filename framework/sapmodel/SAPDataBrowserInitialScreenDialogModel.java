package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToolbarTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPABAPEditorInitialScreenDialogModel.ABAPEditorDialogToolbar;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  April 03, 2014
 */
public  class SAPDataBrowserInitialScreenDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = new SAPApplicationModel();
	public SAPDataBrowserInitialScreenDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","Data Browser: Initial Screen"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		
		return dialog;
	}
	public DataBrowserInitialScreenDialogToolbar dataBrowserInitialScreenDialogToolbar(){
		return new DataBrowserInitialScreenDialogToolbar();
	}
	public class DataBrowserInitialScreenDialogToolbar{
		private SAPGuiToolbarTestObject toolbar = null;
		public DataBrowserInitialScreenDialogToolbar(){
			toolbar = (SAPGuiToolbarTestObject)dialog().find(atList(atChild("Id","/tbar[1]")))[0];
		}
		public SAPGuiToggleTestObject tableContentsButton(){
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[7]")), true)[0];
		}
		public SAPGuiToggleTestObject createEntriesButton(){
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[5]")), true)[0];
		}
	}
	public SAPGuiTextTestObject tableNameTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtDATABROWSE-TABLENAME")), true)[0];
	}
}
