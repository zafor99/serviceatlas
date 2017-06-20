package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiCtrlGridViewTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;

public class SAPiDocProcessingDialogModel extends SAPModelBase{

	private SAPApplicationModel appModel =null;
	public SAPiDocProcessingDialogModel(){
		appModel = new SAPApplicationModel();
	}
	
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text",xRegex("IDoc Reporting Tool|IDoc processing")));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		return dialog;
		
	}
	
	public SAPGuiCtrlGridViewTestObject processIDocGrid(){
		return (SAPGuiCtrlGridViewTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/cntlCUST_100/shellcont/shell")), true)[0];
	}
}
