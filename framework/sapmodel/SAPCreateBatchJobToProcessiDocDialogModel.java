package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiCtrlGridViewTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToolbarTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;



/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  June 25, 2014
 */
public  class SAPCreateBatchJobToProcessiDocDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = new SAPApplicationModel();
	public SAPCreateBatchJobToProcessiDocDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","Create batch job to process IDocs"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		
		return dialog;
	}
	public createBatchJobDialogToolbar createBatchJobDialogToolbar(){
		return new createBatchJobDialogToolbar();
	}
	public class createBatchJobDialogToolbar{
		private SAPGuiToolbarTestObject toolbar = null;
		public createBatchJobDialogToolbar(){
			toolbar = (SAPGuiToolbarTestObject)dialog().find(atList(atChild("Id","/tbar[1]")))[0];
		}
		public SAPGuiToggleTestObject executeButton(){
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[8]")), true)[0];
		}
		public SAPGuiToggleTestObject refreshStatusButton(){
			return (SAPGuiToggleTestObject)toolbar.find(atList(atChild("Id","/btn[24]")), true)[0];
		}
	}
	public SAPGuiTextTestObject iDocNumberTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/txtS_DOCNUM-LOW")), true)[0];
	}
	public SAPGuiToggleTestObject processiDocsWith51StatusCheckBox(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/chkP_51")), true)[0];
	}
	public SAPGuiToggleTestObject processiDocsWith64StatusCheckBox(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/chkP_64")), true)[0];
	}
	public SAPGuiToggleTestObject processiDocsWith69StatusCheckBox(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/chkP_69")), true)[0];
	}

	
	public SAPGuiToggleTestObject testModeCheckBox(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/chkP_TEST")), true)[0];
	}
	public SAPGuiCtrlGridViewTestObject batchJobGridView(){
		return (SAPGuiCtrlGridViewTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/cntlGRID1/shellcont/shell/shellcont[1]/shell")), true)[0];
	}
}
