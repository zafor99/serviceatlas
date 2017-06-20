package framework.sapmodel;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTableControlTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author zsadeque
 * @since  June 19, 2013
 */
public  class SAPCreateBillingDocumentModel extends SAPModelBase
{	
	private SAPApplicationModel appModel =null;
	
	public SAPCreateBillingDocumentModel(){
		appModel = new SAPApplicationModel();
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text",xRegex("Create Billing Document|.*Overview of Billing Items")));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}

		return dialog;
		
	}
	
	public SAPGuiTableControl docsToBeProcessedTable(){
		return new  SAPGuiTableControl(atDescendant("Id","/tblSAPMV60ATCTRL_ERF_FAKT"));
	}
}
