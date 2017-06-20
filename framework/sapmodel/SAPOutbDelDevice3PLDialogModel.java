package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTableControlTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiToggleTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;

import framework.sapmodel.SAPDisplayDeviceOrderOverViewDialogModel.DisplayDeviceOrderToolBar;

public class SAPOutbDelDevice3PLDialogModel extends SAPModelBase{
	private SAPApplicationModel appModel =null;
	public SAPOutbDelDevice3PLDialogModel(){
		appModel = new SAPApplicationModel();
	}

	
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text",xRegex(".*Outb Del Device 3PL.*Overview.*")));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}

		return dialog;
		
	}
	public SAPGuiToggleTestObject displayChangeButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[25]")), true)[0];
		
	}
	public SAPGuiToggleTestObject postGoodsIssueButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[20]")), true)[0];
		
	}
	public SAPGuiTableControlTestObject allItemsTree(){
		SAPGuiTableControlTestObject table = null;
		table = (SAPGuiTableControlTestObject)appModel.getSAPSession().find(atDescendant("Id","/ssubSUBSCREEN_BODY:SAPMV50A:1102/tblSAPMV50ATC_LIPS_OVER"))[0];
		return table;
	}
}
