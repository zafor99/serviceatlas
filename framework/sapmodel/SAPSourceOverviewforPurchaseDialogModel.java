package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

import framework.sapcontroller.SAPApplicationController;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  August 20, 2013
 */
public  class SAPSourceOverviewforPurchaseDialogModel extends SAPModelBase
{
	private SAPApplicationModel appModel = null;
	public SAPSourceOverviewforPurchaseDialogModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text",xRegex("Source Overview for Purchase Requisition")));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		return dialog;
	}
	public SAPUserTableModel documentTable(){
		return new SAPUserTableModel(dialog(),"6,8,9,10,11,12,13,14","1,47,56,76,86,110","1,47,58,78,86,106");
	}

}
