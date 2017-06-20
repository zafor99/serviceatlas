package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiCtrlGridViewTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  September 30, 2013
 */
public  class SAPDeviceInfoSerialNumberDialogModel extends SAPModelBase
{
private SAPApplicationModel appModel = null;
	
	public SAPDeviceInfoSerialNumberDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text",xRegex("Device info: Serial number *")));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		return dialog;
	}
	public SAPGuiCtrlGridViewTestObject deviceHistoryGrid(){
		return (SAPGuiCtrlGridViewTestObject)RationalTestScript.find(atList(atDescendant("Id","/ssubSUBSCREEN_BODY:SAPLZDEVICE:4300/cntlLIST_AREA/shellcont/shell")), true)[0];		
	}
	public SAPGuiToggleTestObject rtrnForRefundButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/tabsTABSTRIPAPP"),atChild("Id","/tabpHISTTAB"),atChild("Id",xRegex("/ssubSUBSCREEN_BODY:SAPLZDEVICE:.*subSUB_BUTTON:SAPLZDEVICE:.*btnBTN_REFUND"))), true)[0];
	}

}
