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
public  class SAPReleaseCustomerExpectedPriceModel extends SAPModelBase
{
	private SAPApplicationModel appModel = null;
	
	public SAPReleaseCustomerExpectedPriceModel(){
		appModel = new SAPApplicationModel();		
	}
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant("Id","/wnd[0]","Text","Release Customer Expected Price"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}
		return dialog;
		
	}
	public SAPUserTableModel documentTable(){
		return new SAPUserTableModel(dialog(),"5,7","2,13,20,39,52,58,63,68,81,87,92","2,13,20,39,52,58,64,68,81,87,93");
	}
	public SAPUserTableModel releaseDocumentTable(){
		return new SAPUserTableModel(dialog(),"1,3","1,12,19","1,12,19");
	}
	public SAPGuiToggleTestObject documentCheckbox(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/usr"),atChild("Id","/chk[0,7]")), true)[0];
	}
	public SAPGuiToggleTestObject releaseButton(){
		return (SAPGuiToggleTestObject)dialog().find(atList(atChild("Id","/tbar[1]"),atChild("Id","/btn[8]")), true)[0];
	}
}
