package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToolbarTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPDataBrowserInitialScreenDialogModel.DataBrowserInitialScreenDialogToolbar;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  April 03, 2014
 */
public  class SAPDataBrowserTableSelectionScreenDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = new SAPApplicationModel();
	public SAPDataBrowserTableSelectionScreenDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","Data Browser: Table EDIDC: Selection Screen"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		
		return dialog;
	}
	public DataBrowserTableSelectionScreenDialogToolbar dataBrowserTableSelectionScreenDialogToolbar(){
		return new DataBrowserTableSelectionScreenDialogToolbar();
	}
	public class DataBrowserTableSelectionScreenDialogToolbar{
		private SAPGuiToolbarTestObject toolbar = null;
		public DataBrowserTableSelectionScreenDialogToolbar(){
			toolbar = (SAPGuiToolbarTestObject)dialog().find(atList(atChild("Id","/tbar[1]")))[0];
		}
		public SAPGuiToggleTestObject executeButton(){
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[8]")), true)[0];
		}
		public SAPGuiToggleTestObject selectionOptionsButton(){
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[2]")), true)[0];
		}
	}
	public SAPGuiTextTestObject sndladTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/txtI7-LOW")), true)[0];
	}
}
