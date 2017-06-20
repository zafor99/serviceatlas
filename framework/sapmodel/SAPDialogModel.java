package framework.sapmodel;

import static com.rational.test.ft.script.RationalTestScript.getRootTestObject;

import com.rational.test.ft.object.interfaces.RootTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiApplicationTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiConnectionTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiMenubarTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiSessionTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPGuiStatusbarTestObject;
import com.rational.test.ft.object.interfaces.SAP.SAPTopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.Subitem;

import framework.AtlasScriptbase;
import framework.sapcontroller.SAPTopLevelToolbarController;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 15, 2013
 */
public class SAPDialogModel extends SAPModelBase
{
	private  SAPTopLevelTestObject dialog = null;
	private  String dialogTitle = null;
	

	
	public SAPDialogModel(SAPTopLevelTestObject dialog){
		this.dialog = dialog;
	}
	
	public AtlasScriptbase getExecutingScript(){
		
		return AtlasScriptbase.getExecutingScript();
	}
	
	public  void delayFor(double p_Seconds)
	{
		RationalTestScript.sleep(p_Seconds);
	}
	
	
	public SAPTopLevelToolbarModel topLevelToolBar(){
		return new SAPTopLevelToolbarModel();
	}
	
	public SAPGuiMenubarTestObject topLevelMenuBar(){
		return (SAPGuiMenubarTestObject)SAPSession.getActiveWindow().find(atList(atChild("Id","/mbar")))[0];
	}
	
	public SAPGuiStatusbarTestObject bottomStatusBar(){
		return (SAPGuiStatusbarTestObject)SAPSession.getActiveWindow().find(atList(atChild("Id","/sbar")))[0];
	}
	
	
		
}
