package framework.sapmodel;

import com.rational.test.ft.object.interfaces.SAP.SAPGuiMenubarTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 17, 2013
 */
public class SAPTopLevelMenuModel extends SAPModelBase
{
	private SAPApplicationModel appModel = new SAPApplicationModel();
	private SAPTopLevelTestObject dialog = null;
	private SAPGuiMenubarTestObject menuBar = null;
	
	public SAPTopLevelMenuModel(){
		menuBar = (SAPGuiMenubarTestObject)SAPSession.getActiveWindow().find(atList(atChild("Id","/mbar")))[0];
		
	}
	
	
}
