package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiCtrlTreeTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiSessionTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

import framework.sapmodel.SAPApplicationModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 02, 2013
 */
public class SAPEasyAccessDialogModel extends SAPModelBase
{

	private SAPApplicationModel appModel = new SAPApplicationModel();
	public SAPEasyAccessDialogModel(){		
	}
	
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			
			//tos = appModel.getSAPSession().find(atDescendant("Id","/wnd[0]"/*,"Text","SAP Easy Access  SAP Retail"*/));
			/*System.out.println(appModel.mainWnd.getProperty("Id"));
			System.out.println(appModel.mainWnd.getChildren().length);
			tos = appModel.mainWnd.find(atDescendant("Id","/wnd[0]","Text","SAP Easy Access  SAP Retail"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}*/
			
			if(SAPSession.getActiveWindow()!=null){
				dialog = SAPSession.getActiveWindow();
			}
		} catch (NullPointerException e) {
			
		}		
		return dialog;

	}
	
	public SAPGuiCtrlTreeTestObject easyAccessOptionTree(){
		return (SAPGuiCtrlTreeTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/cntlIMAGE_CONTAINER/shellcont/shell/shellcont[0]/shell")))[0];
	}
}
