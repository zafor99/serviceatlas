package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiCtrlGridViewTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToolbarTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPCreateIncomingInvoiceDialogModel.CreateIncomingInvoiceToolbar;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  May 15, 2014
 */
public  class SAPCreateAndProcessSubscriptioniDocsDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = new SAPApplicationModel();
	public SAPCreateAndProcessSubscriptioniDocsDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","Create and process subscription idocs"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		
		return dialog;
	}
	public CreateAndProcessSubcriptioniDocsToolbar createAndProcessSubcriptioniDocsToolbar(){
		return new CreateAndProcessSubcriptioniDocsToolbar();
	}
	public class CreateAndProcessSubcriptioniDocsToolbar{
		private SAPGuiToolbarTestObject toolbar = null;
		public CreateAndProcessSubcriptioniDocsToolbar(){
			toolbar = (SAPGuiToolbarTestObject)dialog().find(atList(atChild("Id","/tbar[1]")))[0];
		}
		public SAPGuiToggleTestObject executeButton(){
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[8]")), true)[0];
		}
		public SAPGuiToggleTestObject refreshStatusButton(){
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[24]")), true)[0];
		}
	}
	public SAPGuiTextTestObject billingDateTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtS_ERDAT-LOW")), true)[0];
	}
	public SAPGuiTextTestObject contractTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtS_VBELN-LOW")), true)[0];
	}
	public SAPGuiToggleTestObject testModeCheckBox(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/chkP_TEST")), true)[0];
	}
	public SAPGuiToggleTestObject printSelectionCheckBox(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/chkP_SELSCR")), true)[0];
	}
	public SAPGuiCtrlGridViewTestObject contractsProcessedGrid(){
		return (SAPGuiCtrlGridViewTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/cntlGRID1/shellcont/shell/shellcont[1]/shell")), true)[0];
	}

}
