package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  May 14, 2014
 */
public  class SAPDisplayContractInitialScreenDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel =null;
	private String dialogTitle = "";

	public SAPDisplayContractInitialScreenDialogModel() {
		appModel = new SAPApplicationModel();
	}
	
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text",xRegex("Display Contract: Initial Screen|Change Contract: Initial Screen")));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (Exception e) {
			
		}
		return dialog;
	}
	public SAPGuiTextTestObject purchaseOrderNoTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/txtRV45S-BSTNK")))[0];
	}
	public SAPGuiTextTestObject contractTextBox(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/ctxtVBAK-VBELN")))[0];
	}
	public SAPGuiToggleTestObject searchButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/btnBT_SUCH")))[0];
	}
}
