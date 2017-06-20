package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiCtrlGridViewTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiCtrlTreeTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  May 30, 2013
 */
public class SAPStatusMonitorDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = null;
	public SAPStatusMonitorDialogModel(){
		appModel = new SAPApplicationModel();
	}
	
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","Status Monitor for ALE Messages"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}

		return dialog;
		
	}
	public SAPTopLevelTestObject dialog(String idXpath,String textXpath){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = appModel.getSAPSession().find(atDescendant("Id",idXpath,"Text",textXpath));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}

		return dialog;
		
	}
	
	public SAPGuiCtrlTreeTestObject iDocTree(){
		return (SAPGuiCtrlTreeTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/cntlTREE_CONTAINER/shellcont/shell")), true)[0];
	}
	public SAPGuiToggleTestObject processButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[8]")), true)[0];
		
	}
	public SAPGuiToggleTestObject refreshButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[39]")), true)[0];
		
	}

	public SAPGuiToggleTestObject displayIDOCsButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[18]")), true)[0];
		
	}
	public SAPGuiToggleTestObject okButton(){
		return (SAPGuiToggleTestObject)dialog("/wnd[1]","Information").find(atList(atChild("Id","/tbar[0]"),atChild("Id","/btn[0]")), true)[0];
		
	}


}
