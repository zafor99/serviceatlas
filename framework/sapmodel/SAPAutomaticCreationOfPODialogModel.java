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
 * @since  April 04, 2014
 */
public  class SAPAutomaticCreationOfPODialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = new SAPApplicationModel();
	public SAPAutomaticCreationOfPODialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","Automatic Creation of Purchase Orders from Requisitions"));
			//tos = appModel.getSAPSession().find(atDescendant("Id","/wnd[0]","Text","Automatic Creation of Purchase Orders from Requisitions"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		
		return dialog;
	}
	public AutomaticCreationOfPODialogToolbar automaticCreationOfPODialogToolbar(){
		return new AutomaticCreationOfPODialogToolbar();
	}
	public class AutomaticCreationOfPODialogToolbar{
		private SAPGuiToolbarTestObject toolbar = null;
		public AutomaticCreationOfPODialogToolbar(){
			toolbar = (SAPGuiToolbarTestObject)dialog().find(atList(atChild("Id","/tbar[1]")))[0];
		}
		public SAPGuiToggleTestObject executeButton(){
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[8]")), true)[0];
		}
		public SAPGuiToggleTestObject getVariantButton(){
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[17]")), true)[0];
		}
	}
	public SAPGuiTextTestObject purchaseRequisitionTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtS_BANFN-LOW")), true)[0];
	}
	public SAPGuiTextTestObject purchaseOrderLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[41,4]")), true)[0];
	}
	public SAPGuiTextTestObject purchaseOrderNoLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[56,4]")), true)[0];
	}
	public SAPGuiTextTestObject alreadyprocessingSalesDocLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[41,5]")), true)[0];
	}
	public SAPGuiTextTestObject purchaseOrderCreateLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[67,4]")), true)[0];
	}

}
