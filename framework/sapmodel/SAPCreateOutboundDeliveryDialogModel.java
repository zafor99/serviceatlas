package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;

public class SAPCreateOutboundDeliveryDialogModel extends SAPModelBase{

	private SAPApplicationModel appModel = null;
	
	public SAPCreateOutboundDeliveryDialogModel(){
		appModel = new SAPApplicationModel();
	}
	
	public SAPTopLevelTestObject dialog(){

		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text",xRegex("^Create Outbound Delivery with Order Reference.*")));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {

		}

		
		return dialog;
		
	}
}
