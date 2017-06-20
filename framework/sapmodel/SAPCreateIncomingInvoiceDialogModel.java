package framework.sapmodel;

import com.bn.qa.xobject.XGuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToolbarTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPABAPEditorInitialScreenDialogModel.ABAPEditorDialogToolbar;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  October 22, 2013
 */
public  class SAPCreateIncomingInvoiceDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = new SAPApplicationModel();
	public SAPCreateIncomingInvoiceDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","Create Incoming Invoice for Downloadable Products"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		
		return dialog;
	}
	public CreateIncomingInvoiceToolbar createIncomingInvoiceToolbar(){
		return new CreateIncomingInvoiceToolbar();
	}
	public class CreateIncomingInvoiceToolbar{
		private SAPGuiToolbarTestObject toolbar = null;
		public CreateIncomingInvoiceToolbar(){
			toolbar = (SAPGuiToolbarTestObject)dialog().find(atList(atChild("Id","/tbar[1]")))[0];
		}
		public SAPGuiToggleTestObject executeButton(){
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[8]")), true)[0];
		}
	}
	public SAPGuiTextTestObject purchaseOrderTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtS_EBELN-LOW")), true)[0];
	}
	public SAPGuiTextTestObject poDocumentTypeTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtP_BSART")), true)[0];
	}

	public SAPGuiTextTestObject invoiceDocumentLabel(){
		TestObject tos[] = null;
		
		SAPGuiTextTestObject tos2 = null;
		tos = dialog().find(atList(atChild("Id", "/usr"), atChild("Id", "/lbl[28,5]")),true);
		if(tos.length>0){
			tos2 = (SAPGuiTextTestObject)tos[0];
		}
		return tos2;
	}
	public SAPGuiTextTestObject noPOsLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[28,4]")), true)[0];
	}
	
}
