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
 * @since  August 20, 2013
 */
public  class SAPAssignSourceofSupplyDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = null;
	public SAPAssignSourceofSupplyDialogModel(){
		appModel = new SAPApplicationModel();		
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","Assign Source of Supply to Requisitions"));
			//tos = appModel.getSAPSession().find(atDescendant("Id","/wnd[0]","Text","Assign Source of Supply to Requisitions"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		return dialog;
		
	}
	public SAPGuiToggleTestObject eanCheckBox(){
		 return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/chk[1,5]")), true)[0];
	}
	public SAPGuiToggleTestObject assignAutomaticallyButton(){
		 return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[18]")), true)[0];
	}
	public SAPGuiTableControl sourceOverviewTable(){
		return new SAPGuiTableControl(atDescendant("Id","/tblSAPLMEQRTC_0500"));
	}
	public SAPGuiTextTestObject inforecLabel(){
		return (SAPGuiTextTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/lbl[3,8]")), true)[0];
	}
}
