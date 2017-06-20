package framework.sapmodel;

import com.rational.test.ft.object.interfaces.SelectGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiTextTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 16, 2013
 */
public class SAPLogon720DialogModel extends SAPModelBase
{
	public SAPLogon720DialogModel(){
		//super(atDescendant(".name","SAP Logon 720",".text","SAP Logon 720"));
	}
	
	public SAPTopLevelTestObject dialog(){
		SAPTopLevelTestObject dialog = null;
		TestObject[] tos = null;
		try {
			tos = SAPSession.getSession().find(atDescendant(".name","SAP Logon 730",".text","SAP Logon 730"));
			if(tos.length>0){
				dialog = new SAPTopLevelTestObject(tos[0]);
			}
		} catch (NullPointerException e) {
			
		}		
		return dialog;
		
	}
	
	public SelectGuiSubitemTestObject sytemListView(){
		return (SelectGuiSubitemTestObject)dialog().find(atDescendant(".class","SysListView32"))[0];
	}
}
