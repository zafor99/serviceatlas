package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiCtrlGridViewTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  August 26, 2013
 */
public  class SAPAutomaticCreationOfPurchaseDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = null;
	
	public SAPAutomaticCreationOfPurchaseDialogModel(){
		appModel = new SAPApplicationModel();		
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			//tos = appModel.getSAPSession().find(atDescendant("Id","/wnd[0]","Text",xRegex("Automatic Creation of Purchase Orders from Requisitions")));
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text",xRegex("Automatic Creation of Purchase Orders from Requisitions")));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		return dialog;
		
	}
	public SAPGuiCtrlGridViewTestObject poCreationGrid(){
		return (SAPGuiCtrlGridViewTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/cntlGRID1/shellcont/shell")), true)[0];
	}
}
