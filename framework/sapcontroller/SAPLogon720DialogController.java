package framework.sapcontroller;

import com.rational.test.ft.script.RationalTestScript;

import framework.AtlasScriptbase;
import framework.sapmodel.SAPLogon720DialogModel;
import framework.sapmodel.SAPLogon720PageModel;

/**
 * Description   : Super class for script helper
 * 
 * @author fahmed1
 * @since  May 16, 2013
 */
public class SAPLogon720DialogController extends SAPDialogController
{
	private static SAPLogon720DialogModel appModel = new SAPLogon720DialogModel();
	
	public SAPLogon720DialogController(){
		super(appModel.dialog());
	}
	
	public void selectSystemToLogin(String item){
		appModel.dialog().activate();
		appModel.sytemListView().doubleClick(atCell(atRow(atText(item)), 
                atColumn(atText("Name"))));
		AtlasScriptbase.	getExecutingScript().delayFor(5);
		AtlasScriptbase.getExecutingScript().sap().userLoginDialog().waitForExistence();
		
	}
}
